package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

/**
 * Created by renan on 26/03/2017.
 */
public abstract class AbstractProperty {

    @JsonProperty("properties")
    private final Collection<Property> properties;

    AbstractProperty(Collection<Property> properties) {
        this.properties = properties;
    }

    public Collection<Property> getProperties() {
        return properties;
    }
}
