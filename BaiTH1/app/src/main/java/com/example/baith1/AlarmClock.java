package com.example.baith1;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmClock extends AppCompatActivity {
    private TextView textView;
    private TimePicker timePicker;
    private ToggleButton toggleButton;
    private Spinner sp;
    private int hourOfTimePicker;
    private Integer timeLapLai;
    private Timer timer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock);
        timePicker=findViewById(R.id.timePicker);
        textView=findViewById(R.id.tvBaoThuc);
        sp=findViewById(R.id.spTimeLapLai);
        toggleButton=findViewById(R.id.btnOK);
        timePicker.setOnTimeChangedListener((view, hour, minute) -> {
            hourOfTimePicker=hour;
        });


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String p = sp.getSelectedItem().toString();
                timeLapLai = Integer.valueOf(p);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                String p = sp.getItemAtPosition(0).toString();
                timeLapLai = Integer.valueOf(p);
            }
        });

        int oneMinute = 60000;

        toggleButton.setOnCheckedChangeListener((compoundButton, ischecked) -> {
            if(ischecked){
                if(timer==null){
                    timer =new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.append("Dien thoai dang rung bao thuc\n");
                                }
                            });

                        }
                    },5000,timeLapLai*oneMinute);
                }
            }else{
                if(timer !=null){
                    timer.cancel();
                    timer=null;
                }
                textView.setText("");
            }
        });

    }
}