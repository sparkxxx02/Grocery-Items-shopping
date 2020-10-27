package com.sdp.remotehealthcareapp.Fragments.Selected_Category;

import android.widget.ImageView;

public class Dataclass {
    String name;
    String quantity;
    String price;
    String img;
    String desc;
    Dataclass()
    {

    }
    Dataclass(String name, String quantity, String price, String img, String desc){
        this.name= name;
        this.quantity=quantity;
        this.price=price;
        this.img= img;
        this.desc= desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
