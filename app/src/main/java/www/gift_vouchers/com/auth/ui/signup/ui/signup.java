package www.gift_vouchers.com.auth.ui.signup.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.login.ui.login;
import www.gift_vouchers.com.databinding.LoginBinding;
import www.gift_vouchers.com.databinding.SignupBinding;
import www.gift_vouchers.com.main_screen.ui.MainActivity;
import www.gift_vouchers.com.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class signup extends Fragment {

    SignupBinding binding;

    public signup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.signup, container, false);
        View view = binding.getRoot();

        //ON CLICK LISTNERS
        onClickListners();

        return view;
    }

    //CLICK LISTNERS
    void onClickListners()
    {
        //SET CLICK LISTNERS BACK
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new login(), R.id.frag, getContext());
            }
        });

        //SET CLICK AUTH
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
    }
}
