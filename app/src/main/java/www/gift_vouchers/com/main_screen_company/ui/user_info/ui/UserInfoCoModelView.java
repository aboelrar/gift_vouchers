package www.gift_vouchers.com.main_screen_company.ui.user_info.ui;

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
import www.gift_vouchers.com.local_data.send_data;
import www.gift_vouchers.com.main_screen.ui.user_info.model.UserInfoRootClass;
import www.gift_vouchers.com.utils.utils;

public class UserInfoCoModelView extends ViewModel implements NetworkInterface {

    UserInfoCoModelViewFactory UserInfoModelViewFactory;
    UserInfoRootClass UserInfoRootClass;
    www.gift_vouchers.com.main_screen.ui.user_info.model.UserInfoBody UserInfoBody;

    public UserInfoCoModelView(UserInfoCoModelViewFactory UserInfoModelViewFactory) {
        this.UserInfoModelViewFactory = UserInfoModelViewFactory;
    }

    void get_data(String username, String phone, String email, String gold, String silver, String platinum) {

        //CALL API
        new Apicalls(UserInfoModelViewFactory.context, this).edit_profile_co(username,
                phone, email, gold, silver, platinum);

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        new utils().dismiss_dialog(UserInfoModelViewFactory.context);

        Gson gson = new Gson();
        UserInfoRootClass = gson.fromJson("" + model.getJsonObject(), UserInfoRootClass.class);

        if (UserInfoRootClass.getStatus() == 0) {
            Toasty.error(UserInfoModelViewFactory.context, UserInfoRootClass.getMessage(), Toasty.LENGTH_SHORT).show();
        } else {
            //GET BODY
            UserInfoBody = UserInfoRootClass.getBody();

            //PRINT MESSAGE
            Toasty.success(UserInfoModelViewFactory.context, UserInfoRootClass.getMessage(), Toasty.LENGTH_SHORT).show();


            //ADD DATA TO SHARED PREFRENCES
            save_local_data();


        }


    }

    @Override
    public void OnError(VolleyError error) {
        Log.e("no_connection", "no");
        new utils().dismiss_dialog(UserInfoModelViewFactory.context);
    }


    //SAVE LOCAL DATA
    void save_local_data() {

        //ADD USER DATA IN ARRAY LIST
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(UserInfoBody.getUsername());
        arrayList.add(UserInfoBody.getEmail());
        arrayList.add(UserInfoBody.getPhone());

        Observable.fromArray(arrayList).
                observeOn(Schedulers.computation()).subscribe(new Consumer<ArrayList<String>>() {
            @Override
            public void accept(ArrayList<String> arrayList) throws Throwable {

                //GET DATA FROM OBSERVABLE AND ADDED IN LOCAL DATA
                send_data.send_name(UserInfoModelViewFactory.context, arrayList.get(0)); //ADD USER NAME
                send_data.send_email(UserInfoModelViewFactory.context, arrayList.get(1)); //ADD Email
                send_data.send_phone(UserInfoModelViewFactory.context, arrayList.get(2)); //ADD PHONE

            }
        });
    }

}
