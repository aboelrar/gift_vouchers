package www.gift_vouchers.com.main_screen.ui.contact_us.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.ContactUsBinding;
import www.gift_vouchers.com.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class contact_us extends Fragment {
    ContactUsBinding binding;

    public contact_us() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.contact_us, container, false);
        View view = binding.getRoot();

        click_listners();

        return view;
    }

    private void click_listners() {
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OPEN DIALOG
                new utils().set_dialog(getContext());

                //SEND DATA TO FACTORY
                ContactModelView view_model = ViewModelProviders.of(contact_us.this).get(ContactModelView.class);

                //CALL METHOD THAT CALLING API
                view_model.get_data(getContext(), binding.suggestion.getText().toString());
            }
        });
    }
}
