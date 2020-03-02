package com.example.bol.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.bol.R;
import com.example.bol.dal.NetworkUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListPopupWindow listPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupMainActivity();
    }

    private void setupMainActivity(){
        findViewById(R.id.main_but_search).setOnClickListener(this);

        ImageView imgV = findViewById(R.id.main_img_filter);
        imgV.setOnClickListener(this);

        listPopupWindow = new ListPopupWindow(this);
        listPopupWindow.setAnchorView(imgV);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        listPopupWindow.setWidth(metrics.widthPixels);
        listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setModal(true);

        View filterLayout = getLayoutInflater().inflate(R.layout.filterlayout, null);
        listPopupWindow.setPromptView(filterLayout);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_but_search: onSearch();break;
            case R.id.main_img_filter: onFilter();break;
        }
    }

    private void onSearch(){
        SearchView input = findViewById(R.id.main_txt_in);
        if (input != null) {
            NetworkUtils mUtils = new NetworkUtils(input.getQuery().toString(), this);
            mUtils.showBaseUrlJSON();
        }else{
            Toast.makeText(this, "No results found!", Toast.LENGTH_SHORT).show();
        }
    }

    private void onFilter(){
        listPopupWindow.show();
    }
}
