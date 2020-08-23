package www.gift_vouchers.com.auth.ui.forget_pass.ui;

import android.os.Bundle;
import android.util.Log;

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
import www.gift_vouchers.com.auth.ui.verfication_code.verfication_code;
import www.gift_vouchers.com.utils.utils;

public class ForgetPasswordModelView extends ViewModel implements NetworkInterface {

    int type;
    ForgetPasswordModelViewFactory ForgetPasswordModelViewFactory;


    public ForgetPasswordModelView(ForgetPasswordModelViewFactory ForgetPasswordModelViewFactory) {
        this.ForgetPasswordModelViewFactory = ForgetPasswordModelViewFactory;
    }

    public void get_data(String email, int type) {

        this.type = type;

        //CALL API
        new Apicalls(ForgetPasswordModelViewFactory.context, this).FORGET_PASS(email);


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

                if (type != 1) {

                    //SEND DATA TO NEXT FRAGMENT
                    Fragment home = new verfication_code();
                    Bundle bundle = new Bundle();
                    bundle.putString("email", ForgetPasswordModelViewFactory.email);
                    //set Fragmentclass Arguments
                    home.setArguments(bundle);

                    ((AppCompatActivity) ForgetPasswordModelViewFactory.context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frag, home).addToBackStack(null).commit();

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void OnError(VolleyError error) {
        Log.e("no_connection", "no");
        new utils().dismiss_dialog(ForgetPasswordModelViewFactory.context);
    }


}
