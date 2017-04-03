package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.*;

/**
 * Created by renan on 26/03/2017.
 */
public class PropertyResponse extends AbstractProperty implements Serializable {

    private static final long serialVersionUID = -1298366674984872572L;

    @JsonProperty("foundProperties")
    private final Long foundProperties;

    @JsonCreator
    public PropertyResponse(@JsonProperty("properties") Collection<Property> spotippos) {
        super(spotippos);
        this.foundProperties = (long) spotippos.size();
    }

    public Long getFoundProperties() {
        return foundProperties;
    }

}
