package com.example.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonResponse {
    Integer id;
    String name;

    @JsonProperty("version_group")
    VersionGroup versionGroup;

    public String getName() {
        return name;
    }

    public PokemonResponse setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public PokemonResponse setId(Integer id) {
        this.id = id;
        return this;
    }

/*    public VersionGroup getVersionGroup() {
        return versionGroup;
    }

    public PokemonResponse setVersionGroup(VersionGroup versionGroup) {
        this.versionGroup = versionGroup;
        return this;
    }*/
}

