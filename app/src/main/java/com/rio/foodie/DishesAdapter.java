package com.rio.foodie;

import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.DishViewHolder> {
    private ArrayList<Dish> dishes;

    public DishesAdapter(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item, parent, false);
        return new DishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.dishTextView.setText(dish.getName());
        holder.quantityTextView.setText(String.valueOf(dish.getQuantity()));
        holder.dishImageView.setImageResource(dish.getPhoto());
        holder.dish = dish;
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public class DishViewHolder extends RecyclerView.ViewHolder {
        TextView dishTextView, quantityTextView;
        ImageView dishImageView;
        Button subsButton, addButton;
        Dish dish;
        int quantity;

        public DishViewHolder(@NonNull View itemView) {
            super(itemView);
            dishTextView = itemView.findViewById(R.id.dish_textView);
            quantityTextView = itemView.findViewById(R.id.quantity_textView);
            dishImageView = itemView.findViewById(R.id.dish_imageView);
            subsButton = itemView.findViewById(R.id.subs_button);
            addButton = itemView.findViewById(R.id.add_button);
            subsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity = dish.getQuantity();
                    quantity--;
                    dish.setQuantity(quantity);
                    quantityTextView.setText(dish.getQuantity().toString());
                }
            });
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity = dish.getQuantity();
                    quantity++;
                    dish.setQuantity(quantity);
                    quantityTextView.setText(dish.getQuantity().toString());
                }
            });
        }
    }
}
