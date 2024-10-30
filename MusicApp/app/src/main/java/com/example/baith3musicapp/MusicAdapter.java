package com.example.baith3musicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private Context context;
    private List<SongEntity> songList;

    public MusicAdapter(Context context, List<SongEntity> songList) {
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song,parent,false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        SongEntity song = songList.get(position);
        holder.textView.setText(song.getNameSong());
        holder.textView.setTag(song);
    }

    @Override
    public int getItemCount() {
        if(songList!=null) return songList.size();
        return 0;
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.nameSong);
            itemView.setOnClickListener(v ->{
                v.startAnimation(AnimationUtils.loadAnimation(context,
                        androidx.appcompat.R.anim.abc_fade_in));
                //((MainActivity)context).playSong((SongEntity)textView.getTag());
            });
        }
    }
}
