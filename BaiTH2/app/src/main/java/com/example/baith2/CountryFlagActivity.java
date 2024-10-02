package com.example.baith2;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class CountryFlagActivity extends AppCompatActivity {

    List<Country> countries;
    List<Country> countriesBackup;
    private Spinner spinner, spinnerAddCountry;
    private Button button;
    private Country country;
    private CountryFlagAdapter countryFlagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_country_flag);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinner=findViewById(R.id.spCountryFlag);
        button =findViewById(R.id.btnAddCountry);

        initData();
        countryFlagAdapter = new CountryFlagAdapter(this,countries, R.layout.item_spinner_country_flag);
        spinner.setAdapter(countryFlagAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog();
            }
        });
    }


    private void initData(){
        countries = new ArrayList<>();
        countriesBackup = new ArrayList<>();
        int[] imageCountry = {R.drawable.vietnam_flag,R.drawable.america_flag,
                R.drawable.argentina_flag, R.drawable.canada_flag,R.drawable.france_flag,
                R.drawable.japan_flag,R.drawable.south_korea_flag};
        String[] nameCountry = {"Vietnam","America","Argentina","Canada","France",
                "Japan","South Korea"};
        for (int i = 0; i < imageCountry.length; i++) {
            countries.add(new Country(imageCountry[i],nameCountry[i]));
            countriesBackup.add(new Country(imageCountry[i],nameCountry[i]));
        }
    }


    private void getDialog(){
        Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_country_dialog);
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        if(window == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        spinnerAddCountry= dialog.findViewById(R.id.spAddCountry);
        Button btnHuy = dialog.findViewById(R.id.btnCancel);
        Button btnThem = dialog.findViewById(R.id.btnAdd);
        EditText editText = dialog.findViewById(R.id.edtNameCountry);

        CountryFlagAdapter adapter = new CountryFlagAdapter(this, countriesBackup,R.layout.item_spinner2);
        spinnerAddCountry.setAdapter(adapter);

        //Click item spinner
        spinnerAddCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            int prepos=0;
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                country = countriesBackup.get(position);
                editText.setText(country.getNameCountry());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                country = countriesBackup.get(0);
                editText.setText(country.getNameCountry());
            }

        });


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countries.add(country);
                dialog.dismiss();
                countryFlagAdapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }

}