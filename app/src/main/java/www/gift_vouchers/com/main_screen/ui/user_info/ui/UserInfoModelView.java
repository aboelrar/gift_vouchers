package www.gift_vouchers.com.main_screen.ui.user_info.ui;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import www.gift_vouchers.com.NetworkLayer.Apicalls;
import www.gift_vouchers.com.NetworkLayer.NetworkInterface;
import www.gift_vouchers.com.NetworkLayer.ResponseModel;
import www.gift_vouchers.com.NetworkLayer.networkis;
import www.gift_vouchers.com.local_data.send_data;
import www.gift_vouchers.com.main_screen.ui.user_info.model.UserInfoRootClass;
import www.gift_vouchers.com.utils.utils;

public class UserInfoModelView extends ViewModel implements NetworkInterface {

    UserInfoModelViewFactory UserInfoModelViewFactory;
    www.gift_vouchers.com.main_screen.ui.user_info.model.UserInfoRootClass UserInfoRootClass;
    www.gift_vouchers.com.main_screen.ui.user_info.model.UserInfoBody UserInfoBody;

    public UserInfoModelView(UserInfoModelViewFactory UserInfoModelViewFactory) {
        this.UserInfoModelViewFactory = UserInfoModelViewFactory;
    }


    void data_1() {
        //DEFINE ALL VARS
        Retrofit retrofits = new Retrofit.Builder().baseUrl("http://vouchers-sa.com/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();

//       RequestBody fbody = RequestBody.create(MediaType.parse("image/*"),
//              UserInfoModelViewFactory.file);

        // Assume your file is PNG
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("image/png"), UserInfoModelViewFactory.file);

        MultipartBody.Part fileData =
                MultipartBody.Part.createFormData("picture", UserInfoModelViewFactory.file.getName(), requestFile);


        // add another part within the multipart request
        String descriptionString = "hello, this is description speaking";
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);

        String name = UserInfoModelViewFactory.username;

        String email = UserInfoModelViewFactory.email;

        String phone = UserInfoModelViewFactory.phone;

        networkis networkis = retrofits.create(www.gift_vouchers.com.NetworkLayer.networkis.class);
        Call<ResponseBody> call = networkis.upload(description, fileData);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                new utils().dismiss_dialog(UserInfoModelViewFactory.context);
                Toast.makeText(UserInfoModelViewFactory.context, "" + call.toString(), Toast.LENGTH_SHORT).show();
                Log.e("response_is", "" + response.code() + " " + response.message() + " ..." + response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                new utils().dismiss_dialog(UserInfoModelViewFactory.context);
                Log.e("ssssexxx", t.getMessage());

            }
        });
    }

    void get_data() {

        //CALL API
        new Apicalls(UserInfoModelViewFactory.context, this).edit_profile(UserInfoModelViewFactory.username,
                UserInfoModelViewFactory.phone, UserInfoModelViewFactory.email);

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        new utils().dismiss_dialog(UserInfoModelViewFactory.context);

        Gson gson = new Gson();
        UserInfoRootClass = gson.fromJson("" + model.getJsonObject(), UserInfoRootClass.class);

        if (UserInfoRootClass.getStatus() == 0) {
            Toasty.error(UserInfoModelViewFactory.context, UserInfoRootClass.getMessage(), Toasty.LENGTH_SHORT).show();
        } else {
            //GET BODY
            UserInfoBody = UserInfoRootClass.getBody();

            //PRINT MESSAGE
            Toasty.success(UserInfoModelViewFactory.context, UserInfoRootClass.getMessage(), Toasty.LENGTH_SHORT).show();


            //ADD DATA TO SHARED PREFRENCES
            save_local_data();


        }


    }

    @Override
    public void OnError(VolleyError error) {
        Log.e("no_connection", "no");
        new utils().dismiss_dialog(UserInfoModelViewFactory.context);
    }


    //SAVE LOCAL DATA
    void save_local_data() {

        //ADD USER DATA IN ARRAY LIST
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(UserInfoBody.getUsername());
        arrayList.add(UserInfoBody.getEmail());
        arrayList.add(UserInfoBody.getPhone());

        Observable.fromArray(arrayList).
                observeOn(Schedulers.computation()).subscribe(new Consumer<ArrayList<String>>() {
            @Override
            public void accept(ArrayList<String> arrayList) throws Throwable {

                //GET DATA FROM OBSERVABLE AND ADDED IN LOCAL DATA
                send_data.send_name(UserInfoModelViewFactory.context, arrayList.get(0)); //ADD USER NAME
                send_data.send_email(UserInfoModelViewFactory.context, arrayList.get(1)); //ADD Email
                send_data.send_phone(UserInfoModelViewFactory.context, arrayList.get(2)); //ADD PHONE

            }
        });
    }

}
