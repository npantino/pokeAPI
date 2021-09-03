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
        return pokeClient.fetchMachine("machine", "1");
    }

    @Get("override")
    public Mono<MachineResponse> override() {
        return pokeClient.fetchMachine(URI.create("/machine/2"));
    }
}
