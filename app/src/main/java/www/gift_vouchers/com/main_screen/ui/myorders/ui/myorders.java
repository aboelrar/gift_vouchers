package www.gift_vouchers.com.main_screen.ui.myorders.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.MyordersBinding;
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

        //SEND DATA TO FACTORY
        MyOrdersModelView MyOrdersModelView = new ViewModelProvider(myorders.this,
                new MyOrdersModelViewFactory(getContext())).get(MyOrdersModelView.class);

        //CALL METHOD THAT CALLING API
        MyOrdersModelView.get_data();

        //SET DATA INTO ARRAYLIST
        MyOrdersModelView.MutableLiveData.observe(myorders.this, new Observer<ArrayList<my_order_list>>() {
            @Override
            public void onChanged(ArrayList<my_order_list> my_order_lists) {

                if (my_order_lists.size() == 0) {
                    binding.noOrders.setVisibility(View.VISIBLE);
                    new utils_adapter().Adapter(binding.myorderList, new myorders_adapter(getContext(), my_order_lists), getContext());
                } else {
                    binding.noOrders.setVisibility(View.GONE);
                    new utils_adapter().Adapter(binding.myorderList, new myorders_adapter(getContext(), my_order_lists), getContext());

                }

            }
        });
    }

}
