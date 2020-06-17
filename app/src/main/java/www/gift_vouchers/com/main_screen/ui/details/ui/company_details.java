package www.gift_vouchers.com.main_screen.ui.details.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.CompanyDetailsBinding;
import www.gift_vouchers.com.main_screen.ui.select_gift_design.ui.select_design;
import www.gift_vouchers.com.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class company_details extends Fragment {
    CompanyDetailsBinding binding;

    public company_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.company_details, container, false);
        View view = binding.getRoot();

        //CLICK LISTNERS
        click_listners();

        return view;
    }

    //SET ON CLICK LISTNERS
    void click_listners() {

        //SET ON CLICK NEXT
     binding.next.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             new utils().Replace_Fragment(new select_design(), R.id.frag, getContext());
         }
     });

     //SET ON CLICK BACK
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }
}
