package com.abouqassilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import com.kyleduo.switchbutton.SwitchButton;

public class settings extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    private static NumberPicker day;
    @SuppressLint("StaticFieldLeak")
    private static NumberPicker month;
    @SuppressLint("StaticFieldLeak")
    private static NumberPicker year;
    private SwitchButton themeSwitcher;
    public char switched = 0;
    LinearLayout background;
    LinearLayout themeSwitcherButton;
    LinearLayout back;
    LinearLayout set_edit;
    TextView tedit;
    TextView title;
    ImageView backIcon;
    LinearLayout dBackground;
    LinearLayout dLine;
    LinearLayout dEdit;
    LinearLayout dCancel;
    TextView dText1;
    TextView dText2;
    SharedPreferences sharedPreferences;
    void setColorToSystem(String color, LinearLayout l, boolean dark){
        Window win = getWindow();
        win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        win.setStatusBarColor(Color.parseColor(color));
        if (dark) {
            l.setSystemUiVisibility(0);
        } else {l.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);}
        l.setBackgroundColor(Color.parseColor(color));
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(Color.parseColor(color));
        }
    }
    void color(TextView text, String color){
        text.setTextColor(Color.parseColor(color));
    }
    void setFont(TextView text) {
        Typeface customFont = ResourcesCompat.getFont(this, R.font.visby_round);
        text.setTypeface(customFont);
    }
    void filter(ImageView image, String color){
        Drawable Icon = image.getDrawable();
        Icon.setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN);
    }
    void buttonEffect(LinearLayout linear, String color, String rippleColor) {
        {
            GradientDrawable ui = new GradientDrawable();
            int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
            ui.setColor(Color.parseColor(color));
            if (!sharedPreferences.getString("dark", "").equals("on") && linear != back)
                linear.setElevation(d * 5);
            if (linear == dEdit || linear == dCancel) {
                float cornerRadius = (float) d * 15;
                if (linear == dEdit) {
                    ui.setCornerRadii(new float[]{
                            0.0f, 0.0f,      // Top-left radius (flat)
                            0.0f, 0.0f,      // Top-right radius (flat)
                            cornerRadius, 0.0f, // Bottom-right radius
                            cornerRadius, 0.0f  // Bottom-left radius
                    });
                } else {
                    ui.setCornerRadii(new float[]{
                            0.0f, 0.0f,      // Top-left radius (flat)
                            0.0f, 0.0f,      // Top-right radius (flat)
                            cornerRadius, cornerRadius, // Bottom-right radius
                            cornerRadius, cornerRadius  // Bottom-left radius
                    });
                }

            } else {
                if (linear == back)
                    ui.setCornerRadius(d * 50);
                else
                    ui.setCornerRadius(d * 15);
            }
            RippleDrawable ripped;
            ripped = new RippleDrawable(new ColorStateList(new int[][]{new  int[]{}}, new int[]{Color.parseColor(rippleColor)}), ui, null);
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
    void applyDarkTheme() {
        sharedPreferences.edit().putString("dark", "on").apply();
        AissamUtils.setDarkBars(this);
        themeSwitcher.setChecked(true);
        ((TextView) findViewById(R.id.themeTitle)).setTextColor(Color.WHITE);
        background.setBackgroundColor(Color.parseColor(AissamUtils.black));
        buttonEffect(themeSwitcherButton, AissamUtils.soft_dark, AissamUtils.grey);
        buttonEffect(back, AissamUtils.black, AissamUtils.grey);
        buttonEffect(set_edit, AissamUtils.soft_dark, AissamUtils.grey);
        filter(backIcon, AissamUtils.white);
        color(tedit, AissamUtils.white);
        color(title, AissamUtils.white);
        // for dialog
        viewEffect(dBackground, AissamUtils.black, false);
        viewEffect(dLine, AissamUtils.white, false);
        color(dText1, AissamUtils.white);
        color(dText2, AissamUtils.white);
        color(title, AissamUtils.white);
        buttonEffect(dEdit, AissamUtils.soft_dark, AissamUtils.grey);
        buttonEffect(dCancel, AissamUtils.soft_dark, AissamUtils.grey);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            year.setTextColor(Color.WHITE);
            month.setTextColor(Color.WHITE);
            day.setTextColor(Color.WHITE);
        }
    }
    void applyLightTheme() {
        sharedPreferences.edit().putString("dark", "off").apply();
        AissamUtils.setLightBars(this);
        themeSwitcher.setChecked(false);
        ((TextView) findViewById(R.id.themeTitle)).setTextColor(Color.BLACK);
        background.setBackgroundColor(Color.parseColor(AissamUtils.white));
        buttonEffect(themeSwitcherButton, AissamUtils.white, AissamUtils.grey);
        buttonEffect(back, AissamUtils.white, AissamUtils.grey);
        buttonEffect(set_edit, AissamUtils.white, AissamUtils.grey);
        filter(backIcon, AissamUtils.black);
        color(tedit, AissamUtils.black);
        color(title, AissamUtils.black);
        viewEffect(dBackground, AissamUtils.white, false);
        viewEffect(dLine, AissamUtils.soft_dark, false);
        color(dText1, AissamUtils.black);
        color(dText2, AissamUtils.black);
        buttonEffect(dEdit, AissamUtils.white, AissamUtils.grey);
        buttonEffect(dCancel, AissamUtils.white, AissamUtils.grey);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            year.setTextColor(Color.BLACK);
            month.setTextColor(Color.BLACK);
            day.setTextColor(Color.BLACK);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        themeSwitcher = findViewById(R.id.themeSwitcher);
        background =  findViewById(R.id.background);
        themeSwitcherButton = findViewById(R.id.themeSwitcherButton);
        back = findViewById(R.id.back);
        set_edit = findViewById(R.id.set_edit);
        tedit = findViewById(R.id.tedit);
        title = findViewById(R.id.title);
        backIcon = findViewById(R.id.backIcon);
        sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        /*--------------DIALOG-------------*/
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_ui);
        dialog.getWindow().setBackgroundDrawableResource(R.color.traspaernt);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dBackground = dialog.findViewById(R.id.dBackground);
        dLine =       dialog.findViewById(R.id.dLine);
        dEdit =       dialog.findViewById(R.id.dEdit);
        dCancel =     dialog.findViewById(R.id.dCancel);
        dText1 =      dialog.findViewById(R.id.dText1);
        dText2 =      dialog.findViewById(R.id.dText2);

        day =   dialog.findViewById(R.id.day);
        month = dialog.findViewById(R.id.month);
        year =  dialog.findViewById(R.id.year);

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
        themeSwitcher.setTintColor(Color.parseColor(AissamUtils.green));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            year.setSelectionDividerHeight(0);
            month.setSelectionDividerHeight(0);
            day.setSelectionDividerHeight(0);
        }

        dEdit.setOnClickListener(v -> {
            dialog.dismiss();
        });
        themeSwitcherButton.setOnClickListener(view -> {
            if (sharedPreferences.getString("dark", "").equals("off")) {
                applyDarkTheme();
            } else {
                applyLightTheme();
            }
        });
//        themeSwitcher.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (switched == 0 && sharedPreferences.getString("dark", "").equals("off")) {
//                applyDarkTheme();
//            } else {
//                applyLightTheme();
//            }
//            if (switched == 0)
//                switched = 1;
//            else
//                switched = 0;
//        });
        if (sharedPreferences.getString("dark", "").equals("on")) {
            applyDarkTheme();
        } else {
            applyLightTheme();
        }
        back.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), Home.class);
            startActivity(i);
        });
        dCancel.setOnClickListener(v -> dialog.dismiss());
        set_edit.setOnClickListener(view -> {
            String day = sharedPreferences.getString("day", "");
            String month = sharedPreferences.getString("month", "");
            String year = sharedPreferences.getString("year", "");
            settings.day.setValue(Integer.parseInt(Integer.parseInt(day) < 10 ? "0" + day : day));
            settings.month.setValue(Integer.parseInt(month));
            settings.year.setValue(Integer.parseInt(year));
            setFont(dText1);
            setFont(dText2);
            dialog.show();
        });
        setFont(title);
        setFont(tedit);
    }
}