package www.gift_vouchers.com.main_screen.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

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
import www.gift_vouchers.com.local_data.saved_data;

public class payment_page extends AppCompatActivity implements SessionDelegate {

    private final int SDK_REQUEST_CODE = 1001;
    private SDKSession sdkSession;
    private PayButtonView payButtonView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_page);

        // start tap goSellSDK
        startSDK();
    }

    private void startSDK(){
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

    private void configureApp(){
        GoSellSDK.init(this, "sk_test_kovrMB0mupFJXfNZWx6Etg5y","company.tap.goSellSDKExample");  // to be replaced by merchant
        GoSellSDK.setLocale("en");//  language to be set by merchant
    }

    private void configureSDKThemeObject() {

        ThemeObject.getInstance()
                .setAppearanceMode(AppearanceMode.WINDOWED_MODE)
                .setSdkLanguage("en")

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
        if(sdkSession==null) sdkSession = new SDKSession();   //** Required **

        // pass your activity as a session delegate to listen to SDK internal payment process follow
        sdkSession.addSessionDelegate(this);    //** Required **

        // initiate PaymentDataSource
        sdkSession.instantiatePaymentDataSource();    //** Required **

        // set transaction currency associated to your account
        sdkSession.setTransactionCurrency(new TapCurrency("KWD"));    //** Required **

        // Using static CustomerBuilder method available inside TAP Customer Class you can populate TAP Customer object and pass it to SDK
        sdkSession.setCustomer(getCustomer());    //** Required **

        // Set Total Amount. The Total amount will be recalculated according to provided Taxes and Shipping
        sdkSession.setAmount(new BigDecimal(new saved_data().get_price(payment_page.this)));  //** Required **

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
        sdkSession.setReceiptSettings(new Receipt(false,false)); // ** Optional ** you can pass Receipt object or null

        // Set Authorize Action
        sdkSession.setAuthorizeAction(null); // ** Optional ** you can pass AuthorizeAction object or null

        sdkSession.setDestination(null); // ** Optional ** you can pass Destinations object or null

        sdkSession.setMerchantID(null); // ** Optional ** you can pass merchant id or null

        sdkSession.setCardType(CardType.CREDIT); // ** Optional ** you can pass which cardType[CREDIT/DEBIT] you want.By default it loads all available cards for Merchant.

        // sdkSession.setDefaultCardHolderName("TEST TAP"); // ** Optional ** you can pass default CardHolderName of the user .So you don't need to type it.

        //  sdkSession.isUserAllowedToEnableCardHolderName(false); // ** Optional ** you can enable/ disable  default CardHolderName .
    }

    private void configureSDKMode(){

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

    private void startSDKWithUI(){
        if(sdkSession!=null){
            TransactionMode trx_mode = TransactionMode.PURCHASE;
            // set transaction mode [TransactionMode.PURCHASE - TransactionMode.AUTHORIZE_CAPTURE - TransactionMode.SAVE_CARD - TransactionMode.TOKENIZE_CARD ]
            sdkSession.setTransactionMode(trx_mode);    //** Required **
            // if you are not using tap button then start SDK using the following call
            //sdkSession.start(this);
        }
    }

    private void initPayButton() {

        payButtonView = findViewById(R.id.payButtonId);
        if(ThemeObject.getInstance().getPayButtonFont()!=null)
            payButtonView.setupFontTypeFace(ThemeObject.getInstance().getPayButtonFont());
        if( ThemeObject.getInstance().getPayButtonDisabledTitleColor() !=0 && ThemeObject.getInstance().getPayButtonEnabledTitleColor() !=0 )
            payButtonView.setupTextColor(ThemeObject.getInstance().getPayButtonEnabledTitleColor(),
                    ThemeObject.getInstance().getPayButtonDisabledTitleColor());
        if( ThemeObject.getInstance().getPayButtonTextSize()!=0 )
            payButtonView.getPayButton().setTextSize(ThemeObject.getInstance().getPayButtonTextSize());
        if(ThemeObject.getInstance().isPayButtSecurityIconVisible())
            payButtonView.getSecurityIconView().setVisibility(ThemeObject.getInstance().isPayButtSecurityIconVisible()? View.VISIBLE:View.INVISIBLE);
        if( ThemeObject.getInstance().getPayButtonResourceId()!=0)
            payButtonView.setBackgroundSelector(ThemeObject.getInstance().getPayButtonResourceId());

        if(sdkSession!=null){
            TransactionMode trx_mode = sdkSession.getTransactionMode();
            if(trx_mode!=null){

                if (TransactionMode.SAVE_CARD == trx_mode  || TransactionMode.SAVE_CARD_NO_UI ==trx_mode) {
                    payButtonView.getPayButton().setText(getString(company.tap.gosellapi.R.string.save_card));
                }
                else if(TransactionMode.TOKENIZE_CARD == trx_mode || TransactionMode.TOKENIZE_CARD_NO_UI == trx_mode){
                    payButtonView.getPayButton().setText(getString(company.tap.gosellapi.R.string.tokenize));
                }
                else {
                    payButtonView.getPayButton().setText(getString(company.tap.gosellapi.R.string.pay));
                }
            }else{
                configureSDKMode();
            }
            sdkSession.setButtonView(payButtonView, this, SDK_REQUEST_CODE);
        }


    }

    private Customer getCustomer() { // test customer id cus_Kh1b4220191939i1KP2506448



        return new Customer.CustomerBuilder("").email(new saved_data().get_email(payment_page.this)).firstName(new saved_data().get_name(payment_page.this))
                .metadata("").phone(new PhoneNumber("02",new saved_data().get_phone(payment_page.this)))
                .build();
    }

    @Override
    public void paymentSucceed(@NonNull Charge charge) {
        Toasty.success(payment_page.this,"Successful Payment",Toasty.LENGTH_LONG).show();
    }

    @Override
    public void paymentFailed(@Nullable Charge charge) {
        Toasty.error(payment_page.this,"Failed Payment",Toasty.LENGTH_LONG).show();

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
}
