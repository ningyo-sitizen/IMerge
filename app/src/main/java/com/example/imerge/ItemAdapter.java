package com.example.imerge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> itemList;

    // Constructor to initialize the item list
    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate item_layout for each item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item currentItem = itemList.get(position);

        // Bind the data from currentItem to the views
        holder.itemName.setText(currentItem.getNamaBarang());
//        holder.itemId.setText("ID: " + currentItem.getId());

        // You can also handle the image here using an image loading library (e.g., Glide or Picasso)
        // holder.itemImage.setImageResource(currentItem.getGambar());  // For example, if it's a drawable resource

        // If you want to load from a URL, you could use Glide or Picasso
        // Glide.with(holder.itemView.getContext()).load(currentItem.getGambar()).into(holder.itemImage);

        // Handle button clicks for detail and delete
        holder.btnDetail.setOnClickListener(v -> {
            // Handle detail button click (e.g., show item details)
        });

        holder.btnDelete.setOnClickListener(v -> {
            // Handle delete button click (e.g., remove item from the list)
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // ViewHolder class to hold the views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemId;
        public ImageView itemImage;  // You can display an image
        public Button btnDetail;
        public Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
        }
    }
}
