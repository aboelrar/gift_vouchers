package www.gift_vouchers.com.main_screen.ui.cart;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.login.ui.login;
import www.gift_vouchers.com.databinding.CartBinding;
import www.gift_vouchers.com.main_screen.ui.Checkout.check_out;
import www.gift_vouchers.com.main_screen.ui.select_gift_design.ui.select_design;
import www.gift_vouchers.com.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class cart extends Fragment {
    CartBinding binding;

    public cart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.cart, container, false);
        View view = binding.getRoot();

        click_listners();

        return view;
    }

    //CLICK LISTNERS
    void click_listners() {

        //CHECKOUT
      binding.checkout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              new utils().Replace_Fragment(new check_out(), R.id.frag, getContext());
          }
      });

      //BACK
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }
}
