package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by renan on 26/03/2017.
 */
public class Property implements Serializable{

    private static final AtomicLong seq = new AtomicLong();

    @JsonProperty("id")
    private Long id;

    @NotNull
    @Min(0)
    @Max(1400)
    @JsonProperty("x")
    private Integer x;

    @NotNull
    @Min(0)
    @Max(1000)
    @JsonProperty("y")
    private Integer y;

    @NotEmpty
    @JsonProperty("title")
    private String title;

    @NotNull
    @Min(0)
    @JsonProperty("price")
    private BigDecimal price;

    @NotEmpty
    @JsonProperty("description")
    private String description;

    @NotNull
    @Min(1)
    @Max(5)
    @JsonProperty("beds")
    private Integer beds;

    @NotNull
    @Min(1)
    @Max(4)
    @JsonProperty("baths")
    private Integer baths;

    @NotNull
    @Min(20)
    @Max(240)
    @JsonProperty("squareMeters")
    private Long squareMetters;

    private Property(){
        super();
        this.id = seq.incrementAndGet();
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

    @JsonSetter
    public void setLat(Integer lat){
        this.x = lat;
    }

    @JsonSetter
    public void setLong(Integer lon){
        this.y = lon;
    }

    @JsonProperty("provinces")
    public List<String> provinces (){
        return Provinces.getProvinces(this);
    }

}
