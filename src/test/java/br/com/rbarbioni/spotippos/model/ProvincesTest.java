package br.com.rbarbioni.spotippos.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by renan on 30/03/17.
 */
public class ProvincesTest {

    @Test
    public void getProvinces(){
        List<String> provinces = Provinces.getProvinces(1257, 928);
        Assert.assertNotNull(provinces);
        Assert.assertEquals(provinces.size(), 1);
    }

    @Test
    public void getProvincesMultiples(){
        List<String> provinces = Provinces.getProvinces(471, 839);
        Assert.assertNotNull(provinces);
        Assert.assertEquals(provinces.size(), 2);
    }

}
