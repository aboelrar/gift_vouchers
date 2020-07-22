package www.gift_vouchers.com.main_screen.ui.select_gift_design.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.login.ui.login;
import www.gift_vouchers.com.databinding.SelectDesignBinding;
import www.gift_vouchers.com.local_data.send_data;
import www.gift_vouchers.com.main_screen.ui.cart.cart;
import www.gift_vouchers.com.main_screen.ui.details.ui.company_details;
import www.gift_vouchers.com.main_screen.ui.select_gift_design.model.gift_img_list;
import www.gift_vouchers.com.main_screen.ui.select_gift_design.pattern.gifts_img_adapter;
import www.gift_vouchers.com.utils.utils;
import www.gift_vouchers.com.utils.utils_adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class select_design extends Fragment {
    SelectDesignBinding binding;

    public select_design() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.select_design, container, false);
        View view = binding.getRoot();

        //GET DATA
        getData();

        //CLICK LISTNERS
        click_listner();

        return view;
    }


    //GET DATA
    void getData() {

        //SEND DATA TO FACTORY
        DesignsModelView DesignsModelView = new ViewModelProvider(select_design.this,
                new DesignsModelViewFactory(getContext())).get(DesignsModelView.class);

        //CALL METHOD THAT CALLING API
        DesignsModelView.get_data();

        //SET DATA INTO ARRAYLIST
        DesignsModelView.MutableLiveData.observe(this, new Observer<ArrayList<gift_img_list>>() {
            @Override
            public void onChanged(ArrayList<gift_img_list> gift_img_lists) {
                binding.loading.setVisibility(View.GONE); //SET VISABILITY GONE
                new utils_adapter().Adapter(binding.giftList, new gifts_img_adapter(getContext(), gift_img_lists), getContext());
            }
        });

    }

    //SET ON CLICK LISTNERS
    void click_listner() {
        //GO TO CART
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gifts_img_adapter.choose_color.equals("1")) {
                    send_data.check_card(getContext(), "1");
                    new utils().Replace_Fragment(new cart(), R.id.frag, getContext());

                    //SET IT ZERO
                    gifts_img_adapter.choose_color = "0";
                } else {
                    Toasty.warning(getContext(), getString(R.string.select_design), Toasty.LENGTH_SHORT).show();
                }

            }
        });

        //GO TO BACK
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }
}
