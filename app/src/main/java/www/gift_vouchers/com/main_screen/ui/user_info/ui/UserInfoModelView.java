package www.gift_vouchers.com.main_screen.ui.user_info.ui;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import www.gift_vouchers.com.NetworkLayer.Apicalls;
import www.gift_vouchers.com.NetworkLayer.NetworkInterface;
import www.gift_vouchers.com.NetworkLayer.ResponseModel;
import www.gift_vouchers.com.NetworkLayer.networkis;
import www.gift_vouchers.com.local_data.saved_data;
import www.gift_vouchers.com.local_data.send_data;
import www.gift_vouchers.com.main_screen.ui.MainActivity;
import www.gift_vouchers.com.main_screen.ui.user_info.model.UserInfoRootClass;
import www.gift_vouchers.com.utils.utils;

public class UserInfoModelView extends ViewModel implements NetworkInterface {

    UserInfoModelViewFactory UserInfoModelViewFactory;
    www.gift_vouchers.com.main_screen.ui.user_info.model.UserInfoRootClass UserInfoRootClass;
    www.gift_vouchers.com.main_screen.ui.user_info.model.UserInfoBody UserInfoBody;

    public UserInfoModelView(UserInfoModelViewFactory UserInfoModelViewFactory) {
        this.UserInfoModelViewFactory = UserInfoModelViewFactory;
    }

    String Base_url = "http://vouchers-sa.com/api/";

    void data_1(File file, String name, String email, String phone) {

        MultipartBody.Part body = null;
        if (file != null) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            body = MultipartBody.Part.createFormData("picture", file.getName(), requestFile);
        }

        RequestBody phone_body = RequestBody.create(MediaType.parse("text/plain"),
                phone);


        Retrofit retrofits = new Retrofit.Builder().baseUrl(Base_url).
                addConverterFactory(GsonConverterFactory.create()).build();
        networkis networkis = retrofits.create(www.gift_vouchers.com.NetworkLayer.networkis.class);


        Call<UserInfoRootClass> call = networkis.upload("Bearer " + new saved_data().get_token(UserInfoModelViewFactory.context),
                name, email, phone_body, body);

        call.enqueue(new Callback<UserInfoRootClass>() {
            @Override
            public void onResponse(Call<UserInfoRootClass> call, Response<www.gift_vouchers.com.main_screen.ui.user_info.model.UserInfoRootClass> response) {
                new utils().dismiss_dialog(UserInfoModelViewFactory.context);
                //GET DATA
                UserInfoRootClass = response.body();
                UserInfoBody = UserInfoRootClass.getBody();

                //SET DATA TO FUNC
                save_local_data(UserInfoBody.getUsername(), UserInfoBody.getEmail(),
                        UserInfoBody.getPhone(), "" + UserInfoBody.getPicture());

                //TOAST SUCCESS
                Toasty.success(UserInfoModelViewFactory.context, "" + UserInfoRootClass.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<UserInfoRootClass> call, Throwable t) {
                new utils().dismiss_dialog(UserInfoModelViewFactory.context);
                Log.e("error", t.getMessage());

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


        }


    }

    @Override
    public void OnError(VolleyError error) {
        Log.e("no_connection", "no");
        new utils().dismiss_dialog(UserInfoModelViewFactory.context);
    }


    //SAVE LOCAL DATA
    void save_local_data(String username, String email, String phone, String picture) {

        //ADD USER DATA IN ARRAY LIST
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(username);
        arrayList.add(email);
        arrayList.add(phone);
        arrayList.add(picture);

        Observable.fromArray(arrayList).
                observeOn(Schedulers.computation()).subscribe(new Consumer<ArrayList<String>>() {
            @Override
            public void accept(ArrayList<String> arrayList) throws Throwable {

                //GET DATA FROM OBSERVABLE AND ADDED IN LOCAL DATA
                send_data.send_name(UserInfoModelViewFactory.context, arrayList.get(0)); //ADD USER NAME
                send_data.send_email(UserInfoModelViewFactory.context, arrayList.get(1)); //ADD Email
                send_data.send_phone(UserInfoModelViewFactory.context, arrayList.get(2)); //ADD PHONE
                send_data.set_user_img(UserInfoModelViewFactory.context, arrayList.get(3)); //ADD IMAGE

                //GO TO MAIN PAGE
                UserInfoModelViewFactory.context.startActivity(new Intent(UserInfoModelViewFactory.context, MainActivity.class));
                ((AppCompatActivity) UserInfoModelViewFactory.context).finish();
            }
        });
    }

}
