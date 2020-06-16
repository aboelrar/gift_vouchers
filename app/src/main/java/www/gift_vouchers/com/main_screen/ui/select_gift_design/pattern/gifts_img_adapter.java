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
import www.gift_vouchers.com.main_screen.ui.select_gift_design.model.gift_img_list;

public class gifts_img_adapter extends RecyclerView.Adapter<gifts_img_adapter.view_holder> {

    Context context;
    ArrayList<gift_img_list> mylist;
    int last_position = 1000;

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
                mylist.get(position).setStatus(true);
                holder.getgift.setBackgroundResource(R.drawable.selected_gift);


                //CHECK IF TRUE OR FALSE

                if (last_position == 1000) {
                } else if (mylist.get(last_position).isStatus() == false) {
                    holder.getgift.setBackgroundResource(R.drawable.next_page);
                }

                //SET LAST POSITION FALSE
                last_position = position;
                mylist.get(last_position).setStatus(false);

            }

        });




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
