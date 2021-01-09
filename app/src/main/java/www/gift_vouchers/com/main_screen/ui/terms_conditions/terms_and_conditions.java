package www.gift_vouchers.com.main_screen.ui.terms_conditions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import www.gift_vouchers.com.NetworkLayer.Apicalls;
import www.gift_vouchers.com.NetworkLayer.NetworkInterface;
import www.gift_vouchers.com.NetworkLayer.ResponseModel;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.TermsAndConditionsBinding;
import www.gift_vouchers.com.main_screen.ui.contact_us.model.Body;

/**
 * A simple {@link Fragment} subclass.
 */
public class terms_and_conditions extends Fragment implements NetworkInterface {
    TermsAndConditionsBinding binding;
    www.gift_vouchers.com.main_screen.ui.contact_us.model.contact contact;
    Body[] body;

    public terms_and_conditions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.terms_and_conditions, container, false);
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
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        Gson gson = new Gson();
        contact = gson.fromJson("" + model.getJsonObject(), www.gift_vouchers.com.main_screen.ui.contact_us.model.contact.class);
        body = contact.getBody();

        binding.terms.setText(""+body[1].getText());

        binding.progrssbar.setVisibility(View.GONE);

    }

    @Override
    public void OnError(VolleyError error) {
       binding.progrssbar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        new Apicalls(getContext(), this).ContactUsInfo();
    }
}
