package www.gift_vouchers.com.main_screen.ui.settings;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.SettingsBinding;
import www.gift_vouchers.com.main_screen.ui.about_gift_voucher.about_gift_voucher;
import www.gift_vouchers.com.main_screen.ui.contact_us.ui.contact_us;
import www.gift_vouchers.com.main_screen.ui.faq.ui.faq;
import www.gift_vouchers.com.main_screen.ui.terms_conditions.terms_and_conditions;
import www.gift_vouchers.com.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class settings extends Fragment {

    SettingsBinding binding;

    public settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
                inflater, R.layout.settings, container, false);
        View view = binding.getRoot();

        click_listners();

        return view;

    }

    //VOID CLICK LISTNERS
    void click_listners()
    {
        //GO TO ABOUT
        binding.about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new about_gift_voucher(), R.id.frag, getContext());
            }
        });

        //GO TO TERMS
        binding.terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new terms_and_conditions(), R.id.frag, getContext());
            }
        });

        //GO TO FAQ
        binding.faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new faq(), R.id.frag, getContext());
            }
        });

        //GO TO CONTACT US
        binding.contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new contact_us(), R.id.frag, getContext());
            }
        });

        //GO TO BACK
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }
}
