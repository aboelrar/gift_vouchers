package www.gift_vouchers.com.auth.ui.verfication_code;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.change_password.change_pass;
import www.gift_vouchers.com.auth.ui.forget_pass.ui.ForgetPasswordModelViewFactory;
import www.gift_vouchers.com.databinding.VerficationCodeBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class verfication_code extends Fragment {
    VerficationCodeBinding binding;
    String txt1, txt2, txt3, txt4;
    String word;

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

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((binding.edit1.getText().toString().equals("")) || (binding.edit2.getText().toString().equals(""))
                        || (binding.edit3.getText().toString().equals("")) || (binding.edit4.getText().toString().equals(""))) {
                    Toasty.error(getContext(), getString(R.string.insert_code), Toasty.LENGTH_SHORT).show();
                } else {
                    txt1 = binding.edit1.getText().toString();
                    txt2 = binding.edit2.getText().toString();
                    txt3 = binding.edit3.getText().toString();
                    txt4 = binding.edit4.getText().toString();
                    word = txt1 + txt2 + txt3 + txt4;

                    //SEND DATA TO NEXT FRAGMENT
                    Fragment home = new change_pass();
                    Bundle bundle = new Bundle();
                    bundle.putString("email", getArguments().getString("email"));
                    bundle.putString("code", word);
                    //set Fragmentclass Arguments
                    home.setArguments(bundle);

                    ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frag, home).addToBackStack(null).commit();
                }

            }
        });

    }
}

