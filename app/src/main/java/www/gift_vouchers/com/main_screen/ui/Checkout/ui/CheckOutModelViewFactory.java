package www.gift_vouchers.com.main_screen.ui.Checkout.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CheckOutModelViewFactory implements ViewModelProvider.Factory {
    Context context;
    String username;
    String email;
    String phone;
    String notes;

    public CheckOutModelViewFactory(Context context, String username, String email, String phone, String notes) {
        this.context = context;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.notes = notes;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CheckOutModelView.class)) {
            return (T) new CheckOutModelView(new CheckOutModelViewFactory(context, username, email, phone, notes));
        }

        throw new IllegalArgumentException("Model Not Found");
    }

}

