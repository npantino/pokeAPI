package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/pokeApi")
public class PokeAPI {
    @Get("/latios/")
    public String pokemonInfo() {
        return "";
    }
}
