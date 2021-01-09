package www.gift_vouchers.com.main_screen.ui.contact_us.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import www.gift_vouchers.com.NetworkLayer.Apicalls;
import www.gift_vouchers.com.NetworkLayer.NetworkInterface;
import www.gift_vouchers.com.NetworkLayer.ResponseModel;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.ContactUsBinding;
import www.gift_vouchers.com.main_screen.ui.contact_us.model.Body;
import www.gift_vouchers.com.main_screen.ui.contact_us.model.contact;
import www.gift_vouchers.com.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class contact_us extends Fragment implements NetworkInterface {
    ContactUsBinding binding;
    contact contact;
    Body[] body;

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

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        Gson gson = new Gson();
        contact = gson.fromJson("" + model.getJsonObject(), contact.class);
        body = contact.getBody();

        binding.phone.setText("" + body[0].getPhone().toString());
        binding.mail.setText("" + body[0].getEmail().toString());
        binding.address.setText("" + body[0].getAddress().toString());


    }

    @Override
    public void OnError(VolleyError error) {

    }

    @Override
    public void onStart() {
        super.onStart();
        new Apicalls(getContext(), this).ContactUsInfo();
    }
}
