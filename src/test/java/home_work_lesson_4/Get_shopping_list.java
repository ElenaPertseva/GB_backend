package home_work_lesson_4;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Get_shopping_list extends AbstractTest{
    @Test
    void postStatusCode200() {

        given()
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl()+"/mealplanner/:username/shopping-list")
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Test
    void postApiResponseFields() {

        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl()+"/mealplanner/:username/shopping-list")
                .body()
                .jsonPath();
        assertThat(response.get("number"), equalTo(10));
        assertThat(response.get("offset"), equalTo(0));
        assertThat(response.get("totalResults"), equalTo(5219));

    }

    @Test
    void postStatusLineOk() {

        given()
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl()+"/mealplanner/:username/shopping-list")
                .then()
                .assertThat()
                .statusLine(containsString("OK"));

    }

    @Test
    void postHeaderKeepAlive() {

        given()
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl()+"/mealplanner/:username/shopping-list")
                .then()
                .assertThat()
                .header("Connection", "keep-alive");

    }

    @Test
    void postTimeLessThan600() {

        given()
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl()+"/mealplanner/:username/shopping-list")
                .then()
                .assertThat()
                .time(lessThan(600L));

    }
}
