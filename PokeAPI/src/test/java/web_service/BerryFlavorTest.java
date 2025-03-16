package web_service;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured.*;
import io.restassured.RestAssured;

import java.util.List;
import java.util.Map;

public class BerryFlavorTest{
    @Test
    public void testStatusCode() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry-flavor/1/");
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test
    public void testBerryFlavorDetails() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry-flavor/1/");
        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
      // int potency = jsonPath.getInt("potency");

        Assert.assertEquals(id, 1);
        Assert.assertEquals(name, "spicy");
        //Assert.assertEquals(potency, 10);
    }
    @Test
    public void testBerriesList() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry-flavor/1/");
        JsonPath jsonPath = response.jsonPath();

        List<Map<String, String>> berries = jsonPath.getList("berries");

        Assert.assertFalse(((List<?>) berries).isEmpty()); // লিস্ট খালি হওয়া যাবে না
        //Assert.assertEquals(berries.get(0).get("name"), "rowap");
        //Assert.assertEquals(berries.get(0).get("url"), "null");
    }
    @Test
    public void testContestType() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry-flavor/1/");
        JsonPath jsonPath = response.jsonPath();

        Map<String, String> contestType = jsonPath.getMap("contest_type");

        Assert.assertEquals(contestType.get("name"), "cool");
        Assert.assertEquals(contestType.get("url"), "https://pokeapi.co/api/v2/contest-type/1/");
    }
    @Test
    public void testNamesAndLanguage() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry-flavor/1/");
        JsonPath jsonPath = response.jsonPath();

        List<Map<String, Object>> names = jsonPath.getList("names");

        Assert.assertFalse(names.isEmpty()); // লিস্ট খালি হওয়া যাবে না
        Assert.assertEquals(names.get(0).get("name"), "からい");

        Map<String, String> language = (Map<String, String>) names.get(0).get("language");
        Assert.assertEquals(language.get("name"), "ja-Hrkt");
        Assert.assertEquals(language.get("url"), "https://pokeapi.co/api/v2/language/1/");
    }

}
