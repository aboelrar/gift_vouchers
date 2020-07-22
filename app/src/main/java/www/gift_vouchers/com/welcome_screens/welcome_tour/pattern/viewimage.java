package www.gift_vouchers.com.welcome_screens.welcome_tour.pattern;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import www.gift_vouchers.com.R;


public class viewimage extends PagerAdapter implements View.OnClickListener {
    Context context;

    public viewimage(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (LinearLayout) o;
    }

    int pos;

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        pos = position;
        View view = null;

        if (position == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.f_layout_tour, container, false);

        } else if (position == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.s_layout_tour, container, false);
        } else if (position == 2) {
            view = LayoutInflater.from(context).inflate(R.layout.t_layout_tour, container, false);

        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {


    }
}