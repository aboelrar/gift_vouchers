package www.gift_vouchers.com.main_screen.ui.select_gift_design.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DesignsModelViewFactory implements ViewModelProvider.Factory {
    Context context;

    public DesignsModelViewFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DesignsModelView.class)) {
            return (T) new DesignsModelView(new DesignsModelViewFactory(context));
        }

        throw new IllegalArgumentException("Model Not Found");
    }
}

