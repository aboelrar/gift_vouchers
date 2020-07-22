package www.gift_vouchers.com.main_screen_company.ui.details.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CompanyOrderDetailsModelViewFactory implements ViewModelProvider.Factory {
    Context context;
    String id;

    public CompanyOrderDetailsModelViewFactory(Context context, String id) {
        this.context = context;
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CompanyOrderDetailsModelView.class)) {
            return (T) new CompanyOrderDetailsModelView(new CompanyOrderDetailsModelViewFactory(context, id));
        }

        throw new IllegalArgumentException("Model Not Found");
    }
}

