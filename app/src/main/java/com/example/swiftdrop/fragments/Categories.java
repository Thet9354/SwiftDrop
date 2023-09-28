package com.example.swiftdrop.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.swiftdrop.Adapter.CategoryAdapter;
import com.example.swiftdrop.Model.CategoryItem;
import com.example.swiftdrop.R;
import com.example.swiftdrop.Screens.Item;
import com.example.swiftdrop.Screens.Splash_Screen;
import com.example.swiftdrop.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class Categories extends Fragment {

    private ImageView imgView_back;
    private androidx.appcompat.widget.SearchView sw_categories;
    private androidx.recyclerview.widget.RecyclerView rv_categories;
    CategoryAdapter categoryAdapter;

    private Context mContext;

    private final ArrayList<CategoryItem> categoryItemArrayList = new ArrayList<>();

    int[] categoryPics = {R.drawable.broccoli, R.drawable.orange, R.drawable.bread, R.drawable.sweets, R.drawable.pasta, R.drawable.drink};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_categories, container, false);

        mContext = getActivity();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    private void findViews(View v) {

        imgView_back = v.findViewById(R.id.imgView_back);
        sw_categories = v.findViewById(R.id.sw_categories);
        rv_categories = v.findViewById(R.id.rv_categories);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        imgView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, Splash_Screen.class));
            }
        });
    }

    private void initWidget() {

        initRecView();
    }

    private void initRecView() {
        // Init RecyclerView

        // Set the layout manager (GridLayoutManager)
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        rv_categories.setLayoutManager(gridLayoutManager);

        // Add spacing between items
        int spaceInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        rv_categories.addItemDecoration(new SpaceItemDecoration(spaceInPixels));

        // Optimize for fixed size if the data won't change dynamically
        rv_categories.setHasFixedSize(true);

        // Create and set the adapter
        categoryAdapter = new CategoryAdapter(mContext, categoryItemArrayList);
        rv_categories.setAdapter(categoryAdapter);

        new loadCategories().execute();
    }

    CategoryItem categoryItem;

    class loadCategories extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {
            try {

                String[] catItems = getResources().getStringArray(R.array.category_title);
                String[] numOfItems = getResources().getStringArray(R.array.numOfItems);

                for (int i = 0; i < catItems.length; i++) {
                    categoryItem = new CategoryItem();
                    categoryItem.setTitle(catItems[i]);
                    categoryItem.setNumOfItems(numOfItems[i]);
                    categoryItem.setImage(categoryPics[i]);
                    categoryItemArrayList.add(categoryItem);
                    categoryItem = null;
                    System.out.println("Hello world");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String file_url) {
            if (categoryItemArrayList != null && categoryItemArrayList.size() > 0) {
                categoryAdapter = new CategoryAdapter(mContext, categoryItemArrayList);
                rv_categories.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();
            }
        }
    }

}

