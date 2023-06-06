package com.cleanCar.freeTicket.admin.service;

import com.cleanCar.freeTicket.admin.dto.pay.ClientPayInfoListDTO;
import com.cleanCar.freeTicket.admin.dto.pay.SaveClientPayInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientPayInfoService {

    void saveClientPayInfo(SaveClientPayInfoDTO saveClientPayInfoDTO);

    Page<ClientPayInfoListDTO> clientPayInfoList(Long gasStationId, String carNumber, Pageable pageable);
}
