package www.gift_vouchers.com.main_screen.ui.change_password.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ChangePasswordModelViewFactory implements ViewModelProvider.Factory {
    Context context;
    String OldPassword;
    String NewPassword;
    String CoPassword;

    public ChangePasswordModelViewFactory(Context context, String OldPassword, String NewPassword, String CoPassword) {
        this.context = context;
        this.OldPassword = OldPassword;
        this.NewPassword = NewPassword;
        this.CoPassword = CoPassword;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ChangePasswordModelView.class)) {
            return (T) new ChangePasswordModelView(new ChangePasswordModelViewFactory(context, OldPassword, NewPassword, CoPassword));
        }

        throw new IllegalArgumentException("Model Not Found");
    }
}

