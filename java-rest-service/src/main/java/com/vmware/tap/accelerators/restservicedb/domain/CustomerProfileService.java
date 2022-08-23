package com.vmware.tap.accelerators.restservicedb.domain;

import com.vmware.tap.accelerators.restservicedb.data.CustomerProfileEntity;
import com.vmware.tap.accelerators.restservicedb.data.CustomerProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CustomerProfileService {

    private final CustomerProfileRepository repository;

    public CustomerProfileService(CustomerProfileRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerProfileResponse create(CustomerProfileCreateRequest dto) {
        var entity = new CustomerProfileEntity()
                .setId(UUID.randomUUID())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setEmail(dto.getEmail());

        var persistedEntity = repository.save(entity);
        return entityToDto(persistedEntity);
    }

    public Optional<CustomerProfileResponse> getById(String idRepresentation) {
        return safeConvertToUUID(idRepresentation)
                .flatMap(repository::findById)
                .map(this::entityToDto);
    }

    private Optional<UUID> safeConvertToUUID(String stringRepresentation) {
        try {
            return Optional.of(UUID.fromString(stringRepresentation));
        }
        catch (IllegalArgumentException ignorable) {
            return Optional.empty();
        }
    }

    private CustomerProfileResponse entityToDto(CustomerProfileEntity entity) {
        return new CustomerProfileResponse(
                entity.getId().toString(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail());
    }
}
