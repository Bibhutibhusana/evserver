package com.nic.ev.ifms.repo;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.ev.ifms.model.FileSendTrackModel;

public interface FilesSendTranckRepo  extends JpaRepository<FileSendTrackModel, Long>{

	FileSendTrackModel findByFileSendDate(Date formattedDate);

}
