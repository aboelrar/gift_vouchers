package www.gift_vouchers.com.main_screen.ui.Checkout.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.com.NetworkLayer.Apicalls;
import www.gift_vouchers.com.NetworkLayer.NetworkInterface;
import www.gift_vouchers.com.NetworkLayer.ResponseModel;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.local_data.saved_data;
import www.gift_vouchers.com.main_screen.ui.Checkout.model.check_outBody;
import www.gift_vouchers.com.main_screen.ui.pay.ui.pay;
import www.gift_vouchers.com.utils.utils;

public class CheckOutModelView extends ViewModel implements NetworkInterface {

    CheckOutModelViewFactory UserInfoModelViewFactory;
    www.gift_vouchers.com.main_screen.ui.Checkout.model.check_outRootClass check_outRootClass;
    check_outBody check_outBody;

    public CheckOutModelView(CheckOutModelViewFactory UserInfoModelViewFactory) {
        this.UserInfoModelViewFactory = UserInfoModelViewFactory;
    }

    void get_data(String email, String name, String phone, String notes) {

        //CALL API
        new Apicalls(UserInfoModelViewFactory.context, this).check_out(new saved_data().get_price(UserInfoModelViewFactory.context),
                new saved_data().cat_id(UserInfoModelViewFactory.context), email,
                name, phone, notes);

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

                //GO TO NEXT PAGE WITH ID
                Gson gson = new Gson();
                check_outRootClass = gson.fromJson("" + model.getJsonObject(), www.gift_vouchers.com.main_screen.ui.Checkout.model.check_outRootClass.class);
                check_outBody = check_outRootClass.getBody();

                Fragment home = new pay();
                Bundle bundle = new Bundle();
                bundle.putString("id", "" + check_outBody.getId());
                //set Fragmentclass Arguments
                home.setArguments(bundle);

                ((AppCompatActivity) UserInfoModelViewFactory.context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag, home).addToBackStack(null).commit();


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
