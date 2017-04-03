package br.com.rbarbioni.spotippos.integration;

import br.com.rbarbioni.spotippos.model.Property;
import br.com.rbarbioni.spotippos.model.PropertyResponse;
import br.com.rbarbioni.spotippos.model.ResponseError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by renan on 30/03/17.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PropertyIntegrationTest {

    private static final String PROPERTIES_URL = "/properties";

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void save () throws IOException {
        ResponseEntity<String> responseEntity = rest.postForEntity(PROPERTIES_URL, new Property(1257, 928, null, null, "title", BigDecimal.ONE, "description", 1, 1, 21L), String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), 200);
        Property property = this.mapper.readValue(responseEntity.getBody(), Property.class);
        Assert.assertNotNull(property);
        Assert.assertTrue(property.getProvinces().size() > 0);
    }

    @Test
    public void saveValidationNullProperty() throws IOException, URISyntaxException {

        RequestEntity<Object> requestEntity = RequestEntity.post(new URI(PROPERTIES_URL)).header("Content-Type", "application/json").body(null);
        ResponseEntity<String> responseEntity = rest.exchange(requestEntity, String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertNotNull(responseError.getMessage());

    }

    @Test
    public void saveValidationMaxXY () throws IOException {
        ResponseEntity<String> responseEntity = rest.postForEntity(PROPERTIES_URL, new Property(1500, 1100, null, null, "title", BigDecimal.ONE, "description", 1, 1, 21L), String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getErrors().size(), 2);
    }

    @Test
    public void saveValidationMinXY () throws IOException {
        ResponseEntity<String> responseEntity = rest.postForEntity(PROPERTIES_URL, new Property(-1, -1, null, null, "title", BigDecimal.ONE, "description", 1, 1, 21L), String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getErrors().size(), 2);
    }

    @Test
    public void saveValidationNotEmptyTitle () throws IOException {
        ResponseEntity<String> responseEntity = rest.postForEntity(PROPERTIES_URL, new Property(1, 1, null, null, "", BigDecimal.ONE, "description", 1, 1, 21L), String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getErrors().size(), 1);
    }

    @Test
    public void saveValidationNotEmptyDescription () throws IOException {
        ResponseEntity<String> responseEntity = rest.postForEntity(PROPERTIES_URL, new Property(1, 1, null, null, "title", BigDecimal.ONE, "", 1, 1, 21L), String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getErrors().size(), 1);
    }

    @Test
    public void saveValidationPrice () throws IOException {
        ResponseEntity<String> responseEntity = rest.postForEntity(PROPERTIES_URL, new Property(1, 1, null, null, "title", null, "description", 1, 1, 21L), String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getErrors().size(), 1);
    }

    @Test
    public void saveValidationBedsMin0 () throws IOException {
        ResponseEntity<String> responseEntity = rest.postForEntity(PROPERTIES_URL, new Property(1, 1, null, null, "title", BigDecimal.ONE, "description", 0, 1, 21L), String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getErrors().size(), 1);
    }

    @Test
    public void saveValidationBedsMax5 () throws IOException {
        ResponseEntity<String> responseEntity = rest.postForEntity(PROPERTIES_URL, new Property(1, 1, null, null, "title", BigDecimal.ONE, "description", 6, 1, 21L), String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getErrors().size(), 1);
    }

    @Test
    public void saveValidationBathsMin0 () throws IOException {
        ResponseEntity<String> responseEntity = rest.postForEntity(PROPERTIES_URL, new Property(1, 1, null, null, "title", BigDecimal.ONE, "description", 1, 0, 21L), String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getErrors().size(), 1);
    }

    @Test
    public void saveValidationBathsMax4 () throws IOException {
        ResponseEntity<String> responseEntity = rest.postForEntity(PROPERTIES_URL, new Property(1, 1, null, null, "title", BigDecimal.ONE, "description", 1, 5, 21L), String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getErrors().size(), 1);
    }

    @Test
    public void saveValidationSquareMettersMin20 () throws IOException {
        ResponseEntity<String> responseEntity = rest.postForEntity(PROPERTIES_URL, new Property(1, 1, null, null, "title", BigDecimal.ONE, "description", 1, 1, 19L), String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getErrors().size(), 1);
    }

    @Test
    public void saveValidationSquareMettersMax240 () throws IOException {
        ResponseEntity<String> responseEntity = rest.postForEntity(PROPERTIES_URL, new Property(1, 1, null, null, "title", BigDecimal.ONE, "description", 1, 1, 241L), String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getErrors().size(), 1);
    }

    @Test
    public void saveValidationAll () throws IOException {
        ResponseEntity<String> responseEntity = rest.postForEntity(PROPERTIES_URL, new Property(null, null, null, null, null, null, null, null, null, null), String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getErrors().size(), 8);
    }



    @Test
    public void findById () throws IOException {

        ResponseEntity<String> responseEntity = this.rest.getForEntity(PROPERTIES_URL + "/1", String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.OK.value());
        Property property = this.mapper.readValue(responseEntity.getBody(), Property.class);
        Assert.assertNotNull(property);
        Assert.assertTrue(property.getProvinces().size() > 0);
    }

    @Test
    public void findById404 () throws IOException {

        ResponseEntity<String> responseEntity = this.rest.getForEntity(PROPERTIES_URL + "/9999", String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.NOT_FOUND.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getStatus().intValue(), HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void queryZeroResults () throws IOException {

        ResponseEntity<String> responseEntity = this.rest.getForEntity(PROPERTIES_URL + "?ax=0&ay=0&bx=0&by=0", String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.OK.value());
        PropertyResponse propertyResponse = this.mapper.readValue(responseEntity.getBody(), PropertyResponse.class);
        Assert.assertNotNull(propertyResponse);
        Assert.assertTrue(propertyResponse.getFoundProperties() == 0L);
    }

    @Test
    public void queryManyResultsCheckProvinces () throws IOException {

        ResponseEntity<String> responseEntity = this.rest.getForEntity(PROPERTIES_URL + "?ax=0&ay=0&bx=1400&by=1000", String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.OK.value());
        PropertyResponse propertyResponse = this.mapper.readValue(responseEntity.getBody(), PropertyResponse.class);
        Assert.assertNotNull(propertyResponse);
        Assert.assertTrue(propertyResponse.getFoundProperties() > 0L);

        for (Property property : propertyResponse.getProperties()) {
            Assert.assertTrue(property.getProvinces().size() > 0);
        }
    }

    @Test
    public void queryValidationRequiredParameters () throws IOException {

        ResponseEntity<String> responseEntity = this.rest.getForEntity(PROPERTIES_URL + "?z=1", String.class);
        Assert.assertEquals(responseEntity.getStatusCode().value(), HttpStatus.BAD_REQUEST.value());
        ResponseError responseError = this.mapper.readValue(responseEntity.getBody(), ResponseError.class);
        Assert.assertNotNull(responseError);
        Assert.assertEquals(responseError.getError(), HttpStatus.BAD_REQUEST.name());
    }
}