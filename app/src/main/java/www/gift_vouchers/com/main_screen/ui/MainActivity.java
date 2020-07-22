package www.gift_vouchers.com.main_screen.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.paytabs.paytabs_sdk.utils.PaymentParams;

import java.io.File;
import java.io.IOException;

import es.dmoral.toasty.Toasty;
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
    public static File file;

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

        //Glide images for backgrouns
        Glide.with(MainActivity.this).load(R.drawable.masora).into(binding.topImg);
        Glide.with(MainActivity.this).load(R.drawable.leftimg).into(binding.leftImg);
        Glide.with(MainActivity.this).load(R.drawable.saroee).into(binding.rightImg);


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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImage = data.getData();
                file = new File(getRealPathFromURI(selectedImage));
                Toast.makeText(MainActivity.this, "sssss" + file, Toast.LENGTH_LONG).show();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

}
