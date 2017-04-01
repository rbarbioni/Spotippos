package br.com.rbarbioni.spotippos.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by renan on 30/03/17.
 */
public class ProvincesTest {

    @Test
    public void getProvinces(){
        Property property = new Property(1257, 928, "title", BigDecimal.ONE, "description", 1, 1, 1L);
        List<String> provinces = Provinces.getProvinces(property);
        Assert.assertNotNull(provinces);
        Assert.assertEquals(provinces.size(), 1);
    }

    @Test
    public void getProvincesMultiples(){
        Property property = new Property(471, 839, "title", BigDecimal.ONE, "description", 1, 1, 1L);
        List<String> provinces = Provinces.getProvinces(property);
        Assert.assertNotNull(provinces);
        Assert.assertEquals(provinces.size(), 2);
    }

}
