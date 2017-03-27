package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Created by renan on 26/03/2017.
 */
public class PropertyResponse extends AbstractProperty {

    @JsonProperty("foundProperties")
    private final Long foundProperties;

    @JsonCreator
    public PropertyResponse(@JsonProperty("properties") List<Property> spotippos) {
        super(spotippos);
        this.foundProperties = Long.valueOf(spotippos.size());
    }

    public PropertyResponse(Property property) {
        super(Arrays.asList(property));
        this.foundProperties = 1L;
    }

    public Long getFoundProperties() {
        return foundProperties;
    }

}
