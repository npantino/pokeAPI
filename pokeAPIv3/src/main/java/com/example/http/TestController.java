package com.example.http;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller("/api/http-test")
public class TestController {
    final private PokeClient pokeClient;
    final private SearchHistoryRepository searchHistoryRepository;

    public TestController(PokeClient pokeClient, SearchHistoryRepository searchHistoryRepository) {
        this.pokeClient = pokeClient;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    @Get("machine")
    public Mono<MachineResponse> machine() {
        return pokeClient.fetchResource("machine", "1", MachineResponse.class);
    }

    @Get("pokemon/{pokemon}")
    public Mono<PokemonResponse> pokemon(@PathVariable String pokemon) {
        searchHistoryRepository.save(pokemon);
        Mono<PokemonResponse> pokemonResponse =  pokeClient.fetchResource("pokemon", pokemon, PokemonResponse.class);
        Mono<List> encounterResponse =  pokeClient.fetchAltResource("pokemon", pokemon,
                "encounters", List.class);
        return pokemonResponse.map((pr) -> {
            pr.encounterResponse = encounterResponse.block();
            return pr;
        });
    }

    @Get("searchHistory")
    public List<SearchHistory> searchHistory() {
        return searchHistoryRepository.findAll();
    }

    @Delete("deleteSearchHistory")
    public void delete() {
        searchHistoryRepository.deleteAll();
    }

    @Get("encounters/{pokemon}")
    public Mono<List> encounters(@PathVariable String pokemon) {
        return pokeClient.fetchAltResource("pokemon", pokemon, "encounters", List.class);
    }

    /*@Get("override")
    public Mono<MachineResponse> override() {
        return pokeClient.fetchResource(URI.create("/machine/2"));
    }*/

    @Get("pokemon_regular")
    public Mono<PokemonResponse> pokemon_regular() {
        return pokeClient.fetchPokemon("pokemon", "1");
    }

    @Get("pokemon_override")
    public Mono<PokemonResponse> pokemon_override() {
        return pokeClient.fetchPokemon(URI.create("/pokemon/2"));
    }
}
