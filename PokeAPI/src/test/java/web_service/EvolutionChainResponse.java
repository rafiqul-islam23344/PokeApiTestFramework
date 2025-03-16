package web_service;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured.*;
public class EvolutionChainResponse {
@Test
    public void test01(){

    Response response = RestAssured.get("https://pokeapi.co/api/v2/evolution-chain/1/");
    int statuseode = response.getStatusCode();
    System.out.println(response.getBody().asString());
    System.out.println(statuseode);
    System.out.println(response.asString());
    System.out.println(response.getTime());
    Assert.assertEquals(statuseode,200);

}
    @Test
    public void testStatusCodeberryfirmness() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry/1/");
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test
    public void testBerryName() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry/1/");

        JsonPath jsonPath = response.jsonPath();
        String berryName = jsonPath.getString("name"); // "cheri" আশা করা হচ্ছে

        Assert.assertEquals(berryName, "cheri");
    }
    @Test
    public void testResponseTime() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry/1/");

        long responseTime = response.getTime();
        System.out.println("Response Time: " + responseTime + " ms");

        Assert.assertTrue(responseTime < 2000); // ২ সেকেন্ডের মধ্যে আসা উচিত
    }
    @Test
    public void testBerryDetails() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry/1/");
        JsonPath jsonPath = response.jsonPath();

        int growthTime = jsonPath.getInt("growth_time"); // 3 আশা করা হচ্ছে
        int maxHarvest = jsonPath.getInt("max_harvest"); // 5 আশা করা হচ্ছে

        Assert.assertEquals(growthTime, 3);
        Assert.assertEquals(maxHarvest, 5);
    }
    @Test
    public void testBerryFirmnessURL() {
        Response response = RestAssured.get("https://pokeapi.co/api/v2/berry/1/");
        JsonPath jsonPath = response.jsonPath();

        String firmnessUrl = jsonPath.getString("firmness.url");
        Assert.assertEquals(firmnessUrl, "https://pokeapi.co/api/v2/berry-firmness/2/");
    }

}
