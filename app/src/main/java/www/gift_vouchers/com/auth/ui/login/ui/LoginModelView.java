package www.gift_vouchers.com.auth.ui.login.ui;

import android.util.Log;

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
import www.gift_vouchers.com.local_data.send_data;
import www.gift_vouchers.com.utils.utils;

public class LoginModelView extends ViewModel implements NetworkInterface {

    LoginModelViewFactory loginModelViewFactory;
    login_rootRootClass login_rootRootClass;
    login_rootBody login_rootBody;
    login_rootUser login_rootUser;

    public LoginModelView(LoginModelViewFactory loginModelViewFactory) {
        this.loginModelViewFactory = loginModelViewFactory;
    }

    void get_data(String username, String password, String firebase_token) {


        //CALL API
        new Apicalls(loginModelViewFactory.context, this).loginUser(username
                , password, firebase_token);


    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        new utils().dismiss_dialog(loginModelViewFactory.context);

        Gson gson = new Gson();
        login_rootRootClass = gson.fromJson("" + model.getResponse(), login_rootRootClass.class);

        if (login_rootRootClass.getStatus() == 0) {
            Toasty.error(loginModelViewFactory.context, login_rootRootClass.getMessage(), Toasty.LENGTH_SHORT).show();
        } else {
            //GET BODY
            login_rootBody = login_rootRootClass.getBody();

            //GET USER
            login_rootUser = login_rootBody.getUser();

            //ADD DATA TO SHARED PREFRENCES
            save_local_data();

            //GET TYPE
            String type = login_rootUser.getRoleId();

            //SUCCESS LOGIN
            loading loading = new loading();
            loading.dialog(loginModelViewFactory.context, R.layout.successful_login, .80, type);

        }


    }

    @Override
    public void OnError(VolleyError error) {

        new utils().dismiss_dialog(loginModelViewFactory.context);
        Log.e("No_Connection", "no connection");
    }


    //SAVE LOCAL DATA
    void save_local_data() {

        //ADD USER DATA IN ARRAY LIST
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(login_rootUser.getUsername());
        arrayList.add(login_rootUser.getEmail());
        arrayList.add(login_rootUser.getPhone());
        arrayList.add(login_rootBody.getAccessToken());
        arrayList.add(login_rootUser.getRoleId());
        arrayList.add("" + login_rootUser.getPicture());

        Observable.fromArray(arrayList).
                observeOn(Schedulers.computation()).subscribe(new Consumer<ArrayList<String>>() {
            @Override
            public void accept(ArrayList<String> arrayList) throws Throwable {

                //GET DATA FROM OBSERVABLE AND ADDED IN LOCAL DATA
                send_data.send_name(loginModelViewFactory.context, arrayList.get(0)); //ADD USER NAME
                send_data.send_email(loginModelViewFactory.context, arrayList.get(1)); //ADD Email
                send_data.send_phone(loginModelViewFactory.context, arrayList.get(2)); //ADD PHONE
                send_data.send_token(loginModelViewFactory.context, arrayList.get(3)); //ADD TOKEN
                send_data.send_type(loginModelViewFactory.context, arrayList.get(4)); //ADD TYPE
                send_data.login_status(loginModelViewFactory.context, true); //ADD STATUS
                send_data.set_user_img(loginModelViewFactory.context, arrayList.get(5)); //ADD PICTURE

            }
        });
    }

}
