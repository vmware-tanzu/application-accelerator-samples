package com.vmware.tap.accelerators.restservicedb.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.vmware.tap.accelerators.restservicedb.domain.CustomerProfileChangeRequest;
import com.vmware.tap.accelerators.restservicedb.domain.CustomerProfileCreateRequest;
import com.vmware.tap.accelerators.restservicedb.domain.CustomerProfileResponse;
import com.vmware.tap.accelerators.restservicedb.domain.CustomerProfileService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@OpenAPIDefinition(
        info = @Info(
                title = "Customer Profile Management API",
                version = "1.0"),
        tags = @Tag(
                name = "Customer Profile REST API"))
@CrossOrigin
@RestController
@RequestMapping("/api/customer-profiles")
public class CustomerProfileController {

    private final CustomerProfileService service;

    public CustomerProfileController(CustomerProfileService service) {
        this.service = service;
    }

    @Operation(summary = "Saves provided customer profile.", method = "POST", tags = "Customer Profile CRUD")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Customer profile successfully saved.",
                    headers = @Header(
                            name = "Location",
                            description = "Contains path which can be used to retrieve saved profile. Last element is it's ID.",
                            required = true,
                            schema = @Schema(type = "string"))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Passed customer profile is invalid."
            )
    })
    @PostMapping({ "", "/" })
    public ResponseEntity<CustomerProfileResponse> create(@Valid @RequestBody CustomerProfileCreateRequest body) {
        var customerProfileResponse = service.create(body);
        return ResponseEntity
                .created(URI.create("/api/customer-profiles/" + customerProfileResponse.getId()))
                .body(customerProfileResponse);
    }

    @Operation(summary = "Update customer profile.", method = "PATCH", tags = "Customer Profile CRUD")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer profile successfully saved."
            )
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerProfileResponse> update(@PathVariable("id") String id, @Valid @RequestBody CustomerProfileChangeRequest body) {
        var customerProfileResponse = service.change(id, body);
        return customerProfileResponse.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(customerProfileResponse.get());
    }

    @Operation(summary = "Delete customer profile.", method = "DELETE", tags = "Customer Profile CRUD")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer profile successfully deleted."
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get customer profile.", method = "GET", tags = "Customer Profile CRUD")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer profile retrieved successfully."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Customer profile not found."
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerProfileResponse> get(@PathVariable("id") String id) {
        var customerProfileResponse = service.getById(id);
        return customerProfileResponse.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(customerProfileResponse.get());
    }

    @Operation(summary = "Get all customer profiles.", method = "GET", tags = "Customer Profile CRUD")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer profiles retrieved successfully."
            )
    })
    @Transactional(readOnly = true)
    @GetMapping({ "", "/" })
    public ResponseEntity<List<CustomerProfileResponse>> getAll() {
        List<CustomerProfileResponse> all = service.getAll().collect(Collectors.toList());
        return ResponseEntity.ok(all);
    }
}
