package com.abouqassilm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import com.abouqassilm.AissamUtils;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.time.*;

import android.graphics.drawable.*;
import androidx.core.content.res.ResourcesCompat;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import android.graphics.*;

import kotlin.Unit;

public class Home extends AppCompatActivity {

    LinearLayout background;
    LinearLayout menu;
    TextView userDate;
    TextView yy;
    TextView dd_mm;
    TextView hello;
    TextView dds;
    ImageView menuIcon;
    Intent settings;
    CircularProgressBar circularProgressBar;
    ImageView           calen1;
    ImageView           calen2;
    short   progress = 0;

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
    void setFont(TextView text) {
        Typeface customFont = ResourcesCompat.getFont(this, R.font.visby_round);
        text.setTypeface(customFont);
    }
    void buttonEffect(LinearLayout linear, String color, String rippleColor,  boolean light) {
        {
           GradientDrawable ui = new GradientDrawable();
            int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
            ui.setColor(Color.parseColor(color));
            if (light)
                linear.setElevation(d * 5);
            ui.setCornerRadius(d * 30);
            RippleDrawable ripped;
            ripped = new RippleDrawable(new ColorStateList(new int[][]{new  int[]{}}, new int[]{Color.parseColor(rippleColor)}), ui, null);
            linear.setBackground(ripped);
            linear.setClickable(true);
        }
    }
    void viewEffect (LinearLayout linear , String color, boolean light) {
        GradientDrawable ui = new GradientDrawable();
        int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
        ui.setColor(Color.parseColor(color));
        if (light)
            linear.setElevation(d * 5);
        ui.setCornerRadius(d * 15);
        linear.setBackground(ui);
    }
    void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public enum IDS {
        line1, line2, line3, menu, userDate, yy, dd_mm, countDown, background
    };
    void Progress () {
        if (circularProgressBar == null)
                return;

        // or with animation
        circularProgressBar.setProgressWithAnimation(progress, 2000L); // =1s

        // Set Progress Max
        circularProgressBar.setProgressMax(100);

        // Set ProgressBar Color
        circularProgressBar.setProgressBarColor(Color.parseColor(AissamUtils.blue));
        // or with gradient
//        circularProgressBar.setProgressBarColorStart(Color.GRAY);
//        circularProgressBar.setProgressBarColorEnd(Color.RED);
//        circularProgressBar.setProgressBarColorDirection(CircularProgressBar.GradientDirection.TOP_TO_BOTTOM);

        // Set background ProgressBar Color
        circularProgressBar.setBackgroundProgressBarColor(Color.GRAY);
        // or with gradient
//        circularProgressBar.setBackgroundProgressBarColorStart(Color.WHITE);
//        circularProgressBar.setBackgroundProgressBarColorEnd(Color.RED);
//        circularProgressBar.setBackgroundProgressBarColorDirection(CircularProgressBar.GradientDirection.TOP_TO_BOTTOM);

        // Set Width
        circularProgressBar.setBackgroundProgressBarWidth(8); // in DP

        // Other
        circularProgressBar.setRoundBorder(true);
        circularProgressBar.setStartAngle(180f);
        circularProgressBar.setProgressDirection(CircularProgressBar.ProgressDirection.TO_RIGHT);
        circularProgressBar.setProgressBarWidth(15);
        circularProgressBar.setActivated(true);
        circularProgressBar.setOnIndeterminateModeChangeListener(isEnable -> {
            // Do something
            return Unit.INSTANCE;
        });

        circularProgressBar.setOnProgressChangeListener(progress -> {
            // Do something
            return Unit.INSTANCE;
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        background = findViewById(R.id.background);
        menu = findViewById(R.id.menu);
        calen1 = findViewById(R.id.calen1);
        calen2 = findViewById(R.id.calen2);
        userDate = findViewById(R.id.uerdata);
        yy = findViewById(R.id.yy);
        dd_mm = findViewById(R.id.dd_mm);
        hello = findViewById(R.id.hello);
        dds = findViewById(R.id.dayofwake);
        menuIcon = findViewById(R.id.menuIcon);
        settings = new Intent(getApplicationContext(), settings.class);
        circularProgressBar = findViewById(R.id.circularProgressBar);
        setFont(userDate);
        setFont(hello);
        setFont(yy);
        setFont(dd_mm);
        setFont(dds);
        SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor sh = sharedPreferences.edit();


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(settings);
            }
        });

