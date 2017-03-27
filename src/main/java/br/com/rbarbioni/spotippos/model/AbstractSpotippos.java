package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by renan on 26/03/2017.
 */
public abstract class AbstractSpotippos {

    @JsonProperty("properties")
    private final List<Spotippos> spotippos;

    protected AbstractSpotippos(List<Spotippos> spotippos) {
        this.spotippos = spotippos;
    }

    public List<Spotippos> getSpotippos() {
        return spotippos;
    }
}
