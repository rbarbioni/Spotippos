package br.com.rbarbioni.spotippos.exception;

/**
 * Created by renan on 11/02/17.
 */
public class SpotipposException extends RuntimeException {

    private final Integer status;

    public SpotipposException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}