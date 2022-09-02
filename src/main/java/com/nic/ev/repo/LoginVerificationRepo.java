package com.nic.ev.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.ev.model.UserLogin;

public interface LoginVerificationRepo extends JpaRepository<UserLogin, Long>{

	List<UserLogin> findByUsernameAndPassword(String string, String string2);

}
