package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DuneTestsLevel1 {

    //1
    @Test
    @DisplayName("Проверить статус код 200")
    void checkStatus200() {
        get("https://the-dune-api.herokuapp.com/")
                .then()
                .statusCode(200);
    }

    //2
    //Задача:
    //1. сделать запрос на адрес https://the-dune-api.herokuapp.com/quotes/id/14
    //2. проверить, что "quote" = "Fear is the mind-killer."
    //Шаблон: GET /quotes/id/{id}
    //Ответ: {"id":"14","quote":"Fear is the mind-killer."}
    //Способ 1
    //дано(подготовка данных)--тогда(действие-отправка)--когда(проверка)

    @Test
    @DisplayName("Проверить, что цитата с id=Х содержит текст=Y")
    void checkQuoteAndItId1() {
        given() //здесь начата подготовка
                .when()
                .get("https://the-dune-api.herokuapp.com/quotes/id/14") //действие (запрос)
                .then() //здесь начата проверка
                .body("id", is("14"))
                .body("quote", is("Fear is the mind-killer."));
    }

    //Способ 2.
    @Test
    @DisplayName("Проверить, что цитата с id=Х содержит текст=Y")
    void checkQuoteAndItId2WithoutGiven() {
        get("https://the-dune-api.herokuapp.com/quotes/id/14")
                .then()
                //.body("total", is(20))
                .body("id", is("14"))
                .body("quote", is("Fear is the mind-killer."));
    }

    //3
    @Test
    @DisplayName("Запросить случайную книгу. У книги есть название и автор.")
      /*
    Шаблон: GET /books
    Запрос: Get a random book in this format: https://the-dune-api.herokuapp.com/books
    Ответ: [{"id":"14","title":"Sandworms of Dune","year":"2007","author":["Brian Herbert","Kevin J. Anderson"],"wiki_url":"https://en.wikipedia.org/wiki/Sandworms_of_Dune"}]
     */
    void checkRandomBook() {
        get("https://the-dune-api.herokuapp.com/books")
                .then()
                .assertThat().statusCode(200)
                .body("author", notNullValue())
                .body("title", notNullValue());
    }

    //4
    @Test
    @DisplayName("Проверка книги №2. Проверить точное название книги.")
    //ШАБЛОН: {"id":"2","title":"Dune Messiah","year":"1969","author":"Frank Herbert","wiki_url":"https://en.wikipedia.org/wiki/Dune_Messiah"}
    void checkBookNumberIs2() {
        String response = get("https://the-dune-api.herokuapp.com/books/id/2")
                .then()
                .extract()
                .path("title");
        System.out.println("Response: " + response);
        String expectedResponse = "Dune Messiah";
        assertEquals(expectedResponse, response);
    }

    //5
//ШАБЛОН:
//[{"id":"1","title":"Dune","year":"1984","director":"David Lynch","wiki_url":"https://en.wikipedia.org/wiki/Dune_(1984_film)"},
// {"id":"2","title":"Dune","year":"2021","director":"Denis Villeneuve","wiki_url":"https://en.wikipedia.org/wiki/Dune_(2021_film)"}]

    @Test
    @DisplayName("Проверка списка. Размер списка фильмов: 2 пункта")
    void checkMoviesListAsTotal() {
        get("https://the-dune-api.herokuapp.com/movies/2")
                .then()
                .body("size()", is(2));
    }

//6a
//ШАБЛОН ответа:
//[{"id":"1","title":"Dune","year":"1984","director":"David Lynch","wiki_url":"https://en.wikipedia.org/wiki/Dune_(1984_film)"},
// {"id":"2","title":"Dune","year":"2021","director":"Denis Villeneuve","wiki_url":"https://en.wikipedia.org/wiki/Dune_(2021_film)"}]

    String firstName = "David Lynch";
    String secondName = "Denis Villeneuve";
    String firstYear = "1984";
    String secondYear = "2021";

//6b
    String moviesData = "{\"id\":\"1\"," +
            "\"title\":\"Dune\",\"" +
            "year\":\"1984\",\"" +
            "director\":\"David Lynch\",\"" +
            "wiki_url\":\"https://en.wikipedia.org/wiki/Dune_(1984_film)\"},\n" +
            "// {\"" +
            "id\":\"2\",\"" +
            "title\":\"Dune\",\"" +
            "year\":\"2021\",\"" +
            "director\":\"Denis Villeneuve\",\"" +
            "wiki_url\":\"https://en.wikipedia.org/wiki/Dune_(2021_film)\"}";



    @Test
    @DisplayName("Запросить часть содержимого. Вариант2. Проверить несколько пар параметров сразу. Сравниваем 2 фильма (по 3 парам параметров).")
    void checkMoviesList2WithGiven() {
        given()
                .body(moviesData)
                .contentType(JSON) //отметили структуру данных (здесь по умолчанию это json)
                .when()
                .get("https://the-dune-api.herokuapp.com/movies/2")
                .then()
                .statusCode(200)
                .body("director", hasItems(firstName, secondName))
                .body("year", hasItems(firstYear, secondYear))
                .body("wiki_url", hasItems("https://en.wikipedia.org/wiki/Dune_(1984_film)", "https://en.wikipedia.org/wiki/Dune_(2021_film)"));
    }

    /*
    @Disabled
    @Test
    @DisplayName("Запросить часть содержимого. Вариант1. Проверяем соответствие 2 пар параметров: режиссеров и даты.")
    void checkMoviesListWithoutGiven() {
        get("https://the-dune-api.herokuapp.com/movies/2")
                .then()
                .assertThat()
                .body("director", hasItems(firstName, secondName) ,
                        "year", hasItems(firstYear, secondYear));
                 //String firstName = jsonPath.get("users[0].name");
                 //String secondName = jsonPath.get("users[1].name");
    }
     */

}
