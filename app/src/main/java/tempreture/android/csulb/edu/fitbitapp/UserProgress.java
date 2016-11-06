package tempreture.android.csulb.edu.fitbitapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

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

 /* start toolbar----------------------------------------------------------------*/
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setTitle("CHALLENGE PROGRESSION");
        toolbar.setTitleTextColor(Color.WHITE);


/* end toolbar --------------------------------------------------------------------*/
/* start bottom buttons ---------------------------------------------------------- */
        final String[] colors = {"#96CC7A", "#EA705D", "#66BBCC"};

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(getString(R.string.tab_1), R.drawable.ic_map_24dp, Color.parseColor(colors[0]));
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(getString(R.string.tab_2), R.drawable.ic_local_restaurant_24dp, Color.parseColor(colors[1]));
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(getString(R.string.tab_3), R.drawable.ic_store_mall_directory_24dp, Color.parseColor(colors[2]));

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

        //  Enables Reveal effect
        bottomNavigation.setColored(true);

        bottomNavigation.setCurrentItem(0);

/*        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...

                fragment.updateColor(Color.parseColor(colors[position]));
            }
        });*/
 /* end bottom buttons ---------------------------------------------------------------- */
    }
/* start menu ----------------------------------------------------------------- */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.viewmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.dashboard: //(id == R.id.start_challenge) {
                Toast.makeText(UserProgress.this,"dashboard",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UserProgress.this, DashboardMainActivity.class);
                startActivity(i);
                return true;

            case R.id.start_challenge: //(id == R.id.start_challenge) {
                Toast.makeText(UserProgress.this,"start challenge",Toast.LENGTH_SHORT).show();

                Intent in = new Intent(UserProgress.this, UserProgress.class);
                startActivity(in);
                return true;

            case R.id.challenge_progress:// (id == R.id.challenge_progress) {
                Toast.makeText(UserProgress.this,"challenge progress",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
/* end menu -----------------------------------------------------------*/
}
