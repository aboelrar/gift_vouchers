package www.gift_vouchers.com.auth.ui.forget_pass.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.login.ui.LoginModelView;
import www.gift_vouchers.com.auth.ui.login.ui.LoginModelViewFactory;
import www.gift_vouchers.com.auth.ui.verfication_code.verfication_code;
import www.gift_vouchers.com.databinding.ForgetPassBinding;
import www.gift_vouchers.com.utils.utils;

import static www.gift_vouchers.com.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 */
public class forget_pass extends Fragment {
    ForgetPassBinding binding;

    public forget_pass() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.forget_pass, container, false);
        View view = binding.getRoot();

        click_listners();

        return view;
    }

    void click_listners() {
        binding.resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgetPass_validation();
            }
        });
    }


    //LOGIN VAILDATION
    void forgetPass_validation() {

        if (binding.email.getText().toString().length() < 5)  //VALIDATION ON USERNAME
        {
            String email_val = getResources().getString(R.string.email_val);
            binding.email.setError(email_val);
            yoyo(R.id.username, binding.email);
        } else {

            //CALL PROGRESS DIALOG
            new utils().set_dialog(getContext());

            //SEND DATA TO FACTORY
            ForgetPasswordModelView ForgetPasswordModelView = new ViewModelProvider(this, new ForgetPasswordModelViewFactory(getContext(),
                    binding.email.getText().toString())).get(ForgetPasswordModelView.class);

            //CALL METHOD THAT CALLING API
            ForgetPasswordModelView.get_data();


        }
    }
}
