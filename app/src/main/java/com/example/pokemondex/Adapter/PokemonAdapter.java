package com.example.pokemondex.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemondex.Model.Pokemon;
import com.example.pokemondex.R;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private Context pokecontext;
    private List<Pokemon> list;

    public PokemonAdapter(Context context, ArrayList<Pokemon> pokemonList){
            pokecontext = context;
            list = pokemonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return the viewholder
           View v = LayoutInflater.from(pokecontext).inflate(R.layout.single_pokemon, parent,false);
           return new ViewHolder(v);
    }

//        Get current item out of the array list
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Pokemon currentPokemon = list.get(position);

         String pokemonImage = currentPokemon.getImg();
         String pokemonNumber = currentPokemon.getNum();
         String pokemonName = currentPokemon.getName();

//           set the data in the right view
         holder.pokemonNum.setText(pokemonNumber);
         holder.pokemonName.setText(pokemonName);
         Glide.with(pokecontext).load(pokemonImage).into(holder.pokemonImg);
    }

    @Override
    public int getItemCount() {
//            get amount of the items/objects that are in the list
            return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

            public ImageView pokemonImg;
            public TextView pokemonNum;
            public TextView pokemonName;

            public ViewHolder(@NonNull View itemView) {
                    super(itemView);
//                  Get & target the right views for the right data
                    pokemonNum = itemView.findViewById(R.id.pokemon_number);
                    pokemonName = itemView.findViewById(R.id.pokemon_name);
                    pokemonImg = itemView.findViewById(R.id.pokemon_image);
            }
    }
}