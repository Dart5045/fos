package com.mylearning.domain.ports.output.repository;

import com.mylearning.domain.entity.Customer;;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}
