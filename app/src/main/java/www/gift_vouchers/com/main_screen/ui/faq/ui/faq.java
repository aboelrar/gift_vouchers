package www.gift_vouchers.com.main_screen.ui.faq.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.FaqBinding;
import www.gift_vouchers.com.main_screen.ui.faq.model.faq_model;
import www.gift_vouchers.com.main_screen.ui.faq.pattern.faq_adapter;
import www.gift_vouchers.com.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class faq extends Fragment {
    FaqBinding binding;

    public faq() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.faq, container, false);
        View view = binding.getRoot();

        click_listners();

        get_data();

        return view;
    }

    private void click_listners() {
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }

    //GET DATA
    void get_data() {
        FaqModelView FaqModelView = ViewModelProviders.of(this).get(FaqModelView.class);
        FaqModelView.get_data(getContext());

        FaqModelView.faqModelMutableLiveData.observe(this, new Observer<ArrayList<faq_model>>() {
            @Override
            public void onChanged(ArrayList<faq_model> faq_models) {
                //DISMISS DIALOG BAR
                binding.loading.setVisibility(View.GONE);

                new utils_adapter().Adapter(binding.faqList, new faq_adapter(getContext(), faq_models), getContext());
            }
        });

    }
}
