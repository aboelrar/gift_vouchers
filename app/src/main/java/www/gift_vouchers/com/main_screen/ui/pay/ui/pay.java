package www.gift_vouchers.com.main_screen.ui.pay.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.paytabs.paytabs_sdk.payment.ui.activities.PayTabActivity;
import com.paytabs.paytabs_sdk.utils.PaymentParams;

import java.math.BigDecimal;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.PayBinding;
import www.gift_vouchers.com.local_data.saved_data;
import www.gift_vouchers.com.local_data.send_data;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class pay extends Fragment {
    PayBinding binding;
    //PAYPAL CONFIGRATIONS
    private static final int PAYPAL_REQUEST_CODE = 3;
    private static PayPalConfiguration payPalConfiguration = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(Config.CLIENT_ID);

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
    void click_listners() {

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        binding.paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paypal_process_payment();
            }
        });

        binding.visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visa_process_payment();
            }
        });

    }


    //PAYMENT PROCESS PAYPAL
    private void paypal_process_payment() {

        String pay_konsil = getResources().getString(R.string.pay_gifts);

        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(new saved_data().get_price(getContext())),
                "EUR",
                pay_konsil, PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(getContext(), PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);

        startActivityForResult(intent, PAYPAL_REQUEST_CODE);

    }

    //PAYMENT PROCESS VISA
    private void visa_process_payment() {
        Intent in = new Intent(getContext(), PayTabActivity.class);
        in.putExtra(PaymentParams.MERCHANT_EMAIL, "amrngal@gmail.com"); //this a demo account for testing the sdk
        in.putExtra(PaymentParams.SECRET_KEY, "fw4lLpRINazU47Jybez8cWOz8u5b28HiFMDqbu9ZEIUJQEh1SQa4NZDVuJ7wRPOwBrXNV6MAOGjFkBHdsRhfRValEiRv7G2I8Zv4");//Add your Secret Key Here
        in.putExtra(PaymentParams.LANGUAGE, PaymentParams.ENGLISH);
        in.putExtra(PaymentParams.TRANSACTION_TITLE, "Test Paytabs android library");
        in.putExtra(PaymentParams.AMOUNT, Double.parseDouble(new saved_data().get_price(getContext())));

        in.putExtra(PaymentParams.CURRENCY_CODE, "EUR");
        in.putExtra(PaymentParams.CUSTOMER_PHONE_NUMBER, "009733");
        in.putExtra(PaymentParams.CUSTOMER_EMAIL, "customer-email@example.com");
        in.putExtra(PaymentParams.ORDER_ID, "123456");
        in.putExtra(PaymentParams.PRODUCT_NAME, "Product 1, Product 2");

//Billing Address
        in.putExtra(PaymentParams.ADDRESS_BILLING, "Flat 1,Building 123, Road 2345");
        in.putExtra(PaymentParams.CITY_BILLING, "Manama");
        in.putExtra(PaymentParams.STATE_BILLING, "Manama");
        in.putExtra(PaymentParams.COUNTRY_BILLING, "BHR");
        in.putExtra(PaymentParams.POSTAL_CODE_BILLING, "00973"); //Put Country Phone code if Postal code not available '00973'

//Shipping Address
        in.putExtra(PaymentParams.ADDRESS_SHIPPING, "Flat 1,Building 123, Road 2345");
        in.putExtra(PaymentParams.CITY_SHIPPING, "Manama");
        in.putExtra(PaymentParams.STATE_SHIPPING, "Manama");
        in.putExtra(PaymentParams.COUNTRY_SHIPPING, "BHR");
        in.putExtra(PaymentParams.POSTAL_CODE_SHIPPING, "00973"); //Put Country Phone code if Postal code not available '00973'

//Payment Page Style
        in.putExtra(PaymentParams.PAY_BUTTON_COLOR, "#2474bc");

//Tokenization
        in.putExtra(PaymentParams.IS_TOKENIZATION, true);
//PreAuth
        in.putExtra(PaymentParams.IS_PREAUTH, false);

        startActivityForResult(in, PaymentParams.PAYMENT_REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                //VALIDATE ON ALL ITEMS
                if (paymentConfirmation != null) {

                    send_data.check_card(getContext(), "0");

                    //SEND DATA TO FACTORY
                    PayModelView view_model = ViewModelProviders.of(this).get(PayModelView.class);

                    //CALL METHOD THAT CALLING API
                    view_model.get_data(getContext(), getArguments().getString("id"));



                }
            }

        } else if (resultCode == RESULT_OK && requestCode == PaymentParams.PAYMENT_REQUEST_CODE) {
            Log.e("Tagsss", data.getStringExtra(PaymentParams.RESPONSE_CODE));
            Log.e("Tagbbb", data.getStringExtra(PaymentParams.TRANSACTION_ID));
            send_data.check_card(getContext(), "0");

            //SEND DATA TO FACTORY
            PayModelView view_model = ViewModelProviders.of(this).get(PayModelView.class);

            //CALL METHOD THAT CALLING API
            view_model.get_data(getContext(), getArguments().getString("id"));

            if (data.hasExtra(PaymentParams.TOKEN) && !data.getStringExtra(PaymentParams.TOKEN).isEmpty()) {
                Log.e("Tag", data.getStringExtra(PaymentParams.TOKEN));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_EMAIL));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_PASSWORD));
                Toasty.success(getContext(), "Successfull Payment", Toasty.LENGTH_SHORT).show();
            }
        } else if (requestCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toasty.error(getContext(), "Failed", Toasty.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.paypal.setChecked(false);
        binding.visa.setChecked(false);
    }
}
