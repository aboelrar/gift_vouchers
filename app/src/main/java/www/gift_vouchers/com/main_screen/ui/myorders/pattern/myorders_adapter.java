package www.gift_vouchers.com.main_screen.ui.myorders.pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.main_screen.ui.compainies.model.companies_list;
import www.gift_vouchers.com.main_screen.ui.details.ui.company_details;
import www.gift_vouchers.com.main_screen.ui.myorders.model.my_order_list;
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
        View view = LayoutInflater.from(context).inflate(R.layout.myorder_item, parent, false);
        view_holder view_holder = new view_holder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        holder.name.setText(mylist.get(position).getName());
        holder.code.setText(mylist.get(position).getCode());
        holder.price.setText(mylist.get(position).getPrice());
        Glide.with(context).load(mylist.get(position).getImage()).into(holder.logo);

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


        public view_holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            logo = itemView.findViewById(R.id.logo);
            code = itemView.findViewById(R.id.code);
            price = itemView.findViewById(R.id.price);

        }
    }


}
