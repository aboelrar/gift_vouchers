package www.gift_vouchers.com.auth.ui.change_password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.ChangePassBinding;
import www.gift_vouchers.com.utils.utils;

import static www.gift_vouchers.com.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 */
public class change_pass extends Fragment {
    ChangePassBinding binding;

    public change_pass() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.change_pass, container, false);
        View view = binding.getRoot();

        click_listners();

        return view;
    }

    //ON CLICK LISTNERS
    void click_listners() {
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChange_pass();
            }
        });
    }

    //CHANGE PASS
    void setChange_pass() {
        if (binding.password.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            binding.password.setError(pass_val);
            yoyo(R.id.new_pass, binding.password);
        } else if (!binding.coPassword.getText().toString().equals(binding.coPassword.getText().toString()))  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_d_match);
            binding.password.setError(pass_val);
            yoyo(R.id.co_new_pass, binding.password);
        } else {
            //CALL PROGRESS DIALOG
            new utils().set_dialog(getContext());

            //SEND DATA TO FACTORY
            ChangePasswordModelView ChangePasswordModelView = new ViewModelProvider(change_pass.this, new ChangePasswordModelViewFactory(getContext(),
                    getArguments().getString("email"), getArguments().getString("code"),
                    binding.password.getText().toString(), binding.coPassword.getText().toString())).get(ChangePasswordModelView.class);

            //CALL METHOD THAT CALLING API
            ChangePasswordModelView.get_data(getArguments().getString("code"), getArguments().getString("email"),
                    binding.password.getText().toString(), binding.coPassword.getText().toString());

        }
    }
}
