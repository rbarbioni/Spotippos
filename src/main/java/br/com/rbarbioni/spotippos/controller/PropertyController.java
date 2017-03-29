package br.com.rbarbioni.spotippos.controller;

import br.com.rbarbioni.spotippos.model.Property;
import br.com.rbarbioni.spotippos.model.PropertyResponse;
import br.com.rbarbioni.spotippos.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    @RequestMapping(method = {RequestMethod.GET})
    public PropertyResponse query(
            @RequestParam("ax") @NotNull @Min(0) @Max(1400) Integer ax,
            @RequestParam("ay") @NotNull @Min(0) @Max(1000) Integer ay,
            @RequestParam("bx") @NotNull @Min(0) @Max(1400) Integer bx,
            @RequestParam("by") @NotNull @Min(0) @Max(1000) Integer by){

        return this.propertyService.query(ax, ay, bx, by);
    }

    @RequestMapping(method = {RequestMethod.POST})
    public PropertyResponse save(@NotNull @Valid @RequestBody Property property){
        return this.propertyService.save(property);
    }
}
