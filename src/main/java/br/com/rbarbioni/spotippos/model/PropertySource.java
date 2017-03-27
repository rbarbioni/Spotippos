package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Created by renan on 26/03/2017.
 */
public class PropertySource extends AbstractProperty {

    @JsonProperty("totalProperties")
    private final Long foundProperties;

    @JsonCreator
    public PropertySource(@JsonProperty("properties") List<Property> spotippos) {
        super(spotippos);
        this.foundProperties = Long.valueOf(spotippos.size());
    }

    public PropertySource(Property property) {
        super(Arrays.asList(property));
        this.foundProperties = 1L;
    }

    public Long getFoundProperties() {
        return foundProperties;
    }
}
