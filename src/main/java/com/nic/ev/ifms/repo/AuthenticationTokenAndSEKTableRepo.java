package com.nic.ev.ifms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.ev.ifms.model.webservice.AuthenticationTokenAndSEKTable;

@Repository
public interface AuthenticationTokenAndSEKTableRepo  extends JpaRepository<AuthenticationTokenAndSEKTable,Long>{

	@Query(value="select * from evschema.auth_table order by opdate desc limit 1", nativeQuery=true)
	AuthenticationTokenAndSEKTable findLatestSEK();

}
