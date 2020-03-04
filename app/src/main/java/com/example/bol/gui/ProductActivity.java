package com.example.bol.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bol.R;
import com.example.bol.domain.Product;
import com.example.bol.logic.IntentSwitch;
import com.example.bol.logic.NetworkManager;
import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {
    private NetworkManager mNetworkManager;
    private TextView mProductName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mNetworkManager = new NetworkManager(this);
        this.mNetworkManager.checkLanguage();
        setContentView(R.layout.activity_product);
        setupData();
    }

    private void setupData(){
        Intent intent = getIntent();
        Product product = intent.getParcelableExtra("product");

        mProductName = findViewById(R.id.product_txt_name);
        mProductName.setText(product.getTitle());

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

        findViewById(R.id.product_but_sharewa).setOnClickListener(this);
        findViewById(R.id.product_but_shared).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.product_but_sharewa:{
                shareProductOnWhatsapp();
            }break;
            case R.id.product_but_shared:{
                shareProductOnDiscord();
            }break;
        }
    }

    private void shareProductOnWhatsapp(){
        IntentSwitch.switchIntentShare(this, "com.whatsapp", this.mProductName.getText().toString());
    }
    private void shareProductOnDiscord(){
        IntentSwitch.switchIntentShare(this, "com.discord", this.mProductName.getText().toString());
    }
}
