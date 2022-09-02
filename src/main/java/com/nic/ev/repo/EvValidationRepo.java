package com.nic.ev.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.ev.model.EvmValidation;

public interface EvValidationRepo extends JpaRepository<EvmValidation, Long>{

	Optional<EvmValidation> findByModelName(String model);

}
