package com.cleanCar.freeTicket.admin.repository;

import com.cleanCar.freeTicket.admin.dto.pay.ClientPayInfoListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientPayInfoRepositoryCustom {

    Page<ClientPayInfoListDTO> clientPayInfoList(Long gasStationId, String carNumber, Pageable pageable);
}
