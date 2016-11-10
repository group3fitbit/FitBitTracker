package tempreture.android.csulb.edu.fitbitapp;
import java.text.DecimalFormat;
import java.util.*;


public class Challenge {
    String name;
    String challengeType;
    int goal;
    Date date;
    double betAmount;
    ArrayList<Dummy> participants;
    boolean over;

    public Challenge(){
        name="Challenge Name";
        challengeType="?";
        goal=0;
        over = false;
        participants = new ArrayList<Dummy>();
    }

    public Challenge(String name,String type, int goal, Date date, double betAmount, ArrayList<Dummy> participants){
        setName(name);
        setChallengeType(type);
        setGoal(goal);
        setDate(date);
        setBetAmount(betAmount);
        setParticipants(participants);
    }

    public void setName(String n){
        name = n;
        replaceChallengeInStore();
    }

    public void setChallengeType(String ct){
        challengeType = ct;
        replaceChallengeInStore();
    }
    public void setGoal(int g){
        goal = g;
        replaceChallengeInStore();
    }
    public void setDate(Date d){
        date = d;
        replaceChallengeInStore();
    }
    public void setParticipants(ArrayList<Dummy> p){
        participants = p;
        replaceChallengeInStore();
    }
    public void setBetAmount(double bm){
        betAmount = bm;
        replaceChallengeInStore();
    }

    //challenge is over
    public void closeChallenge(){
        over = true;
        replaceChallengeInStore();
    }

    public void setChallenge(String name,String type, int goal, Date date, double betAmount, ArrayList<Dummy> participants){
        setName(name);
        setChallengeType(type);
        setGoal(goal);
        setDate(date);
        setBetAmount(betAmount);
        setParticipants(participants);
        replaceChallengeInStore();
    }

    //returns whether challenge is over or not
    public boolean isOver(){
        return over;
    }

    public String getName(){
        return name;
    }
    public String getChallengeType(){
        return challengeType;
    }
    public Date getDate(){
        return date;
    }
    public int getType(){
        return goal;
    }
    public double getBetAmount(){
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(betAmount));
    }
    public ArrayList<Dummy> getParticipants(){
        return participants;
    }

    public boolean equals(Challenge c){ //assuming there are no repeats of same challenge name
        if(c.getName() == this.name){

            return true;
        }
        return false;
    }

    public void replaceChallengeInStore(){
        for(int i = 0; i < ChallengeStore.getInstance().getChallenges().size();i++) {
            if(ChallengeStore.getInstance().getChallenges().get(i).getClass().getName() == name){
                ChallengeStore.getInstance().getChallenges().set(i,this.getClass());
                break;
            }
        }
    }
}

/* implementataions:
-> create a new Challenge: Challenge challenge = new Challenge();
                       or: Challenge challenge = new Challenge(name,type,goal,date,int days,betAmount,ArrayList<Dummy> participants);
*/


//old version just in case
/*
package tempreture.android.csulb.edu.fitbitapp;

import java.text.DecimalFormat;
import java.util.*;


public class Challenge {
    private String name;
    private String challengeType;
    private int goal;
    private int days;
    private Date date;
    private double betAmount;
    private ArrayList<Dummy> participants;

    public Challenge(){
        name="Challenge Name";
        challengeType="?";
        goal=0;
        days=0;
        participants = new ArrayList<Dummy>();
    }

    public void setName(String n){
        name = n;
    }
    public void setChallengeType(String ct){
        challengeType = ct;
    }
    public void setGoal(int g){
        goal = g;
    }
    public void setDays(int d){
        days = d;
    }
    public void setDate(Date d){
        date = d;
    }
    public void setParticipants(ArrayList<Dummy> p){
        participants = p;
    }
    public void setBetAmount(double bm){
        betAmount = bm;
    }
    public void setChallenge(String name,String type, int goal, Date date, int days, double betAmount, ArrayList<Dummy> participants){
        setName(name);
        setChallengeType(type);
        setGoal(goal);
        setDate(date);
        setDays(days);
        setBetAmount(betAmount);
        setParticipants(participants);
    }

    public String getName(){
        return name;
    }
    public String getChallengeType(){
        return challengeType;
    }
    public int getDays(){
        return days;
    }
    public Date getDate(){ return date; }
    public int getType(){
        return goal;
    }
    public double getBetAmount(){
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(betAmount));
    }
    public ArrayList<Dummy> getParticipants(){
        return participants;
    }
}
*/
