package com.example.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EncounterResponse {
    Integer id;
    String name;

    @JsonProperty("version_group")
    VersionGroup versionGroup;

    public String getName() {
        return name;
    }

    public EncounterResponse setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public EncounterResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public VersionGroup getVersionGroup() {
        return versionGroup;
    }

    public EncounterResponse setVersionGroup(VersionGroup versionGroup) {
        this.versionGroup = versionGroup;
        return this;
    }
}
