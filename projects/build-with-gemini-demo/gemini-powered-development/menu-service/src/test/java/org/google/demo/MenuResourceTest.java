package org.google.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.containsString;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class MenuResourceTest {

  @Inject
  MenuRepository menuRepository;

  @BeforeEach
  @Transactional
  public void setup() {
    menuRepository.deleteAll();
  }

  @Test
  public void testCreateMenu() {
    Menu menu = new Menu();
    menu.itemName = "Test Item";
    menu.itemPrice = java.math.BigDecimal.valueOf(10.0);
    menu.spiceLevel = 1;
    menu.tagLine = "Test Tagline";
    menu.status = Status.Ready;

    given()
        .contentType(ContentType.JSON)
        .body(menu)
        .when()
        .post("/menu")
        .then()
        .statusCode(200)
        .body("id", notNullValue())
        .body("itemName", is("Test Item"));
  }

  @Test
  public void testGetMenuNotFound() {
    given()
        .when()
        .get("/menu/999")
        .then()
        .statusCode(404)
        .body(containsString("Menu item with id 999 does not exist"));
  }

  @Test
  public void testUpdateMenuNotFound() {
    MenuUpdateDTO menu = new MenuUpdateDTO();
    menu.itemName = "New Name";

    given()
        .contentType(ContentType.JSON)
        .body(menu)
        .when()
        .put("/menu/999")
        .then()
        .statusCode(404)
        .body(containsString("Menu item with id 999 does not exist"));
  }

  @Test
  public void testDeleteMenuNotFound() {
    given()
        .when()
        .delete("/menu/999")
        .then()
        .statusCode(404)
        .body(containsString("Menu item with id 999 does not exist"));
  }

  @Test
  public void testDeleteAndRetrieve() {
    Menu menu = new Menu();
    menu.itemName = "To Be Deleted";
    menu.status = Status.Ready;

    // Create
    Integer id = given()
        .contentType(ContentType.JSON)
        .body(menu)
        .when()
        .post("/menu")
        .then()
        .statusCode(200)
        .extract()
        .path("id");

    // Delete
    given()
        .when()
        .delete("/menu/" + id)
        .then()
        .statusCode(204);

    // Retrieve - should be 404
    given()
        .when()
        .get("/menu/" + id)
        .then()
        .statusCode(404);
  }

  @Test
  public void testPartialUpdatePreservesSpiceLevel() {
    Menu menu = new Menu();
    menu.itemName = "Spicy Tuna Roll";
    menu.itemPrice = java.math.BigDecimal.valueOf(12.0);
    menu.spiceLevel = 3;
    menu.status = Status.Ready;

    // Create
    Integer id = given()
        .contentType(ContentType.JSON)
        .body(menu)
        .when()
        .post("/menu")
        .then()
        .statusCode(200)
        .extract()
        .path("id");

    // Update only price
    MenuUpdateDTO updateDTO = new MenuUpdateDTO();
    updateDTO.itemPrice = java.math.BigDecimal.valueOf(15.0);

    given()
        .contentType(ContentType.JSON)
        .body(updateDTO)
        .when()
        .put("/menu/" + id)
        .then()
        .statusCode(200)
        .body("itemPrice", is(15.0f))
        .body("spiceLevel", is(3)); // Should be preserved
  }

  @Test
  public void testUpdateSpiceLevelToZero() {
    Menu menu = new Menu();
    menu.itemName = "Spicy Tuna Roll";
    menu.itemPrice = java.math.BigDecimal.valueOf(12.0);
    menu.spiceLevel = 3;
    menu.status = Status.Ready;

    // Create
    Integer id = given()
        .contentType(ContentType.JSON)
        .body(menu)
        .when()
        .post("/menu")
        .then()
        .statusCode(200)
        .extract()
        .path("id");

    // Update spiceLevel to 0
    MenuUpdateDTO updateDTO = new MenuUpdateDTO();
    updateDTO.spiceLevel = 0;

    given()
        .contentType(ContentType.JSON)
        .body(updateDTO)
        .when()
        .put("/menu/" + id)
        .then()
        .statusCode(200)
        .body("spiceLevel", is(0));
  }
}
