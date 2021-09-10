package com.example.http;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PokemonResponse {
    Integer id;
    String name;
    List<Types> types;
    List<Stats> stats;

    public int getTotalStats() {
        int totalStats = 0;
        for (int i = 0; i < stats.size(); i++) {
            totalStats += stats.get(i).base_stat;
        }
        return totalStats;
    }


    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

    @JsonProperty("version_group")
    VersionGroup versionGroup;

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

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

    public VersionGroup getVersionGroup() {
        return versionGroup;
    }

    public PokemonResponse setVersionGroup(VersionGroup versionGroup) {
        this.versionGroup = versionGroup;
        return this;
    }
}

class Stats {
    int base_stat;
    Stat stat;

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
}

class Stat {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


class Types {
    Integer slot;
    Type type;

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

class Type {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}