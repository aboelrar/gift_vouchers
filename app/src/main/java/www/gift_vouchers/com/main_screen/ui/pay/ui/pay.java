package www.gift_vouchers.com.main_screen.ui.pay.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import company.tap.gosellapi.GoSellSDK;
import company.tap.gosellapi.internal.api.callbacks.GoSellError;
import company.tap.gosellapi.internal.api.models.Authorize;
import company.tap.gosellapi.internal.api.models.Charge;
import company.tap.gosellapi.internal.api.models.PhoneNumber;
import company.tap.gosellapi.internal.api.models.Token;
import company.tap.gosellapi.open.buttons.PayButtonView;
import company.tap.gosellapi.open.controllers.SDKSession;
import company.tap.gosellapi.open.controllers.ThemeObject;
import company.tap.gosellapi.open.delegate.SessionDelegate;
import company.tap.gosellapi.open.enums.AppearanceMode;
import company.tap.gosellapi.open.enums.CardType;
import company.tap.gosellapi.open.enums.TransactionMode;
import company.tap.gosellapi.open.models.CardsList;
import company.tap.gosellapi.open.models.Customer;
import company.tap.gosellapi.open.models.Receipt;
import company.tap.gosellapi.open.models.TapCurrency;
import es.dmoral.toasty.Toasty;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.PayBinding;
import www.gift_vouchers.com.local_data.saved_data;
import www.gift_vouchers.com.local_data.send_data;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class pay extends Fragment implements SessionDelegate {

    private final int SDK_REQUEST_CODE = 1001;
    private SDKSession sdkSession;
    private PayButtonView payButtonView;
    String lan;

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

        binding.payTot.setText(getString(R.string.total_amount_20) + new saved_data().get_price(getContext()));

        Log.e("ssss", Locale.getDefault().getDisplayLanguage());

        //CHECK LANGUAGE
        if (Locale.getDefault().getDisplayLanguage().equals("العربية")) {
            lan = "ar";
        } else {
            lan = "en";
        }

        // start tap goSellSDK
        startSDK();

        //on click button
        onClick();


        return view;
    }

    private void startSDK() {
        /**
         * Required step.
         * Configure SDK with your Secret API key and App Bundle name registered with tap company.
         */
        configureApp();

        /**
         * Optional step
         * Here you can configure your app theme (Look and Feel).
         */
        configureSDKThemeObject();

        /**
         * Required step.
         * Configure SDK Session with all required data.
         */
        configureSDKSession();

        /**
         * Required step.
         * Choose between different SDK modes
         */
        configureSDKMode();

        /**
         * If you included Tap Pay Button then configure it first, if not then ignore this step.
         */
        initPayButton();

    }

    private void configureApp() {
        GoSellSDK.init(getContext(), "sk_live_mHMtli8bYfZ2gKSBN7jTnxdk", "www.gift_vouchers.com");  // to be replaced by merchant
        GoSellSDK.setLocale(lan);//  language to be set by merchant
    }

    private void configureSDKThemeObject() {

        ThemeObject.getInstance()
                .setAppearanceMode(AppearanceMode.WINDOWED_MODE)
                .setSdkLanguage(lan)

                .setHeaderTextColor(getResources().getColor(R.color.black1))
                .setHeaderTextSize(17)
                .setHeaderBackgroundColor(getResources().getColor(R.color.french_gray_new))


                .setCardInputTextColor(getResources().getColor(R.color.black))
                .setCardInputInvalidTextColor(getResources().getColor(R.color.red))
                .setCardInputPlaceholderTextColor(getResources().getColor(R.color.gray))


                .setSaveCardSwitchOffThumbTint(getResources().getColor(R.color.french_gray_new))
                .setSaveCardSwitchOnThumbTint(getResources().getColor(R.color.vibrant_green))
                .setSaveCardSwitchOffTrackTint(getResources().getColor(R.color.french_gray))
                .setSaveCardSwitchOnTrackTint(getResources().getColor(R.color.vibrant_green_pressed))

                .setScanIconDrawable(getResources().getDrawable(R.drawable.btn_card_scanner_normal))

                .setPayButtonResourceId(R.drawable.btn_pay_selector)  //btn_pay_merchant_selector


                .setPayButtonDisabledTitleColor(getResources().getColor(R.color.white))
                .setPayButtonEnabledTitleColor(getResources().getColor(R.color.white))
                .setPayButtonTextSize(14)
                .setPayButtonLoaderVisible(true)
                .setPayButtonSecurityIconVisible(true)
                .setPayButtonText("PAY BTN CAN BE VERY VERY VERY  LONGGGG LONGGGGG") // **Optional**

                // setup dialog textcolor and textsize
                .setDialogTextColor(getResources().getColor(R.color.black1))     // **Optional**
                .setDialogTextSize(17)                // **Optional**

        ;

    }

    private void configureSDKSession() {

        // Instantiate SDK Session
        if (sdkSession == null) sdkSession = new SDKSession();   //** Required **

        // pass your activity as a session delegate to listen to SDK internal payment process follow
        sdkSession.addSessionDelegate(this);    //** Required **

        // initiate PaymentDataSource
        sdkSession.instantiatePaymentDataSource();    //** Required **

        // set transaction currency associated to your account
        sdkSession.setTransactionCurrency(new TapCurrency("SR"));    //** Required **

        // Using static CustomerBuilder method available inside TAP Customer Class you can populate TAP Customer object and pass it to SDK
        sdkSession.setCustomer(getCustomer());    //** Required **

        // Set Total Amount. The Total amount will be recalculated according to provided Taxes and Shipping
        sdkSession.setAmount(new BigDecimal(new saved_data().get_price(getContext())));  //** Required **

        // Set Payment Items array list
        sdkSession.setPaymentItems(new ArrayList<>());// ** Optional ** you can pass empty array list

        //   sdkSession.setPaymentType("WEB");   //** Merchant can pass paymentType

        // Set Taxes array list
        sdkSession.setTaxes(new ArrayList<>());// ** Optional ** you can pass empty array list

        // Set Shipping array list
        sdkSession.setShipping(new ArrayList<>());// ** Optional ** you can pass empty array list

        // Post URL
        sdkSession.setPostURL(""); // ** Optional **

        // Payment Description
        sdkSession.setPaymentDescription(""); //** Optional **

        // Payment Extra Info
        sdkSession.setPaymentMetadata(new HashMap<>());// ** Optional ** you can pass empty array hash map

        // Payment Reference
        sdkSession.setPaymentReference(null); // ** Optional ** you can pass null

        // Payment Statement Descriptor
        sdkSession.setPaymentStatementDescriptor(""); // ** Optional **

        // Enable or Disable Saving Card
        sdkSession.isUserAllowedToSaveCard(true); //  ** Required ** you can pass boolean

        // Enable or Disable 3DSecure
        sdkSession.isRequires3DSecure(true);

        //Set Receipt Settings [SMS - Email ]
        sdkSession.setReceiptSettings(new Receipt(false, false)); // ** Optional ** you can pass Receipt object or null

        // Set Authorize Action
        sdkSession.setAuthorizeAction(null); // ** Optional ** you can pass AuthorizeAction object or null

        sdkSession.setDestination(null); // ** Optional ** you can pass Destinations object or null

        sdkSession.setMerchantID(null); // ** Optional ** you can pass merchant id or null

        sdkSession.setCardType(CardType.CREDIT); // ** Optional ** you can pass which cardType[CREDIT/DEBIT] you want.By default it loads all available cards for Merchant.

        // sdkSession.setDefaultCardHolderName("TEST TAP"); // ** Optional ** you can pass default CardHolderName of the user .So you don't need to type it.

        //  sdkSession.isUserAllowedToEnableCardHolderName(false); // ** Optional ** you can enable/ disable  default CardHolderName .
    }

    private void configureSDKMode() {

        /**
         * You have to choose only one Mode of the following modes:
         * Note:-
         *      - In case of using PayButton, then don't call sdkSession.start(this); because the SDK will start when user clicks the tap pay button.
         */
        //////////////////////////////////////////////////////    SDK with UI //////////////////////
        /**
         * 1- Start using  SDK features through SDK main activity (With Tap CARD FORM)
         */
        startSDKWithUI();

    }

    private void startSDKWithUI() {
        if (sdkSession != null) {
            TransactionMode trx_mode = TransactionMode.PURCHASE;
            // set transaction mode [TransactionMode.PURCHASE - TransactionMode.AUTHORIZE_CAPTURE - TransactionMode.SAVE_CARD - TransactionMode.TOKENIZE_CARD ]
            sdkSession.setTransactionMode(trx_mode);    //** Required **
            // if you are not using tap button then start SDK using the following call
            //sdkSession.start(this);
        }
    }

    private void initPayButton() {

        payButtonView = binding.payButtonId;
        if (ThemeObject.getInstance().getPayButtonFont() != null)
            payButtonView.setupFontTypeFace(ThemeObject.getInstance().getPayButtonFont());
        if (ThemeObject.getInstance().getPayButtonDisabledTitleColor() != 0 && ThemeObject.getInstance().getPayButtonEnabledTitleColor() != 0)
            payButtonView.setupTextColor(ThemeObject.getInstance().getPayButtonEnabledTitleColor(),
                    ThemeObject.getInstance().getPayButtonDisabledTitleColor());
        if (ThemeObject.getInstance().getPayButtonTextSize() != 0)
            payButtonView.getPayButton().setTextSize(ThemeObject.getInstance().getPayButtonTextSize());
        if (ThemeObject.getInstance().isPayButtSecurityIconVisible())
            payButtonView.getSecurityIconView().setVisibility(ThemeObject.getInstance().isPayButtSecurityIconVisible() ? View.VISIBLE : View.INVISIBLE);
        if (ThemeObject.getInstance().getPayButtonResourceId() != 0)
            payButtonView.setBackgroundSelector(ThemeObject.getInstance().getPayButtonResourceId());

        if (sdkSession != null) {
            TransactionMode trx_mode = sdkSession.getTransactionMode();
            if (trx_mode != null) {

                if (TransactionMode.SAVE_CARD == trx_mode || TransactionMode.SAVE_CARD_NO_UI == trx_mode) {
                    payButtonView.getPayButton().setText(getString(company.tap.gosellapi.R.string.save_card));
                } else if (TransactionMode.TOKENIZE_CARD == trx_mode || TransactionMode.TOKENIZE_CARD_NO_UI == trx_mode) {
                    payButtonView.getPayButton().setText(getString(company.tap.gosellapi.R.string.tokenize));
                } else {
                    payButtonView.getPayButton().setText(getString(company.tap.gosellapi.R.string.pay));
                }
            } else {
                configureSDKMode();
            }
            sdkSession.setButtonView(payButtonView, getActivity(), SDK_REQUEST_CODE);
        }


    }

    private Customer getCustomer() { // test customer id cus_Kh1b4220191939i1KP2506448


        return new Customer.CustomerBuilder("").email(new saved_data().get_email(getContext())).firstName(new saved_data().get_name(getContext()))
                .metadata("").phone(new PhoneNumber("02", new saved_data().get_phone(getContext())))
                .build();
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
        in.putExtra(PaymentParams.MERCHANT_EMAIL, "Giftvouchers.app@gmail.com"); //this a demo account for testing the sdk
        in.putExtra(PaymentParams.SECRET_KEY, "YoHDLk6EslWeqkYfhPd1VikBDy36k4lis2Wen2SQoP5Oa34rShsOANqdlukGyFY1IVf5X8VlX1a74ezXw2M6PckbGERfkQh2Lw7J");//Add your Secret Key Here
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


            if (data.hasExtra(PaymentParams.TOKEN) && !data.getStringExtra(PaymentParams.TOKEN).isEmpty()) {
                Log.e("Tag", data.getStringExtra(PaymentParams.TOKEN));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_EMAIL));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_PASSWORD));
                Toasty.success(getContext(), "Successfull Payment", Toasty.LENGTH_SHORT).show();

                send_data.check_card(getContext(), "0");

                //SEND DATA TO FACTORY
                PayModelView view_model = ViewModelProviders.of(this).get(PayModelView.class);

                //CALL METHOD THAT CALLING API
                view_model.get_data(getContext(), getArguments().getString("id"));

            }
        } else if (requestCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toasty.error(getContext(), "Failed", Toasty.LENGTH_SHORT).show();
        } else {
            Toasty.error(getContext(), "Failed Payment", Toasty.LENGTH_SHORT).show();
        }
    }

    @Override
    public void paymentSucceed(@NonNull Charge charge) {
        send_data.check_card(getContext(), "0");

        //SEND DATA TO FACTORY
        PayModelView view_model = ViewModelProviders.of(this).get(PayModelView.class);

        //CALL METHOD THAT CALLING API
        view_model.get_data(getContext(), getArguments().getString("id"));


    }

    @Override
    public void paymentFailed(@Nullable Charge charge) {
        Toasty.error(getContext(), "Failed Payment", Toasty.LENGTH_LONG).show();
    }

    @Override
    public void authorizationSucceed(@NonNull Authorize authorize) {

    }

    @Override
    public void authorizationFailed(Authorize authorize) {

    }

    @Override
    public void cardSaved(@NonNull Charge charge) {

    }

    @Override
    public void cardSavingFailed(@NonNull Charge charge) {

    }

    @Override
    public void cardTokenizedSuccessfully(@NonNull Token token) {

    }

    @Override
    public void savedCardsList(@NonNull CardsList cardsList) {

    }

    @Override
    public void sdkError(@Nullable GoSellError goSellError) {

    }

    @Override
    public void sessionIsStarting() {

    }

    @Override
    public void sessionHasStarted() {

    }

    @Override
    public void sessionCancelled() {

    }

    @Override
    public void sessionFailedToStart() {

    }

    @Override
    public void invalidCardDetails() {

    }

    @Override
    public void backendUnknownError(String message) {

    }

    @Override
    public void invalidTransactionMode() {

    }

    @Override
    public void invalidCustomerID() {

    }

    @Override
    public void userEnabledSaveCardOption(boolean saveCardEnabled) {

    }

    void onClick()
    {
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }
}
