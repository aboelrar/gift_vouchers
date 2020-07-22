package www.gift_vouchers.com.main_screen.ui.user_info.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;

public class UserInfoModelViewFactory implements ViewModelProvider.Factory {
    Context context;
    String username;
    String email;
    String phone;
    File file;

    public UserInfoModelViewFactory(Context context, String username, String email, String phone, File file) {
        this.context = context;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.file = file;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserInfoModelView.class)) {
            return (T) new UserInfoModelView(new UserInfoModelViewFactory(context, username, email, phone, file));
        }

        throw new IllegalArgumentException("Model Not Found");
    }
}

