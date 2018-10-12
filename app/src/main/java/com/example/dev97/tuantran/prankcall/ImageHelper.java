package com.example.dev97.tuantran.prankcall;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageHelper {
    // with place holder resId
    public static void load(Context mContext,ImageView imageView, String url) {
//        Glide.with(mContext).
        Glide.with(mContext)
                .load(url)
                .into(imageView);
    }

    public static void load(Context mContext, ImageView imageView, int image){
        Glide.with(mContext)
                .load(image)
                .into(imageView);
    }
}
