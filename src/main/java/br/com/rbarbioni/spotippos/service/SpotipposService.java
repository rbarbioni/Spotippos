package br.com.rbarbioni.spotippos.service;

import br.com.rbarbioni.spotippos.model.Spotippos;
import br.com.rbarbioni.spotippos.model.SpotipposResponse;
import br.com.rbarbioni.spotippos.repository.SpotipposRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renan on 26/03/2017.
 */

@Service
public class SpotipposService {

    private final SpotipposRepository spotipposRepository;

    @Autowired
    public SpotipposService(SpotipposRepository spotipposRepository) {
        this.spotipposRepository = spotipposRepository;
    }

    public SpotipposResponse save(Spotippos spotippos){
        return new SpotipposResponse(this.spotipposRepository.save(spotippos));
    }

    public SpotipposResponse findById(Long id){
        return new SpotipposResponse(this.spotipposRepository.findById(id));
    }

    public SpotipposResponse find (Integer ax, Integer ay, Integer bx, Integer by){
        return null;
    }

}
