package www.gift_vouchers.com.auth.ui.login.ui;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginModelViewFactory implements ViewModelProvider.Factory {
    Context context;
    String username;
    String password;
    String firebase_token;
    Class modelClass;


    public LoginModelViewFactory(Context context, String username, String password, String firebase_token) {
        this.context = context;
        this.username = username;
        this.password = password;
        this.firebase_token = firebase_token;


    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Log.e("eeeexxc", "" + username);
        return (T) new LoginModelView(new LoginModelViewFactory(context, username, password, firebase_token));

    }





}

