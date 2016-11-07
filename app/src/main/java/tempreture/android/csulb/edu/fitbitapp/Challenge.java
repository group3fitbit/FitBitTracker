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
