package com.example.bol.gui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bol.R;
import com.example.bol.domain.Product;
import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        setupData();
    }

    private void setupData(){
        Intent intent = getIntent();
        Product product = intent.getParcelableExtra("product");

        TextView productName = findViewById(R.id.product_txt_name);
        productName.setText(product.getTitle());

        TextView productPrice = findViewById(R.id.product_txt_price);
        productPrice.setText(String.valueOf(product.getCurrentPrice()));

        TextView productShortDescription = findViewById(R.id.product_txt_shortdesc);
        productShortDescription.setText(product.getShortDescription());

        ImageView img = findViewById(R.id.product_img_background);
        Picasso.get().load(product.getImageUrlBig()).into(img);

        TextView rating  = findViewById(R.id.product_txt_rating);
        StringBuilder sb = new StringBuilder();
        sb.append("Rating: ");
        sb.append(((double)product.getRating() / 10));
        sb.append("/");
        sb.append(10);

        rating.setText(sb.toString());
    }
}
