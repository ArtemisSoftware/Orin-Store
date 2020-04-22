package com.artemissoftware.orionstore;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artemissoftware.orionstore.databinding.FragmentViewProductBinding;
import com.artemissoftware.orionstore.util.Products;


public class ViewProductFragment extends Fragment{

    private static final String TAG = "ViewProductFragment";

    // Data binding
    FragmentViewProductBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentViewProductBinding.inflate(inflater);

        /*
        Products products = new Products();
        mBinding.setProduct(products.PRODUCTS[0]);

        mBinding.setQty(1);
        */

        return mBinding.getRoot();
    }

}

