package www.gift_vouchers.com.auth.ui.signup.ui;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import www.gift_vouchers.com.NetworkLayer.Apicalls;
import www.gift_vouchers.com.NetworkLayer.NetworkInterface;
import www.gift_vouchers.com.NetworkLayer.ResponseModel;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.login.model.login_rootBody;
import www.gift_vouchers.com.auth.ui.login.model.login_rootRootClass;
import www.gift_vouchers.com.auth.ui.login.model.login_rootUser;
import www.gift_vouchers.com.auth.ui.signup.model.signup_rootBody;
import www.gift_vouchers.com.auth.ui.signup.model.signup_rootRootClass;
import www.gift_vouchers.com.auth.ui.signup.model.signup_rootUser;
import www.gift_vouchers.com.local_data.send_data;
import www.gift_vouchers.com.utils.utils;

public class SignupModelView extends ViewModel implements NetworkInterface {

    SignupModelViewFactory SignupModelViewFactory;
    www.gift_vouchers.com.auth.ui.signup.model.signup_rootRootClass signup_rootRootClass;
    signup_rootBody signup_rootBody;
    www.gift_vouchers.com.auth.ui.signup.model.signup_rootUser signup_rootUser;
    String type;

    public SignupModelView(SignupModelViewFactory SignupModelViewFactory) {
        this.SignupModelViewFactory = SignupModelViewFactory;
    }

    void get_data(String username, String type, String phone, String email, String password, String firebase_token) {


        //CALL API
        new Apicalls(SignupModelViewFactory.context, this).insertUser(username
                , type, phone, email, password, firebase_token);

        this.type = type;


    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        new utils().dismiss_dialog(SignupModelViewFactory.context);

        Gson gson = new Gson();
        signup_rootRootClass = gson.fromJson("" + model.getResponse(), signup_rootRootClass.class);

        if (signup_rootRootClass.getStatus() == 0) {
            Toasty.error(SignupModelViewFactory.context, signup_rootRootClass.getMessage(), Toasty.LENGTH_SHORT).show();
        } else {
            //GET BODY
            signup_rootBody = signup_rootRootClass.getBody();

            //GET USER
            signup_rootUser = signup_rootBody.getUser();

            //ADD DATA TO SHARED PREFRENCES
            save_local_data();

            if (type.equals("3")) {
                //SUCCESS LOGIN
                loading loading = new loading();
                loading.dialog(SignupModelViewFactory.context, R.layout.successful_login, .80, type);
            } else {
                Toasty.success(SignupModelViewFactory.context, signup_rootRootClass.getMessage(), Toasty.LENGTH_SHORT).show();
            }


        }


    }

    @Override
    public void OnError(VolleyError error) {
        Log.e("no_connection", "no");
        new utils().dismiss_dialog(SignupModelViewFactory.context);
    }


    //SAVE LOCAL DATA
    void save_local_data() {

        //ADD USER DATA IN ARRAY LIST
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(signup_rootUser.getUsername());
        arrayList.add(signup_rootUser.getEmail());
        arrayList.add(signup_rootUser.getPhone());
        arrayList.add(signup_rootBody.getAccessToken());
        arrayList.add(signup_rootUser.getRoleId());

        Observable.fromArray(arrayList).
                observeOn(Schedulers.computation()).subscribe(new Consumer<ArrayList<String>>() {
            @Override
            public void accept(ArrayList<String> arrayList) throws Throwable {

                //GET DATA FROM OBSERVABLE AND ADDED IN LOCAL DATA
                send_data.send_name(SignupModelViewFactory.context, arrayList.get(0)); //ADD USER NAME
                send_data.send_email(SignupModelViewFactory.context, arrayList.get(1)); //ADD Email
                send_data.send_phone(SignupModelViewFactory.context, arrayList.get(2)); //ADD PHONE
                send_data.send_token(SignupModelViewFactory.context, arrayList.get(3)); //ADD TOKEN
                send_data.send_type(SignupModelViewFactory.context, arrayList.get(4)); //ADD TYPE
                send_data.login_status(SignupModelViewFactory.context, true); //ADD STATUS

            }
        });
    }

}
