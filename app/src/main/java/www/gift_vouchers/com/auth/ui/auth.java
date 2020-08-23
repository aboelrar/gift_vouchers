package www.gift_vouchers.com.auth.ui;

import android.app.FragmentManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.login.ui.login;
import www.gift_vouchers.com.network_check_status.regist_network_broadcast;
import www.gift_vouchers.com.utils.utils;

public class auth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

        //ADD LOGIN
        new utils().Replace_Fragment(new login(), R.id.frag, this);

        //CALL BROADCAST RECIEVER METHOD
        new regist_network_broadcast().registerNetworkBroadcastForNougat(auth.this);
    }


    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.frag); //GET CURRENT FRAGMENT

        if (f instanceof login) {  //CHECK IF FRAGMENT == MAIN PAGE TO CLOSE
            moveTaskToBack(true);
        } else {
            super.onBackPressed();
        }
    }
}
