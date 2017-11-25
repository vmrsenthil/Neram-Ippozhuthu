package com.sample.drawclock;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static int hour_var, min_var = 0;
    public static EditText minutes, hours;
    public static TableLayout tableLayout;
    public static Button BTN_check;
    public static Clock clock;
    public static Boolean correct;
    public static TextView TV_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setHourMin();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minutes = (EditText) findViewById(R.id.minutes);
        hours = (EditText) findViewById(R.id.hour);
        tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        TV_result =(TextView)findViewById(R.id.result);

    }
    public void setHourMin(){
        Random random = new Random();
        min_var = random.nextInt(60);
        min_var = min_var - min_var%5;
        hour_var = random.nextInt(13);
        hour_var = hour_var == 0 ? 12:hour_var;
    }

    public void check(View view){
        BTN_check = (Button)findViewById(R.id.btn_check);
        if(BTN_check.getText().equals("Try next") || BTN_check.getText().equals("Try again")){
            result();
        }else if(BTN_check.getText().equals("Check")) {
            clock = (Clock) findViewById(R.id.clock);

            minutes = (EditText) findViewById(R.id.minutes);
            hours = (EditText) findViewById(R.id.hour);
            tableLayout = (TableLayout) findViewById(R.id.tablelayout);
            TV_result =(TextView)findViewById(R.id.result);
            TV_result.setVisibility(View.VISIBLE);
            if (!minutes.getText().equals("") && !hours.getText().equals("")) {
                if ((min_var == Integer.parseInt(minutes.getText().toString())) && (hour_var == Integer.parseInt(hours.getText().toString()))) {
                    correct = true;
                    BTN_check.setText("Try next");
                    TV_result.setText("Correct");
                    TV_result.setTextSize(25);
                    TV_result.setTextColor(0xff00ff00);
                    this.displayToast("Yippee.....!");
                } else {
                    correct = false;
                    BTN_check.setText("Try again");
                    TV_result.setText("Wrong");
                    this.displayToast("Oops....!");
                    TV_result.setTextSize(25);
                    TV_result.setTextColor(0xffff0000);
                }
                minutes.setEnabled(false);
                hours.setEnabled(false);
            }
        }
    }
    public void result(){
        clearTextView();
        removeLastRow();
        minutes.setEnabled(true);
        hours.setEnabled(true);
        BTN_check.setText("Check");
        if(correct) {
            setHourMin();
            resetClock();
        }
    }
    public void clearTextView() {
        minutes.setText("");
        hours.setText("");
        BTN_check.setVisibility(View.VISIBLE);
        hours.requestFocus();
    }
    public void resetClock(){
        clock.invalidate();
    }
    public void removeLastRow(){
        TV_result.setVisibility(View.INVISIBLE);
    }

    public void displayToast(String toast_str) {
        try {
            Toast.makeText(getApplicationContext(), toast_str, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e("YOUR_APP_LOG_TAG", "I got activity error", e);
        }
    }
}
