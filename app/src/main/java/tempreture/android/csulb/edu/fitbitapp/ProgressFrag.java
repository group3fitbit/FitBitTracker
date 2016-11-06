package tempreture.android.csulb.edu.fitbitapp;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static tempreture.android.csulb.edu.fitbitapp.UserProgress.Users;


public class ProgressFrag extends Fragment {
    public static ArrayList<ImageView> imageContainerArray = new ArrayList<ImageView>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.challenge_progress, container,false);
        imageContainerArray.clear();
        TextView testText = (TextView) view.findViewById(R.id.testText);
        TextView userName = (TextView) view.findViewById(R.id.userName);

        for(int numUsers = 0; numUsers < Users.size(); numUsers++){
            String userID = "user"+(numUsers+1);
            int userImageID = getResources().getIdentifier(userID, "id", view.getContext().getPackageName());
            imageContainerArray.add((ImageView) view.findViewById(userImageID));

            final int num = numUsers;
            final TextView userText = userName;
            final ImageView clickedUser = imageContainerArray.get(numUsers);
            imageContainerArray.get(numUsers).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userText.setText(Users.get(num).getName());
                }
            });
        }
        testText.setText("image container array: "+imageContainerArray.size());

        for(int i = 0; i< Users.size();i++) {
            ImageLoader imageloader = new ImageLoader();
            imageloader.loadImage(getResources(),imageContainerArray.get(i),Users.get(i).getCircleImage());
        }

        /* range: 2 to 333*/
        /*  input: image/text view,top,left */
        advanceImage(imageContainerArray.get(0),65,2);//user1 image
        advanceImage(imageContainerArray.get(1),65,50);//user2 image
        advanceImage(imageContainerArray.get(2),65,65);//user3 image
        advanceImage(imageContainerArray.get(3),65,44);//user4 image
        advanceImage(imageContainerArray.get(4),65,333);//user5 image
        //winner gets a bigger circle
       biggerCircle(imageContainerArray.get(4));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
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

