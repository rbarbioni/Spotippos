package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by renan on 26/03/2017.
 */
public abstract class AbstractProperty {

    @JsonProperty("properties")
    private final List<Property> properties;

    protected AbstractProperty(List<Property> properties) {
        this.properties = properties;
    }

    public List<Property> getProperties() {
        return properties;
    }
}
