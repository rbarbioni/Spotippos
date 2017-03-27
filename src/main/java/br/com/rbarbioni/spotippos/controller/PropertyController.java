package br.com.rbarbioni.spotippos.controller;

import br.com.rbarbioni.spotippos.model.Property;
import br.com.rbarbioni.spotippos.model.PropertyResponse;
import br.com.rbarbioni.spotippos.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by renan on 26/03/2017.
 */
@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public PropertyResponse findById(@NotNull @PathVariable Long id){
        return this.propertyService.findById(id);
    }

    @RequestMapping(method = {RequestMethod.POST})
    public PropertyResponse findById(@NotNull @Valid @RequestBody Property property){
        return this.propertyService.save(property);
    }
}
