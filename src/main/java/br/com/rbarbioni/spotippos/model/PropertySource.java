package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by renan on 26/03/2017.
 */
public class PropertySource extends AbstractProperty implements Serializable {

    private static final long serialVersionUID = 2283181207844719728L;

    private final Long foundProperties;

    @JsonCreator
    public PropertySource(@JsonProperty("properties") Set<Property> spotippos) {
        super(spotippos);
        this.foundProperties = (long) spotippos.size();
    }

    @JsonGetter("totalProperties")
    public Long getFoundProperties() {
        return foundProperties;
    }
}
