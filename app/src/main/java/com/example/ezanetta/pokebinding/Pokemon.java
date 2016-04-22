package com.example.ezanetta.pokebinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by ezanetta on 4/22/16.
 */
public class Pokemon implements Parcelable{

    public final String name;
    public final String url;

    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;
    }

    protected Pokemon(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    @BindingAdapter({"bind:imageUrl", "bind:error"})
    public static void loadImage(ImageView view, String url, Drawable error){
        Picasso.with(view.getContext()).load(url).error(error).into(view);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }
}
