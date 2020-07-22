package www.gift_vouchers.com.main_screen.ui.select_gift_design.pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.local_data.send_data;
import www.gift_vouchers.com.main_screen.ui.select_gift_design.model.gift_img_list;

public class gifts_img_adapter extends RecyclerView.Adapter<gifts_img_adapter.view_holder> {

    Context context;
    ArrayList<gift_img_list> mylist;
    int last_position = 1000;
    public static String choose_color = "0";

    public gifts_img_adapter(Context context, ArrayList<gift_img_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gift_item, parent, false);
        view_holder view_holder = new view_holder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        Glide.with(context).load(mylist.get(position).getGift_img()).into(holder.gift_image);


        //SET ON CLICK LISTNER
        holder.getgift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SET LAST POSITION FALSE
                last_position = position;
                notifyDataSetChanged();

                //ADD DESIGN TO SQLLITE
                send_data.set_design(context, mylist.get(position).getGift_img());

                //SET COLOR
                choose_color = "1";


            }

        });

        if (last_position == position) {
            holder.getgift.setBackgroundResource(R.drawable.selected_gift);
        } else {
            holder.getgift.setBackgroundResource(R.drawable.next_page);
        }


    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


    class view_holder extends RecyclerView.ViewHolder {
        ImageView gift_image;
        Button getgift;

        public view_holder(@NonNull View itemView) {
            super(itemView);
            gift_image = itemView.findViewById(R.id.gift_img);
            getgift = itemView.findViewById(R.id.getgift);
        }
    }

}
