package www.gift_vouchers.com.auth.ui.forget_pass.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.verfication_code.verfication_code;
import www.gift_vouchers.com.databinding.ForgetPassBinding;
import www.gift_vouchers.com.utils.utils;

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

    void click_listners()
    {
        binding.resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new verfication_code(), R.id.frag, getContext());

            }
        });
    }
}
