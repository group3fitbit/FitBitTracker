package tempreture.android.csulb.edu.fitbitapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

public class UserProgress extends AppCompatActivity {
    public static ArrayList<Dummy> Users = new ArrayList<Dummy>();

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.user_progress_main);
        Users.clear();
        Users.add(0,new Dummy(getString(R.string.Adrian),R.drawable.adrian_circle,R.drawable.adrian));
        Users.add(1,new Dummy(getString(R.string.Jordan),R.drawable.jordan_circle,R.drawable.jordan));
        Users.add(2,new Dummy(getString(R.string.Ming),R.drawable.ming_circle,R.drawable.ming));
        Users.add(3,new Dummy(getString(R.string.Leslie),R.drawable.leslie_circle,R.drawable.leslie));
        Users.add(4,new Dummy(getString(R.string.Noam),R.drawable.noam_circle,R.drawable.noam));

        ProgressFrag progressfrag = new ProgressFrag();
        DetailFrag detailfrag = new DetailFrag();

        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container1, progressfrag).commit();
        manager.beginTransaction().replace(R.id.fragment_container2, detailfrag).commit();

        super.onCreate(savedInstanceState);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.selectTabWithId(R.id.tab_challenges);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if(tabId == R.id.tab_dashboard) {
                    Intent intent = new Intent(UserProgress.this, DashboardMainActivity.class);
                    startActivity(intent);
                } else if (tabId == R.id.tab_startchallenge) {
                    Intent intent = new Intent(UserProgress.this, CreateChallengeActivity.class);
                    startActivity(intent);
                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
//                Intent intent = new Intent(UserProgress.this, UserProgress.class);
//                startActivity(intent);
            }
        });
    }


}
