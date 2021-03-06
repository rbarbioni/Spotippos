package br.com.rbarbioni.spotippos.service;

import br.com.rbarbioni.spotippos.exception.SpotipposException;
import br.com.rbarbioni.spotippos.model.Property;
import br.com.rbarbioni.spotippos.model.PropertyResponse;
import br.com.rbarbioni.spotippos.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Created by renan on 26/03/2017.
 */

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property save(Property property){
        return this.propertyRepository.save(property);
    }

    public Property findById(Long id){
        Property property = this.propertyRepository.findById(id);

        if(property == null){
            throw new SpotipposException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
        }
        return property;
    }

    public PropertyResponse query (Integer ax, Integer ay, Integer bx, Integer by){
        return new PropertyResponse(this.propertyRepository.query(ax, ay, bx, by));
    }
}
