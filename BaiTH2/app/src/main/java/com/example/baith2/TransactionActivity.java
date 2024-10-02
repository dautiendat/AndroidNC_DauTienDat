package com.example.baith2;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {

    ListView listView;
    private Transaction[] transactions;
    private ListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transaction);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView=findViewById(R.id.listView);
        initData();
        adapter = new ListViewAdapter(this,transactions);
        listView.setAdapter(adapter);
    }

    private void initData() {
        String dates[] = {"15-jul", "10-sep","30-Oct","2-Feb","5-Mar"};
        String names[] = {"Salary", "Market basket","Starbucks","McDonalds","PG&E"};
        String types[] = {"Income", "Party stuff","Coffee","Breakfast","Utilities"};
        String money1s[] = {"$1,543.11", "$1,114,22","-$3,432","-$1,112","-$3,223"};
        String money2s[] = {"-$1,454", "-$3,221","-$1,453","-$3,665","-$1,867"};

        transactions= new Transaction[names.length];
        for (int i = 0; i < transactions.length; i++) {
            transactions[i]=new Transaction(dates[i],names[i],types[i],money1s[i],money2s[i]);
        }
    }

}