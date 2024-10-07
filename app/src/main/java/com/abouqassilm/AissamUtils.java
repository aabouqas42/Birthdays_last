package com.abouqassilm;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AissamUtils {
    static String black = "#FF242426";
    static String white = "#FFFFFFFF";
    static String amoled = "#FF121212";
    static String grey = "#9E9E9E";
    static String soft_dark = "#FF1B1B1B";
    static String blue = "#2196F3";
    static String whiteSmoke = "#F5F5F5";
    static String brandiesBlue = "#0070FF";
    static void setLightBars(Activity activity) {
        activity.getWindow().setStatusBarColor(Color.parseColor(whiteSmoke));
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        activity.getWindow().setNavigationBarColor(Color.parseColor(whiteSmoke));
    }
    static void setDarkBars(Activity activity) {
        activity.getWindow().setStatusBarColor(Color.parseColor(black));
        activity.getWindow().setNavigationBarColor(Color.parseColor(black));
    }
    static void setTextColorDark(TextView text){
        text.setTextColor(Color.WHITE);
    }
    static void setTextColorLight(TextView text){
        text.setTextColor(Color.BLACK);
    }
    static void setFilterToImage(ImageView image, int color){
        Drawable Icon = image.getDrawable();
        Icon.setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }
    static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}

