package com.example.bol.gui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bol.R;
import com.example.bol.logic.IntentSwitch;
import com.example.bol.logic.NetworkManager;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private NetworkManager mNetworkManager;
    private final static String LANGUAGE_CODE = "lang";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.mNetworkManager = new NetworkManager(this);
        this.mNetworkManager.checkLanguage();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner sp = findViewById(R.id.set_spin_lang);
        sp.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Languages, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Log.i("LANGUAGE", Locale.getDefault().getDisplayLanguage());
        int spinnerPos = adapter.getPosition(mNetworkManager.getPreferences(LANGUAGE_CODE));
        sp.setAdapter(adapter);
        sp.setSelection(spinnerPos);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedLang = adapterView.getItemAtPosition(i).toString();
        String savedLang = mNetworkManager.getPreferences(LANGUAGE_CODE);

        if (!selectedLang.equals(savedLang)) {
            this.mNetworkManager.setPreferences(LANGUAGE_CODE, selectedLang);
            IntentSwitch.switchIntent(this, SettingsActivity.class);
            Toast.makeText(this, selectedLang + " set as language!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
