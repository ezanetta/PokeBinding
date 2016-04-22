package com.example.ezanetta.pokebinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by ezanetta on 4/22/16.
 */
public class Pokemon {

    public final String name;
    public final String url;

    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @BindingAdapter({"bind:imageUrl", "bind:error"})
    public static void loadImage(ImageView view, String url, Drawable error){
        Picasso.with(view.getContext()).load(url).error(error).into(view);
    }
}
