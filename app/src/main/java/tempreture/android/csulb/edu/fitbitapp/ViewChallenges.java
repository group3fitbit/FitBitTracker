

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

        final ArrayList<Challenge> tempChallengeData = (ArrayList<Challenge>) cs.getChallenges().clone();
        currentChallengeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){//if past challenge is chosen
            public void onItemClick(AdapterView arg0, View view, int position, long id){
                challengeChosen = tempChallengeData.get(position);
                //Toast.makeText(ViewChallenges.this,"CHALLENGE CHOSEN: "+challengeChosen.getParticipants().get(0).getName(),Toast.LENGTH_SHORT).show();//delete me
                if(tempChallengeData.get(position).isPending()){
                    Toast.makeText(ViewChallenges.this,"Challenge Pending. You will be notified once your friends accept the challenge request ",Toast.LENGTH_SHORT).show();//delete me
                }else{
                    Intent intent = new Intent(ViewChallenges.this, UserProgress.class);
                    startActivity(intent);

                }
            }
        });

        pastChallengeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){//if past current challenge is chosen
            public void onItemClick(AdapterView arg0, View view,int position,long id){
               // Toast.makeText(ViewChallenges.this,"current challenge position: "+position,Toast.LENGTH_SHORT).show();//delete me
                setContentView(R.layout.past_challenges);
            }
        });


    }

    public void backToList(){
        setContentView(R.layout.challenge_list_view);
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

        // TODO replace findViewById by ViewHolder
        if(!empty) {
            Challenge challengeData = (Challenge) mData.get(position);
            challengeName.setText(challengeData.getName());
            betAmount.setText("$ " + String.valueOf(String.format("%.2f",challengeData.getBetAmount())));
            if(challengeData.isPending()){//if challenge pending, display "pending"
                statusName.setText("challenge pending");
                statusName.setTypeface(null,Typeface.ITALIC);
            }else {
                statusName.setText(challengeData.isOver() ? "Winner: Noam" : "In Lead: ");
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
