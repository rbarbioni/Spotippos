package br.com.rbarbioni.spotippos.controller;

import br.com.rbarbioni.spotippos.model.Spotippos;
import br.com.rbarbioni.spotippos.model.SpotipposResponse;
import br.com.rbarbioni.spotippos.service.SpotipposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by renan on 26/03/2017.
 */
@RestController
@RequestMapping("/properties")
public class SpotipposController {

    private final SpotipposService spotipposService;

    @Autowired
    public SpotipposController(SpotipposService spotipposService) {
        this.spotipposService = spotipposService;
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public SpotipposResponse findById(@NotNull @PathVariable Long id){
        return this.spotipposService.findById(id);
    }

    @RequestMapping(method = {RequestMethod.POST})
    public SpotipposResponse findById(@NotNull @Valid @RequestBody Spotippos spotippos){
        return this.spotipposService.save(spotippos);
    }
}
