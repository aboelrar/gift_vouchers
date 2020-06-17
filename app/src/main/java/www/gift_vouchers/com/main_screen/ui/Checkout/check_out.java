package www.gift_vouchers.com.main_screen.ui.Checkout;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.CheckOutBinding;
import www.gift_vouchers.com.main_screen.ui.pay.pay;
import www.gift_vouchers.com.main_screen.ui.select_gift_design.ui.select_design;
import www.gift_vouchers.com.utils.utils;

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
    void click_listner()
    {
        //SET ON CLICK PAY
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new pay(), R.id.frag, getContext());
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
}
