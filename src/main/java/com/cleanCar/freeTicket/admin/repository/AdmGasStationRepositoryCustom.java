package com.cleanCar.freeTicket.admin.repository;

import com.cleanCar.freeTicket.admin.dto.station.GasStationDetailResponseDTO;
import com.cleanCar.freeTicket.admin.dto.station.GasStationListResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdmGasStationRepositoryCustom {

    void deleteGasStations(List<Long> gasStationIds);

    GasStationDetailResponseDTO detailGasStation(Long gasStationId);

    Page<GasStationListResponseDTO> gasStationList(Pageable pageable);
}
