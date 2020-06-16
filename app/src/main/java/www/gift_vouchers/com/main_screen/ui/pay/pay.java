package www.gift_vouchers.com.main_screen.ui.pay;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.PayBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class pay extends Fragment {
    PayBinding binding;

    public pay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.pay, container, false);
        View view = binding.getRoot();

        click_listners();

        return view;
    }

    //CLICK LISTNERS
    void click_listners()
    {
        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading loading = new loading();
                loading.dialog(getContext(),R.layout.successful_login,.80);
            }
        });
    }
}
