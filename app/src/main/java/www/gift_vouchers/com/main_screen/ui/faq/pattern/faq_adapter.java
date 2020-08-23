package www.gift_vouchers.com.main_screen.ui.faq.pattern;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import www.gift_vouchers.com.main_screen.ui.faq.model.faq_model;
import www.gift_vouchers.com.main_screen.ui.myorders.model.my_order_list;

public class faq_adapter extends RecyclerView.Adapter<faq_adapter.view_holder> {

    Context context;
    ArrayList<faq_model> mylist;

    public faq_adapter(Context context, ArrayList<faq_model> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faq_item, parent, false);
        view_holder view_holder = new view_holder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        holder.question.setText(mylist.get(position).getQuestion());
        holder.answer.setText(mylist.get(position).getAnswer());
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


    class view_holder extends RecyclerView.ViewHolder {
        TextView question;
        TextView answer;

        public view_holder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            answer = itemView.findViewById(R.id.answer);

        }
    }


}
