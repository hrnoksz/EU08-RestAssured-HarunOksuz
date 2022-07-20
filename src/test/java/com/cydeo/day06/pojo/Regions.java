package com.cydeo.day06.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter //all of them from lombok dependency
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true) //this from jackson

public class Regions {

    private List<Region> items;
    private int count;
}
