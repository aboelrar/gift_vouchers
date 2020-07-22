package www.gift_vouchers.com.main_screen.ui.cart;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.login.ui.login;
import www.gift_vouchers.com.databinding.CartBinding;
import www.gift_vouchers.com.local_data.saved_data;
import www.gift_vouchers.com.local_data.send_data;
import www.gift_vouchers.com.main_screen.ui.Checkout.check_out;
import www.gift_vouchers.com.main_screen.ui.select_gift_design.ui.select_design;
import www.gift_vouchers.com.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class cart extends Fragment {
    CartBinding binding;

    public cart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.cart, container, false);
        View view = binding.getRoot();

        click_listners();

        set_data();

        check_visability();

        delete_cart();

        return view;
    }

    //CLICK LISTNERS
    void click_listners() {

        //CHECKOUT
        binding.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new check_out(), R.id.frag, getContext());
            }
        });

        //BACK
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }

    //SET DATA
    void set_data() {
        binding.price.setText(new saved_data().get_price(getContext()));
        Glide.with(getContext()).load(new saved_data().get_logo(getContext())).into(binding.logo);
        Glide.with(getContext()).load(new saved_data().get_design(getContext())).into(binding.giftImg);
        binding.cartName.setText(new saved_data().get_order_name(getContext()) + " " + getString(R.string.gift_voucher));

        //CHECK TYPE BACKGROUND
        if (new saved_data().get_type(getContext()).equals(getString(R.string.gold))) {
            binding.type.setBackgroundResource(R.drawable.unsecleted_gold);
            binding.type.setText(R.string.gold);
        } else if (new saved_data().get_type(getContext()).equals(getString(R.string.silver))) {
            binding.type.setBackgroundResource(R.drawable.unselected_silver);
            binding.type.setText(R.string.silver);
        } else if (new saved_data().get_type(getContext()).equals(getString(R.string.platinum))) {
            binding.type.setBackgroundResource(R.drawable.unselected_platinum);
            binding.type.setText(R.string.platinum);
        }
    }

    //DELETE CART
    void delete_cart() {
        binding.trashM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle(getString(R.string.delete_item))
                        .setMessage(getString(R.string.sure))

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operationa
                                send_data.check_card(getContext(), "0");
                                check_visability();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(getString(R.string.no), null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    //CHECK VISABILITY
    void check_visability() {

        if (new saved_data().get_cart(getContext()).equals("0")) {
            binding.layout.setVisibility(View.GONE);
            binding.cartHolder.setVisibility(View.VISIBLE);
        }

    }
}
