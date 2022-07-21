package com.cydeo.day06.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@JsonIgnoreProperties(value = "id", allowSetters = true) //we use this part for day07 SpartanPostRequest test3
public class Spartan {

    private int id;
    private String name;
    private String gender;
    private  long phone;


}
