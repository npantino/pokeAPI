//package com.example;
//
//import io.micronaut.core.type.Argument;
//import io.micronaut.http.HttpRequest;
//import io.micronaut.http.uri.UriBuilder;
//import jakarta.inject.Singleton;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//
//import static io.netty.handler.codec.http.HttpHeaderNames.ACCEPT;
//import static jdk.internal.net.http.HttpRequestImpl.USER_AGENT;
//
//@Singleton
//public class PokeClient {
//    private final HttpClient httpClient;
//    private final URI uri;
//
//    public GithubLowLevelClient(HttpClient httpClient) {
//        this.httpClient = httpClient;
//        this.uri = UriBuilder.of("/repos")
//                .path("releases")
//                .build();
//    }
//
//    Mono<List<GithubRelease>> fetchReleases() {
//        HttpRequest<?> req = HttpRequest.GET(uri)
//                .header(USER_AGENT, "Micronaut HTTP Client")
//                .header(ACCEPT, "application/vnd.github.v3+json, application/json");
//        return Mono.from(httpClient.retrieve(req, Argument.listOf(GithubRelease.class)));
//    }
//}
