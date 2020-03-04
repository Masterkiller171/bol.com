package com.example.bol.logic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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

    public static void switchIntentShare(Context context,String appPackage, String input){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.setPackage(appPackage);

        i.putExtra(Intent.EXTRA_TEXT, input);
        try {
            context.startActivity(i);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "App has not been installed on this device!", Toast.LENGTH_SHORT).show();
        }
    }
}
