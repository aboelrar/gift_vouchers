package www.gift_vouchers.com.main_screen.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.main_screen.ui.compainies.ui.compainies;


public class search_dialog {
    public void dialog(final Context context, int resource, double widthh) {
        final Dialog dialog = new Dialog(context);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);

        WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();

        wlp.gravity = Gravity.TOP;
        dialog.getWindow().setAttributes(wlp);

        EditText search_txt = dialog.findViewById(R.id.search_name);

        ImageView search = dialog.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment home = new compainies();
                Bundle bundle = new Bundle();
                bundle.putString("search", search_txt.getText().toString());
                //set Fragmentclass Arguments
                home.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag, home).addToBackStack(null).commit();

                //DISMIISS DIALOG
                dialog.dismiss();
            }
        });

        dialog.show();


    }


}

