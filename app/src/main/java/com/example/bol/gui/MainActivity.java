package com.example.bol.gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.bol.R;
import com.example.bol.domain.Product;
import com.example.bol.logic.IntentSwitch;
import com.example.bol.logic.NetworkManager;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListPopupWindow mListPopupWindow;
    private int mSortingButtonId;
    private NetworkManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.manager = new NetworkManager(this);
        this.manager.checkLanguage();

        setupMainActivity();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        SearchView view  = findViewById(R.id.main_txt_in);
        String input = view.getQuery().toString();
//        if (!input.isEmpty() || !input.equals("")){
            outState.putString("searchInput", input);

            if (manager != null){
                outState.putParcelableArray("searchOutput", manager.getProducts());
            }
//        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        this.manager.checkLanguage();
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.getString("searchInput") != null){
            Toast.makeText(this, "Page reloaded!", Toast.LENGTH_SHORT).show();
            SearchView view = findViewById(R.id.main_txt_in);
            view.setQuery(savedInstanceState.getString("searchInput"), true);
        }

        if (savedInstanceState.getParcelableArray("searchOutput") != null){
            Product[] products = toProducts(savedInstanceState.getParcelableArray("searchOutput"));

            manager = new NetworkManager(this);
            manager.insertAdapterData(products);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        IntentSwitch.switchIntent(this, SettingsActivity.class);
        return super.onOptionsItemSelected(item);
    }

    public static Product[] toProducts(Parcelable[] parcelables) {
        return Arrays.copyOf(parcelables, parcelables.length, Product[].class);
    }

    private void setupMainActivity(){
        findViewById(R.id.main_but_search).setOnClickListener(this);
        setupListPopUpWindow();
    }

    private void setupListPopUpWindow(){
        ImageView imgV = findViewById(R.id.main_img_filter);
        imgV.setOnClickListener(this);
        mListPopupWindow = new ListPopupWindow(this);
        mListPopupWindow.setAnchorView(imgV);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mListPopupWindow.setWidth(metrics.widthPixels);
        mListPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
        mListPopupWindow.setModal(true);

        View filterLayout = getLayoutInflater().inflate(R.layout.filterlayout, null);
        mListPopupWindow.setPromptView(filterLayout);

        filterLayout.findViewById(R.id.filter_but_hightolow).setOnClickListener(this);
        filterLayout.findViewById(R.id.filter_but_lowtohigh).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_but_search: onSearch();break;
            case R.id.main_img_filter: onFilter();break;
            case R.id.filter_but_hightolow:
            case R.id.filter_but_lowtohigh:{
                this.mSortingButtonId = view.getId();
                onSearch();
            }break;
        }
    }

    private void onSearch(){
        SearchView input = findViewById(R.id.main_txt_in);
        this.manager.showProducts(mSortingButtonId, input.getQuery().toString());
    }

    private void onFilter(){
        mListPopupWindow.show();
    }
}
