package www.gift_vouchers.com.main_screen.ui.select_gift_design.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.login.ui.login;
import www.gift_vouchers.com.databinding.SelectDesignBinding;
import www.gift_vouchers.com.main_screen.ui.cart.cart;
import www.gift_vouchers.com.main_screen.ui.details.ui.company_details;
import www.gift_vouchers.com.main_screen.ui.select_gift_design.model.gift_img_list;
import www.gift_vouchers.com.main_screen.ui.select_gift_design.pattern.gifts_img_adapter;
import www.gift_vouchers.com.utils.utils;
import www.gift_vouchers.com.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class select_design extends Fragment {
    SelectDesignBinding binding;

    public select_design() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.select_design, container, false);
        View view = binding.getRoot();

        //GET DATA
        getData();

        //CLICK LISTNERS
        click_listner();

        return view;
    }

    //GET DATA
    void getData() {
        ArrayList<gift_img_list> mylist = new ArrayList<>();
        mylist.add(new gift_img_list("1", R.drawable.gift_img));
        mylist.add(new gift_img_list("1", R.drawable.gift_img));


        new utils_adapter().Adapter(binding.giftList, new gifts_img_adapter(getContext(), mylist), getContext());
    }


    //SET ON CLICK LISTNERS
    void click_listner() {
        //GO TO CART
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new cart(), R.id.frag, getContext());
            }
        });

        //GO TO BACK
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new company_details(), R.id.frag, getContext());
            }
        });
    }
}
