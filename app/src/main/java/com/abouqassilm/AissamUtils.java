package com.abouqassilm;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

public class AissamUtils {
    static String black = "#FF242426";
    static String white = "#FFFFFFFF";
    static String amoled = "#FF121212";
    static String grey = "#9E9E9E";
    static String soft_dark = "#FF1B1B1B";
    static String blue = "#2196F3";
    static void setLightBars(Activity activity) {
        activity.getWindow().setStatusBarColor(Color.parseColor(white));
        activity.getWindow().setNavigationBarColor(Color.parseColor(white));
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
}

