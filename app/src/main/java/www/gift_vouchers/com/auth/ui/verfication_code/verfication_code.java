package www.gift_vouchers.com.auth.ui.verfication_code;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.forget_pass.ui.ForgetPasswordModelView;
import www.gift_vouchers.com.auth.ui.forget_pass.ui.ForgetPasswordModelViewFactory;
import www.gift_vouchers.com.databinding.VerficationCodeBinding;
import www.gift_vouchers.com.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class verfication_code extends Fragment {
    VerficationCodeBinding binding;
    String txt1, txt2, txt3, txt4;
    String word;
    VerfiyCodeModelView VerfiyCodeModelView;

    public verfication_code() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.verfication_code, container, false);

        View view = binding.getRoot();

        move_next_num();

        onclick();

        VerfiyCodeModelView = ViewModelProviders.of(verfication_code.this).get(VerfiyCodeModelView.class);

        return view;
    }

    void move_next_num() {
        //TEXT ONE
        binding.edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.edit1.clearFocus();
                    binding.edit2.requestFocus();
                    binding.edit2.setCursorVisible(true);

                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //TEXT TWO
        binding.edit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.edit2.clearFocus();
                    binding.edit3.requestFocus();
                    binding.edit3.setCursorVisible(true);

                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //TEXT THREE
        binding.edit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.edit3.clearFocus();
                    binding.edit4.requestFocus();
                    binding.edit4.setCursorVisible(true);

                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    void onclick() {

        //SEND FORGET PASS
        binding.resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //OPEN DIALOG
                new utils().set_dialog(getContext());

                //SEND DATA TO FACTORY
                ForgetPasswordModelView ForgetPasswordModelView = new ViewModelProvider(verfication_code.this, new ForgetPasswordModelViewFactory(getContext(),
                        getArguments().getString("email"))).get(ForgetPasswordModelView.class);

                //CALL METHOD THAT CALLING API
                ForgetPasswordModelView.get_data(getArguments().getString("email"),1);
            }
        });

        //SEND DATA
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //OPEN DIALOG
                new utils().set_dialog(getContext());

                if ((binding.edit1.getText().toString().equals("")) || (binding.edit2.getText().toString().equals(""))
                        || (binding.edit3.getText().toString().equals("")) || (binding.edit4.getText().toString().equals(""))) {
                    Toasty.error(getContext(), getString(R.string.insert_code), Toasty.LENGTH_SHORT).show();
                } else {
                    txt1 = binding.edit1.getText().toString();
                    txt2 = binding.edit2.getText().toString();
                    txt3 = binding.edit3.getText().toString();
                    txt4 = binding.edit4.getText().toString();
                    word = txt1 + txt2 + txt3 + txt4;


                    VerfiyCodeModelView.get_data(getContext(), word, getArguments().getString("email"));
                }

            }
        });

    }
}

