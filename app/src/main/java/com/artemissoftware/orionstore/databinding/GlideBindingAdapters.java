package com.artemissoftware.orionstore.databinding;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.artemissoftware.orionstore.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class GlideBindingAdapters {

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView view, int imageUrl) {

        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(view);

    }


    @BindingAdapter("imageUrl")
    public static void setImage(ImageView view, String imageUrl) {

        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(view);

    }
}
