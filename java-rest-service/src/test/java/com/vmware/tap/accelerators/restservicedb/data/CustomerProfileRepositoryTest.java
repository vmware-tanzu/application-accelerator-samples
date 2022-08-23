package com.vmware.tap.accelerators.restservicedb.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CustomerProfileRepositoryTest {

    @Autowired
    private CustomerProfileRepository subject;

    @Test
	void shouldPersistCustomerProfile() {
		var id = UUID.randomUUID();
		var entity = new CustomerProfileEntity()
				.setFirstName("Joe")
				.setLastName("Doe")
				.setEmail("joe.doe@test.com")
				.setId(id);

		subject.save(entity);

		var actual = subject.findById(id);
		assertThat(actual).isPresent();
		var actualEntity = actual.get();
		assertThat(actualEntity).isNotSameAs(entity);
		assertThat(actualEntity.getFirstName()).isEqualTo(entity.getFirstName());
		assertThat(actualEntity.getLastName()).isEqualTo(entity.getLastName());
		assertThat(actualEntity.getEmail()).isEqualTo(entity.getEmail());
	}
}
