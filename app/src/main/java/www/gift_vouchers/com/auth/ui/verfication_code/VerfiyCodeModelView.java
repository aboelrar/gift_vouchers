package www.gift_vouchers.com.auth.ui.verfication_code;

import android.content.Context;
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
import www.gift_vouchers.com.auth.ui.change_password.change_pass;
import www.gift_vouchers.com.utils.utils;

public class VerfiyCodeModelView extends ViewModel implements NetworkInterface {

    Context context;
    String code;
    String email;



    void get_data(Context context, String code, String email) {

        this.context = context;
        this.code = code;
        this.email = email;

        //CALL API
        new Apicalls(context, this).verify_code(code);


    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        new utils().dismiss_dialog(context);


        try {
            if (model.getJsonObject().getString("status").equals("0")) {

                Toasty.error(context,
                        "" + model.getJsonObject().getString("message"), Toasty.LENGTH_SHORT).show();
            } else {

                Toasty.success(context,
                        "" + model.getJsonObject().getString("message"), Toasty.LENGTH_SHORT).show();

                //SEND DATA TO NEXT FRAGMENT
                Fragment home = new change_pass();
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                bundle.putString("code", code);
                //set Fragmentclass Arguments
                home.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag, home).addToBackStack(null).commit();


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void OnError(VolleyError error) {
        Log.e("no_connection", "no");
        new utils().dismiss_dialog(context);
    }


}
