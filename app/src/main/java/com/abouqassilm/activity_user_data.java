package com.abouqassilm;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Objects;

public class activity_user_data extends AppCompatActivity {
    LinearLayout cnt;
    TextView cntText;
    TextView ques;
    LinearLayout background;
    LinearLayout dataBackground;
    NumberPicker day;
    NumberPicker month;
    NumberPicker year;
    public int yearNumberPicker = 0;
    public int monthNumberPicker = 0;
    public int dayNumberPicker = 0;
    void buttonEffect(LinearLayout linear, String color, String rippleColor,  boolean light) {
        {
            android.graphics.drawable.GradientDrawable ui = new android.graphics.drawable.GradientDrawable();
            int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
            ui.setColor(Color.parseColor(color));
            if (light)
                linear.setElevation(d * 5);
            ui.setCornerRadius(d * 15);
            android.graphics.drawable.RippleDrawable ripped;
            ripped = new RippleDrawable(new android.content.res.ColorStateList(new int[][]{new  int[]{}}, new int[]{Color.parseColor(rippleColor)}), ui, null);
            linear.setBackground(ripped);
            linear.setClickable(true);
        }
    }
    void viewEffect(LinearLayout linear , String color, boolean light) {
        {
            android.graphics.drawable.GradientDrawable ui = new android.graphics.drawable.GradientDrawable();
            int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
            ui.setColor(Color.parseColor(color));
            if (light)
                linear.setElevation(d * 5);
            ui.setCornerRadius(d * 15);
            linear.setBackground(ui);
        }
    }
    void color(TextView text, String color){
        text.setTextColor(Color.parseColor(color));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        cnt = findViewById(R.id.cnt);
        cntText = findViewById(R.id.cntText);
        ques = findViewById(R.id.ques);
        background = findViewById(R.id.background);
        dataBackground = findViewById(R.id.dataBackground);

        day = findViewById(R.id.day);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);

        day.setMinValue(1);
        day.setMaxValue(31);
        month.setMinValue(1);
        month.setMaxValue(12);
        year.setMinValue(1900);
        year.setMaxValue(Calendar.getInstance().get(Calendar.YEAR));
        year.setValue(2000);

        year.setOnValueChangedListener((numberPicker, i, i1) -> {
            sharedPreferences.edit().putString("year", String.valueOf(i1)).apply();
        });

        month.setOnValueChangedListener((numberPicker, i, i1) -> {
            sharedPreferences.edit().putString("month", String.valueOf(i1)).apply();
        });
        day.setOnValueChangedListener((numberPicker, i, i1) -> {
            sharedPreferences.edit().putString("day", String.valueOf(i1)).apply();
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            year.setSelectionDividerHeight(0);
            month.setSelectionDividerHeight(0);
            day.setSelectionDividerHeight(0);
        }
        if (sharedPreferences.contains("dark")) {
            if (sharedPreferences.getString("dark", "").equals("on")){
                AissamUtils.setDarkBars(this);
                background.setBackgroundColor(Color.parseColor(AissamUtils.black));
                color(ques, AissamUtils.white);
                color(cntText, AissamUtils.white);
                buttonEffect(dataBackground, "#121313", "#121313", false);
                buttonEffect(cnt, AissamUtils.amoled, AissamUtils.grey, false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    year.setTextColor(Color.WHITE);
                    month.setTextColor(Color.WHITE);
                    day.setTextColor(Color.WHITE);
                }
            } else {
                AissamUtils.setLightBars(this);
                background.setBackgroundColor(Color.parseColor(AissamUtils.white));
                color(ques, AissamUtils.black);
                color(cntText, AissamUtils.black);
                buttonEffect(cnt, AissamUtils.white, AissamUtils.grey, true);
                buttonEffect(dataBackground,"#FFFFFF", "#121313", false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    year.setTextColor(Color.BLACK);
                    month.setTextColor(Color.BLACK);
                    day.setTextColor(Color.BLACK);
                }
            }
        }
        cnt.setOnClickListener(new View.OnClickListener() {
            final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            @Override
            public void onClick(View view) {
                if (sharedPreferences.getString("year", "").isEmpty())
                    sharedPreferences.edit().putString("year", "2000").apply();
                if (sharedPreferences.getString("day", "").isEmpty())
                    sharedPreferences.edit().putString("day", "1").apply();
                if (sharedPreferences.getString("month", "").isEmpty())
                    sharedPreferences.edit().putString("month", "1").apply();
                v.vibrate(20);
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}