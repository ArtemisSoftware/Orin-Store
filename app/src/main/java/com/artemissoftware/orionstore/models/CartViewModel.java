package com.artemissoftware.orionstore.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.artemissoftware.orionstore.util.BigDecimalUtil;
import com.artemissoftware.orionstore.util.Prices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends BaseObservable {

    private List<CartItem> cart = new ArrayList<>();
    private boolean isCartVisible;

    @Bindable
    public List<CartItem> getCart() {
        return cart;
    }

    @Bindable
    public boolean isCartVisible() {
        return isCartVisible;
    }


    public void setCart(List<CartItem> cart) {
        this.cart = cart;
        notifyPropertyChanged(BR.cart);
    }


    public void setCartVisible(boolean cartVisible) {
        isCartVisible = cartVisible;
        notifyPropertyChanged(BR.cartVisible);
    }

    public String getProductQuantities(){

        int totalItems = 0;

        for(CartItem cartItem : cart){
            totalItems += cartItem.getQuantity();
        }

        if(totalItems > 1){
            return "(" + totalItems + " items)";
        }
        return "(" + totalItems + " item)";
    }

    public String getTotalCost(){

        double totalCost = 0;

        for(CartItem cartItem : cart){

            int productQuantity = cartItem.getQuantity();

            double cost = productQuantity * (Prices.getPrices().get(String.valueOf(cartItem.getProduct().getSerial_number()))).doubleValue();
            totalCost += cost;
        }

        return "$" + BigDecimalUtil.getValue(new BigDecimal(totalCost));
    }
}
