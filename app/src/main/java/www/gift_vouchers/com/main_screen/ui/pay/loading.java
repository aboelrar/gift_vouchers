package www.gift_vouchers.com.main_screen.ui.pay;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import java.util.Objects;


public class loading {
    public void dialog(final Context context, int resource, double widthh) {
        final Dialog dialog = new Dialog(context);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);

        Activity activity = (Activity) context;
        if (!activity.isFinishing()) {
            dialog.show();
        }

    }


}

