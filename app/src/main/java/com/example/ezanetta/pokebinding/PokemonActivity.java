package com.example.ezanetta.pokebinding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ezanetta.pokebinding.databinding.ActivityPokemonBinding;

public class PokemonActivity extends AppCompatActivity {

    public static final String POKEMON_PARCELABLE_OBJECT = "POKEMON_PARCELABLE_OBJECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPokemonBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon);
        Intent intent = getIntent();

        Pokemon pokemon = intent.getParcelableExtra(POKEMON_PARCELABLE_OBJECT);
        binding.setPokemon(pokemon);
    }
}
