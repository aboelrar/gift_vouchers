package www.gift_vouchers.com.auth.ui.forget_pass.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;

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
import www.gift_vouchers.com.auth.ui.login.ui.loading;
import www.gift_vouchers.com.auth.ui.verfication_code.verfication_code;
import www.gift_vouchers.com.local_data.send_data;
import www.gift_vouchers.com.main_screen.ui.details.ui.company_details;
import www.gift_vouchers.com.utils.utils;

public class ForgetPasswordModelView extends ViewModel implements NetworkInterface {

    ForgetPasswordModelViewFactory ForgetPasswordModelViewFactory;


    public ForgetPasswordModelView(ForgetPasswordModelViewFactory ForgetPasswordModelViewFactory) {
        this.ForgetPasswordModelViewFactory = ForgetPasswordModelViewFactory;
    }

    void get_data() {


        //CALL API
        new Apicalls(ForgetPasswordModelViewFactory.context, this).FORGET_PASS(ForgetPasswordModelViewFactory.email);


    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        new utils().dismiss_dialog(ForgetPasswordModelViewFactory.context);


        try {
            if (model.getJsonObject().getString("status").equals("0")) {

                Toasty.success(ForgetPasswordModelViewFactory.context,
                        "" + model.getJsonObject().getString("message"), Toasty.LENGTH_SHORT).show();
            } else {

                Toasty.success(ForgetPasswordModelViewFactory.context,
                        "" + model.getJsonObject().getString("message"), Toasty.LENGTH_SHORT).show();

                //SEND DATA TO NEXT FRAGMENT
                Fragment home = new verfication_code();
                Bundle bundle = new Bundle();
                bundle.putString("email", ForgetPasswordModelViewFactory.email);
                //set Fragmentclass Arguments
                home.setArguments(bundle);

                ((AppCompatActivity) ForgetPasswordModelViewFactory.context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag, home).addToBackStack(null).commit();


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void OnError(VolleyError error) {
        Log.e("no_connection","no");
        new utils().dismiss_dialog(ForgetPasswordModelViewFactory.context);
    }


}
