package com.example.ezanetta.pokebinding;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView)findViewById(R.id.pokeRV);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ParsePokemons parsePokemons = new ParsePokemons();
        parsePokemons.execute();
    }

    private class ParsePokemons extends AsyncTask<Void,Void,List<Pokemon>> {

        private String readPokemonJson(){

            int raw_pokemon = getResources().getIdentifier("pokemons", "raw", getPackageName());
            InputStream is = getResources().openRawResource(raw_pokemon);

            Writer writer = new StringWriter();
            char[] buffer = new char[1024];

            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }  finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return writer.toString();
        }

        private List<Pokemon> parsePokemonsJson(String jsonString) throws JSONException {

            List<Pokemon> pokemons = new ArrayList<>();

            JSONObject jsonObj = new JSONObject(jsonString);
            JSONArray pokemonsJson = jsonObj.getJSONArray("pokemons");

            for(int i = 0; i < pokemonsJson.length(); i++){

                String name = pokemonsJson.getJSONObject(i).getString("pokemon-name");
                String url = pokemonsJson.getJSONObject(i).getString("image-url");

                Pokemon pokemon = new Pokemon(name, url);
                pokemons.add(pokemon);

            }

            return pokemons;
        }

        @Override
        protected List<Pokemon> doInBackground(Void... params) {
            List<Pokemon> pokemons = new ArrayList<>();

            String jsonString = readPokemonJson();

            try {
                pokemons = parsePokemonsJson(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return pokemons;
        }

        @Override
        protected void onPostExecute(List<Pokemon> pokemons) {
            mRecyclerView.setAdapter(new PokeAdapter(pokemons));
        }
    }
}
