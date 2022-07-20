package com.cydeo.day06;

import com.cydeo.day06.pojo.Employee;
import com.cydeo.day06.pojo.Link;
import com.cydeo.day06.pojo.Region;
import com.cydeo.day06.pojo.Regions;
import com.cydeo.utililities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest(){

        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getRegion_name() = " + region1.getRegionName());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());
        System.out.println("region1.getLinks().get(0).getRel() = " + region1.getLinks().get(0).getRel());

        Link link1 = region1.getLinks().get(0);//we can retrieve elements by creating link object which is inside
        //region1 object
        System.out.println("link1.getHref() = " + link1.getHref());
    }
    @DisplayName("GET request to /employees and only get couple of values as a Pojo clas")
    @Test
    public void employeeGet(){

       JsonPath jsonPath = get("/employees").then().statusCode(200).extract().jsonPath();

       Employee employee1 = jsonPath.getObject("items[0]", Employee.class);

       System.out.println(employee1);

    }

    /* send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non-used fields
     */
    @DisplayName("GET request to region only some fields test")
    @Test
    public void regionPojoTest(){

        Response response = get("/regions").then().statusCode(200).extract().response();

        Regions regions = response.as(Regions.class);

        //verify count is 4
        assertThat(regions.getCount(), is(4));

        //create empty list to store values
        List<String> regionNames = new ArrayList<>();
        List<Integer> regionIds = new ArrayList<>();

        //get list of regions out of regions object
        List<Region> items = regions.getItems();

        //loop through each of the region, save their ids and names to empty list that we prepare
        for (Region region : items) {
            regionNames.add(region.getRegionName());
            regionIds.add(region.getRegionId());
        }

        System.out.println("regionIds = " + regionIds);
        System.out.println("regionNames = " + regionNames);

        //prepare expected result
        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        //compare two result
        assertThat(regionIds,is(expectedRegionIds)); //First argument is actual result
        assertThat(regionNames,is(equalTo(expectedRegionNames)));
    }
}
