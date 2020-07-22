package www.gift_vouchers.com.auth.ui.signup.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SignupModelViewFactory implements ViewModelProvider.Factory {
    Context context;
    String username;
    String password;
    String email;
    String phone;
    String type;
    String firebase_token;

    public SignupModelViewFactory(Context context, String username, String password, String email, String phone, String firebase_token, String type) {
        this.context = context;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.firebase_token = firebase_token;
        this.type = type;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SignupModelView.class)) {
            return (T) new SignupModelView(new SignupModelViewFactory(context, username, password, email, phone, firebase_token, type));
        }

        throw new IllegalArgumentException("Model Not Found");
    }
}

