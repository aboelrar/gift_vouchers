package www.gift_vouchers.com.main_screen.ui.contact_us.ui;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;

import org.json.JSONException;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.com.NetworkLayer.Apicalls;
import www.gift_vouchers.com.NetworkLayer.NetworkInterface;
import www.gift_vouchers.com.NetworkLayer.ResponseModel;
import www.gift_vouchers.com.utils.utils;

public class ContactModelView extends ViewModel implements NetworkInterface {

    Context context;

    void get_data(Context context, String suggestion) {

        //CALL API
        new Apicalls(context, this).suggestion(suggestion);
        this.context = context;

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        try {
            if (model.getJsonObject().getString("status").equals("0")) {
                Toasty.warning(context, "" + model.getJsonObject().getString("message"), Toasty.LENGTH_SHORT).show();
            } else {

                new utils().dismiss_dialog(context);
                Toasty.success(context, "" + model.getJsonObject().getString("message"), Toasty.LENGTH_SHORT).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnError(VolleyError error) {

    }

}