        if (sharedPreferences.contains("day") && sharedPreferences.contains("month") && sharedPreferences.contains("year")) {
            int day = Integer.parseInt(sharedPreferences.getString("day", "0"));
            int month = Integer.parseInt(sharedPreferences.getString("month", "0"));
            int year = Integer.parseInt(sharedPreferences.getString("year", "0"));
            userDate.setText(new StringBuilder().append((day < 10) ? "0" + day : day).append(" • ").append((month < 10) ? "0" + month : month).append(" • ").append(year));
            hello.setText("Birthdays");
        }
        AissamUtils.setFilterToImage(calen1, Color.parseColor(AissamUtils.blue));
        AissamUtils.setFilterToImage(calen2, Color.parseColor(AissamUtils.blue));
        if (sharedPreferences.contains("dark")) {
            if (sharedPreferences.getString("dark", "").equals("on")) {
                AissamUtils.setDarkBars(this);
                AissamUtils.setTextColorDark(userDate);
                AissamUtils.setTextColorDark(dd_mm);
                AissamUtils.setTextColorDark(yy);
                AissamUtils.setTextColorDark(dds);
                AissamUtils.setTextColorDark(hello);
                AissamUtils.setFilterToImage (menuIcon, Color.WHITE);
                buttonEffect(menu, AissamUtils.black, AissamUtils.grey, false);
                background.setBackgroundColor(Color.parseColor(AissamUtils.black));
            } else {
                AissamUtils.setLightBars(this);
                AissamUtils.setTextColorLight(userDate);
                AissamUtils.setTextColorLight(dd_mm);
                AissamUtils.setTextColorLight(yy);
                AissamUtils.setTextColorLight(dds);
                AissamUtils.setTextColorLight(hello);
                AissamUtils.setFilterToImage (menuIcon, Color.BLACK);
                buttonEffect(menu, AissamUtils.white, AissamUtils.grey, false);
                background.setBackgroundColor(Color.parseColor(AissamUtils.whiteSmoke));
            }
        }

        int day = Integer.parseInt(sharedPreferences.getString("day", "-1"));
        int month = Integer.parseInt(sharedPreferences.getString("month", "-1")) - 1;
        int year = Integer.parseInt(sharedPreferences.getString("year", "-1"));
        Calendar birthdate = Calendar.getInstance();
        birthdate.set(year, month, day);
        int DayOfWeekNumber = birthdate.get(Calendar.DAY_OF_WEEK);
        String[] DaysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String dayOfWakeSTR = DaysOfWeek[DayOfWeekNumber - 1];
        Calendar currentDate = Calendar.getInstance();
        long millisecond = currentDate.getTimeInMillis() - birthdate.getTimeInMillis();
        long days = millisecond / (1000 * 60 * 60 * 24);
        long years = (long) (days / 365.25);
        long months = (long) ((days - (years * 365.25)) / 30.44);
        long remainingDays = (long) ((days - (years * 365.25)) % 30.44);
//        progress = (short) ( (((float)years / 60)) * 100);
        progress = (short) ( (((float)(months) / 12)) * 100);
        Progress();
        yy.setText(String.valueOf(years));
        dds.setText(dayOfWakeSTR);
        dd_mm.setText(months + (months > 1 ? " months" : " month") + " • " + remainingDays + (remainingDays > 1 ? " days" : " day"));
        Calendar nextBirth = Calendar.getInstance();
        nextBirth.set(currentDate.get(Calendar.YEAR), month, day, 0,0,0);
        if (nextBirth.before(currentDate)) {
            nextBirth.add(Calendar.YEAR, 1);
        }
        long nextBirthMills = nextBirth.getTimeInMillis() - currentDate.getTimeInMillis();
//        CountDownTimer timer = new CountDownTimer(nextBirthMills, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
//                long remainingDays = 0;
//                long weeks = 0;
//                long months = 0;
//                countDown.setTextSize(25);
//                if (days >= 30.44) {
//                    months = (long) (days / 30.44);
//                    remainingDays = (long) (days - (months * 30.44)) % 7;
//                    weeks = (long) ((days % 30.44) / 7);
//                    nextBirthDate.setText(String.valueOf( months));
//                    countDown.setText(weeks + (weeks > 1 ? " weeks" : " week") + " • " + remainingDays + (remainingDays > 1 ? " days" : " day"));
//                    nextBirthDate.setVisibility(View.VISIBLE);
//                    time_state.setText((months > 1) ? " Months" : " Month");
//                } else if (days < 30.44 && days > 3) {
//                    weeks = days / 7;
//                    remainingDays = days % 7;
//                    nextBirthDate.setText(String.valueOf(weeks));
//                    time_state.setText((weeks > 1) ? " Weeks" : " Week");
//                    countDown.setText(" " + remainingDays);
//                    nextBirthDate.setVisibility(View.VISIBLE);
//                } else {
//                    StringBuilder time = new StringBuilder();
//                    millisUntilFinished -= TimeUnit.DAYS.toMillis(days);
//                    long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
//                    millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);
//                    long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
//                    millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);
//                    long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
//                    nextBirthDate.setText(String.valueOf(days));
//                    time_state.setText((days > 1) ? " Days" : " Day");
//                    if (hours < 10)
//                        time.append("0" + hours + " : ");
//                    else
//                        time.append(hours + " : ");
//                    if (minutes < 10)
//                        time.append("0" + minutes + " : ");
//                    else
//                        time.append(minutes + " : ");
//                    if (seconds < 10)
//                        time.append("0" + seconds);
//                    else
//                        time.append(seconds);
//                    countDown.setText(time);
//                    if (days == 0) {
//                        nextBirthDate.setVisibility(View.GONE);
//                        time_state.setVisibility(View.GONE);
//                        countDown.setTextSize(40);
//                    }
//                }
//            }
//            @Override
//            public void onFinish() {

//            }
//        };
//        timer.start();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), activity_user_data.class);
        startActivity(intent);
    }
}