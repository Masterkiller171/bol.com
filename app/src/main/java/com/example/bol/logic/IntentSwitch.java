package com.example.bol.logic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.bol.domain.Product;

public class IntentSwitch {
    public static void switchIntent(Context context, Class classObj){
        Intent i = new Intent(context, classObj);
        context.startActivity(i);
    }

    public static void switchIntentWithData(Context context, Class classObj, Product product){
        Intent i = new Intent(context, classObj);
        i.putExtra("product", product);
        context.startActivity(i);
    }
}
