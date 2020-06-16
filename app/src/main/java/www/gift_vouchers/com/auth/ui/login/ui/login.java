package www.gift_vouchers.com.auth.ui.login.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.forget_pass.ui.forget_pass;
import www.gift_vouchers.com.auth.ui.signup.ui.signup;
import www.gift_vouchers.com.databinding.LoginBinding;
import www.gift_vouchers.com.main_screen.ui.MainActivity;
import www.gift_vouchers.com.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class login extends Fragment {

    LoginBinding binding;

    public login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.login, container, false);
        View view = binding.getRoot();

        //CLICK ON BUTTONS
        click_listners();


        return view;

    }


    //SET ON CLICK LISTNERS
    void click_listners()
    {
        //SET CLICK LISTNERS BACK
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new signup(), R.id.frag, getContext());
            }
        });

        //GO TO MAIN PAGE
        binding.auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        //GO TO FORGET PASSWORD
        binding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new forget_pass(), R.id.frag, getContext());
            }
        });
    }


}
