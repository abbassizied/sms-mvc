package io.github.abbassizied.sms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;

import io.github.abbassizied.sms.entities.CompanySetting;
 
/**
 * This repository manages the single record of CompanySetting.
 * Use findFirst() to retrieve the single existing record.
 */
public interface CompanySettingRepository extends JpaRepository<CompanySetting, Long> {
	
    // Option 1: Use a custom query to get the first record
    @Query("SELECT cs FROM CompanySetting cs ORDER BY cs.id ASC")
    Optional<CompanySetting> findFirst();

    // Option 2: Use query derivation
    Optional<CompanySetting> findTop1By();
}
