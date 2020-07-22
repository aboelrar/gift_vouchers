package www.gift_vouchers.com.main_screen.ui.myorders.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MyOrdersModelViewFactory implements ViewModelProvider.Factory {
    Context context;

    public MyOrdersModelViewFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MyOrdersModelView.class)) {
            return (T) new MyOrdersModelView(new MyOrdersModelViewFactory(context));
        }

        throw new IllegalArgumentException("Model Not Found");
    }
}

