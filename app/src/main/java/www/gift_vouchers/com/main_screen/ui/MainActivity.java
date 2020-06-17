package www.gift_vouchers.com.main_screen.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.ActivityMainBinding;
import www.gift_vouchers.com.main_screen.ui.cart.cart;
import www.gift_vouchers.com.main_screen.ui.compainies.ui.compainies;
import www.gift_vouchers.com.main_screen.ui.myorders.ui.myorders;
import www.gift_vouchers.com.main_screen.ui.settings.settings;
import www.gift_vouchers.com.main_screen.ui.user_info.ui.user_info;
import www.gift_vouchers.com.utils.utils;

public class MainActivity extends AppCompatActivity implements NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    ActivityMainBinding binding;
    ImageView menu;
    BottomNavigationView bottomNavigationView;
    ImageView settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        //SET UP DRAWER
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, binding.drawer);

        //GET DATA COMPAINIES
        new utils().Replace_Fragment(new compainies(), R.id.frag, this);

        //CLICK LISTNERS
        click_listners();

        //BOTTOM NAVIGATION
        nav_bootom();

        check_fragment_bottom_nav();



    }

    //SET ON CLICK LISTNERS
    void click_listners() {
        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNavigationDrawerFragment.openDrawer(); //MENU OPEN DRAWER
            }
        });

        settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new settings(), R.id.frag, MainActivity.this);
            }
        });

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (position == 0) {
            new utils().Replace_Fragment(new myorders(), R.id.frag, this);
        } else if (position == 1) {
            new utils().Replace_Fragment(new settings(), R.id.frag, this);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mNavigationDrawerFragment.closeDrawer();
    }

    @Override
    public void onBackPressed() {

        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
        } else {

            FragmentManager fm = getFragmentManager();
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.frag); //GET CURRENT FRAGMENT

            if (f instanceof compainies) {  //CHECK IF FRAGMENT == MAIN PAGE TO CLOSE
                moveTaskToBack(true);

            } else if (fm.getBackStackEntryCount() > 0) {
                Log.i("MainActivity", "popping backstack");
                fm.popBackStack();
            } else {
                Log.i("MainActivity", "nothing on backstack, calling super");
                super.onBackPressed();
            }
        }

    }

    //**BOTTOM NAVIGATION
    private void nav_bootom() {
        bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    new utils().Replace_Fragment(new compainies(), R.id.frag, MainActivity.this); //HOME FRAGMENT
                } else if (item.getItemId() == R.id.person) {
                    new utils().Replace_Fragment(new user_info(), R.id.frag, MainActivity.this); //HOME MESSAGES
                } else if (item.getItemId() == R.id.cart) {
                    new utils().Replace_Fragment(new cart(), R.id.frag, MainActivity.this); //HOME MESSAGES
                }
                return true;
            }

        });

    }

    //SET CHECK ON FRAGMENT PLACES
    void check_fragment_bottom_nav() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.frag); //GET CURRENT FRAGMENT
        if (f instanceof compainies) {
            bottomNavigationView.setSelectedItemId(R.id.home);
        } else if (f instanceof cart) {
            bottomNavigationView.setSelectedItemId(R.id.cart);
        } else if (f instanceof user_info) {
            bottomNavigationView.setSelectedItemId(R.id.person);
        }

    }

    //SET BOTTOM NAVIGATION VISIABILITY
    void check_bottom_nav_visability() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.frag); //GET CURRENT FRAGMENT
        if ((f instanceof compainies) || (f instanceof cart) || (f instanceof user_info)) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        } else {
            bottomNavigationView.setVisibility(View.GONE);
        }

    }

}
