package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Created by renan on 26/03/2017.
 */
public class SpotipposResponse extends AbstractSpotippos {

    @JsonProperty("foundProperties")
    private final Long foundProperties;

    @JsonCreator
    public SpotipposResponse(@JsonProperty("properties") List<Spotippos> spotippos) {
        super(spotippos);
        this.foundProperties = Long.valueOf(spotippos.size());
    }

    public SpotipposResponse(Spotippos spotippos) {
        super(Arrays.asList(spotippos));
        this.foundProperties = 1L;
    }

    public Long getFoundProperties() {
        return foundProperties;
    }

}
