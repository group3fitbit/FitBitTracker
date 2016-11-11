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
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.user_progress_main);

        ProgressFrag progressfrag = new ProgressFrag();
        DetailFrag detailfrag = new DetailFrag();

        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container1, progressfrag).commit();
        manager.beginTransaction().replace(R.id.fragment_container2, detailfrag).commit();
        super.onCreate(savedInstanceState);
    }


}
