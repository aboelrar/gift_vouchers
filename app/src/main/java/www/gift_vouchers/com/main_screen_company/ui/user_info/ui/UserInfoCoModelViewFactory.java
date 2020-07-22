package www.gift_vouchers.com.main_screen_company.ui.user_info.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class UserInfoCoModelViewFactory implements ViewModelProvider.Factory {
    Context context;
    String username;
    String email;
    String phone;
    String gold;
    String silver;
    String platinum;

    public UserInfoCoModelViewFactory(Context context, String username, String email, String phone, String gold, String silver, String platinum) {
        this.context = context;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.gold = gold;
        this.silver = silver;
        this.platinum = platinum;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserInfoCoModelView.class)) {
            return (T) new UserInfoCoModelView(new UserInfoCoModelViewFactory(context, username, email, phone, gold, silver, platinum));
        }

        throw new IllegalArgumentException("Model Not Found");
    }
}

