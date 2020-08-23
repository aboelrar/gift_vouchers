package www.gift_vouchers.com.main_screen.ui.pay.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Objects;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.main_screen.ui.compainies.ui.compainies;
import www.gift_vouchers.com.main_screen.ui.myorders.ui.myorders;
import www.gift_vouchers.com.utils.utils;


public class loading {
    public void dialog(final Context context, int resource, double widthh) {
        final Dialog dialog = new Dialog(context);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);


        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .playOn(dialog.findViewById(R.id.pop_up));


        //CLOSE
        ImageView close = dialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //GO TO HOME
                Fragment home = new compainies();
                Bundle bundle = new Bundle();
                bundle.putString("search", "");
                //set Fragmentclass Arguments
                home.setArguments(bundle);
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag, home).addToBackStack(null).commit();
            }
        });

        //MY ORDERS
        Button my_orders = dialog.findViewById(R.id.myorders);
        my_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new myorders(), R.id.frag, context);
                dialog.dismiss();
            }
        });

        Activity activity = (Activity) context;
        if (!activity.isFinishing()) {
            dialog.show();
        }

    }


}

