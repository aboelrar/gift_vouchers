package www.gift_vouchers.com.main_screen_company.ui.user_info.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.UserInfoBinding;
import www.gift_vouchers.com.databinding.UserInfoCompanyBinding;
import www.gift_vouchers.com.local_data.saved_data;
import www.gift_vouchers.com.main_screen.ui.change_password.ui.change_password;
import www.gift_vouchers.com.utils.utils;

import static www.gift_vouchers.com.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 */
public class user_info_company extends Fragment {
    UserInfoCompanyBinding binding;

    public user_info_company() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.user_info_company, container, false);
        View view = binding.getRoot();

        //SET TEXT
        binding.cardNum.setText(new saved_data().get_name(getContext()));
        binding.email.setText(new saved_data().get_email(getContext()));
        binding.phone.setText(new saved_data().get_phone(getContext()));


        //CLiCK LISTNERS
        click_listners();

        return view;
    }

    void click_listners() {
        binding.changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new change_password(), R.id.frag, getContext());
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        binding.uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().upload_image(getContext(), 1);
            }
        });

        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.cardNum.length() <= 3) {
                    String username_val = getResources().getString(R.string.user_val);
                    binding.cardNum.setError(username_val);
                    yoyo(R.id.card_num, binding.cardNum);
                } else if (binding.email.length() <= 6) {
                    String email_val = getResources().getString(R.string.email_val);
                    binding.email.setError(email_val);
                    yoyo(R.id.email, binding.email);
                } else if (binding.phone.length() <= 6) {
                    String phone_val = getResources().getString(R.string.phone_val);
                    binding.phone.setError(phone_val);
                    yoyo(R.id.phone, binding.phone);
                } else if (binding.gold.length() == 0) {
                    String enter_price_val = getResources().getString(R.string.enter_price_val);
                    binding.gold.setError(enter_price_val);
                    yoyo(R.id.gold, binding.gold);
                } else if (binding.silver.length() == 0) {
                    String enter_price_val = getResources().getString(R.string.enter_price_val);
                    binding.silver.setError(enter_price_val);
                    yoyo(R.id.silver, binding.silver);
                } else if (binding.platinum.length() == 0) {
                    String enter_price_val = getResources().getString(R.string.enter_price_val);
                    binding.platinum.setError(enter_price_val);
                    yoyo(R.id.platinum, binding.platinum);
                } else {
                    //CALL PROGRESS DIALOG
                    new utils().set_dialog(getContext());

                    //SEND DATA TO FACTORY
                    UserInfoCoModelView UserInfoModelView = new ViewModelProvider(user_info_company.this, new UserInfoCoModelViewFactory(getContext(),
                            binding.cardNum.getText().toString(), binding.email.getText().toString(),
                            binding.phone.getText().toString(), binding.gold.getText().toString(),
                            binding.silver.getText().toString(), binding.platinum.getText().toString()))
                            .get(UserInfoCoModelView.class);

                    //CALL METHOD THAT CALLING API
                    UserInfoModelView.get_data();
                }


            }
        });
    }
}
