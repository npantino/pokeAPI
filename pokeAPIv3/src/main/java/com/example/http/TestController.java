package com.example.http;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Mono;

import java.net.URI;

@Controller("/api/http-test")
public class TestController {
    final private PokeClient pokeClient;

    public TestController(PokeClient pokeClient) {
        this.pokeClient = pokeClient;
    }

    @Get("machine")
    public Mono<MachineResponse> machine() {
        return pokeClient.fetchResource("machine", "1", MachineResponse.class);
    }

    @Get("pokemon")
    public Mono<PokemonResponse> pokemon() {
        return pokeClient.fetchResource("pokemon", "1", PokemonResponse.class);
    }

    @Get("override")
    public Mono<MachineResponse> override() {
        return pokeClient.fetchResource(URI.create("/machine/2"));
    }

    @Get("pokemon_regular")
    public Mono<PokemonResponse> pokemon_regular() {
        return pokeClient.fetchPokemon("pokemon", "1");
    }

    @Get("pokemon_override")
    public Mono<PokemonResponse> pokemon_override() {
        return pokeClient.fetchPokemon(URI.create("/machine/2"));
    }
}
