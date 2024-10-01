package com.tiendat.foodapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewholder>{

    private List<Food> foodList = new ArrayList<>();

    public FoodAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item_layout,parent,false);
        return new FoodViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewholder holder, int position) {
        Food food = foodList.get(position);
        holder.foodName.setText(food.getName());
        holder.foodDescription.setText(food.getDescription());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    class FoodViewholder extends RecyclerView.ViewHolder{
        private TextView foodName;
        private TextView foodDescription;
        public FoodViewholder(@NonNull View itemView) {
            super(itemView);
            foodName=itemView.findViewById(R.id.food_name);
            foodDescription=itemView.findViewById(R.id.food_description);
        }
    }
}
