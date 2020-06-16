package www.gift_vouchers.com.main_screen.ui.compainies.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.CompainiesBinding;
import www.gift_vouchers.com.main_screen.ui.compainies.model.companies_list;
import www.gift_vouchers.com.main_screen.ui.compainies.pattern.companies_adapter;
import www.gift_vouchers.com.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class compainies extends Fragment {
    CompainiesBinding binding;

    public compainies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.compainies, container, false);
        View view = binding.getRoot();

        //GET DATA
        getData();

        return view;
    }

    //GET DATA
    void getData() {
        ArrayList<companies_list> mylist = new ArrayList<>();
        mylist.add(new companies_list("1", "Souq Gift Voucher online shopping", R.drawable.logo));
        mylist.add(new companies_list("1", "Souq Gift Voucher online shopping", R.drawable.logo));
        mylist.add(new companies_list("1", "Souq Gift Voucher online shopping", R.drawable.logo));
        mylist.add(new companies_list("1", "Souq Gift Voucher online shopping", R.drawable.logo));
        mylist.add(new companies_list("1", "Souq Gift Voucher online shopping", R.drawable.logo));

        new utils_adapter().griddAdapters(binding.companiesList, new companies_adapter(getContext(), mylist), getContext(), 2);
    }
}
