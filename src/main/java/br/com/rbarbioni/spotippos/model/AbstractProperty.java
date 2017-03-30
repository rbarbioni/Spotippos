package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

/**
 * Created by renan on 26/03/2017.
 */
public abstract class AbstractProperty {

    @JsonProperty("properties")
    private final Set<Property> properties;

    protected AbstractProperty(Set<Property> properties) {
        this.properties = properties;
    }

    public Set<Property> getProperties() {
        return properties;
    }
}
