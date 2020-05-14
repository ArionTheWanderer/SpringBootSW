package ru.itis.springsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springsem.model.BillingDetails;
import ru.itis.springsem.model.Product;

public interface BillingRepository extends JpaRepository<BillingDetails, Long> {
}
