package www.gift_vouchers.com.auth.ui.change_password;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;

import org.json.JSONException;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.com.NetworkLayer.Apicalls;
import www.gift_vouchers.com.NetworkLayer.NetworkInterface;
import www.gift_vouchers.com.NetworkLayer.ResponseModel;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.auth.ui.login.ui.login;
import www.gift_vouchers.com.auth.ui.verfication_code.verfication_code;
import www.gift_vouchers.com.utils.utils;

public class ChangePasswordModelView extends ViewModel implements NetworkInterface {

    ChangePasswordModelViewFactory ForgetPasswordModelViewFactory;


    public ChangePasswordModelView(ChangePasswordModelViewFactory ForgetPasswordModelViewFactory) {
        this.ForgetPasswordModelViewFactory = ForgetPasswordModelViewFactory;
    }

    void get_data() {


        //CALL API
        new Apicalls(ForgetPasswordModelViewFactory.context, this).change_new_pass(ForgetPasswordModelViewFactory.code,
                ForgetPasswordModelViewFactory.email, ForgetPasswordModelViewFactory.new_pass, ForgetPasswordModelViewFactory.new_pass);


    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        new utils().dismiss_dialog(ForgetPasswordModelViewFactory.context);


        try {
            if (model.getJsonObject().getString("status").equals("0")) {

                Toasty.error(ForgetPasswordModelViewFactory.context,
                        "" + model.getJsonObject().getString("message"), Toasty.LENGTH_SHORT).show();
            } else {

                Toasty.success(ForgetPasswordModelViewFactory.context,
                        "" + model.getJsonObject().getString("message"), Toasty.LENGTH_SHORT).show();


                new utils().Replace_Fragment(new login(), R.id.frag, ForgetPasswordModelViewFactory.context);


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
