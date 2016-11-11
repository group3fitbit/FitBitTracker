package tempreture.android.csulb.edu.fitbitapp;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import static tempreture.android.csulb.edu.fitbitapp.ViewChallenges.challengeChosen;

public class ProgressFrag extends Fragment {
    Dummy me = new Dummy("Me",R.drawable.me_circle,R.drawable.me);
    public static ArrayList<Dummy> tempUserArrayList;

    public static ArrayList<ImageView> imageContainerArray = new ArrayList<ImageView>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.challenge_progress, container,false);
        DataContainer dc = DataContainer.getInstance();
        ArrayList<DataEntry> steps = dc.getSteps();

        tempUserArrayList = new ArrayList<Dummy>(challengeChosen.getParticipants());
        me.setSteps(0);

        if(!tempUserArrayList.contains(me)) {
            tempUserArrayList.add(me);
        }

        for(DataEntry ent : steps) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date d = df.parse(ent.getDate());
                if(d.after(challengeChosen.getDate())) {
                    me.setSteps(me.getSteps() + Integer.parseInt(ent.getValue()));
                }
            } catch(Exception e) {}
        }
        for(DataEntry ent : steps) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date d = df.parse(ent.getDate());
                if(d.after(challengeChosen.getDate())) {
                    me.setSteps(me.getSteps() + Integer.parseInt(ent.getValue()));
                }
            } catch(Exception e) {}
        }
        challengeChosen.getDate();
        me.setProgress(challengeChosen.getChallengeType());

        imageContainerArray.clear();
        TextView testText = (TextView) view.findViewById(R.id.testText);
        TextView userName = (TextView) view.findViewById(R.id.userName);
        setProgress();
//testText.setText(String.valueOf(me.getSteps()));
        for(int numUsers = 0; numUsers < challengeChosen.getParticipants().size(); numUsers++){
            //get numUsers image views into arraycontainerarray
            String userID = "user"+(numUsers+1);
            int userImageID = getResources().getIdentifier(userID, "id", view.getContext().getPackageName());
            imageContainerArray.add((ImageView) view.findViewById(userImageID));


            final int num = numUsers;
            final TextView userText = userName;
            //final ImageView clickedUser = imageContainerArray.get(numUsers);
            //textview that sets clicked text to participant name
            imageContainerArray.get(numUsers).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userText.setText(challengeChosen.getParticipants().get(num).getName());
                }
            });
        }
        imageContainerArray.add((ImageView) view.findViewById(R.id.me));
        //testText.setText("image container array: "+imageContainerArray.size());

        ImageLoader imageloader = new ImageLoader();
        //load participant circle images into imagecontainerarray
        for(int i = 0; i< challengeChosen.getParticipants().size();i++) {
            imageloader.loadImage(getResources(),imageContainerArray.get(i),challengeChosen.getParticipants().get(i).getCircleImage());
        }
        imageloader.loadImage(getResources(),imageContainerArray.get(challengeChosen.getParticipants().size()),me.getCircleImage());


       // testText.setText("start moving images here");
        //move players
        /* range: 2 to 333*/
        /*  input: image/text view,top,left */
        int move = 0;
        String moveString = "";
        for(int player = 0; player <= challengeChosen.getParticipants().size(); player++) {

                if(player == challengeChosen.getParticipants().size()){//if at last participant
                    if(me.getProgress() >= challengeChosen.getGoal()) {
                        move = 333;
                    }else{
                        move = (int) (me.getProgress() * 333.0)/challengeChosen.getGoal();
                    }
                    advanceImage(imageContainerArray.get(player),65,move);
                }else{
                    if(challengeChosen.getParticipants().get(player).getProgress() >= challengeChosen.getGoal()) {
                        move = 333;
                    }else{
                        move = (int) (challengeChosen.getParticipants().get(player).getProgress() * 333.0)/challengeChosen.getGoal();
                    }
                    advanceImage(imageContainerArray.get(player),65,move);
                }

            moveString = moveString + ", "+String.valueOf(move);
            //testText.setText(moveString);
        }

        int max = 0;
        for(int i = 1; i< tempUserArrayList.size();i++){
           if(tempUserArrayList.get(i).getProgress() > tempUserArrayList.get(max).getProgress()){
               max = i;
           }
        }
       biggerCircle(imageContainerArray.get(max));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    public void setProgress(){
        for(int i=0;i<challengeChosen.getParticipants().size();i++) {
            challengeChosen.getParticipants().get(i).setProgress(challengeChosen.getChallengeType());
        }
    }

    public int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public void biggerCircle(ImageView image){
       int leftMargin = ((ViewGroup.MarginLayoutParams) image.getLayoutParams()).leftMargin;
       int topMargin = ((ViewGroup.MarginLayoutParams) image.getLayoutParams()).topMargin;
        ((ViewGroup.MarginLayoutParams) image.getLayoutParams()).width = dpToPx(45);
        ((ViewGroup.MarginLayoutParams) image.getLayoutParams()).height = dpToPx(45);
        ((ViewGroup.MarginLayoutParams) image.getLayoutParams()).setMargins(leftMargin-dpToPx(20),topMargin-30,0,0);
    }


    //public void winnerText(TextView text)
    public void advanceImage(ImageView image,int top, int left){
        //int marginTop = 65;
        ((ViewGroup.MarginLayoutParams) image.getLayoutParams()).setMargins(dpToPx(left),dpToPx(top),0,0);
    }

    public void showText(ImageView image){
       //show userName based on location of image
    }



}

