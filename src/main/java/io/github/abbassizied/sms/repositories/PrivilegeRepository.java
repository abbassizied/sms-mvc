package io.github.abbassizied.sms.repositories;

import io.github.abbassizied.sms.entities.Privilege;
import io.github.abbassizied.sms.enums.EPrivilege;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
	Optional<Privilege> findByName(EPrivilege name);
}
