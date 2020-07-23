package www.gift_vouchers.com.main_screen.ui.Checkout;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.CheckOutBinding;
import www.gift_vouchers.com.main_screen.ui.pay.pay;
import www.gift_vouchers.com.main_screen.ui.select_gift_design.ui.select_design;
import www.gift_vouchers.com.main_screen.ui.user_info.ui.UserInfoModelView;
import www.gift_vouchers.com.main_screen.ui.user_info.ui.UserInfoModelViewFactory;
import www.gift_vouchers.com.main_screen.ui.user_info.ui.user_info;
import www.gift_vouchers.com.utils.utils;

import static www.gift_vouchers.com.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 */
public class check_out extends Fragment {
    CheckOutBinding binding;

    public check_out() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
                inflater, R.layout.check_out, container, false);
        View view = binding.getRoot();

        click_listner();

        return view;

    }

    //SET ON CLICK LISTNERS
    void click_listner() {
        //SET ON CLICK PAY
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_out();
            }
        });

        //SET ON BACK
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }

    //CHECK OUT VALIDATION
    void check_out() {
        if (binding.fullname.getText().toString().length() < 5)  //VALIDATION ON USERNAME
        {
            String username_val = getResources().getString(R.string.user_val);
            binding.fullname.setError(username_val);
            yoyo(R.id.fullname, binding.fullname);
        } else if (binding.emailAddress.getText().toString().length() < 6)  //VALIDATION ON EMAIL
        {
            String email_val = getResources().getString(R.string.email_val);
            binding.emailAddress.setError(email_val);
            yoyo(R.id.email_address, binding.emailAddress);
        } else if (binding.phone.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String phone_val = getResources().getString(R.string.phone_val);
            binding.phone.setError(phone_val);
            yoyo(R.id.phone, binding.phone);
        } else {
            //CALL PROGRESS DIALOG
            new utils().set_dialog(getContext());

            //SEND DATA TO FACTORY
            CheckOutModelView CheckOutModelView = new ViewModelProvider(check_out.this, new CheckOutModelViewFactory(getContext(),
                    binding.fullname.getText().toString(), binding.emailAddress.getText().toString(),
                    binding.phone.getText().toString(), binding.descripition.getText().toString())).get(CheckOutModelView.class);

            //CALL METHOD THAT CALLING API
            CheckOutModelView.get_data(binding.emailAddress.getText().toString(), binding.fullname.getText().toString(), binding.phone.getText().toString(), binding.descripition.getText().toString());
        }
    }
}
