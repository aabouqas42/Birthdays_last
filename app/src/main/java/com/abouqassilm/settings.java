package com.abouqassilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class settings extends AppCompatActivity {
    private static NumberPicker day;
    private static NumberPicker month;
    private static NumberPicker year;
    private Switch  themeSwitcher;
    boolean b = true;
    public boolean swtched = false;
    LinearLayout background;
    LinearLayout themeSwitcherButton;
    LinearLayout line;
    LinearLayout back;
    LinearLayout set_edit;
    TextView tedit;
    TextView set;
    ImageView back_icon;
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
            if (sharedPreferences.getString("dark", null).equals("off"))
                linear.setElevation(d * 5);
            ui.setCornerRadius(d * 15);
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
    void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    void applyDarkTheme() {
        setColorToSystem(AissamUtils.black, background, true);
        themeSwitcher.setTextColor(Color.WHITE);
        background.setBackgroundColor(Color.parseColor(AissamUtils.black));
        viewEffect(line, AissamUtils.soft_dark, false);
        buttonEffect(themeSwitcherButton, AissamUtils.soft_dark, AissamUtils.grey);
        buttonEffect(back, AissamUtils.soft_dark, AissamUtils.grey);
        buttonEffect(set_edit, AissamUtils.soft_dark, AissamUtils.grey);
        filter(back_icon, AissamUtils.white);
        color(tedit, AissamUtils.white);
        color(set, AissamUtils.white);
        // for dialog
        viewEffect(dBackground, AissamUtils.black, false);
        viewEffect(dLine, AissamUtils.white, false);
        color(dText1, AissamUtils.white);
        color(dText2, AissamUtils.white);
        color(set, AissamUtils.white);
        sharedPreferences.edit().putString("dark", "on").apply();
        buttonEffect(dEdit, AissamUtils.soft_dark, AissamUtils.grey);
        buttonEffect(dCancel, AissamUtils.soft_dark, AissamUtils.grey);
    }
    void applyLightTheme() {
        setColorToSystem(AissamUtils.white, background, false);
        themeSwitcher.setTextColor(Color.BLACK);
        background.setBackgroundColor(Color.parseColor(AissamUtils.white));
        viewEffect(line, AissamUtils.white, true);
        buttonEffect(themeSwitcherButton, AissamUtils.white, AissamUtils.grey);
        buttonEffect(back, AissamUtils.white, AissamUtils.grey);
        buttonEffect(set_edit, AissamUtils.white, AissamUtils.grey);
        filter(back_icon, AissamUtils.black);
        color(tedit, AissamUtils.black);
        color(set, AissamUtils.black);
        viewEffect(dBackground, AissamUtils.white, false);
        viewEffect(dLine, AissamUtils.soft_dark, false);
        color(dText1, AissamUtils.black);
        color(dText2, AissamUtils.black);
        sharedPreferences.edit().putString("dark", "off").apply();
        buttonEffect(dEdit, AissamUtils.white, AissamUtils.soft_dark);
        buttonEffect(dCancel, AissamUtils.white, AissamUtils.soft_dark);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        themeSwitcher = findViewById(R.id.themeSwitcher);
        background =  findViewById(R.id.background);
        themeSwitcherButton = findViewById(R.id.themeSwitcherButton);
        line = findViewById(R.id.line);
        back = findViewById(R.id.back);
        set_edit = findViewById(R.id.set_edit);
        tedit = findViewById(R.id.tedit);
        set = findViewById(R.id.set);
        back_icon = findViewById(R.id.back_icon);
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            year.setSelectionDividerHeight(0);
            month.setSelectionDividerHeight(0);
            day.setSelectionDividerHeight(0);
        }

        dEdit.setOnClickListener(v -> {
            dialog.dismiss();
        });
        themeSwitcher.setChecked(sharedPreferences.getString("dark", "").equals("on"));
        themeSwitcherButton.setOnClickListener(view -> {
            if (!swtched && sharedPreferences.getString("dark", "").equals("off")) {
                applyDarkTheme();
            } else {
                applyLightTheme();
            }
            swtched = swtched == false;
            themeSwitcher.setChecked(swtched);
        });
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
        setFont(set);
        setFont(tedit);
    }
}