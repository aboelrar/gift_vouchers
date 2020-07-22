package www.gift_vouchers.com.main_screen.ui.Checkout;

import android.util.Log;
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
import www.gift_vouchers.com.local_data.saved_data;
import www.gift_vouchers.com.local_data.send_data;
import www.gift_vouchers.com.main_screen.ui.pay.pay;
import www.gift_vouchers.com.main_screen.ui.user_info.model.UserInfoRootClass;
import www.gift_vouchers.com.utils.utils;

public class CheckOutModelView extends ViewModel implements NetworkInterface {

    CheckOutModelViewFactory UserInfoModelViewFactory;

    public CheckOutModelView(CheckOutModelViewFactory UserInfoModelViewFactory) {
        this.UserInfoModelViewFactory = UserInfoModelViewFactory;
    }

    void get_data() {

        //CALL API
        new Apicalls(UserInfoModelViewFactory.context, this).check_out(new saved_data().get_price(UserInfoModelViewFactory.context),
                new saved_data().cat_id(UserInfoModelViewFactory.context), UserInfoModelViewFactory.email,
                UserInfoModelViewFactory.username, UserInfoModelViewFactory.phone, UserInfoModelViewFactory.notes);

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        new utils().dismiss_dialog(UserInfoModelViewFactory.context);

        try {
            if (model.getJsonObject().getString("status").equals("0")) {
                Toasty.warning(UserInfoModelViewFactory.context, "" + model.getJsonObject().getString("message"),
                        Toasty.LENGTH_SHORT).show();
            } else {
                Toasty.success(UserInfoModelViewFactory.context, "" + model.getJsonObject().getString("message"),
                        Toasty.LENGTH_SHORT).show();

                //GO TO FRAGMENT
                new utils().Replace_Fragment(new pay(), R.id.frag, UserInfoModelViewFactory.context);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void OnError(VolleyError error) {
        new utils().dismiss_dialog(UserInfoModelViewFactory.context);
        Log.e("noconnection", "noconnection");
    }


}
