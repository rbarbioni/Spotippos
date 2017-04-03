package br.com.rbarbioni.spotippos.repository;

import br.com.rbarbioni.spotippos.model.Property;
import br.com.rbarbioni.spotippos.model.PropertySource;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Created by renan on 26/03/2017.
 */
@Component
public class PropertyRepository {

    private static final Logger logger = LoggerFactory.getLogger(PropertyRepository.class);

    private static final Map<Long, Property> PROPERTIES = Collections.synchronizedMap(new HashMap<>());

    private final Resource resource;

    private final ObjectMapper objectMapper;

    @Autowired
    public PropertyRepository(@Value("classpath:properties.json") Resource resource, ObjectMapper objectMapper) {
        this.resource = resource;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void initialize(){

        try {
            InputStream inputStream = resource.getInputStream();
            PropertySource spotipposSource = this.objectMapper.readValue(inputStream, PropertySource.class);
            for (Property property : spotipposSource.getProperties()) {
                PROPERTIES.put(property.getId(), property);
            }

        } catch (IOException e) {
            logger.error("Error initialize PROPERTIES source classpath:properties.json", e);
        }
    }

    public synchronized Property save(Property property){
        PROPERTIES.put(property.getId(), property);
        return property;
    }

    public synchronized Set<Property> query(Integer ax, Integer ay, Integer bx, Integer by){

        return PROPERTIES.values()
                .parallelStream()
                .filter(property -> property.apply(ax, ay, bx, by))
                .collect(Collectors.toSet());
    }

    public synchronized Property findById(Long id){
        return PROPERTIES.get(id);
    }
}