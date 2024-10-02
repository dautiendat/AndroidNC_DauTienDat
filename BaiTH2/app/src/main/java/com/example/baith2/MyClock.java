package com.example.baith2;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.baith2.R;

public class MyClock extends androidx.appcompat.widget.AppCompatTextView {
    TextView textView;

    public MyClock(Context context, @Nullable AttributeSet attrs, TextView textView) {
        super(context, attrs);
        this.textView=textView;
    }
    int hour, minute, second;
    public void setClock(int h, int m, int s) {
        hour = h;
        minute = m;  second = s;
        String str = "" + h + ": " + m + ": " + s;
        textView.setText(str);
    }
    Handler myHandler = new Handler();
    public void start() {
        stop();
        th = new Thread(new Runnable() {
            public void run() {
            while (true) {  try {
                Thread.sleep(1000);
                addSecond();
            } catch (Exception ex) {}
            }
        }
        });
        th.start();
    }
    void addSecond() {
        second++;
        if (second >= 60){
            second = 0; minute++;
        }

        if (minute >= 60){
            minute = 0; hour++;
        }

        myHandler.post(new Runnable() {
            public void run() {
                setClock(hour, minute, second); }
        });
    }
    Thread th = null;
    @SuppressWarnings("deprecation")
    public void stop() {
        if (th != null) th.stop();
    }

}
