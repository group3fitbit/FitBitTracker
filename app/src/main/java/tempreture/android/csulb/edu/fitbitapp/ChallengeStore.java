package tempreture.android.csulb.edu.fitbitapp;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



public class ChallengeStore {
    private static ChallengeStore cs = new ChallengeStore();
    public static ArrayList<Dummy> Users = new ArrayList<Dummy>();
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
        boolean alreadyExists = false;
        for(Challenge ch : challenges){
            if(ch.getName().equalsIgnoreCase(c.getName())){
                alreadyExists = true;
            }
        }
        if(!alreadyExists) {
            challenges.add(c);
        }
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

    public void initChallenges(){
        //Adding Dummy Data to Friends list
        Users.clear();
        Users.add(0,new Dummy("Adrian",R.drawable.adrian_circle,R.drawable.adrian));
        Users.add(1,new Dummy("Jordan",R.drawable.jordan_circle,R.drawable.jordan));
        Users.add(2,new Dummy("Ming",R.drawable.ming_circle,R.drawable.ming));
        Users.add(3,new Dummy("Leslie",R.drawable.leslie_circle,R.drawable.leslie));
        Users.add(4,new Dummy("Noam",R.drawable.noam_circle,R.drawable.noam));
        //setStats reference -> (int calories,int steps, int elevation, double active, double distance)
        Users.get(0).setStats(200,10000,35,100.3,78.5);
        Users.get(1).setStats(1050,3000,20,100.3,33.1);
        Users.get(2).setStats(2000,2500,50,100.3,50.2);
        Users.get(3).setStats(500,500,75,100.3,20.3);
        Users.get(4).setStats(300,5000,100,86.3,5.5);

        SimpleDateFormat formatter =  new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date firstDate = null;
        Date secondDate = null;
        try {
            firstDate = formatter.parse("11/06/2016 00:00");
            secondDate = formatter.parse("09/28/2016 1:00");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Dummy> challenge1Friends = new ArrayList<Dummy>();
        challenge1Friends.add(Users.get(0));
        challenge1Friends.add(Users.get(2));
        challenge1Friends.add(Users.get(4));

        Challenge challenge1 = new Challenge();
        challenge1.setChallenge("Best Runner","steps",15000,firstDate,20,challenge1Friends);
        challenge1.startChallenge();

        Challenge challenge2 = new Challenge();
        challenge2.setChallenge("To The Top","floors climbed",100,secondDate,18,Users);
        challenge2.startChallenge();
        challenge2.closeChallenge();

        addChallenge(challenge1);
        addChallenge(challenge2);
    }

}
/* implementations:
* -> get instance: ArrayList<Challenge> challengeList = ChallengeStore.getInstance().getChallenges();
* -> get challenge by name: challengeList.get("my challenge")
* -> get challenge by index: challengeList.get(0);
* -> get specific variables: eg: name: challengeList.get("my challenge").getName();*/