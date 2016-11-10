

package tempreture.android.csulb.edu.fitbitapp;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static tempreture.android.csulb.edu.fitbitapp.UserProgress.Users;

/* start class ------------------------------------------------------------> */
public class ViewChallenges extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_list_view);
        ChallengeStore cs = ChallengeStore.getInstance();

/* DELETE ME I AM A TEST - START ==========================================================*/
        SimpleDateFormat formatter =  new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date startTime = Calendar.getInstance().getTime();
        Date otherDate = null;
        Date pastDate = null;
        try {
            otherDate = formatter.parse("11/06/2016 00:00");
            pastDate = formatter.parse("05/06/2015 1:00");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Challenge challenge1 = new Challenge();
        challenge1.setChallenge("Challenge 1","random type",15,startTime,15,Users);

        Challenge challenge2 = new Challenge();
        challenge2.setChallenge("Challenge 2","random type",5,otherDate,18,Users);

        Challenge challenge3 = new Challenge();
        challenge3.setChallenge("I am a past challenge","random type",5,pastDate,18,Users);

        challenge3.closeChallenge();

        cs.addChallenge(challenge1);
        cs.addChallenge(challenge2);
        cs.addChallenge(challenge3);

        cs.get("Challenge 2").setName("I got renamed");//mutate both Challenge AND challengeStore
        cs.get("Challenge 1").closeChallenge();
        ArrayList challengeData = cs.getChallenges();

        Log.v("challenge array size: ",String.valueOf(cs.getChallenges().size()));


/* DELETE ME I AM A TEST - END ==========================================================*/

        ChallengeListAdapter currentAdapter = new ChallengeListAdapter(challengeData,"current"); //place your String array in place of MyString
        ChallengeListAdapter pastAdapter = new ChallengeListAdapter(challengeData,"past"); //place your String array in place of MyString

        ListView currentChallengeList = (ListView) findViewById(R.id.current); //the ID you set your ListView to.
        ListView pastChallengeList = (ListView) findViewById(R.id.past); //the ID you set your ListView to.

        currentChallengeList.setAdapter(currentAdapter);
        pastChallengeList.setAdapter(pastAdapter);


    }
}

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
        Challenge challengeData = (Challenge) mData.get(position);

        if(!empty) {
            challengeName.setText(challengeData.getName());
            betAmount.setText("$ " + String.valueOf(challengeData.getBetAmount()));
            statusName.setText(challengeData.isOver() ? "Winner: " : "In Lead: ");
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
