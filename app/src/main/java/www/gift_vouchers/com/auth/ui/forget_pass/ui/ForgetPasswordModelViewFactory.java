package www.gift_vouchers.com.auth.ui.forget_pass.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ForgetPasswordModelViewFactory implements ViewModelProvider.Factory {
    Context context;
    String email;

    public ForgetPasswordModelViewFactory(Context context, String email) {
        this.context = context;
        this.email = email;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ForgetPasswordModelView.class)) {
            return (T) new ForgetPasswordModelView(new ForgetPasswordModelViewFactory(context, email));
        }

        throw new IllegalArgumentException("Model Not Found");
    }
}

