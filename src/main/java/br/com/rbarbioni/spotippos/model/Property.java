package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by renan on 26/03/2017.
 */
public class Property implements Serializable{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("x")
    private Integer x;

    @JsonProperty("y")
    private Integer y;

    @JsonProperty("title")
    private String title;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("description")
    private String description;

    @JsonProperty("beds")
    private Integer beds;

    @JsonProperty("baths")
    private Integer baths;

    @JsonProperty("squareMeters")
    private Long squareMetters;

    private Property(){
        super();
    }

    public Property(Integer x, Integer y, String title, BigDecimal price, String description, Integer beds, Integer baths, Long squareMetters) {
        this();
        this.x = x;
        this.y = y;
        this.title = title;
        this.price = price;
        this.description = description;
        this.beds = beds;
        this.baths = baths;
        this.squareMetters = squareMetters;
    }

    public Long getId() {
        return id;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Integer getBeds() {
        return beds;
    }

    public Integer getBaths() {
        return baths;
    }

    public Long getSquareMetters() {
        return squareMetters;
    }

    @JsonProperty("provinces")
    public List<String> provinces (){
        return null;
    }

    @JsonIgnore
    public synchronized Long generateId(Integer size) {
        id = Long.valueOf(size+1);
        return id;
    }
}
