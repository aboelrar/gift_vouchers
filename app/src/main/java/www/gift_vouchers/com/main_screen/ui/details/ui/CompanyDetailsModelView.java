package www.gift_vouchers.com.main_screen.ui.details.ui;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import www.gift_vouchers.com.NetworkLayer.Apicalls;
import www.gift_vouchers.com.NetworkLayer.NetworkInterface;
import www.gift_vouchers.com.NetworkLayer.ResponseModel;
import www.gift_vouchers.com.main_screen.ui.details.model.CompaniniesDetailsRootClass;
import www.gift_vouchers.com.main_screen.ui.compainies.model.CompaniniesCategory;
import www.gift_vouchers.com.main_screen.ui.compainies.model.CompaniniesRootClass;
import www.gift_vouchers.com.main_screen.ui.compainies.model.companies_list;
import www.gift_vouchers.com.utils.utils;

public class CompanyDetailsModelView extends ViewModel implements NetworkInterface {

    CompanyDetailsModelViewFactory companiesModelViewFactory;
    CompaniniesDetailsRootClass CompaniniesDetailsRootClass;
    MutableLiveData<CompaniniesDetailsRootClass> MutableLiveData = new MutableLiveData<>();


    public CompanyDetailsModelView(CompanyDetailsModelViewFactory companiesModelViewFactory) {
        this.companiesModelViewFactory = companiesModelViewFactory;
    }

    void get_data() {

        //CALL API
        new Apicalls(companiesModelViewFactory.context, this)
                .company_details(companiesModelViewFactory.id);

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        Gson gson = new Gson();
        CompaniniesDetailsRootClass = gson.fromJson("" + model.getJsonObject(), CompaniniesDetailsRootClass.class);

        //SET DATA CLASS
        MutableLiveData.setValue(CompaniniesDetailsRootClass);
    }

    @Override
    public void OnError(VolleyError error) {
        Log.e("no_connection", "no");
    }

}
