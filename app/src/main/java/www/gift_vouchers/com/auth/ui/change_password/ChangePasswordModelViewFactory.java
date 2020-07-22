package www.gift_vouchers.com.auth.ui.change_password;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ChangePasswordModelViewFactory implements ViewModelProvider.Factory {
    Context context;
    String email;
    String code;
    String new_pass;
    String co_pass;

    public ChangePasswordModelViewFactory(Context context, String email, String code, String new_pass, String co_pass) {
        this.context = context;
        this.email = email;
        this.code = code;
        this.new_pass = new_pass;
        this.co_pass = co_pass;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ChangePasswordModelView.class)) {
            return (T) new ChangePasswordModelView(new ChangePasswordModelViewFactory(context, email, code, new_pass, co_pass));
        }

        throw new IllegalArgumentException("Model Not Found");
    }
}

