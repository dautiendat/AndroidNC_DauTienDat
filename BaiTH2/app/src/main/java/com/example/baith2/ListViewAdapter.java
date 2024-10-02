package com.example.baith2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListViewAdapter extends ArrayAdapter<Transaction> {
    private Context context;
    private Transaction[] transactions;
    public ListViewAdapter(@NonNull Context context, Transaction[] transactions) {
        super(context, 0,transactions);
        this.context=context;
        this.transactions=transactions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView=LayoutInflater.from(context).inflate(R.layout.item_listview,parent,false);
        TextView tv1=convertView.findViewById(R.id.date);
        TextView tv2=convertView.findViewById(R.id.name);
        TextView tv3=convertView.findViewById(R.id.type);
        TextView tv4=convertView.findViewById(R.id.money1);
        TextView tv5=convertView.findViewById(R.id.money2);

        Transaction transaction = transactions[position];
        tv1.setText(transaction.getDateTrans());
        tv2.setText(transaction.getNameTrans());
        tv3.setText(transaction.getTypeTrans());
        tv4.setText(transaction.getMoney1());
        tv5.setText(transaction.getMoney2());
        return convertView;
    }
    public Transaction getItem(int position){
        return transactions[position];
    }

}
