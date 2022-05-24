package tests;

import models.QuoteData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.request;
import static specs.Specs.responseSpec;


public class DuneAPITests {

    /*
    API
GET /quotes
Get a random quote in this format:
https://the-dune-api.herokuapp.com/quotes

[
    {
        id: "14",
        quote: "Fear is the mind-killer."
    }
]
     */

    //Bad
    @Test
    @DisplayName("Проверка Цитаты")
    public void usersListTest() {

        step("Отправляем запрос о цитате");
        QuoteData data = given()
                .spec(request) //base URL
                .when()
                .get("/quotes") //+запрос
                .then()
                .spec(responseSpec)
                .log().body()
                .extract().as(QuoteData.class);

        step("Проверяем совпадение данных в ответе");
        assertEquals(1, data.getQuote()[0].getId());
    }

    //------------------------------------------
    //good
    //PUT
    @Test
    @DisplayName("Обновить пользователя")
    void updateUserTest() {
        String data = "{\"name\": \"morpheus2\", \"job\": \"zion resident1\"}";
        given()
                .body(data)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users")//put
                .then()
                .body("name", is("morpheus2"))
                .body("job", is("zion resident1"));
    }

    //**************************************

     /*
    API
GET /quotes

Get a random quote in this format:
https://the-dune-api.herokuapp.com/quotes

data
[
    {
        id: "14",
        quote: "Fear is the mind-killer."
    }
]
     */


    // №6
    //Bad
    @Test //get SINGLE <RESOURCE>
    @DisplayName("Проверка3")
    void singleResource200unknown2() {

    String data = "{\"id\": 14\n" +
            "       \"quote\": \"Fear is the mind-killer.\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .get("https://the-dune-api.herokuapp.com/quotes")
                .then()
                .body("data.id", is(14))
                .body("data.quote", is("Fear is the mind-killer."));
    }
}
