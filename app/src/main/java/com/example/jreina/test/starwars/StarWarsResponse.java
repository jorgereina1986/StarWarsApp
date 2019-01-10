package com.example.jreina.test.starwars;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StarWarsResponse {

    @SerializedName("results")
    private List<Character> characters;

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
