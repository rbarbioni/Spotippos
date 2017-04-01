package br.com.rbarbioni.spotippos.integration;

import br.com.rbarbioni.spotippos.controller.PropertyController;
import br.com.rbarbioni.spotippos.exception.SpotipposException;
import br.com.rbarbioni.spotippos.model.Property;
import br.com.rbarbioni.spotippos.model.PropertyResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.math.BigDecimal;

/**
 * Created by renan on 30/03/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(PropertyController.class)
public class PropertyIntegrationTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void save (){
        Property property = new Property(1257, 928, "title", BigDecimal.ONE, "description", 1, 1, 1L);
        this.mvc.perform()
        property = this.propertyController.save(property);
        Assert.assertNotNull(property);
    }

//    @Test
//    public void saveValidation (){
//        Property property = new Property(1257, 928, "title", BigDecimal.ONE, "description", 1, 1, 1L);
//        property = this.propertyController.save(property);
//        Assert.assertNotNull(property);
//    }
//
//    @Test
//    public void findById (){
//        PropertyResponse propertyResponse = this.propertyController.findById(1L);
//        Assert.assertNotNull(propertyResponse);
//        Assert.assertEquals(propertyResponse.getFoundProperties(), Long.valueOf(1L));
//    }
//
//    @Test(expected = SpotipposException.class)
//    public void findByIdNotFound (){
//        PropertyResponse propertyResponse = this.propertyController.findById(Long.MAX_VALUE);
//        Assert.assertNotNull(propertyResponse);
//        Assert.assertEquals(propertyResponse.getFoundProperties(), Long.valueOf(0));
//    }
//
//    @Test
//    public void queryZeroResults (){
//        PropertyResponse propertyResponse = this.propertyController.query(0, 0, 0, 0);
//        Assert.assertNotNull(propertyResponse);
//        Assert.assertEquals(propertyResponse.getFoundProperties(), Long.valueOf(0L));
//    }
//
//    @Test
//    public void queryManyResults (){
//        PropertyResponse propertyResponse = this.propertyController.query(0, 0, 1400, 1000);
//        Assert.assertNotNull(propertyResponse);
//        Assert.assertTrue(propertyResponse.getFoundProperties() > 8000);
//    }

}
