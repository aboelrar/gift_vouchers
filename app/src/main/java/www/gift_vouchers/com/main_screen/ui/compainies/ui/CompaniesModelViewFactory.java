package www.gift_vouchers.com.main_screen.ui.compainies.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import www.gift_vouchers.com.main_screen.ui.user_info.ui.UserInfoModelView;

public class CompaniesModelViewFactory implements ViewModelProvider.Factory {
    Context context;

    public CompaniesModelViewFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CompaniesModelView.class)) {
            return (T) new CompaniesModelView(new CompaniesModelViewFactory(context));
        }

        throw new IllegalArgumentException("Model Not Found");
    }
}

