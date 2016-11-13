

package tempreture.android.csulb.edu.fitbitapp;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;


/* start class ------------------------------------------------------------> */
public class ViewChallenges extends AppCompatActivity {
    public static Challenge challengeChosen = new Challenge();
    ArrayList<Dummy> allUsers = new ArrayList<Dummy>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_list_view);
        ChallengeStore cs = ChallengeStore.getInstance();




        /* start toolbar*/
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.selectTabWithId(R.id.tab_challenges);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if(tabId == R.id.tab_dashboard) {
                    Intent intent = new Intent(ViewChallenges.this, DashboardMainActivity.class);
                    startActivity(intent);
                } else if (tabId == R.id.tab_trophies) {
                    Intent intent = new Intent(ViewChallenges.this, TrophiesActivity.class);
                    startActivity(intent);
                }
            }
        });

        /* end toolbar*/
        ArrayList challengeData = cs.getChallenges();
        // cs.addChallenge(challenge1);

        ChallengeListAdapter currentAdapter = new ChallengeListAdapter(challengeData,"current"); //place your String array in place of MyString
        ChallengeListAdapter pastAdapter = new ChallengeListAdapter(challengeData,"past"); //place your String array in place of MyString

        ListView currentChallengeList = (ListView) findViewById(R.id.current); //the ID you set your ListView to.
        ListView pastChallengeList = (ListView) findViewById(R.id.past); //the ID you set your ListView to.

        currentChallengeList.setAdapter(currentAdapter);
        pastChallengeList.setAdapter(pastAdapter);

        final ArrayList<Challenge> tempCurrentChallengeData = (ArrayList<Challenge>) cs.getChallenges().clone();
        final ArrayList<Challenge> tempPastChallengeData = (ArrayList<Challenge>) cs.getChallenges().clone();

        for (Challenge ch : new ArrayList<Challenge>(tempCurrentChallengeData)) {
            if (ch.isOver()) {//remove anything past
                tempCurrentChallengeData.remove(ch);
            }
        }
        for (Challenge ch : new ArrayList<Challenge>(tempPastChallengeData)) {
            if (!ch.isOver()) {//remove anything past
                tempPastChallengeData.remove(ch);
            }
        }

        currentChallengeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){//if past challenge is chosen
            public void onItemClick(AdapterView arg0, View view, int position, long id){
                challengeChosen = tempCurrentChallengeData.get(position);
                //Toast.makeText(ViewChallenges.this,"CHALLENGE CHOSEN: "+challengeChosen.getParticipants().get(0).getName(),Toast.LENGTH_SHORT).show();//delete me
                if(challengeChosen.isPending()){
                    Toast.makeText(ViewChallenges.this,"Challenge Pending. You will be notified once your friends accept the challenge request ",Toast.LENGTH_SHORT).show();//delete me
                }else{
                    Intent intent = new Intent(ViewChallenges.this, UserProgress.class);
                    startActivity(intent);
                }
            }
        });

        pastChallengeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){//if past current challenge is chosen
            public void onItemClick(AdapterView arg0, View view,int position,long id){
                setContentView(R.layout.past_challenges);
            }
        });


    }

    public void backToList(View view){
        Intent in = new Intent(ViewChallenges.this,ViewChallenges.class);
        startActivity(in);
    }
    /* start menu ----------------------------------------------------------------- */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.start_challenge:
                Intent in = new Intent(ViewChallenges.this, CreateChallengeActivity.class);
                startActivity(in);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
/* end menu -----------------------------------------------------------*/

}//end class



class ChallengeListAdapter extends BaseAdapter {
    private ArrayList mData;
    private String status;
    private boolean empty = false;
    public ChallengeListAdapter(ArrayList<Challenge> challenges,String status) {
        mData = new ArrayList();
        mData.clear();
        mData.addAll(challenges);
        this.status = status;
    }

    @Override
    public int getCount() {
        if(status.equalsIgnoreCase("past")) {
            for (Challenge c : new ArrayList<Challenge>(mData)) {
                if (!c.isOver()) {//remove anything current
                    mData.remove(c);
                }
            }
            if(mData.size() == 0){
                empty = true;
                return 1;
            }
            return mData.size();
        }else if(status.equalsIgnoreCase("current")) {
            for (Challenge c : new ArrayList<Challenge>(mData)) {
                if (c.isOver()) {//remove anything past
                    mData.remove(c);
                }
            }
            if(mData.size() == 0){
                empty = true;
                return 1;
            }
            return mData.size();
        }
        return mData.size();//will make a bunch of bogus list items to show we did something wrong
    }

    @Override
    public Challenge getItem(int position) {
        return (Challenge) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;

        //view = LayoutInflater.from(parent.getContext()).inflate(R.layout.challenge_list_data, parent, false);
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.challenge_list_data, null);

