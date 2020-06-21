package com.example.pokemondex.Model;


public class Pokemon {
    private String pokeNum;
    private String pokeName;
    private String pokeImg;

//    past values when new object get created
    public Pokemon (String num, String name, String img) {
        pokeNum = num;
        pokeName = name;
        pokeImg = img;

    }

    //  get the collected data and return them
    public String getNum() {
        return pokeNum;
    }

    public String getName() {
        return pokeName;
    }

    public String getImg() {
        return pokeImg;
    }

}
