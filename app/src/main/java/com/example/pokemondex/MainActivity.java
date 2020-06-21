package com.example.pokemondex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.pokemondex.Adapter.PokemonAdapter;
import com.example.pokemondex.Model.Pokemon;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //        URL to retrieve the data/json from url
    private static final String url = "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json";

    private RecyclerView recyclerView;
    private ArrayList<Pokemon> pokemonList;
    private PokemonAdapter pAdapter;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.pokedex_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pokemonList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

//        make new adapter for the arraylist and set the adapter for the recyclerview
        pAdapter = new PokemonAdapter(MainActivity.this, pokemonList);
        recyclerView.setAdapter(pAdapter);

        getData();

    }

    private void getData() {

//        Request json object from url
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            try {
//                    target & get the array with the assigned name "pokemon"
                JSONArray jsonArray = response.getJSONArray("pokemon");

//                Get all the items from the array
                for (int i = 0; i < jsonArray.length(); i++ ) {
                    JSONObject pokemon = jsonArray.getJSONObject(i);

//                  Get the values out of the jsonobjects
                    String pokemonNum = pokemon.getString("num");
                    String pokemonName = pokemon.getString("name");
                    String pokemonImage = pokemon.getString("img");

//                        set retrieved data in as item in list
                    pokemonList.add(new Pokemon(pokemonNum, pokemonName, pokemonImage));
//                        notify that data has changed so adapter gets created & attached
                    pAdapter.notifyDataSetChanged();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);

    }
}
