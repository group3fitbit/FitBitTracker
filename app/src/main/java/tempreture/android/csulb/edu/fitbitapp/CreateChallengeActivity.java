package tempreture.android.csulb.edu.fitbitapp;

//Create Challenges

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static tempreture.android.csulb.edu.fitbitapp.ChallengeStore.Users;


public class CreateChallengeActivity extends AppCompatActivity {

    //Arraylist containing the users
   //////// public static ArrayList<Dummy> Users = new ArrayList<Dummy>();

    EditText mEditChallengeName;
    EditText mEditChallengeGoals;
    Spinner mSpinnerChallengeType;
    EditText mEditChallengeBetAmount;
    AutoCompleteTextView mAddPartcipants;
    Button mButton;
    Button mSetChallenge;

    ImageView mChallengers;

    public TextView textView_Title;
    public TextView textView_BetAmount;
    public TextView textView_ChallengeType;
    public TextView textView_ChallengeGoal;
    public TextView textView_Participants;

    Challenge challenge;
    ArrayList<Challenge> challengeList;
    ArrayList<Dummy> tempParticipants;
    ArrayAdapter<String> sAdapter;
    ArrayList<String> usernames;
    AutoCompleteTextView acTextView;

    int imageCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_challenge);
        imageCounter = 0;

        final ImageLoader imageloader = new ImageLoader();

        challenge = new Challenge();
        Date startTime = Calendar.getInstance().getTime();
        challenge.setDate(startTime);
        challengeList = ChallengeStore.getInstance().getChallenges();
        tempParticipants = new ArrayList<Dummy>();
        challenge.setChallengeType("Steps");
        usernames = new ArrayList<String>();
        usernames.clear();

        //Adding Dummy Data to Friends list
