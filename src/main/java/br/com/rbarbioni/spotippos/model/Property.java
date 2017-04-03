package br.com.rbarbioni.spotippos.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by renan on 26/03/2017.
 */
public class Property implements Serializable{

    private static final long serialVersionUID = -955231805543935925L;

    private static final AtomicLong seq = new AtomicLong();

    @JsonProperty("id")
    private final Long id;

    @NotNull(message = "validation.property.x.null")
    @Min(value = 0, message = "validation.property.x.min")
    @Max(value = 1400, message = "validation.property.x.max")
    private final Integer x;

    @NotNull(message = "validation.property.y.null")
    @Min(value = 0, message = "validation.property.y.min")
    @Max(value = 1000, message = "validation.property.y.max")
    private final Integer y;

    @NotEmpty(message = "validation.property.title.null_or_empty")
    private final String title;

    @NotNull(message = "validation.property.price.null")
    @Min(value = 0, message = "validation.property.price.min")
    private final BigDecimal price;

    @NotEmpty(message = "validation.property.description.null_or_empty")
    private final String description;

    @NotNull(message = "validation.property.beds.null")
    @Min(value = 1, message = "validation.property.beds.min")
    @Max(value = 5, message = "validation.property.beds.max")
    private final Integer beds;

    @NotNull(message = "validation.property.baths.null")
    @Min(value = 1, message = "validation.property.baths.min")
    @Max(value = 4, message = "validation.property.baths.max")
    private final Integer baths;

    @NotNull(message = "validation.property.squareMeters.null")
    @Min(value = 20, message = "validation.property.squareMeters.min")
    @Max(value = 240, message = "validation.property.squareMeters.max")
    private final Long squareMeters;

    private final List<String> provinces;

    @JsonCreator
    public Property(
            @JsonProperty("x") Integer x,
            @JsonProperty("y") Integer y,
            @JsonProperty("lat") Integer lat,
            @JsonProperty("long") Integer lon,
            @JsonProperty("title") String title,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("description") String description,
            @JsonProperty("beds") Integer beds,
            @JsonProperty("baths") Integer baths,
            @JsonProperty("squareMeters") Long squareMeters) {
        this.id = seq.incrementAndGet();
        this.x = x != null ? x : lat;
        this.y = y != null ? y : lon;
        this.title = title;
        this.price = price;
        this.description = description;
        this.beds = beds;
        this.baths = baths;
        this.squareMeters = squareMeters;
        this.provinces = Provinces.getProvinces(this.x, this.y);
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

    public Long getSquareMeters() {
        return squareMeters;
    }

    @JsonGetter
    public List<String> getProvinces (){
        return provinces;
    }

    public boolean apply(Integer ax, Integer ay, Integer bx, Integer by){
        return (this.x >= ax && this.x <= bx) && (this.y >= ay && this.y <= by);
    }
}
