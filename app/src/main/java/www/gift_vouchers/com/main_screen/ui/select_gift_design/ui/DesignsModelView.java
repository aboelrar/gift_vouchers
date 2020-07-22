package www.gift_vouchers.com.main_screen.ui.select_gift_design.ui;

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
import www.gift_vouchers.com.main_screen.ui.select_gift_design.model.designsBody;
import www.gift_vouchers.com.main_screen.ui.select_gift_design.model.designsRootClass;
import www.gift_vouchers.com.main_screen.ui.select_gift_design.model.gift_img_list;

public class DesignsModelView extends ViewModel implements NetworkInterface {

    DesignsModelViewFactory companiesModelViewFactory;
    www.gift_vouchers.com.main_screen.ui.select_gift_design.model.designsRootClass designsRootClass;
    designsBody[] designsBody;
    MutableLiveData<ArrayList<gift_img_list>> MutableLiveData = new MutableLiveData<>();


    public DesignsModelView(DesignsModelViewFactory companiesModelViewFactory) {
        this.companiesModelViewFactory = companiesModelViewFactory;
    }

    void get_data() {

        //CALL API
        new Apicalls(companiesModelViewFactory.context, this).get_all_designs();

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

    }

    ArrayList<gift_img_list> get_data_list(JSONObject jsonObject) {
        ArrayList<gift_img_list> arrayList = new ArrayList<>();

        Gson gson = new Gson();
        designsRootClass = gson.fromJson("" + jsonObject, designsRootClass.class);
        //GET DATA FROM SERVER
        designsBody = designsRootClass.getBody();

        for (int index = 0; index < designsBody.length; index++) {
            arrayList.add(new gift_img_list("" + designsBody[index].getId(),designsBody[index].getPath()));
        }

        //SET DATA IN MULTI LIVE DATA
        MutableLiveData.setValue(arrayList);

        return arrayList;
    }




}
