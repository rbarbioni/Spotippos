package br.com.rbarbioni.spotippos.repository;

import br.com.rbarbioni.spotippos.model.Spotippos;
import br.com.rbarbioni.spotippos.model.SpotipposSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * Created by renan on 26/03/2017.
 */
@Component
public class SpotipposRepository {

    private static final Logger logger = LoggerFactory.getLogger(SpotipposRepository.class);

    private static final Map<Long, Spotippos> STORAGE = Collections.synchronizedMap(new HashMap<>());

    private final Resource resource;

    private final ObjectMapper objectMapper;

    @Autowired
    public SpotipposRepository(@Value("classpath:properties.json") Resource resource, ObjectMapper objectMapper) {
        this.resource = resource;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void initialize(){

        try {
            InputStream inputStream = resource.getInputStream();
            SpotipposSource spotipposSource = this.objectMapper.readValue(inputStream, SpotipposSource.class);
            for (Spotippos spotippos : spotipposSource.getSpotippos()) {
                STORAGE.put(spotippos.getId(), spotippos);
            }

        } catch (IOException e) {
            logger.error("Error initialize STORAGE source classpath:properties.json", e);
        }
    }

    public synchronized Spotippos save(Spotippos spotippos){
        STORAGE.put(spotippos.generateId(STORAGE.size()), spotippos);
        return spotippos;
    }

    public synchronized Spotippos findById(Long id){
        return STORAGE.get(id);
    }
}