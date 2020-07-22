package www.gift_vouchers.com.main_screen.ui.compainies.ui;

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

        //SEND DATA TO FACTORY
        CompaniesModelView CompaniesModelView = new ViewModelProvider(compainies.this,
                new CompaniesModelViewFactory(getContext())).get(CompaniesModelView.class);

        //CALL METHOD THAT CALLING API
        CompaniesModelView.get_data();

        //SET DATA INTO ARRAYLIST
        CompaniesModelView.MutableLiveData.observe(this, new Observer<ArrayList<companies_list>>() {
            @Override
            public void onChanged(ArrayList<companies_list> companies_lists) {
                new utils_adapter().griddAdapters(binding.companiesList, new companies_adapter(getContext(), companies_lists), getContext(), 2);
            }
        });

    }
}
