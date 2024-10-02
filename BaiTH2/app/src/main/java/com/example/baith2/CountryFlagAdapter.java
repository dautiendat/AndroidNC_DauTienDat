package com.example.baith2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CountryFlagAdapter extends ArrayAdapter<Country> {
    private Context context;
    private List<Country> countries;
    private List<Country> countriesBackup;

    private int layout;
    public CountryFlagAdapter(@NonNull Context context, List<Country> countries, int layout) {
        super(context, layout,countries);
        this.context=context;
        this.countries=countries;
        this.layout=layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(context)
                    .inflate(layout,parent,false);

        ImageView imageView = convertView.findViewById(R.id.imageCountryFlag);
        TextView textView = convertView.findViewById(R.id.nameCountry);
        Button remove = convertView.findViewById(R.id.btnRemove);
        if (remove==null) return convertView;
        else{
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countries.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        Country country = countries.get(position);
        imageView.setImageResource(country.getImageCountry());
        textView.setText(country.getNameCountry());


        return convertView;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(context)
                    .inflate(layout,parent,false);

        ImageView imageView = convertView.findViewById(R.id.imageCountryFlag);
        TextView textView = convertView.findViewById(R.id.nameCountry);

        Country country = countries.get(position);
        imageView.setImageResource(country.getImageCountry());
        textView.setText(country.getNameCountry());

        Button remove = convertView.findViewById(R.id.btnRemove);
        if (remove==null) return convertView;
        else{
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countries.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        return convertView;
    }

    public Country getItem(int position){
        return countries.get(position);
    }


}
