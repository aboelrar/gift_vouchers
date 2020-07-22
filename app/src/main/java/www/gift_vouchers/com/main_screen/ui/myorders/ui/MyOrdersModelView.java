package www.gift_vouchers.com.main_screen.ui.myorders.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import www.gift_vouchers.com.NetworkLayer.Apicalls;
import www.gift_vouchers.com.NetworkLayer.NetworkInterface;
import www.gift_vouchers.com.NetworkLayer.ResponseModel;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.main_screen.ui.compainies.model.CompaniniesBody;
import www.gift_vouchers.com.main_screen.ui.compainies.model.CompaniniesCategory;
import www.gift_vouchers.com.main_screen.ui.compainies.model.CompaniniesRootClass;
import www.gift_vouchers.com.main_screen.ui.compainies.model.companies_list;
import www.gift_vouchers.com.main_screen.ui.myorders.model.UserOrdersBody;
import www.gift_vouchers.com.main_screen.ui.myorders.model.UserOrdersCategory;
import www.gift_vouchers.com.main_screen.ui.myorders.model.UserOrdersCompany;
import www.gift_vouchers.com.main_screen.ui.myorders.model.UserOrdersRootClass;
import www.gift_vouchers.com.main_screen.ui.myorders.model.my_order_list;

public class MyOrdersModelView extends ViewModel implements NetworkInterface {

    MyOrdersModelViewFactory companiesModelViewFactory;
    UserOrdersRootClass UserOrdersRootClass;
    UserOrdersBody[] UserOrdersBody;
    UserOrdersCompany UserOrdersCompany;
    UserOrdersCategory UserOrdersCategory;
    MutableLiveData<ArrayList<my_order_list>> MutableLiveData = new MutableLiveData<>();


    public MyOrdersModelView(MyOrdersModelViewFactory companiesModelViewFactory) {
        this.companiesModelViewFactory = companiesModelViewFactory;
    }

    void get_data() {

        //CALL API
        new Apicalls(companiesModelViewFactory.context, this).my_orders();

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        get_data_list(model.getJsonObject());
    }

    @Override
    public void OnError(VolleyError error) {
        Log.e("no_connection", "no");

    }

    ArrayList<my_order_list> get_data_list(JSONObject jsonObject) {

        ArrayList<my_order_list> arrayList = new ArrayList<>();

        Gson gson = new Gson();
        UserOrdersRootClass = gson.fromJson("" + jsonObject, UserOrdersRootClass.class);
        UserOrdersBody = UserOrdersRootClass.getBody();

        for (int index = 0; index < UserOrdersBody.length; index++) {
            UserOrdersCompany = UserOrdersBody[index].getCompany();

            arrayList.add(new my_order_list("" + UserOrdersCompany.getId(),
                    UserOrdersCompany.getPicture(), UserOrdersCompany.getUsername() + " " +
                    companiesModelViewFactory.context.getString(R.string.gift_voucher),
                    UserOrdersBody[index].getOrderNumber(), UserOrdersBody[index].getTotalPrice()));
        }

        //SET DATA IN MULTI LIVE DATA
        MutableLiveData.setValue(arrayList);

        return arrayList;
    }


}
