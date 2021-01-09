package www.gift_vouchers.com.welcome_screens.splash.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.auth;
import www.gift_vouchers.com.local_data.saved_data;
import www.gift_vouchers.com.main_screen.ui.MainActivity;
import www.gift_vouchers.com.main_screen_company.ui.Main_Activity_Company;
import www.gift_vouchers.com.utils.utils;
import www.gift_vouchers.com.welcome_screens.welcome_tour.ui.welcome_tour;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        if (new saved_data().get_welcome_num(splash_screen.this).equals("1")) {
            if (new saved_data().get_login_status(splash_screen.this) == true) {
                if (new saved_data().get_user_type(splash_screen.this).equals("1")) {
                    new utils().splash_screen(this, Main_Activity_Company.class);
                } else if (new saved_data().get_user_type(splash_screen.this).equals("3")) {
                    new utils().splash_screen(this, MainActivity.class);
                }
            } else {
                new utils().splash_screen(this, auth.class);
            }
        } else {
            new utils().splash_screen(this, welcome_tour.class);
        }
    }
}
