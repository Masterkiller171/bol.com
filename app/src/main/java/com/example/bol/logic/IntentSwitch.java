package com.example.bol.logic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class IntentSwitch {
    public static void switchIntent(Context context, Class classObj){
        Intent i = new Intent(context, classObj);
        context.startActivity(i);
    }
}
