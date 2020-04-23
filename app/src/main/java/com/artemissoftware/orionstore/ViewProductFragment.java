package com.artemissoftware.orionstore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artemissoftware.orionstore.databinding.FragmentViewProductBinding;
import com.artemissoftware.orionstore.models.Product;
import com.artemissoftware.orionstore.util.Products;


public class ViewProductFragment extends Fragment{

    private static final String TAG = "ViewProductFragment";

    // Data binding
    FragmentViewProductBinding mBinding;

    private Product mProduct;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        if(bundle != null){
            mProduct = bundle.getParcelable(getString(R.string.intent_product));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentViewProductBinding.inflate(inflater);
        mBinding.setIMainActivity((IMainActivity)getActivity());

        ProductViewModel productView = new ProductViewModel();
        productView.setProduct(mProduct);
        productView.setQuantity(1);

        mBinding.setProductView(productView);
        return mBinding.getRoot();
    }

}

