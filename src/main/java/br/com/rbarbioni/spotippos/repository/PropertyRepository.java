package br.com.rbarbioni.spotippos.repository;

import br.com.rbarbioni.spotippos.exception.SpotipposException;
import br.com.rbarbioni.spotippos.model.Property;
import br.com.rbarbioni.spotippos.model.PropertySource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


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

        Set<Property> properties = new HashSet<>();

        Iterator<Map.Entry<Long, Property>> iterator = PROPERTIES.entrySet().iterator();

        while (iterator.hasNext()){

            Map.Entry<Long, Property> entry = iterator.next();

            Property property = entry.getValue();
            if((property.getX() >= ax && property.getX() <= bx) && (property.getY() >= ay && property.getY() <= by)){
                properties.add(property);
            }
        }

        return properties;
    }

    public synchronized Property findById(Long id){
        return PROPERTIES.get(id);
    }
}