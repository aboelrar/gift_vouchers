package www.gift_vouchers.com.main_screen_company.ui.my_orders_compant.pattern;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.main_screen.ui.details.ui.company_details;
import www.gift_vouchers.com.main_screen_company.ui.details.ui.myorder_details;
import www.gift_vouchers.com.main_screen_company.ui.my_orders_compant.model.my_order_list;
import www.gift_vouchers.com.main_screen_company.ui.my_orders_compant.ui.myorders_company;
import www.gift_vouchers.com.utils.utils;

public class myorders_adapter extends RecyclerView.Adapter<myorders_adapter.view_holder> {

    Context context;
    ArrayList<my_order_list> mylist;

    public myorders_adapter(Context context, ArrayList<my_order_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.myorder_company_item, parent, false);
        view_holder view_holder = new view_holder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        holder.name.setText(mylist.get(position).getName());
        holder.code.setText(mylist.get(position).getCode());
        holder.price.setText(mylist.get(position).getPrice());

        //SHIMMER FOR IMAGES
        Glide.with(context)
                .load(mylist.get(position).getImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.container.stopShimmerAnimation();
                        return false;
                    }
                })
                .into(holder.logo);

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment home = new myorder_details();
                Bundle bundle = new Bundle();
                bundle.putString("id", mylist.get(position).getId());
                //set Fragmentclass Arguments
                home.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag, home).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


    class view_holder extends RecyclerView.ViewHolder {
        TextView name;
        TextView code;
        CircleImageView logo;
        TextView price;
        Button details;
        ShimmerFrameLayout container;

        public view_holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            logo = itemView.findViewById(R.id.logo);
            code = itemView.findViewById(R.id.code);
            price = itemView.findViewById(R.id.price);
            details = itemView.findViewById(R.id.details);

            container = itemView.findViewById(R.id.container);
            container.startShimmerAnimation();

        }
    }


}
