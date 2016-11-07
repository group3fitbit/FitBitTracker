package tempreture.android.csulb.edu.fitbitapp;

import java.util.ArrayList;

public class Challenge {
    private String name;
    private String challengeType;
    private int goal;
    private int days;
    private ArrayList<Dummy> participants;

    public Challenge(){
        name="Chellange Name";
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
    public void setParticipants(ArrayList<Dummy> p){
        participants = p;
    }
    public void setChallenge(String name,String type, int goal, int days, ArrayList<Dummy> participants){
        setName(name);
        setChallengeType(type);
        setGoal(goal);
        setDays(days);
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
    public int getType(){
        return goal;
    }
    public ArrayList<Dummy> getParticipants(){
        return participants;
    }
}
