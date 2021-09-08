package com.example.http;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

@Singleton
public class PokeClient {
    private final HttpClient httpClient;
    private final UriBuilder uri;

    public PokeClient(
        @Client("https://pokeapi.co/api/v2") HttpClient httpClient
    ) {
        this.httpClient = httpClient;
        this.uri = UriBuilder.of("/{resource}/{id}");
    }

    <T> Mono<T> fetchResource(String resource, String id, Class<T> type) {
        HttpRequest<?> req = HttpRequest.GET(uri.expand(Map.of("resource", resource,"id", id)));

        return Mono.from(httpClient.retrieve(req, type));
    }

    Mono<MachineResponse> fetchResource(URI override) {
        HttpRequest<?> req = HttpRequest.GET(override);

        return Mono.from(httpClient.retrieve(req, MachineResponse.class));
    }

    Mono<PokemonResponse> fetchPokemon(String resource, String id) {
        HttpRequest<?> req = HttpRequest.GET(uri.expand(Map.of("resource", resource,"id", id)));

        return Mono.from(httpClient.retrieve(req, PokemonResponse.class));
    }

    Mono<PokemonResponse> fetchPokemon(URI override) {
        HttpRequest<?> req = HttpRequest.GET(override);

        return Mono.from(httpClient.retrieve(req, PokemonResponse.class));
    }
}
