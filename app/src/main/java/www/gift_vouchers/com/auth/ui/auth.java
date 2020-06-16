package www.gift_vouchers.com.auth.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.login.ui.login;
import www.gift_vouchers.com.utils.utils;

public class auth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

        //ADD LOGIN
        new utils().Replace_Fragment(new login(),R.id.frag,this);
    }
}
