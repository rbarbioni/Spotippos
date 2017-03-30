package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by renan on 26/03/2017.
 */
public class PropertyResponse extends AbstractProperty {

    @JsonProperty("foundProperties")
    private final Long foundProperties;

    @JsonCreator
    public PropertyResponse(@JsonProperty("properties") Set<Property> spotippos) {
        super(spotippos);
        this.foundProperties = Long.valueOf(spotippos.size());
    }

    public PropertyResponse(Property property) {
        super(new HashSet<>(Arrays.asList(property)));
        this.foundProperties = 1L;
    }

    public Long getFoundProperties() {
        return foundProperties;
    }

}
