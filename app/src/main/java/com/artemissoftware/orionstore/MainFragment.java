package com.artemissoftware.orionstore;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artemissoftware.orionstore.databinding.FragmentMainBinding;


public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "MainFragment";

    // Data binding
    FragmentMainBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentMainBinding.inflate(inflater);
        mBinding.swipeRefreshLayout.setOnRefreshListener(this);

        return mBinding.getRoot();
    }



    @Override
    public void onRefresh() {

        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        //(mBinding.recyclervView.getAdapter()).notifyDataSetChanged();
        //mBinding.swipeRefreshLayout.setRefreshing(false);
    }
}
