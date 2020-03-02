package com.example.bol.GUI;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bol.R;
import com.example.bol.logic.NetworkUtils;

public class Eventlistener implements View.OnClickListener {
    private NetworkUtils mUtils;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_but_search:{
                EditText view = v.findViewById(R.id.main_txt_in);

                if (view != null) {
                    this.mUtils = new NetworkUtils(view.getText().toString(), (Activity) v.getContext());
                    this.mUtils.showBaseUrlJSON();
                }else{
                    Toast.makeText(v.getContext(), "No results found!", Toast.LENGTH_SHORT).show();
                }
            } break;
        }
    }
}
