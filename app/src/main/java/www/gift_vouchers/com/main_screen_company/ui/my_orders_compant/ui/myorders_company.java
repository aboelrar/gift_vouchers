package www.gift_vouchers.com.main_screen_company.ui.my_orders_compant.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.MyordersBinding;
import www.gift_vouchers.com.databinding.MyordersCompanyBinding;
import www.gift_vouchers.com.main_screen_company.ui.my_orders_compant.model.my_order_list;
import www.gift_vouchers.com.main_screen_company.ui.my_orders_compant.pattern.myorders_adapter;
import www.gift_vouchers.com.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class myorders_company extends Fragment {
    MyordersCompanyBinding binding;

    public myorders_company() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.myorders_company, container, false);
        View view = binding.getRoot();

        //GET DATA
        getData();

        return view;
    }


    //GET DATA
    void getData() {

        //SEND DATA TO FACTORY
        MyOrdersModelView MyOrdersModelView = new ViewModelProvider(myorders_company.this,
                new MyOrdersModelViewFactory(getContext())).get(MyOrdersModelView.class);

        //CALL METHOD THAT CALLING API
        MyOrdersModelView.get_data();

        //SET DATA INTO ARRAYLIST
        MyOrdersModelView.MutableLiveData.observe(myorders_company.this, new Observer<ArrayList<my_order_list>>() {
            @Override
            public void onChanged(ArrayList<my_order_list> my_order_lists) {
                new utils_adapter().Adapter(binding.myorderList, new myorders_adapter(getContext(), my_order_lists), getContext());
            }
        });
    }

}
