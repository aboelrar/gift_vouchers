package www.gift_vouchers.com.welcome_screens.welcome_tour.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.auth;
import www.gift_vouchers.com.auth.ui.login.ui.login;
import www.gift_vouchers.com.welcome_screens.splash.ui.splash_screen;
import www.gift_vouchers.com.welcome_screens.welcome_tour.pattern.viewimage;

public class welcome_tour extends AppCompatActivity {
   TextView skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_tour);

        final ViewPager viewPager = findViewById(R.id.viewpager);
        viewimage viewimage = new viewimage(welcome_tour.this);
        viewPager.setAdapter(viewimage);

        CircleIndicator circleIndicator = findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);

        skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(welcome_tour.this, auth.class));
            }
        });


    }
}
