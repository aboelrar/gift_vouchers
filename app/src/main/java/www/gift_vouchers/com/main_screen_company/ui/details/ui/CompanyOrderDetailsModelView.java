package www.gift_vouchers.com.main_screen_company.ui.details.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import www.gift_vouchers.com.NetworkLayer.Apicalls;
import www.gift_vouchers.com.NetworkLayer.NetworkInterface;
import www.gift_vouchers.com.NetworkLayer.ResponseModel;
import www.gift_vouchers.com.main_screen.ui.details.model.CompaniniesDetailsRootClass;
import www.gift_vouchers.com.main_screen_company.ui.details.model.myorder_company_detailsRootClass;

public class CompanyOrderDetailsModelView extends ViewModel implements NetworkInterface {

    CompanyOrderDetailsModelViewFactory companiesModelViewFactory;
    www.gift_vouchers.com.main_screen_company.ui.details.model.myorder_company_detailsRootClass myorder_company_detailsRootClass;
    MutableLiveData<myorder_company_detailsRootClass> MutableLiveData = new MutableLiveData<>();


    public CompanyOrderDetailsModelView(CompanyOrderDetailsModelViewFactory companiesModelViewFactory) {
        this.companiesModelViewFactory = companiesModelViewFactory;
    }

    void get_data() {

        //CALL API
        new Apicalls(companiesModelViewFactory.context, this)
                .company_order_details(companiesModelViewFactory.id);

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        Gson gson = new Gson();
        myorder_company_detailsRootClass = gson.fromJson("" + model.getJsonObject(), myorder_company_detailsRootClass.class);

        //SET DATA CLASS
        MutableLiveData.setValue(myorder_company_detailsRootClass);
    }

    @Override
    public void OnError(VolleyError error) {
        Log.e("no_connection", "" + error.networkResponse.statusCode);
    }

}
