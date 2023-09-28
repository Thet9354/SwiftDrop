package com.example.swiftdrop.Model;

public class CategoryItem {

    private int image;
    private String numOfItems;
    private String title;


    public CategoryItem(int image, String numOfItems, String title) {
        this.image = image;
        this.numOfItems = numOfItems;
        this.title = title;
    }

    public CategoryItem() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(String numOfItems) {
        this.numOfItems = numOfItems;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
