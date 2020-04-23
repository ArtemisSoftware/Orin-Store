package com.artemissoftware.orionstore;

import com.artemissoftware.orionstore.models.Product;

public interface IMainActivity {

    void inflateViewProductFragment(Product product);

    void showQuantityDialog();

    void setQuantity(int quantity);
}
