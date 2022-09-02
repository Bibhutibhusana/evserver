package com.nic.ev.ifms.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.ev.ifms.model.IFMSIntegrationTrack;

@Repository
public interface IFMSIntegrationTrackRepo extends JpaRepository<IFMSIntegrationTrack,Long>{

	@Query(value="SELECT distinct file_name"
			+ "	FROM evschema.evt_ifms_integration_track where ack_status is null",nativeQuery=true)
	List<Map<String, String>> findByAckStatus(Object object);

}
