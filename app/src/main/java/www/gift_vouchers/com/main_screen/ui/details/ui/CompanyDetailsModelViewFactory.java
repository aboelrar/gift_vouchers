package www.gift_vouchers.com.main_screen.ui.details.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CompanyDetailsModelViewFactory implements ViewModelProvider.Factory {
    Context context;
    String id;

    public CompanyDetailsModelViewFactory(Context context, String id) {
        this.context = context;
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CompanyDetailsModelView.class)) {
            return (T) new CompanyDetailsModelView(new CompanyDetailsModelViewFactory(context, id));
        }

        throw new IllegalArgumentException("Model Not Found");
    }
}

