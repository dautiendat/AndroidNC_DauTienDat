package com.example.baith3musicapp;

public class SongEntity {
    private String nameSong,pathSong,nameAlbum;

    public SongEntity(String nameSong, String pathSong, String nameAlbum) {
        this.nameSong = nameSong;
        this.pathSong = pathSong;
        this.nameAlbum = nameAlbum;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getPathSong() {
        return pathSong;
    }

    public void setPathSong(String pathSong) {
        this.pathSong = pathSong;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }
}
