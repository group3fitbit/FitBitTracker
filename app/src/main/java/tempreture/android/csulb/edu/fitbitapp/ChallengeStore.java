package tempreture.android.csulb.edu.fitbitapp;

import android.util.Log;

import java.util.ArrayList;

public class ChallengeStore {
    private static ChallengeStore cs = new ChallengeStore();
    ArrayList<Challenge> challenges;

    private ChallengeStore() {
        challenges = new ArrayList<Challenge>();
    }

    //returns instance of singleton
    public static synchronized ChallengeStore getInstance() {
        if(cs == null) {
            cs = new ChallengeStore();
        }
        return cs;
    }

    //gets whole arraylist of challenges
    public ArrayList getChallenges(){
        return challenges;
    }

    //add a new challenge object to challenge list
    public void addChallenge(Challenge c){
        challenges.add(c);
    }

    //gets single challenge by name
    public Challenge get(String name) {
        Challenge temp = new Challenge();
        for(Challenge arr : challenges){
            if(arr.getName().equalsIgnoreCase(name)){
                temp = arr;
                return temp;
            }
        }
        Log.v("invalid parame: ","Challenge Name not found");
        return null;
    }


}
/* implementations:
* -> get instance: ArrayList<Challenge> challengeList = ChallengeStore.getInstance().getChallenges();
* -> get challenge by name: challengeList.get("my challenge")
* -> get challenge by index: challengeList.get(0);
* -> get specific variables: eg: name: challengeList.get("my challenge").getName();*/