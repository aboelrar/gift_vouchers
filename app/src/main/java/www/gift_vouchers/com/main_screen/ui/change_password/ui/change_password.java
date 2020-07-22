package www.gift_vouchers.com.main_screen.ui.change_password.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.ChangePasswordBinding;
import www.gift_vouchers.com.main_screen.ui.user_info.ui.UserInfoModelView;
import www.gift_vouchers.com.main_screen.ui.user_info.ui.UserInfoModelViewFactory;
import www.gift_vouchers.com.main_screen.ui.user_info.ui.user_info;
import www.gift_vouchers.com.utils.utils;

import static www.gift_vouchers.com.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 */
public class change_password extends Fragment {
    ChangePasswordBinding binding;

    public change_password() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.change_password, container, false);
        View view = binding.getRoot();

        click_listners();

        return view;
    }

    private void click_listners() {

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChange_pass();
            }
        });
    }

    //CHANGE PASS
    void setChange_pass() {
        if (binding.oldPass.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            binding.oldPass.setError(pass_val);
            yoyo(R.id.old_pass, binding.oldPass);
        } else if (binding.newPass.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            binding.newPass.setError(pass_val);
            yoyo(R.id.new_pass, binding.newPass);
        } else if (!binding.coNewPass.getText().toString().equals(binding.newPass.getText().toString()))  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_d_match);
            binding.coNewPass.setError(pass_val);
            yoyo(R.id.co_new_pass, binding.coNewPass);
        } else {
            //CALL PROGRESS DIALOG
            new utils().set_dialog(getContext());

            //SEND DATA TO FACTORY
            ChangePasswordModelView ChangePasswordModelView = new ViewModelProvider(change_password.this, new ChangePasswordModelViewFactory(getContext(),
                    binding.oldPass.getText().toString(), binding.newPass.getText().toString(),
                    binding.coNewPass.getText().toString())).get(ChangePasswordModelView.class);

            //CALL METHOD THAT CALLING API
            ChangePasswordModelView.get_data(binding.oldPass.getText().toString(),
                    binding.newPass.getText().toString(), binding.coNewPass.getText().toString());

        }
    }
}
