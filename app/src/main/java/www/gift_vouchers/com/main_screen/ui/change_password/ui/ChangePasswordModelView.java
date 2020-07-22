package www.gift_vouchers.com.main_screen.ui.change_password.ui;

import android.widget.Toast;

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
import www.gift_vouchers.com.local_data.send_data;
import www.gift_vouchers.com.main_screen.ui.user_info.model.UserInfoRootClass;
import www.gift_vouchers.com.utils.utils;

public class ChangePasswordModelView extends ViewModel implements NetworkInterface {

    ChangePasswordModelViewFactory ChangePasswordModelViewFactory;


    public ChangePasswordModelView(ChangePasswordModelViewFactory ChangePasswordModelViewFactory) {
        this.ChangePasswordModelViewFactory = ChangePasswordModelViewFactory;
    }

    void get_data(String old_pass, String new_pass, String co_pass) {

        //CALL API
        new Apicalls(ChangePasswordModelViewFactory.context, this).change_pass(old_pass,
                new_pass, co_pass);

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        new utils().dismiss_dialog(ChangePasswordModelViewFactory.context);


        try {
            if (model.getJsonObject().getString("status").equals("0")) {
                Toasty.error(ChangePasswordModelViewFactory.context, model.getJsonObject().getString("message"), Toasty.LENGTH_SHORT).show();
            } else {

                //PRINT MESSAGE
                Toasty.success(ChangePasswordModelViewFactory.context, model.getJsonObject().getString("message"), Toasty.LENGTH_SHORT).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void OnError(VolleyError error) {
        if (error.networkResponse.statusCode == 422) {
            Toasty.success(ChangePasswordModelViewFactory.context,
                    ChangePasswordModelViewFactory.context.getString(R.string.Passord_changed), Toasty.LENGTH_SHORT).show();
        }
    }


}
