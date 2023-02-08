package com.varmartins.excercise1;

import com.varmartins.exercise1.persistence.entities.Person;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LiveTest {

    private static final String API_ROOT = "http://localhost:8082/api/people";

    @Test
    public void whenGetAllPeople_thenOK() {
        final Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetPeopleByName_thenOK() {
        final Person person = createRandomPerson();
        createPersonAsUri(person);

        final Response response = RestAssured.get(API_ROOT + "/name/" + person.getName());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(!response.as(List.class).isEmpty());
    }

    @Test
    public void whenFindIfExistsPeopleByName_thenOK() {
        final Person person = createRandomPerson();
        createPersonAsUri(person);

        final Response response = RestAssured.get(API_ROOT + "/nameExists/" + person.getName());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.as(Boolean.class));
    }

    @Test
    public void whenGetCreatedPersonById_thenOK() {
        final Person person = createRandomPerson();
        final String location = createPersonAsUri(person);

        final Response response = RestAssured.get(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(person.getName(), response.jsonPath()
            .get("name"));
    }

    @Test
    public void whenGetNotExistPersonById_thenNotFound() {
        final Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    // POST
    @Test
    public void whenCreateNewPerson_thenCreated() {
        final Person person = createRandomPerson();

        final Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(person)
            .post(API_ROOT);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }
    
    @Test
    public void whenCreateNewPeole_thenCreated() {
        final List<Person> people = createRandomPeople();

        final Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(people)
            .post(API_ROOT + "/list");
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void whenDeleteCreatedPerson_thenOk() {
        final Person person = createRandomPerson();
        final String location = createPersonAsUri(person);

        Response response = RestAssured.delete(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    // ===============================
    private Person createRandomPerson() {
        final Person person = new Person();
        person.setName(randomAlphabetic(10));
        return person;
    }
    
    private List<Person> createRandomPeople() {
        final List<Person> people = new ArrayList<>();
        
        for(Person person : people)
            person.setName(randomAlphabetic(10));
        
        return people;
    }

    private String createPersonAsUri(Person person) {
        final Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(person)
            .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath()
            .get("id");
    }
}