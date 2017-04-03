package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

/**
 * Created by renan on 27/03/2017.
 */
public class Point implements Serializable {

    private static final long serialVersionUID = 3700803024801347836L;

    private final Integer x;

    private final Integer y;

    @JsonCreator
    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
