package com.example.bol.logic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.bol.domain.Product;

public class IntentSwitch {
    public static void switchIntent(Activity activity, Class classObj){
        Intent i = new Intent(activity, classObj);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.startActivity(i);
    }

    public static void switchIntentWithData(Context context, Class classObj, Product product){
        Intent i = new Intent(context, classObj);
        i.putExtra("product", product);
        context.startActivity(i);
    }
}
