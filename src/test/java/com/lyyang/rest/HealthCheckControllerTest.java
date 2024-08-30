package com.lyyang.rest;

import com.lyyang.TestWebfluxCucumberTests;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class HealthCheckControllerTest extends TestWebfluxCucumberTests {

    private ResponseEntity<String> statusResponse;

    private ResponseEntity<String> doGet(String url) {
        return new RestTemplate().getForEntity(url, String.class);
    }

    @When("^the client calls /v1/status")
    public void checkV1Status() throws Throwable {
        statusResponse = doGet("http://localhost:8080/v1/status");
    }

    @When("^the client calls /v2/status")
    public void checkV2Status() throws Throwable {
        statusResponse = doGet("http://localhost:8080/v2/status");
    }

    @Then("^the client receives (\\d+) status code$")
    public void verifyStatusCode(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = statusResponse.getStatusCode();
        Assertions.assertEquals(statusCode, currentStatusCode.value());
    }
}