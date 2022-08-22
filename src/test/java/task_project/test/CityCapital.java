package task_project.test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static io.restassured.RestAssured.*;

public class CityCapital{

//    @BeforeAll
//    public static void setUp(){
//        baseURI = "https://restcountries.com/v3.1/";
//    }
//
//    @Test
//    public void firstTest(){
//
//        Response response = given().accept(ContentType.JSON).when().get("name/peru");
//
//        assertTrue(response.asString().contains("capital"));


    String url = "https://restcountries.com/v3.1";
    String name = "Suriname";
    String code = "SUR";

    @DisplayName("GET /name => jsonPath filters")
    @Test
    public void jsonPathCountriesNameTest () {
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParams("country_name", name)
                .when().get(url + "/name/{country_name}");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals("application/json", response.contentType());

        assertThat(response.statusCode(), is(200));
        assertThat(response.contentType(), is("application/json"));

        JsonPath jsonPath = response.jsonPath();
        System.out.println("name = " + jsonPath.getString("name.common"));
        System.out.println("capital = " + jsonPath.getString("capital"));
        System.out.println("country_code = " + jsonPath.getString("cca3"));
//        System.out.println("region_id = " + jsonPath.getInt("region_id"));

        //response.prettyPrint();
        System.out.println("status code = " + response.statusCode());
        System.out.println("status line = " + response.statusLine());

        //assertEquals("[[Paramaribo]]", jsonPath.getString("capital"));
        assertEquals("[" + name + "]", jsonPath.getString("name.common"));
        assertEquals("[" + code + "]", jsonPath.getString("cca3"));
//        assertEquals(2, jsonPath.getInt("region_id"));

    }
    @DisplayName("GET /code => jsonPath filters")
    @Test
    public void jsonPathCountriesCodeTest () {

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParams("country_code", code)
                .when().get(url + "/alpha/{country_code}");
//                .then().assertThat().statusCode(200)
//                .contentType(ContentType.JSON);

        assertThat(response.statusCode(), is(200));
        assertThat(response.contentType(), is("application/json"));
        System.out.println("status code = " + response.statusCode());
        System.out.println("status line = " + response.statusLine());

        JsonPath jsonPath = response.jsonPath();
        //assertEquals("[[Paramaribo]]", jsonPath.getString("capital"));
        assertEquals("[" + name + "]", jsonPath.getString("name.common"));
        assertEquals("[" + code + "]", jsonPath.getString("cca3"));

        System.out.println("name = " + jsonPath.getString("name.common"));
        System.out.println("capital = " + jsonPath.getString("capital"));
        System.out.println("country_code = " + jsonPath.getString("cca3"));


    }
}
