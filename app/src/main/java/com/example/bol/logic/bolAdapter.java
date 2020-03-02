package com.example.bol.logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bol.gui.ProductActivity;
import com.example.bol.R;
import com.example.bol.domain.Product;
import com.squareup.picasso.Picasso;

public class bolAdapter extends RecyclerView.Adapter<bolAdapter.bolViewHolder> {
    private Product[] mDataset;
    private Context context;

    public bolAdapter(Product[] mDataset, Context context) {
        this.mDataset = mDataset;
        this.context = context;
    }

    public static class bolViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout textView;

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
                IntentSwitch.switchIntentWithData(context, ProductActivity.class, mDataset[position]);
            }
        });

        TextView view = holder.textView.findViewById(R.id.rec_txtv_id);

        view.setText(String.valueOf(mDataset[position].getId()));

        ImageView img = holder.textView.findViewById(R.id.rec_img_product);

        Picasso.get().load(mDataset[position].getImageUrlSmall().toString()).into(img);
//        holder.textView.addView(view);



    }

    @Override
    public int getItemCount() {
        Toast.makeText(context, mDataset.length + " results found!", Toast.LENGTH_SHORT).show();
        return mDataset.length;
    }
}
