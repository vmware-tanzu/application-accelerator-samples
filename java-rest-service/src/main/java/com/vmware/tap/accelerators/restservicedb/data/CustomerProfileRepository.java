package com.vmware.tap.accelerators.restservicedb.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfileEntity, String> {

    @Query("select cp from CustomerProfile cp")
    Stream<CustomerProfileEntity> streamAll();
}