/*        Users.clear();
        Users.add(0,new Dummy(getString(R.string.Adrian),R.drawable.adrian_circle,R.drawable.adrian));
        Users.add(1,new Dummy(getString(R.string.Jordan),R.drawable.jordan_circle,R.drawable.jordan));
        Users.add(2,new Dummy(getString(R.string.Ming),R.drawable.ming_circle,R.drawable.ming));
        Users.add(3,new Dummy(getString(R.string.Leslie),R.drawable.leslie_circle,R.drawable.leslie));
        Users.add(4,new Dummy(getString(R.string.Noam),R.drawable.noam_circle,R.drawable.noam));
        //setStats reference -> (int calories,int steps, int elevation, double active, double distance)
        Users.get(0).setStats(200,5000,50,100.3,78.5);
        Users.get(1).setStats(1050,3000,20,100.3,33.1);
        Users.get(2).setStats(2000,400,50,100.3,50.2);
        Users.get(3).setStats(500,500,5,100.3,20.3);
        Users.get(4).setStats(300,200,5,86.3,5.5);*/

        //Dummy Data Friends List
        for(int i = 0; i< Users.size(); i++){
            usernames.add(Users.get(i).getName());
            Log.v("username: ",usernames.get(i));
        }

        //Autocomplete Text
        sAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, usernames);
        acTextView = (AutoCompleteTextView) findViewById(R.id.AddFriends);
        acTextView.setThreshold(1);
        acTextView.setAdapter(sAdapter);

        //Sets Data Entry
        mButton = (Button) findViewById(R.id.Review);
        mSetChallenge = (Button) findViewById(R.id.Send);
        mEditChallengeName = (EditText) findViewById(R.id.ChallengeName);
        mSpinnerChallengeType= (Spinner) findViewById(R.id.ChallengeTypes);
        mEditChallengeGoals = (EditText) findViewById(R.id.Goal);
        mEditChallengeBetAmount = (EditText) findViewById(R.id.Amount);
        mAddPartcipants = (AutoCompleteTextView) findViewById(R.id.AddFriends);

      //Stores Selected Participants
        mAddPartcipants.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedUserID = 0;
                String selected = mAddPartcipants.getText().toString();
               for(int i=0;i < Users.size();i++){
                   if(Users.get(i).getName().equalsIgnoreCase(selected)){
                       tempParticipants.add(Users.get(i));
                       selectedUserID = i;
                       imageCounter++;

                       //removes chosen userName from adapter
                       usernames.remove(Users.get(i).getName());
                       sAdapter = new ArrayAdapter<String>(CreateChallengeActivity.this, android.R.layout.select_dialog_singlechoice, usernames);
                       acTextView.setAdapter(sAdapter);
                       break;
                   }
               }
                challenge.setParticipants(tempParticipants);
               // challengeList.add(challenge);
                    String userID = "p"+(imageCounter);
                    int userImageID = getResources().getIdentifier(userID, "id", view.getContext().getPackageName());//returns R.id
                    mChallengers = (ImageView) findViewById(userImageID);
                    imageloader.loadImage(getResources(),mChallengers,Users.get(selectedUserID).getBoxImage());
                    final int tempSelectedUserID = selectedUserID;

                    mChallengers.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });

                String tempString = null;
                for(Dummy dum : tempParticipants){
                    tempString = tempString + ", "+dum.getName();
                }
                mAddPartcipants.setText("");
            }
        });

        //Retrieves Selected Challenge Type
        mSpinnerChallengeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected=mSpinnerChallengeType.getSelectedItem().toString();
                challenge.setChallengeType(selected);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner for Challenge Type
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.challenge_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerChallengeType.setAdapter(adapter);

    }

    //Initiates Data Elements to Rewrite
    public void initDataElements(){
        textView_Title = (TextView) findViewById(R.id.ChallengeTitle);
        textView_BetAmount = (TextView) findViewById(R.id.BetAmount);
        textView_ChallengeType = (TextView) findViewById(R.id.challengeType);
        textView_ChallengeGoal = (TextView) findViewById(R.id.challengeGoal);
        textView_Participants = (TextView) findViewById(R.id.ParticipantList);
    }

    //Rewrites text on review xml
    private void reloadData(){
        textView_Title.setText(challenge.getName());
        textView_ChallengeType.setText(challenge.getChallengeType());
        textView_ChallengeGoal.setText(String.valueOf(challenge.getGoal()));
        DecimalFormat df = new DecimalFormat("#.##");
        textView_BetAmount.setText(String.valueOf(String.format("%.2f",challenge.getBetAmount())));
        String participants = "";
        for(Dummy d : challenge.getParticipants()){
            participants = participants +", "+ d.getName();
        }
        textView_Participants.setText(participants.substring(1,participants.length()));//get rid of first comma
    }

    private boolean retrieveData(){
        //Retrieves and Set Challenge Name
        String name = mEditChallengeName.getText().toString().toUpperCase();
        if(name.length()<1){
            return false;
        }
        challenge.setName(name);

        //Retrieve and Set Goal Amount
        int goal;
        try {
            goal = Integer.valueOf(mEditChallengeGoals.getText().toString());
        }catch(Exception e){
            return false;
        }

        challenge.setGoal(goal);

        //Retrieve and Set Bet Amount
        double amount;
        try {
            amount = Double.valueOf(mEditChallengeBetAmount.getText().toString());
        }catch(Exception e){
            return false;
        }
        challenge.setBetAmount(amount);

        return true;
    }

    //When the Users press review
    public void onClick_Review(View view) {

            if (retrieveData()) {
                boolean alreadyExists = false;
                for(Challenge ch : (ArrayList<Challenge>)ChallengeStore.getInstance().getChallenges()){
                    if(ch.getName().equalsIgnoreCase(challenge.getName())){
                        alreadyExists = true;
                    }
                }
                if(alreadyExists){
                    Toast.makeText(CreateChallengeActivity.this,"Challenge name already exists",Toast.LENGTH_SHORT).show();//do not delete
                }else {
                    if(challenge.getParticipants().size()==0) {
                        Toast.makeText(CreateChallengeActivity.this,"Don't forget to add your friends first",Toast.LENGTH_SHORT).show();//do not delete

                    }else {
                        setContentView(R.layout.activity_challenge_review);
                        initDataElements();
                        reloadData();
                        tempParticipants.clear();
                    }
                }
            } else {
                Toast.makeText(CreateChallengeActivity.this, "Please fill in all inputs", Toast.LENGTH_SHORT).show();//do not delete
            }

    }

    //user requests challenge
    public void onClick_submitChallenge(View view) {
        ChallengeStore.getInstance().addChallenge(challenge);
        Intent goToChallenge = new Intent(CreateChallengeActivity.this, ViewChallenges.class);
        startActivity(goToChallenge);
    }

    public void cancel_newChallenge(View view){
        challenge = new Challenge();//reset challenge to default
        Intent goToChallenge = new Intent(CreateChallengeActivity.this,DashboardMainActivity.class);
        startActivity(goToChallenge);
    }

}
