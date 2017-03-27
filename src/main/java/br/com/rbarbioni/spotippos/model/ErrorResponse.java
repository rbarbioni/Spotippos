package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by renan on 26/03/2017.
 */
public class ErrorResponse implements Serializable{

    @JsonProperty("status")
    private final Integer status;

    @JsonProperty("message")
    private final String message;

    public ErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
