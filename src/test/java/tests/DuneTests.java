package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuneTests {

    @BeforeAll
    static void beforeAll() {
        baseURI = "https://the-dune-api.herokuapp.com";
    }

    @Test
    @DisplayName("Проверяем, что статус код 200")
    void checkStatus200() {
        get(baseURI)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Проверяем, что список цитат не пуст")
    void getBooksTest() {
        get("/quotes")
                .then()
                .body("id", hasSize(greaterThan(0)));
    }

    @Disabled("Этот тест не будет запущен")
    @Test
    @DisplayName("Проверяем, что цитата с id 14 содержит ожидаемый текст2")
    void checkQuoteAndItId2WithoutGiven() {
        get("/quotes/id/14")
                .then()
                .body("id", is("14"))
                .body("quote", is("Fear is the mind-killer."));
    }

    @Test
    @DisplayName("Проверяем, что цитата с id 14 содержит ожидаемый текст")
    void checkQuoteWithStringData() {
        String data = "{\"id\":\"14\",\"quote\":\"Fear is the mind-killer.\"}";
        given()
                .body(data)
                .contentType(JSON)
                .when()
                .get("/quotes/id/14")
                .then()
                .body("id", is("14"))
                .body("quote", is("Fear is the mind-killer."));
    }

    @Test
    @DisplayName("Запрашиваем случайную книгу. У книги есть название и автор.")
    void checkRandomBook() {
        get("/books")
                .then()
                .assertThat().statusCode(200)
                .body("author", notNullValue())
                .body("title", notNullValue());
    }

    @Test
    @DisplayName("Проверяем книгу, изданную под №2. Проверяем точное название книги.")
    void checkBookNumberIs2() {
        String response = get("/books/id/2")
                .then()
                .extract()
                .path("title");
        System.out.println("Response: " + response);
        String expectedResponse = "Dune Messiah";
        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Проверяем, что размер списка фильмов = 2 записи")
    void checkMoviesListAsTotal() {
        get("/movies/2")
                .then()
                .body("size()", is(2));
    }

    @Test
    @DisplayName("Проверяем 2 параметра сразу. Проверяем имена 2 режиссеров.")
    void directorsTest() {
        Response response = (Response) given()
                .contentType(JSON)
                .when()
                .get("/movies/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> directors = jsonPath.get("director");
        assertThat(directors).contains("Denis Villeneuve", "David Lynch");
    }
}