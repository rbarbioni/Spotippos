package br.com.rbarbioni.spotippos.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by renan on 27/03/2017.
 */
public class MapUtil {

    public static <K, V> Map<K, V> toMap(String key, Object value){
        HashMap map = new HashMap();
        map.put(key, value);
        return map;
    }
}
