package www.gift_vouchers.com.welcome_screens.welcome_tour.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.auth;
import www.gift_vouchers.com.welcome_screens.welcome_tour.pattern.viewimage;

import static www.gift_vouchers.com.local_data.send_data.set_welcome_num;

public class welcome_tour extends AppCompatActivity {
    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_tour);

        final ViewPager viewPager = findViewById(R.id.viewpager);
        viewimage viewimage = new viewimage(welcome_tour.this);
        viewPager.setAdapter(viewimage);

        //CHECK LANGAUGE
        String CurrentLang = Locale.getDefault().getLanguage();
        if (CurrentLang.equals("ar")) {
            viewPager.setCurrentItem(2);
        }


        CircleIndicator circleIndicator = findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);

        skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(welcome_tour.this, auth.class));
                set_welcome_num(welcome_tour.this,"1");
            }
        });


    }
}
