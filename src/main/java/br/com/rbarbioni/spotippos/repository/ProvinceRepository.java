package br.com.rbarbioni.spotippos.repository;

import br.com.rbarbioni.spotippos.model.Province;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by renan on 27/03/17.
 */
public class ProvinceRepository {


    private static final List<Province> PROVINCES = Collections.synchronizedList(new LinkedList<>());

    public ProvinceRepository(){

        PROVINCES.add(new Province("Gode",   0, 0, 500, 500));
        PROVINCES.add(new Province("Ruja",   400, 0, 1000, 500));
        PROVINCES.add(new Province("Jaby",   1000, 0, 1400, 500));
        PROVINCES.add(new Province("Scavy",  0, 500, 500, 1000));
        PROVINCES.add(new Province("Groola", 500, 500, 900, 1000));
        PROVINCES.add(new Province("Nova",   900, 500, 1400, 1000));
    }

    public List<Province> findAll (){
        return PROVINCES;
    }
}
