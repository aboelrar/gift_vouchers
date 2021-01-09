package www.gift_vouchers.com.main_screen.ui.pay.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import www.gift_vouchers.com.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class payment_tap extends Fragment {

    public payment_tap() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.payment_tap, container, false);
    }
}
