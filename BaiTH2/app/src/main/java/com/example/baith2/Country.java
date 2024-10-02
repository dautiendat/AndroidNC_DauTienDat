package com.example.baith2;

public class Country {
    private int imageCountry;
    private String nameCountry;

    public Country(int imageCountry, String nameCountry) {
        this.imageCountry = imageCountry;
        this.nameCountry = nameCountry;
    }

    public int getImageCountry() {
        return imageCountry;
    }

    public void setImageCountry(int imageCountry) {
        this.imageCountry = imageCountry;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }
}
