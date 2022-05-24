package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
//import static listeners.CustomAllureListener.withCustomTemplates;
//import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuneTestsLevel2 {

    @BeforeAll
    static void beforeAll()
    {
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

    //1. сделать запрос на адрес https://the-dune-api.herokuapp.com/quotes/id/14
    //2. проверить, что "quote" = "Fear is the mind-killer."
    //Шаблон: GET /quotes/id/{id}
    //Ответ: {"id":"14","quote":"Fear is the mind-killer."}    
    @Disabled ("Этот тест не будет запущен")
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

       /*
    Шаблон: GET /books
    Запрос: Get a random book in this format: https://the-dune-api.herokuapp.com/books
    Ответ: [{"id":"14","title":"Sandworms of Dune","year":"2007","author":["Brian Herbert","Kevin J. Anderson"],"wiki_url":"https://en.wikipedia.org/wiki/Sandworms_of_Dune"}]
     */
    @Test
    @DisplayName("Запрашиваем случайную книгу. У книги есть название и автор.")
    void checkRandomBook() {
        get("/books")
                .then()
                .assertThat().statusCode(200)
                .body("author", notNullValue())
                .body("title", notNullValue());
    }

     //ШАБЛОН: {"id":"2","title":"Dune Messiah","year":"1969","author":"Frank Herbert","wiki_url":"https://en.wikipedia.org/wiki/Dune_Messiah"}
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

    //ШАБЛОН:
//[{"id":"1","title":"Dune","year":"1984","director":"David Lynch","wiki_url":"https://en.wikipedia.org/wiki/Dune_(1984_film)"},
// {"id":"2","title":"Dune","year":"2021","director":"Denis Villeneuve","wiki_url":"https://en.wikipedia.org/wiki/Dune_(2021_film)"}]
     @Test
     @DisplayName("Проверяем, что размер списка фильмов = 2 записи")
     void checkMoviesListAsTotal() {
         get("/movies/2")
                 .then()
                 .body("size()", is(2));
     }

    //ШАБЛОН ответа:
//[{"id":"1","title":"Dune","year":"1984","director":"David Lynch","wiki_url":"https://en.wikipedia.org/wiki/Dune_(1984_film)"},
// {"id":"2","title":"Dune","year":"2021","director":"Denis Villeneuve","wiki_url":"https://en.wikipedia.org/wiki/Dune_(2021_film)"}]
    @Test
    @DisplayName("Проверяем 2 параметра сразу. Проверяем имена 2 режиссеров.")
    void directorsTest() {
        Response response = (Response) given()
                .contentType(JSON) //отметили структуру данных (здесь по умолчанию это json)
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


    /*
//урок 19 логи
    @Test
    @DisplayName("Проверить, что цитата с id=Х содержит текст=Y (3)")
    void checkQuoteAndItIdLog() {
        given() //здесь начата подготовка
                .log().all()
                .when()
                .get("/quotes/id/14") //действие (запрос)
                .then() //здесь начата проверка
                .log().all() //непонятный конфликт
                .body("id", is("14"));
    }
     */
}
