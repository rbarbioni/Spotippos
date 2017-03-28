package br.com.rbarbioni.spotippos.model;

import java.util.*;

/**
 * Created by renan on 27/03/17.
 */
public class Provinces {

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

    public static List<String> getProvinces(Property property){

        ArrayList<String> provinces = new ArrayList<>();

        Map<String, Boundaries> provincesMap = getProvinces();

        Iterator<Map.Entry<String, Boundaries>> iterator = provincesMap.entrySet().iterator();
        while (iterator.hasNext()){

            Map.Entry<String, Boundaries> entry = iterator.next();

            if(in(property, entry.getValue())){
                provinces.add(entry.getKey());
            }
        }

        return provinces;
    }

    private static boolean in(Property property, Boundaries boundaries){

        int Xmin = boundaries.getUpperLeft().getX() < boundaries.getBottomRight().getX() ? boundaries.getUpperLeft().getX() : boundaries.getBottomRight().getX();
        int Xmax = boundaries.getUpperLeft().getX() > boundaries.getBottomRight().getX() ? boundaries.getUpperLeft().getX() : boundaries.getBottomRight().getX();

        int Ymin = boundaries.getUpperLeft().getY() < boundaries.getBottomRight().getY() ? boundaries.getUpperLeft().getY() : boundaries.getBottomRight().getY();
        int Ymax = boundaries.getUpperLeft().getY() > boundaries.getBottomRight().getY() ? boundaries.getUpperLeft().getY() : boundaries.getBottomRight().getY();

        return between(property.getX(), Xmin, Xmax) && between(property.getY(), Ymin, Ymax);
    }

    private static boolean between(int value, int min, int max) {
        return min <= value && value <= max;
    }
}