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

    @Get("regular")
    public Mono<MachineResponse> regular() {
        return pokeClient.fetchResource("machine", "1");
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
