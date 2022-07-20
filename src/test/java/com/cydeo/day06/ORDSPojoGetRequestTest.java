package com.cydeo.day06;

import com.cydeo.day06.pojo.Link;
import com.cydeo.day06.pojo.Region;
import com.cydeo.utililities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest(){

        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRegion_id());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());
        System.out.println("region1.getLinks().get(0).getRel() = " + region1.getLinks().get(0).getRel());

        Link link1 = region1.getLinks().get(0);//we can retrieve elements by creating link object which is inside
        //region1 object
        System.out.println("link1.getHref() = " + link1.getHref());
    }
}
