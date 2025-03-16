package web_service;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class BerryFirmnesses {

    @Test
    public void testStatusCode() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry-firmness/1/");
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test
    public void testBerryFirmnessName() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry-firmness/1/");
        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");

        Assert.assertEquals(id, 1);
        Assert.assertEquals(name, "very-soft");
    }

    @Test
    public void testBerriesList() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry-firmness/1/");
        JsonPath jsonPath = response.jsonPath();

        List<Map<String, String>> berries = jsonPath.getList("berries");

        Assert.assertFalse(berries.isEmpty()); // লিস্ট খালি হওয়া যাবে না
        Assert.assertEquals(berries.get(0).get("name"), "pecha");
        Assert.assertEquals(berries.get(0).get("url"), "https://pokeapi.co/api/v2/berry/3/");
    }
    @Test
    public void testNamesAndLanguage() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry-firmness/1/");
        JsonPath jsonPath = response.jsonPath();

        List<Map<String, Object>> names = jsonPath.getList("names");

        Assert.assertFalse(names.isEmpty()); // লিস্ট খালি হওয়া যাবে না
        Assert.assertEquals(names.get(0).get("name"), "とてもやわらかい");

        Map<String, String> language = (Map<String, String>) names.get(0).get("language");
        Assert.assertEquals(language.get("name"), "ja-Hrkt");
        Assert.assertEquals(language.get("url"), "https://pokeapi.co/api/v2/language/1/");
    }

}
