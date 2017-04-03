package br.com.rbarbioni.spotippos.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by renan on 27/03/17.
 */
class Provinces {

    private static Map<String, Boundaries> getProvinces(){

        Map<String, Boundaries> provinces = new HashMap<>();
        provinces.put("Gode",   new Boundaries(new Point(0, 1000),    new Point(600, 500)));
        provinces.put("Ruja",   new Boundaries(new Point(400, 1000),  new Point(1100, 500)));
        provinces.put("Jaby",   new Boundaries(new Point(1100, 1000), new Point(1400, 500)));
        provinces.put("Scavy",  new Boundaries(new Point(0, 500),     new Point(600, 0)));
        provinces.put("Groola", new Boundaries(new Point(600, 500),   new Point(800, 0)));
        provinces.put("Nova",   new Boundaries(new Point(800, 500),   new Point(1400, 0)));
        return provinces;
    }

    public static List<String> getProvinces(Integer x, Integer y){

        if(x == null && y == null){
            return null;
        }

        return getProvinces().entrySet()
                .stream()
                .filter(entry -> contains(x, y, entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static boolean contains(Integer x, Integer y, Boundaries boundaries){

        return
                boundaries.getUpperLeft().getX() <= x
                && boundaries.getBottomRight().getX() >= x
                && boundaries.getUpperLeft().getY() >= y
                && boundaries.getBottomRight().getY() <= y;
    }

}