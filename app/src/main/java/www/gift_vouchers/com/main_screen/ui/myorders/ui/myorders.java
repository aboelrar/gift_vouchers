package www.gift_vouchers.com.main_screen.ui.myorders.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.MyordersBinding;
import www.gift_vouchers.com.main_screen.ui.compainies.model.companies_list;
import www.gift_vouchers.com.main_screen.ui.compainies.pattern.companies_adapter;
import www.gift_vouchers.com.main_screen.ui.myorders.model.my_order_list;
import www.gift_vouchers.com.main_screen.ui.myorders.pattern.myorders_adapter;
import www.gift_vouchers.com.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class myorders extends Fragment {
    MyordersBinding binding;

    public myorders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.myorders, container, false);
        View view = binding.getRoot();

        //GET DATA
        getData();

        return view;
    }


    //GET DATA
    void getData() {
        ArrayList<my_order_list> mylist = new ArrayList<>();
        mylist.add(new my_order_list("1", R.drawable.logo, "Souq Gift Voucher", "0ad4eaxfpls", "100 SAR"));
        mylist.add(new my_order_list("1", R.drawable.logo, "Souq Gift Voucher", "0ad4eaxfpls", "100 SAR"));
        mylist.add(new my_order_list("1", R.drawable.logo, "Souq Gift Voucher", "0ad4eaxfpls", "100 SAR"));
        mylist.add(new my_order_list("1", R.drawable.logo, "Souq Gift Voucher", "0ad4eaxfpls", "100 SAR"));


        new utils_adapter().Adapter(binding.myorderList, new myorders_adapter(getContext(), mylist), getContext());
    }

}
