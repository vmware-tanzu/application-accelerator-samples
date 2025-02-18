package com.example.api;

import com.example.domain.CustomerProfileChangeRequest;
import com.example.domain.CustomerProfileCreateRequest;
import com.example.domain.CustomerProfileResponse;
import com.example.domain.CustomerProfileService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerProfileController.class)
class CustomerProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerProfileService service;

    @Nested
    class Create {

        @Test
        void shouldDelegateToService() throws Exception {

            when(service.create(any()))
                    .thenReturn(new CustomerProfileResponse("profile-id", "Joe", "Doe", "joe.doe@test.org"));

            var requestBody = "{" +
                    "\"firstName\": \"Joe\"," +
                    "\"lastName\": \"Doe\"," +
                    "\"email\": \"joe.doe@test.org\"" +
                    "}";

            mockMvc.perform(post("/api/customer-profiles")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isCreated())
                    .andExpect(header().string("Location", "/api/customer-profiles/profile-id"))
                    .andExpect(content().json("{" +
                            "\"id\": \"profile-id\"," +
                            "\"firstName\": \"Joe\"," +
                            "\"lastName\": \"Doe\"," +
                            "\"email\": \"joe.doe@test.org\"" +
                            "}"));

            var profileCaptor = ArgumentCaptor.forClass(CustomerProfileCreateRequest.class);
            verify(service).create(profileCaptor.capture());

            var profile = profileCaptor.getValue();
            assertThat(profile).isNotNull();
            assertThat(profile.getFirstName()).isEqualTo("Joe");
            assertThat(profile.getLastName()).isEqualTo("Doe");
            assertThat(profile.getEmail()).isEqualTo("joe.doe@test.org");
        }

        @Test
        void shouldReturnBadRequestWhenEmailIsNotProvided() throws Exception {
            var requestBody = "{" +
                    "\"firstName\": \"Joe\"," +
                    "\"lastName\": \"Doe\"" +
                    "}";

            mockMvc.perform(post("/api/customer-profiles")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isBadRequest());

            verifyNoInteractions(service);
        }
    }

    @Nested
    class Update {

        @Test
        void shouldDelegateToService() throws Exception {

            when(service.change(any(), any()))
                    .thenReturn(Optional.of(new CustomerProfileResponse("profile-id", "Joe", "Doe", "joe.doe@test.org")));

            var requestBody = "{" +
                    "\"firstName\": \"Joe\"," +
                    "\"lastName\": \"Doe\"" +
                    "}";

            mockMvc.perform(patch("/api/customer-profiles/profile-id")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{" +
                            "\"id\": \"profile-id\"," +
                            "\"firstName\": \"Joe\"," +
                            "\"lastName\": \"Doe\"," +
                            "\"email\": \"joe.doe@test.org\"" +
                            "}"));

            var profileCaptor = ArgumentCaptor.forClass(CustomerProfileChangeRequest.class);
            verify(service).change(eq("profile-id"), profileCaptor.capture());

            var profile = profileCaptor.getValue();
            assertThat(profile).isNotNull();
            assertThat(profile.getFirstName()).isEqualTo("Joe");
            assertThat(profile.getLastName()).isEqualTo("Doe");
        }
    }

    @Nested
    class Delete {

        @Test
        void shouldDelegateToService() throws Exception {

            mockMvc.perform(delete("/api/customer-profiles/profile-id")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            verify(service).delete(eq("profile-id"));
        }
    }

    @Nested
    class Get {

        @Test
        void shouldDelegateToService() throws Exception {

            var id = "customer-profile-id";
            when(service.getById(any()))
                    .thenReturn(Optional.of(new CustomerProfileResponse(id, "Joe", "Doe", "joe.doe@test.org")));

            mockMvc.perform(get("/api/customer-profiles/" + id)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{" +
                            "\"id\": \"customer-profile-id\"," +
                            "\"firstName\": \"Joe\"," +
                            "\"lastName\": \"Doe\"," +
                            "\"email\": \"joe.doe@test.org\"" +
                            "}"));

            verify(service).getById(id);
        }

        @Test
        void shouldReadAllDelegateToService() throws Exception {

            when(service.getAll())
                    .thenReturn(Stream.of(new CustomerProfileResponse("customer-profile-id", "Joe", "Doe", "joe.doe@test.org")));

            mockMvc.perform(get("/api/customer-profiles/")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("[{" +
                            "\"id\": \"customer-profile-id\"," +
                            "\"firstName\": \"Joe\"," +
                            "\"lastName\": \"Doe\"," +
                            "\"email\": \"joe.doe@test.org\"" +
                            "}]"));

            verify(service).getAll();
        }

        @Test
        void shouldReturnNotFoundWhenNotExists() throws Exception {

            var id = "customer-profile-id";
            when(service.getById(any())).thenReturn(Optional.empty());

            mockMvc.perform(get("/api/customer-profiles/" + id)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(content().string(""));

            verify(service).getById(id);
        }
    }
}
