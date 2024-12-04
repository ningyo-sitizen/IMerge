package com.example.imerge;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        // Bind data to the views
        holder.itemName.setText(currentItem.getNamaBarang());
        holder.itemQuantity.setText("Quantity: " + currentItem.getJumlah());

        // Convert and set the image
        Bitmap bitmap = currentItem.getBitmapGambar();
        if (bitmap != null) {
            holder.itemImage.setImageBitmap(bitmap);
        } else {
            holder.itemImage.setImageResource(R.drawable.ic_launcher_background); // Placeholder if image is null
        }

        // Set click listeners for buttons
        holder.btnDetail.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Details of " + currentItem.getNamaBarang(), Toast.LENGTH_SHORT).show();
        });

        holder.btnDelete.setOnClickListener(v -> {
            itemList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, itemList.size());
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // ViewHolder class to hold the views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName, itemQuantity;
        public ImageView itemImage;
        public Button btnDetail, btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            itemImage = itemView.findViewById(R.id.item_image);
            btnDetail = itemView.findViewById(R.id.detail_button);
            btnDelete = itemView.findViewById(R.id.delete_button);
        }
    }
}

