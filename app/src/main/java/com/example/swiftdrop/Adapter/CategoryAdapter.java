package com.example.swiftdrop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiftdrop.Model.CategoryItem;
import com.example.swiftdrop.R;
import com.example.swiftdrop.Screens.Item;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CardViewHolder>{

    private ArrayList<CategoryItem> categoryItemArrayList;
    private Context mContext;

    public CategoryAdapter(Context mContext, ArrayList<CategoryItem> categoryItemArrayList) {
        this.categoryItemArrayList = categoryItemArrayList;
        this.mContext = mContext;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private CategoryAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(CategoryAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    private Context getContext() {
        return mContext;
    }

    @NonNull
    @Override
    public CategoryAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.category_item, parent, false);

        return new CategoryAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CardViewHolder holder, int position) {

        holder.imgView_category.setImageResource(categoryItemArrayList.get(position).getImage());
        holder.txtView_categoryTitle.setText(categoryItemArrayList.get(position).getTitle());
        holder.txtView_numOfItem.setText(categoryItemArrayList.get(position).getNumOfItems());

        holder.cv_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Item.class);
                int pos = holder.getAdapterPosition();
                intent.putExtra("Category Name", categoryItemArrayList.get(pos).getTitle());
                intent.putExtra("Num of items", categoryItemArrayList.get(pos).getNumOfItems());
                intent.putExtra("Category Image", categoryItemArrayList.get(pos).getImage());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItemArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private androidx.cardview.widget.CardView cv_category;
        private ImageView imgView_category;
        private TextView txtView_categoryTitle;
        private TextView txtView_numOfItem;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_category = itemView.findViewById(R.id.cv_category);
            imgView_category = itemView.findViewById(R.id.imgView_category);
            txtView_categoryTitle = itemView.findViewById(R.id.txtView_categoryTitle);
            txtView_numOfItem = itemView.findViewById(R.id.txtView_numOfItem);

        }
    }
}