        TextView challengeName = (TextView) view.findViewById(R.id.challengeText);
        TextView statusName = (TextView) view.findViewById(R.id.challenge_status);
        TextView betAmount = (TextView) view.findViewById(R.id.bet_amount);

        // TODO replace findViewById by ViewHoldert
        if(!empty) {
            Challenge challengeData = (Challenge) mData.get(position);
            challengeName.setText(challengeData.getName());
            betAmount.setText("$ " + String.valueOf(String.format("%.2f",challengeData.getBetAmount())));

            if(challengeData.isPending()){//if challenge pending, display "pending"
                statusName.setText("challenge pending");
                statusName.setTypeface(null,Typeface.ITALIC);
            }else {

                /////////////////////////////////////////////////////////////////////////
                DataContainer dc = DataContainer.getInstance();
                ArrayList<DataEntry> steps = dc.getSteps();
                ArrayList<DataEntry> distance = dc.getDistance();
                ArrayList<DataEntry> calories = dc.getCalories();
                ArrayList<DataEntry> elevation = dc.getElevation();
                ArrayList<DataEntry> active = dc.getTimeActive();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                int totalSteps  = 0;
                int totalCalories = 0;
                int totalActive = 0;
                int totalElevation = 0;
                int totalDistance = 0;
                for(DataEntry ent : steps) {
                    try {
                        Date d = df.parse(ent.getDate());
                        if(d.after(((Challenge) mData.get(position)).getDate())) {
                            totalSteps = totalSteps + Integer.parseInt(ent.getValue());
                        }
                    } catch(Exception e) {}
                }
                for(DataEntry ent : calories) {
                    try {
                        Date d = df.parse(ent.getDate());
                        if(d.after(((Challenge) mData.get(position)).getDate())) {
                            totalCalories = totalCalories + Integer.parseInt(ent.getValue());
                        }
                    } catch(Exception e) {}
                }
                for(DataEntry ent : active) {
                    try {
                        Date d = df.parse(ent.getDate());
                        if(d.after(((Challenge) mData.get(position)).getDate())) {
                            totalActive = totalActive + Integer.parseInt(ent.getValue());
                        }
                    } catch(Exception e) {}
                }
                for(DataEntry ent : elevation) {
                    try {
                        Date d = df.parse(ent.getDate());
                        if(d.after(((Challenge) mData.get(position)).getDate())) {
                           totalElevation =  totalElevation + Integer.parseInt(ent.getValue());
                        }
                    } catch(Exception e) {}
                }
                for(DataEntry ent : distance) {
                    try {
                        Date d = df.parse(ent.getDate());
                        if(d.after(((Challenge) mData.get(position)).getDate())) {
                           totalDistance = totalDistance + Integer.parseInt(ent.getValue());
                        }
                    } catch(Exception e) {}
                }
                /////////////////////////////////////////////////////////////////////////


                TreeMap<Double,String> WinnerData = new TreeMap<Double,String>();
                int myProgress = 0;
                if(challengeData.getChallengeType().equalsIgnoreCase("steps")){
                    myProgress = totalSteps;
                }else if(challengeData.getChallengeType().equalsIgnoreCase("calories burned")){
                    myProgress = totalCalories;
                }else if(challengeData.getChallengeType().equalsIgnoreCase("floors climbed")){
                    myProgress = totalElevation;
                }else if(challengeData.getChallengeType().equalsIgnoreCase("distance traveled")){
                    myProgress = totalDistance;
                }else if(challengeData.getChallengeType().equalsIgnoreCase("hours active")){
                    myProgress = totalActive;
                }
                for(Dummy dum : challengeData.getParticipants())
                {
                    dum.setProgress(challengeData.getChallengeType());
                }

                    WinnerData.clear();//reset winner
                for(int i=0;i<challengeData.getParticipants().size();i++){
                    WinnerData.put(challengeData.getParticipants().get(i).getProgress(),challengeData.getParticipants().get(i).getName());
                }
                WinnerData.put((double)myProgress,"Me");
                int lastTreeIndex = WinnerData.size()-1;
                String inLead = WinnerData.get(WinnerData.keySet().toArray()[lastTreeIndex]).toString();

                challengeData.getParticipants();
                statusName.setText(challengeData.isOver() ? "Winner: Noam" : ("In Lead: "+inLead+" "));
            }
        }

        if(empty){
            challengeName.setText("You have no "+(status.equalsIgnoreCase("past") ? "past":"current")+" challenges");
            challengeName.setTextColor(Color.GRAY);
            challengeName.setTextSize(15);
            challengeName.setTypeface(challengeName.getTypeface(), Typeface.ITALIC);
            view.setBackgroundColor(view.getResources().getColor(R.color.bg_light));
            view.setPadding(10,0,0,0);
            empty = false;//reset empty
        }
        return view;
    }
}
