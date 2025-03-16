package web_service;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
public class testGetRequestAbility {
    @Test
    public static void main(String[] args) {


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 1);
        map.put("name", "stench");
        map.put("url", "https://pokeapi.co/api/v2/ability/1/");

        System.out.println(map);

        JSONObject request = new JSONObject(map);
            System.out.println(request.toJSONString());
            given().
                    body(request.toJSONString()).
                    when().
                    get("https://pokeapi.co/api/v2/ability/1/");

}
}
