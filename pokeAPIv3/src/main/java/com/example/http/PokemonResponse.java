package com.example.http;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PokemonResponse {
    Integer id;
    String name;
    List<Abilities> abilities;
    List<Types> types;
    List<Stats> stats;
    List<EncounterResponse> encounterResponse;
    @JsonProperty("version_group")
    VersionGroup versionGroup;
    List<Moves> moves;


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

    public List<EncounterResponse> getEncounterResponse() {
        return encounterResponse;
    }

    public void setEncounterResponse(List<EncounterResponse> encounterResponse) {
        this.encounterResponse = encounterResponse;
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

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }

    public List<Moves> getMoves() {
        return moves;
    }

    public void setMoves(List<Moves> moves) {
        this.moves = moves;
    }
}


class Abilities {
    boolean is_hidden;
    Ability ability;

    public boolean getis_hidden() {
        return is_hidden;
    }

    public void setis_hidden(boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}

class Ability {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

class Moves {
    PokemonMove pokemonMove;
    Version_group_details version_group_details;

    public PokemonMove getPokemonMove() {
        return pokemonMove;
    }

    public void setPokemonMove(PokemonMove pokemonMove) {
        this.pokemonMove = pokemonMove;
    }

    public Version_group_details getVersion_group_details() {
        return version_group_details;
    }

    public void setVersion_group_details(Version_group_details version_group_details) {
        this.version_group_details = version_group_details;
    }
}

class PokemonMove {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Version_group_details {
    int level_learned_at;
    Version_group version_group;
    Move_learn_method move_learn_method;

    public int getLevel_learned_at() {
        return level_learned_at;
    }

    public void setLevel_learned_at(int level_learned_at) {
        this.level_learned_at = level_learned_at;
    }

    public Version_group getVersion_group() {
        return version_group;
    }

    public void setVersion_group(Version_group version_group) {
        this.version_group = version_group;
    }

    public Move_learn_method getMove_learn_method() {
        return move_learn_method;
    }

    public void setMove_learn_method(Move_learn_method move_learn_method) {
        this.move_learn_method = move_learn_method;
    }
}

class Version_group {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Move_learn_method {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}