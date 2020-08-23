package www.gift_vouchers.com.auth.ui.signup.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.login.ui.login;
import www.gift_vouchers.com.databinding.SignupBinding;
import www.gift_vouchers.com.utils.utils;

import static www.gift_vouchers.com.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 */
public class signup extends Fragment {

    SignupBinding binding;
    SignupModelView SignupModelView;
    String type = "0";
    String tax = "0";

    public signup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.signup, container, false);
        View view = binding.getRoot();

        //ON CLICK LISTNERS
        onClickListners();

        //SPINNER DATA
        add_spinner_data();

        return view;
    }

    //CLICK LISTNERS
    void onClickListners() {
        //SET CLICK LISTNERS BACK
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new login(), R.id.frag, getContext());
            }
        });

        //SET CLICK AUTH
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup_validation();
            }
        });
    }


    //ADD DATA TO SPINNER
    void add_spinner_data() {
        //ADD DATA TO LIST
        List<String> arraylist = new ArrayList<>();
        arraylist.add(getString(R.string.Choose_type));
        arraylist.add(getString(R.string.user));
        arraylist.add(getString(R.string.company));

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item, arraylist); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.countries.setAdapter(spinnerArrayAdapter);

        binding.countries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    type = "0";
                    binding.tax.setVisibility(View.GONE);
                } else if (i == 1) {
                    type = "3";
                    binding.tax.setVisibility(View.GONE);
                } else if (i == 2) {
                    type = "1";
                    binding.tax.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    //SIGN UP VAILDATION
    void signup_validation() {

        tax = binding.tax.getText().toString();

        if (binding.username.getText().toString().length() < 3)  //VALIDATION ON USERNAME
        {
            String username_val = getResources().getString(R.string.user_val);
            binding.username.setError(username_val);
            yoyo(R.id.username, binding.username);
        } else if (binding.email.getText().toString().length() < 6)  //VALIDATION ON EMAIL
        {
            String email_val = getResources().getString(R.string.email_val);
            binding.email.setError(email_val);
            yoyo(R.id.email, binding.email);
        } else if (binding.phoneNumber.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String phone_val = getResources().getString(R.string.phone_val);
            binding.phoneNumber.setError(phone_val);
            yoyo(R.id.phone, binding.phoneNumber);
        } else if (binding.password.getText().toString().length() < 6)  //VALIDATION ON PASSWORD
        {
            String pass_val = getResources().getString(R.string.password_val);
            binding.password.setError(pass_val);
            yoyo(R.id.password, binding.password);
        } else if (type == "0") {
            Toasty.warning(getContext(), getString(R.string.check_type), Toasty.LENGTH_SHORT).show();
        } else if ((type.equals("1")) && (tax.equals(""))) {
            Toasty.warning(getContext(), getString(R.string.inset_commerical), Toasty.LENGTH_SHORT).show();
        } else {

            //CALL PROGRESS DIALOG
            new utils().set_dialog(getContext());

            //SEND DATA TO FACTORY
            SignupModelView = new ViewModelProvider(this, new SignupModelViewFactory(getContext(),
                    binding.username.getText().toString(), binding.password.getText().toString(),
                    binding.email.getText().toString(), binding.phoneNumber.getText().toString(),
                    "dd", type)).get(SignupModelView.class);

            //CALL METHOD THAT CALLING API
            SignupModelView.get_data(binding.username.getText().toString(), type, binding.phoneNumber.getText().toString(),
                    binding.email.getText().toString(), binding.password.getText().toString(), tax,
                    "dd");

        }
    }
}
