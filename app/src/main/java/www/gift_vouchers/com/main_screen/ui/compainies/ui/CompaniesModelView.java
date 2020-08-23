package www.gift_vouchers.com.main_screen.ui.compainies.ui;

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
import www.gift_vouchers.com.main_screen.ui.compainies.model.CompaniniesBody;
import www.gift_vouchers.com.main_screen.ui.compainies.model.CompaniniesCategory;
import www.gift_vouchers.com.main_screen.ui.compainies.model.CompaniniesRootClass;
import www.gift_vouchers.com.main_screen.ui.compainies.model.companies_list;

public class CompaniesModelView extends ViewModel implements NetworkInterface {

    CompaniesModelViewFactory companiesModelViewFactory;
    CompaniniesRootClass companiniesRootClass;
    CompaniniesBody[] companiniesBody;
    CompaniniesCategory companiniesCategory;
    MutableLiveData<ArrayList<companies_list>> MutableLiveData = new MutableLiveData<>();


    public CompaniesModelView(CompaniesModelViewFactory companiesModelViewFactory) {
        this.companiesModelViewFactory = companiesModelViewFactory;
    }

    void get_data(String search) {

        //CALL API
        new Apicalls(companiesModelViewFactory.context, this).companies(search);

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

    ArrayList<companies_list> get_data_list(JSONObject jsonObject) {
        ArrayList<companies_list> arrayList = new ArrayList<>();

        Gson gson = new Gson();
        companiniesRootClass = gson.fromJson("" + jsonObject, CompaniniesRootClass.class);
        //GET DATA FROM SERVER
        companiniesBody = companiniesRootClass.getBody();

        if (companiniesBody != null) {
            for (int index = 0; index < companiniesBody.length; index++) {
                arrayList.add(new companies_list("" + companiniesBody[index].getId(),
                        companiniesBody[index].getUsername() + " Gift Voucher online shopping",
                        companiniesBody[index].getPicture()));
            }
        }

        //SET DATA IN MULTI LIVE DATA
        MutableLiveData.setValue(arrayList);

        return arrayList;
    }


}
