package io.github.abbassizied.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.abbassizied.sms.entities.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
