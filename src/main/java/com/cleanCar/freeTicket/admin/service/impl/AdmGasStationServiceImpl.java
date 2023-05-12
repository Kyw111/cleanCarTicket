package com.cleanCar.freeTicket.admin.service.impl;

import com.cleanCar.freeTicket.admin.dto.AdmSaveGasStationDTO;
import com.cleanCar.freeTicket.admin.repository.AdmGasStationRepository;
import com.cleanCar.freeTicket.admin.service.AdmGasStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 관리자 - 주유소 서비스 impl
 */
@Service
@RequiredArgsConstructor
public class AdmGasStationServiceImpl implements AdmGasStationService {

    private final AdmGasStationRepository admGasStationRepository;

    @Transactional
    @Override
    public void saveGasStation(AdmSaveGasStationDTO saveGasStationDTO) {
        admGasStationRepository.save(saveGasStationDTO.toEntity());
    }
}
