package com.rio.foodie;

import android.os.Parcel;
import android.os.Parcelable;

public class Dish implements Parcelable {
    private String name;
    private String description;
    private Integer quantity;
    private Integer price;
    private Integer photo;

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description =
            description; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) {this.quantity = quantity;}
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public Integer getPhoto() { return photo; }
    public void setPhoto(Integer photo) { this.photo = photo; }

    @Override
    public int describeContents() { return 0; }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.quantity);
        dest.writeInt(this.price);
        dest.writeInt(this.photo);
    }
    Dish(String foodName, byte quantity, int fried_rice) {}
    private Dish(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.quantity = in.readInt();
        this.price = in.readInt();
        this.photo = in.readInt();
    }
    public static final Parcelable.Creator<Dish> CREATOR = new
            Parcelable.Creator<Dish>() {
                @Override
                public Dish createFromParcel(Parcel source) {
                    return new Dish(source);
                }
                @Override
                public Dish[] newArray(int size) {
                    return new Dish[size];
                }
            };
}
