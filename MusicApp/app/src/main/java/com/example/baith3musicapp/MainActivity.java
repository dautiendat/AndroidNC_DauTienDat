package com.example.baith3musicapp;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener {

    private RecyclerView recyclerView;
    private List<SongEntity> songList;
    private TextView tvNameSong, tvAlbum, tvTime;
    private SeekBar seekBar;
    private ImageView ivPlay;

    private static final int LEVEL_PAUSE = 0;
    private static final int LEVEL_PLAY = 1;
    private static final MediaPlayer player = new MediaPlayer();
    private static final int STATE_IDE = 1;
    private static final int STATE_PLAYING = 2;
    private static final int STATE_PAUSED = 3;
    private int index;
    private SongEntity songEntity;
    private Thread thread;
    private int state = STATE_IDE;
    private String totalTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        loadListSongOffline();
    }

    private void initViews() {
        ivPlay=findViewById(R.id.imgPlay);
        ivPlay.setOnClickListener(this);
        (findViewById(R.id.imgBack)).setOnClickListener(this);
        (findViewById(R.id.imgNext)).setOnClickListener(this);
        tvNameSong=findViewById(R.id.nameSong);
        tvAlbum=findViewById(R.id.nameAlbum);
        tvTime=findViewById(R.id.timePlaySong);
        seekBar=findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
    }

    private void play(){
        songEntity=songList.get(index);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
            loadListSongOffline();
        else {
            Toast.makeText(this, R.string.text_alert, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void loadListSongOffline() {
        Cursor cursor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    null,null,null);
        }
        if(cursor!=null){
            cursor.moveToFirst();
            //songList.clear();
            while (!cursor.isAfterLast()){
                int indexTitle = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                int indexData = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
                String name=cursor.getString(indexTitle);
                String path=cursor.getString(indexData);
                String album="N/A";
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                    int indexAlbum=cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ARTIST);
                    album = cursor.getString(indexAlbum);
                }
                songList.add(new SongEntity(name, path, album));
                cursor.moveToNext();
            }
            cursor.close();
        }
        recyclerView=findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MusicAdapter(this,songList));
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}