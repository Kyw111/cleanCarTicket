package com.cleanCar.freeTicket.admin.service;

import com.cleanCar.freeTicket.admin.dto.station.*;
import com.cleanCar.freeTicket.admin.dto.type.AdmSaveCleanCarTypeDTO;
import com.cleanCar.freeTicket.admin.dto.type.AdmSaveCleanCarTypeResponse;
import com.cleanCar.freeTicket.admin.dto.type.AdmUpdateCleanCarTypeDTO;
import com.cleanCar.freeTicket.admin.dto.type.CleanCarTypeListResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdmGasStationService {

    AdmSaveGasStationResponse saveGasStation(AdmSaveGasStationDTO saveGasStationDTO);

    void updateGasStation(AdmUpdateGasStationDTO updateGasStationDTO);

    void deleteGasStation(List<Long> gasStationIds);

    GasStationDetailResponseDTO detailGasStation(Long gasStationId);

    Page<GasStationListResponseDTO> gasStationList(Pageable pageable);


    /**
     * 이하 세차 종류 도메인 관련 (child)
     */
    AdmSaveCleanCarTypeResponse saveCleanCarType(AdmSaveCleanCarTypeDTO admSaveCleanCarTypeDTO);

    void updateCleanCarType(AdmUpdateCleanCarTypeDTO admUpdateCleanCarTypeDTO);

    void deleteCleanCarType(Long cleanCarTypeId);

    Page<CleanCarTypeListResponseDTO> cleanCarTypeList(Pageable pageable, Long gasStationId);

    AdmSaveCleanCarTypeResponse detailCleanCarType(Long cleanCarTypeId);


}
