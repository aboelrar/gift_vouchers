package www.gift_vouchers.com.auth.ui.login.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.forget_pass.ui.forget_pass;
import www.gift_vouchers.com.auth.ui.signup.ui.signup;
import www.gift_vouchers.com.databinding.LoginBinding;

import static www.gift_vouchers.com.utils.utils.yoyo;

import www.gift_vouchers.com.main_screen_company.ui.Main_Activity_Company;
import www.gift_vouchers.com.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class login extends Fragment {

    LoginBinding binding;
    LoginModelView loginModelView;

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
    void click_listners() {
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
                //GO TO LOGIN
                login_validation();
            }
        });

        //GO TO FORGET PASSWORD
        binding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new forget_pass(), R.id.frag, getContext());
            }
        });

        //GO TO MAIN SCREEN
        binding.explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Main_Activity_Company.class));
            }
        });
    }


    //LOGIN VAILDATION
    void login_validation() {

        if (binding.username.getText().toString().length() < 5)  //VALIDATION ON USERNAME
        {
            String username_val = getResources().getString(R.string.user_val);
            binding.username.setError(username_val);
            yoyo(R.id.username, binding.username);
        } else if (binding.password.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            binding.password.setError(pass_val);
            yoyo(R.id.password, binding.password);
        } else {

            //CALL PROGRESS DIALOG
            new utils().set_dialog(getContext());


            //SEND DATA TO FACTORY
            loginModelView = new ViewModelProvider(this, new LoginModelViewFactory(getContext(),
                    binding.username.getText().toString(), binding.password.getText().toString(), "dd")).get(LoginModelView.class);


            //CALL METHOD THAT CALLING API
            loginModelView.get_data(binding.username.getText().toString(), binding.password.getText().toString(), "dd");


        }
    }


}
