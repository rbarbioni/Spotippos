package br.com.rbarbioni.spotippos.model;

import java.io.Serializable;

/**
 * Created by renan on 27/03/17.
 */
public class Province implements Serializable {

    private final String name;

    private final Integer ax;

    private final Integer ay;

    private final Integer bx;

    private final Integer by;

    public Province(String name, Integer ax, Integer ay, Integer bx, Integer by) {
        this.name = name;
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
    }

    public String getName() {
        return name;
    }

    public Integer getAx() {
        return ax;
    }

    public Integer getAy() {
        return ay;
    }

    public Integer getBx() {
        return bx;
    }

    public Integer getBy() {
        return by;
    }
}
