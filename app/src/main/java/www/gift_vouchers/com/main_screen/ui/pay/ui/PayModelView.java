package www.gift_vouchers.com.main_screen.ui.pay.ui;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;

import org.json.JSONException;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.com.NetworkLayer.Apicalls;
import www.gift_vouchers.com.NetworkLayer.NetworkInterface;
import www.gift_vouchers.com.NetworkLayer.ResponseModel;
import www.gift_vouchers.com.R;

public class PayModelView extends ViewModel implements NetworkInterface {

    Context context;

    void get_data(Context context, String id) {

        //CALL API
        new Apicalls(context, this).confirm_order(id);
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


                //OPEN DIALOG
                loading loading = new loading();
                loading.dialog(context, R.layout.successfull_payment, .80);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnError(VolleyError error) {

    }

}
