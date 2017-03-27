package br.com.rbarbioni.spotippos.service;

import br.com.rbarbioni.spotippos.model.Property;
import br.com.rbarbioni.spotippos.model.PropertyResponse;
import br.com.rbarbioni.spotippos.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public PropertyResponse save(Property property){
        return new PropertyResponse(this.propertyRepository.save(property));
    }

    public PropertyResponse findById(Long id){
        return new PropertyResponse(this.propertyRepository.findById(id));
    }

    public PropertyResponse find (Integer ax, Integer ay, Integer bx, Integer by){
        return null;
    }

}
