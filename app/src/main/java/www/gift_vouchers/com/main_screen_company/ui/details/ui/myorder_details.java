package www.gift_vouchers.com.main_screen_company.ui.details.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.MyorderDetailsBinding;
import www.gift_vouchers.com.main_screen.ui.details.ui.CompanyDetailsModelView;
import www.gift_vouchers.com.main_screen.ui.details.ui.CompanyDetailsModelViewFactory;
import www.gift_vouchers.com.main_screen.ui.details.ui.company_details;
import www.gift_vouchers.com.main_screen_company.ui.details.model.myorder_company_detailsBody;
import www.gift_vouchers.com.main_screen_company.ui.details.model.myorder_company_detailsCategory;
import www.gift_vouchers.com.main_screen_company.ui.details.model.myorder_company_detailsCompany;
import www.gift_vouchers.com.main_screen_company.ui.details.model.myorder_company_detailsReceiver;
import www.gift_vouchers.com.main_screen_company.ui.details.model.myorder_company_detailsRootClass;

/**
 * A simple {@link Fragment} subclass.
 */
public class myorder_details extends Fragment {
    MyorderDetailsBinding binding;
    myorder_company_detailsBody myorder_company_detailsBody;
    myorder_company_detailsReceiver myorder_company_detailsReceiver;
    myorder_company_detailsCompany myorder_company_detailsCompany;
    myorder_company_detailsCategory myorder_company_detailsCategory;

    public myorder_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.myorder_details, container, false);
        View view = binding.getRoot();

        set_data();

        return view;
    }


    //SET DATA TEXT
    void set_data() {
        //SEND DATA TO FACTORY
        CompanyOrderDetailsModelView CompanyOrderDetailsModelView = new ViewModelProvider(myorder_details.this,
                new CompanyOrderDetailsModelViewFactory(getContext(), getArguments().getString("id"))).get(CompanyOrderDetailsModelView.class);

        //CALL METHOD THAT CALLING API
        CompanyOrderDetailsModelView.get_data();

        CompanyOrderDetailsModelView.MutableLiveData.observe(myorder_details.this, new Observer<myorder_company_detailsRootClass>() {
            @Override
            public void onChanged(myorder_company_detailsRootClass myorder_company_detailsRootClass) {

                //STOP PROGRSSBAR
                binding.pg.setVisibility(View.GONE);

                //DEFINE ALL VARS
                myorder_company_detailsBody = myorder_company_detailsRootClass.getBody();
                myorder_company_detailsReceiver = myorder_company_detailsBody.getReceiver();
                myorder_company_detailsCompany = myorder_company_detailsBody.getCompany();
                myorder_company_detailsCategory = myorder_company_detailsBody.getCategory();


                //SET DATA
                binding.emailReciever.setText(myorder_company_detailsReceiver.getEmail());
                binding.to.setText(myorder_company_detailsReceiver.getUsername());
                binding.title.setText(myorder_company_detailsReceiver.getUsername());
                binding.code.setText(myorder_company_detailsBody.getOrderNumber());
                binding.price.setText(myorder_company_detailsBody.getTotalPrice());
                binding.phone.setText("" + myorder_company_detailsReceiver.getPhone());
                binding.type.setText(myorder_company_detailsCategory.getCategory());
                binding.secPrice.setText(myorder_company_detailsBody.getTotalPrice());
                binding.title.setText(myorder_company_detailsCompany.getUsername() + " " + getString(R.string.gift_voucher));
                Glide.with(getContext()).load(myorder_company_detailsCompany.getPicture()).into(binding.logo);
            }
        });
    }
}
