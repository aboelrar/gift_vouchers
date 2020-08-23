package www.gift_vouchers.com.main_screen.ui.faq.ui;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import www.gift_vouchers.com.NetworkLayer.Apicalls;
import www.gift_vouchers.com.NetworkLayer.NetworkInterface;
import www.gift_vouchers.com.NetworkLayer.ResponseModel;
import www.gift_vouchers.com.main_screen.ui.faq.model.faqBody;
import www.gift_vouchers.com.main_screen.ui.faq.model.faqRootClass;
import www.gift_vouchers.com.main_screen.ui.faq.model.faq_model;

public class FaqModelView extends ViewModel implements NetworkInterface {

    MutableLiveData<ArrayList<faq_model>> faqModelMutableLiveData = new MutableLiveData<ArrayList<faq_model>>();
    faqRootClass faqRootClass;
    faqBody[] faqBody;

    void get_data(Context context) {

        //CALL API
        new Apicalls(context, this).GET_FAQ();

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

    ArrayList<faq_model> get_data_list(JSONObject jsonObject) {
        ArrayList<faq_model> arrayList = new ArrayList<>();

        Gson gson = new Gson();
        faqRootClass = gson.fromJson("" + jsonObject, faqRootClass.class);
        //GET DATA FROM SERVER
        faqBody = faqRootClass.getBody();

        for (int index = 0; index < faqBody.length; index++) {
            arrayList.add(new faq_model("" + faqBody[index].getId(), faqBody[index].getQuestion(), faqBody[index].getAnswer()));
        }

        //SET DATA IN MULTI LIVE DATA
        faqModelMutableLiveData.setValue(arrayList);

        return arrayList;
    }


}
