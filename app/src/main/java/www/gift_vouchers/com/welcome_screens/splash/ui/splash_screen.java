package www.gift_vouchers.com.welcome_screens.splash.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.utils.utils;
import www.gift_vouchers.com.welcome_screens.welcome_tour.ui.welcome_tour;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new utils().splash_screen(this, welcome_tour.class);
    }
}
