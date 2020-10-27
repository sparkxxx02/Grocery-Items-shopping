package com.sdp.remotehealthcareapp.Fragments.Selected_Category;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sdp.remotehealthcareapp.R;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView txtTitle;
    public ImageView img;
    public TextView txtPrice;

    public TextView cart_name;
    public ImageView cart_image;
    public TextView cart_quantity;
    public TextView cart_price;

    public ViewHolder(View itemView) {
        super(itemView);
        txtTitle = itemView.findViewById(R.id.selected_item_name);
        img = itemView.findViewById(R.id.selected_item_image);
        txtPrice= itemView.findViewById(R.id.selected_item_price);

        cart_image=itemView.findViewById(R.id.cart_image);
        cart_name=itemView.findViewById(R.id.cart_name);
        cart_price=itemView.findViewById(R.id.cart_price);
        cart_quantity=itemView.findViewById(R.id.cart_quantity);
    }
    public void setCartname(String string)
    {
        cart_name.setText(string);
    }
    public void setCartquantity(String string)
    {
        cart_quantity.setText(string);
    }
    public void setCartprice(String string)
    {
        cart_price.setText(string);
    }
    public void setCartimage(String string)
    {
        Picasso.get().load(string).into(cart_image);
    }

    public TextView getCart_quantity() {
        return cart_quantity;
    }

    public TextView getCart_price() {
        return cart_price;
    }

    public void setTxtPrice(String string) {
        txtPrice.setText("$"+string+" /kg");
    }
    public void setTxtTitle(String string) {
        txtTitle.setText(string);
    }


    public void setimg(String url) {
        Picasso.get().load(url).error(R.drawable.bev).into(img);
    }
}