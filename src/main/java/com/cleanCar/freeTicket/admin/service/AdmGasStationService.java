package com.cleanCar.freeTicket.admin.service;

import com.cleanCar.freeTicket.admin.dto.AdmSaveGasStationDTO;
import com.cleanCar.freeTicket.admin.dto.AdmUpdateGasStationDTO;
import com.cleanCar.freeTicket.admin.dto.GasStationDetailResponseDTO;
import com.cleanCar.freeTicket.admin.dto.GasStationListResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdmGasStationService {

    void saveGasStation(AdmSaveGasStationDTO saveGasStationDTO);

    void updateGasStation(AdmUpdateGasStationDTO updateGasStationDTO);

    void deleteGasStation(List<Long> gasStationIds);

    GasStationDetailResponseDTO detailGasStation(Long gasStationId);

    Page<GasStationListResponseDTO> gasStationList(Pageable pageable);
}
