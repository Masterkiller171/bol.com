package com.example.bol.logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bol.gui.ProductActivity;
import com.example.bol.R;
import com.example.bol.domain.Product;
import com.squareup.picasso.Picasso;

public class BolAdapter extends RecyclerView.Adapter<BolAdapter.bolViewHolder>{
    private Product[] mDataset;
    private Context mContext;

    public BolAdapter(Product[] mDataset, Context context) {
        this.mDataset = mDataset;
        this.mContext = context;
    }

    public static class bolViewHolder extends RecyclerView.ViewHolder{
        private FrameLayout textView;

        public bolViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.rec_row);
        }
    }

    @NonNull
    @Override
    public bolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.recycler_row, parent, false);

        return new bolViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull bolViewHolder holder, final int position) {
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentSwitch.switchIntentWithData(mContext, ProductActivity.class, mDataset[position]);
            }
        });

        TextView view = holder.textView.findViewById(R.id.rec_txtv_title);
        if (mDataset[position].getTitle().length() > 30) {
            view.setText(mDataset[position].getTitle().substring(0, 30));
        }else{
            view.setText(mDataset[position].getTitle());
        }

        view = holder.textView.findViewById(R.id.rec_txtv_price);
        view.setText(String.valueOf(mDataset[position].getCurrentPrice()));

        view = holder.textView.findViewById(R.id.rec_txtv_summary);
        view.setText(mDataset[position].getSummary());

        ImageView img = holder.textView.findViewById(R.id.rec_img_product);

        Picasso.get().load(mDataset[position].getImageUrlSmall()).into(img);


    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
