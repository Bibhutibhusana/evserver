package com.nic.ev.repo;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.ev.model.RevertStatus;

public interface RevertStatusRepo extends JpaRepository<RevertStatus, Long>{

	RevertStatus save(@Valid Map<String, String> rs);

}
