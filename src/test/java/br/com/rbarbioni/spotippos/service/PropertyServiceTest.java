package br.com.rbarbioni.spotippos.service;

import br.com.rbarbioni.spotippos.exception.SpotipposException;
import br.com.rbarbioni.spotippos.model.Property;
import br.com.rbarbioni.spotippos.model.PropertyResponse;
import br.com.rbarbioni.spotippos.repository.PropertyRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;

/**
 * Created by renan on 30/03/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class PropertyServiceTest {

    @InjectMocks
    private PropertyService propertyService;

    @Mock
    private PropertyRepository propertyRepository;

    @Test
    public void save(){
        Property property = new Property(1, 1, "title", BigDecimal.ONE, "description", 1, 1, 1L);
        Mockito.when(this.propertyRepository.save(Mockito.any(Property.class))).thenReturn(property);
        property = this.propertyService.save(property);
        Assert.assertNotNull(property);
    }

    @Test
    public void findById(){
        Property property = new Property(1, 1, "title", BigDecimal.ONE, "description", 1, 1, 1L);
        Mockito.when(this.propertyRepository.findById(Mockito.anyLong())).thenReturn(property);
        PropertyResponse propertyResponse = this.propertyService.findById(1L);
        Assert.assertNotNull(propertyResponse);
        Assert.assertEquals(propertyResponse.getFoundProperties(), Long.valueOf(1));
    }

    @Test(expected = SpotipposException.class)
    public void findByIdNotFound(){
        Mockito.when(this.propertyRepository.findById(Mockito.anyLong())).thenReturn(null);
        this.propertyService.findById(1L);
    }

    @Test
    public void query(){
        HashSet<Property> set = new HashSet();
        set.add(new Property(1, 1, "title", BigDecimal.ONE, "description", 1, 1, 1L));
        Mockito.when(this.propertyRepository.query(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(set);
        PropertyResponse propertyResponse = this.propertyService.query(1, 1, 1, 1);
        Assert.assertNotNull(propertyResponse);
        Assert.assertEquals(propertyResponse.getFoundProperties(), Long.valueOf(1));
    }

    @Test
    public void queryEmptyList(){
        HashSet<Property> set = new HashSet();
        Mockito.when(this.propertyRepository.query(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(set);
        PropertyResponse propertyResponse = this.propertyService.query(1, 1, 1, 1);
        Assert.assertNotNull(propertyResponse);
        Assert.assertEquals(propertyResponse.getFoundProperties(), Long.valueOf(0));
    }
}
