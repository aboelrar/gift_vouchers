package www.gift_vouchers.com.main_screen.ui.compainies.pattern;

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
import www.gift_vouchers.com.auth.ui.signup.ui.signup;
import www.gift_vouchers.com.main_screen.ui.compainies.model.companies_list;
import www.gift_vouchers.com.main_screen.ui.details.ui.company_details;
import www.gift_vouchers.com.utils.utils;

public class companies_adapter extends RecyclerView.Adapter<companies_adapter.view_holder> {

    Context context;
    ArrayList<companies_list> mylist;

    public companies_adapter(Context context, ArrayList<companies_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.company_item, parent, false);
        view_holder view_holder = new view_holder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        holder.name.setText(mylist.get(position).getName());
        Glide.with(context).load(mylist.get(position).getLogo()).into(holder.logo);

        //SET ON CLICK LISTNERS
        holder.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new company_details(), R.id.frag, context);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


    class view_holder extends RecyclerView.ViewHolder {
        TextView name;
        CircleImageView logo;
        ImageView go;

        public view_holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            logo = itemView.findViewById(R.id.logo);
            go = itemView.findViewById(R.id.go);

        }
    }


}
