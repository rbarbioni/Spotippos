package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by renan on 26/03/2017.
 */
public class ResponseError implements Serializable{

    private static final long serialVersionUID = -4252006764524660205L;

    private final long timestamp;

    private final Integer status;

    private final String error;

    private final String message;

    private final String path;

    private final List<String> errors;

    @JsonCreator
    public ResponseError(@JsonProperty("status") Integer status, @JsonProperty("error") String errror,  @JsonProperty("message") String message,  @JsonProperty("path") String path, @JsonProperty("errors") List<String> errors) {
        this.timestamp = new Date().getTime();
        this.status = status;
        this.message = message;
        this.error = errror;
        this.path = path;
        this.errors = errors;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
