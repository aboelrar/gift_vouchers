package www.gift_vouchers.com.main_screen.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.main_screen.ui.compainies.ui.compainies;
import www.gift_vouchers.com.utils.utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GET DATA COMPAINIES
        new utils().Replace_Fragment(new compainies(), R.id.frag, this);

    }
}
