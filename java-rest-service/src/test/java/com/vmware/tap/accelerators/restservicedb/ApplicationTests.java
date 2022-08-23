package com.vmware.tap.accelerators.restservicedb;

import com.vmware.tap.accelerators.restservicedb.data.CustomerProfileEntity;
import com.vmware.tap.accelerators.restservicedb.data.CustomerProfileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerProfileRepository repository;

    @BeforeEach
    @AfterEach
    void cleanupDatabase() {
        repository.deleteAllInBatch();
    }

    @Test
    void shouldPersistCustomerProfileOnPostRequest() {

        var body = "{" +
                "\"firstName\": \"Joe\"," +
                "\"lastName\": \"Doe\"," +
                "\"email\": \"joe.doe@test.org\"" +
                "}";

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var httpEntity = new HttpEntity<>(body, headers);
        var responseEntity = restTemplate.postForEntity("/api/customer-profiles", httpEntity, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.hasBody()).isTrue();
        var location = responseEntity.getHeaders().getLocation();
        assertThat(location).isNotNull();

        var profileId = location.getPath().substring(location.getPath().lastIndexOf("/") + 1);
        var profile = repository.findById(UUID.fromString(profileId));
        assertThat(profile).isPresent();
    }

    @Test
    void shouldReturnCustomerProfileOnGetRequest() {

        var entity = new CustomerProfileEntity()
                .setId(UUID.randomUUID())
                .setFirstName("Joe")
                .setLastName("Doe")
                .setEmail("joe.doe@test.org");
        repository.save(entity);

        var responseEntity = restTemplate.getForEntity("/api/customer-profiles/" + entity.getId(), String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.hasBody()).isTrue();
        assertThat(responseEntity.getBody()).contains(entity.getId().toString());
    }

    @Test
    void shouldExposeOpenAPIEndpoint() {

        var responseEntity = restTemplate.getForEntity("/api-docs", String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
