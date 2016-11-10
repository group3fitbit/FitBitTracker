package tempreture.android.csulb.edu.fitbitapp;

//Create Challenges

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CreateChallengeActivity extends AppCompatActivity {

    EditText mEditChallengeName;
    EditText mEditChallengeGoals;
    Spinner mSpinnerChallengeType;
    EditText mEditChallengeBetAmount;
    Button mButton;

    public TextView textView_Title;
    public TextView textView_BetAmount;
    public TextView textView_ChallengeType;
    public TextView textView_ChallengeGoal;
    public TextView textView_ChallengeDuration;
    Challenge challenge;
    ArrayList<Challenge> challengeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_challenge);

        challenge = new Challenge();
        challengeList = ChallengeStore.getInstance().getChallenges();

        //Dummy Data Friends List
        String[] Friends = {"Adrian", "Noam", "Leslie", "Ming", "Jordan"};

        //Autocomplete Text
        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, Friends);
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.AddFriends);
        acTextView.setThreshold(1);
        acTextView.setAdapter(sAdapter);

        //Sets Data Entry
        mButton = (Button) findViewById(R.id.Review);
        mEditChallengeName = (EditText) findViewById(R.id.ChallengeName);
        mSpinnerChallengeType= (Spinner) findViewById(R.id.ChallengeTypes);
        mEditChallengeGoals = (EditText) findViewById(R.id.Goal);
        mEditChallengeBetAmount = (EditText) findViewById(R.id.Amount);

        //Retrieves Selected Challenge Type
        mSpinnerChallengeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected=mSpinnerChallengeType.getSelectedItem().toString();
                challenge.setChallengeType(selected);
                challengeList.add(challenge);
                //cd.addChallengeTypeEntry(name, selected);
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
    }

    //Rewrites text on review xml
    private void reloadData(){

        textView_Title.setText(challenge.getName());
        textView_ChallengeType.setText(challenge.getChallengeType());
        textView_ChallengeGoal.setText(challenge.getType());
        textView_BetAmount.setText((int) challenge.getBetAmount());
    }

    private void retrieveData(){

        //Retrieves and Set Challenge Name
        String name = mEditChallengeName.getText().toString();
        challenge.setName(name);

        //Retrieves and Set Participants

        //Retrieve and Set Goal Amount
        int goal = Integer.parseInt(mEditChallengeGoals.getText().toString());
        challenge.setGoal(goal);

        //Retrieve and Set Bet Amount
        int amount = Integer.parseInt(mEditChallengeBetAmount.getText().toString());
        challenge.setBetAmount(amount);

        ChallengeStore.getInstance().addChallenge(challenge);
    }

    //When the Users press review
    public void onClick_Review(View view) {
        setContentView(R.layout.activity_challenge_review);
        retrieveData();
        initDataElements();
        reloadData();
    }
}
