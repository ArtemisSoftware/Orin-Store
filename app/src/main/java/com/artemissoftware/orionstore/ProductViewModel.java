package com.artemissoftware.orionstore;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.artemissoftware.orionstore.models.Product;
import androidx.databinding.library.baseAdapters.BR;

public class ProductViewModel extends BaseObservable {

    private Product product;
    private int quantity;


    @Bindable
    public Product getProduct() {
        return product;
    }

    @Bindable
    public int getQuantity() {
        return quantity;
    }


    public void setProduct(Product product) {
        this.product = product;
        notifyPropertyChanged(BR.product);
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(BR.quantity);
    }

}
