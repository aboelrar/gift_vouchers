package www.gift_vouchers.com.auth.ui.signup.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;

import java.util.Objects;

import www.gift_vouchers.com.main_screen.ui.MainActivity;
import www.gift_vouchers.com.main_screen_company.ui.Main_Activity_Company;


public class loading {
    public void dialog(final Context context, int resource, double widthh, String type) {
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


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (type.equals("3")) {
                    context.startActivity(new Intent(context, MainActivity.class));
                    ((Activity) context).finish();
                    dialog.dismiss();
                }
            }
        }, 2000);
    }


}

