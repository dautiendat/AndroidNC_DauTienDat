package com.example.baith2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.baith2.R;

import java.io.IOException;

public class ProgressBarActivity extends AppCompatActivity {

    private ProgressBar horizontal;
    private ProgressBar eclipse;
    private int statusBar=0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            horizontal.setProgress(msg.getData().getInt("stt"));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_progress_bar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        horizontal=findViewById(R.id.progressBar1);
        eclipse=findViewById(R.id.progressBar2);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (statusBar < 100){
                        Message message = handler.obtainMessage();
                        Bundle bundle = new Bundle();
                        statusBar+=5;
                        if (statusBar == 100){
                            statusBar = 0;
                        }
                        bundle.putInt("stt",statusBar);
                        message.setData(bundle);
                        handler.sendMessage(message);
                        Thread.sleep(2000);
                    }

                }catch (Exception e){

                }
            }
        });
        thread.start();


    }
}