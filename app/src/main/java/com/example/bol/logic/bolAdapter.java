package com.example.bol.logic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bol.R;
import com.example.bol.domain.Product;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
    public void onBindViewHolder(@NonNull bolViewHolder holder, int position) {
        TextView view = holder.textView.findViewById(R.id.rec_txtv_id);

        view.setText(String.valueOf(mDataset[position].getId()));

        ImageView img = holder.textView.findViewById(R.id.rec_img_product);

        Picasso.get().load(mDataset[position].getImageUrl().toString()).into(img);
//        holder.textView.addView(view);



    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }


}
