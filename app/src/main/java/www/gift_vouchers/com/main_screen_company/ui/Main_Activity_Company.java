package www.gift_vouchers.com.main_screen_company.ui;

import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.MainActivityCompanyBinding;
import www.gift_vouchers.com.main_screen.ui.NavigationDrawerCallbacks;
import www.gift_vouchers.com.main_screen.ui.NavigationDrawerFragment;
import www.gift_vouchers.com.main_screen.ui.cart.cart;
import www.gift_vouchers.com.main_screen.ui.compainies.ui.compainies;
import www.gift_vouchers.com.main_screen.ui.settings.settings;
import www.gift_vouchers.com.main_screen.ui.user_info.ui.user_info;
import www.gift_vouchers.com.main_screen_company.ui.my_orders_compant.ui.myorders_company;
import www.gift_vouchers.com.main_screen_company.ui.user_info.ui.user_info_company;
import www.gift_vouchers.com.network_check_status.regist_network_broadcast;
import www.gift_vouchers.com.utils.utils;

public class Main_Activity_Company extends AppCompatActivity implements NavigationDrawerCallbacks {
    MainActivityCompanyBinding binding;
    ImageView menu;
    BottomNavigationView bottomNavigationView;
    ImageView settings;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    public static File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity_company);

        //SET UP DRAWER
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, binding.drawer);

        //GET DATA COMPAINIES
        new utils().Replace_Fragment(new myorders_company(), R.id.frag, this);

        //CLICK LISTNERS
        click_listners();

        //CLICK LISTNERS
        nav_bootom();

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(Main_Activity_Company.this);
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

        //Glide images for backgrouns
        Glide.with(Main_Activity_Company.this).load(R.drawable.masora).into(binding.topImg);
        Glide.with(Main_Activity_Company.this).load(R.drawable.leftimg).into(binding.leftImg);
        Glide.with(Main_Activity_Company.this).load(R.drawable.saroee).into(binding.rightImg);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (position == 0) {
            new utils().Replace_Fragment(new myorders_company(), R.id.frag, this);
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

            if (f instanceof myorders_company) {  //CHECK IF FRAGMENT == MAIN PAGE TO CLOSE
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
                    new utils().Replace_Fragment(new myorders_company(), R.id.frag, Main_Activity_Company.this); //HOME FRAGMENT
                } else if (item.getItemId() == R.id.person) {
                    new utils().Replace_Fragment(new user_info_company(), R.id.frag, Main_Activity_Company.this); //HOME MESSAGES
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
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);

                    user_info_company fragment_obj = (user_info_company) getSupportFragmentManager().
                            findFragmentById(R.id.frag);

                            ((CircleImageView) fragment_obj.getView().findViewById(R.id.cri_img)).setImageBitmap(bitmap);


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
