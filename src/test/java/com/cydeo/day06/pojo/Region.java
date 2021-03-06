package com.cydeo.day06.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString

public class Region {
    @JsonProperty("region_id") //Jackson annotations;@JsonProperty-->is used to change variable name
    private int regionId;

    @JsonProperty("region_name")
    private String regionName;

    @JsonProperty("links")
    private List<Link> links;

    private int count;


}
