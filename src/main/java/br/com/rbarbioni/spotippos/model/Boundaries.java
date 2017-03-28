package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by renan on 27/03/2017.
 */
public class Boundaries {

    private final Point upperLeft;

    private final Point bottomRight;

    @JsonCreator
    public Boundaries(Point upperLeft, Point bottomRight) {
        this.upperLeft = upperLeft;
        this.bottomRight = bottomRight;
    }

    public Point getUpperLeft() {
        return upperLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }
}
