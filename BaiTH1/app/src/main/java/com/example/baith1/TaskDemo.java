package com.example.baith1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.logging.Handler;

public class TaskDemo extends Activity {
    private TextView tv1, tv2, tv3;
    private ToggleButton tog1,tog2,tog3;

    boolean togStatus1=true;
    boolean togStatus2=true;
    boolean togStatus3=true;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            tv1.setText(msg.getData().getString("bg1"));
            tv2.setText(msg.getData().getString("bg2"));
            tv3.setText(msg.getData().getString("bg3"));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_demo);
        initView();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Threads();
            }
        }, 2000);

        tog1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked){
                    togStatus1 = false;
                }else{
                    togStatus1=true;
                }
            }
        });
        tog2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked){
                    togStatus2 = false;
                }else{
                    togStatus2=true;
                }
            }
        });
        tog3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked){
                    togStatus3 = false;
                }else{
                    togStatus3=true;
                }
            }
        });
    }

    void initView(){
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tog1=findViewById(R.id.toggleButton1);
        tog2=findViewById(R.id.toggleButton2);
        tog3=findViewById(R.id.toggleButton3);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    private void Threads() {
        Thread backgroundTV1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        if(togStatus1){
                            Random x = new Random();
                            int n = x.nextInt(51) + 50;
                            Thread.sleep(1000);
                            Message msg = handler.obtainMessage();
                            Bundle bundle = new Bundle();
                            bundle.putString("bg1", String.valueOf(n));
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                        }
                    }
                } catch (Exception e) {

                }
            }
        });
        backgroundTV1.start();

        Thread backgroundTV2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int n = 1;
                    while (true){
                        if(togStatus2) {
                            Thread.sleep(2500);
                            Message msg = handler.obtainMessage();
                            Bundle bundle = new Bundle();
                            bundle.putString("bg2", String.valueOf(n));
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                            n += 2;
                        }
                    }
                } catch (Exception e) {
                }
            }
        });
        backgroundTV2.start();

        Thread backgroundTV3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int n = 0;
                    while (true){
                        if(togStatus3) {
                            Thread.sleep(2000);
                            Message msg = handler.obtainMessage();
                            Bundle bundle = new Bundle();
                            bundle.putString("bg3", String.valueOf(n));
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                            n++;
                        }
                    }
                } catch (Exception e) {
                }
            }
        });
        backgroundTV3.start();
    }
}