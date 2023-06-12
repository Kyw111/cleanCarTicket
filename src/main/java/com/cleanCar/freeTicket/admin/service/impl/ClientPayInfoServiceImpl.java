package com.cleanCar.freeTicket.admin.service.impl;

import com.cleanCar.freeTicket.admin.domain.ClientPayInfo;
import com.cleanCar.freeTicket.admin.domain.GasStation;
import com.cleanCar.freeTicket.admin.dto.pay.ClientPayInfoListDTO;
import com.cleanCar.freeTicket.admin.dto.pay.SaveClientPayInfoDTO;
import com.cleanCar.freeTicket.admin.dto.pay.UpdateClientPayInfoDTO;
import com.cleanCar.freeTicket.admin.repository.AdmGasStationRepository;
import com.cleanCar.freeTicket.admin.repository.ClientPayInfoRepository;
import com.cleanCar.freeTicket.admin.service.ClientPayInfoService;
import com.cleanCar.freeTicket.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cleanCar.freeTicket.utils.Constant.NO_CLIENT_PAY_INFO_MSG;
import static com.cleanCar.freeTicket.utils.Constant.NO_GAS_STATION_MSG;

@Service
@RequiredArgsConstructor
public class ClientPayInfoServiceImpl implements ClientPayInfoService {

    private final ClientPayInfoRepository clientPayInfoRepository;
    private final AdmGasStationRepository admGasStationRepository;

    private GasStation getGasStationEntity(Long gasStationId) {
        return admGasStationRepository.findById(gasStationId)
                .orElseThrow(() -> new IllegalArgumentException(NO_GAS_STATION_MSG));
    }

    /**
     * 관리자 - 고객 주유 정보 저장
     */
    @Transactional
    @Override
    public void saveClientPayInfo(SaveClientPayInfoDTO saveClientPayInfoDTO) {
        ClientPayInfo clientPayInfo = saveClientPayInfoDTO.toEntity(
                getGasStationEntity(saveClientPayInfoDTO.gasStationId()).getCleanCarFreePeriod()
        );
        clientPayInfo.setGasStation(getGasStationEntity(saveClientPayInfoDTO.gasStationId()));
        clientPayInfoRepository.save(clientPayInfo);
    }

    /**
     * 관리자 - 고객 주유 정보 목록
     */
    @Transactional(readOnly = true)
    @Override
    public Page<ClientPayInfoListDTO> clientPayInfoList(Long gasStationId, String carNumber, Pageable pageable) {
        return clientPayInfoRepository.clientPayInfoList(gasStationId, carNumber, pageable);
    }

    /**
     * 관리자 - 고객 주유 정보 수정
     */
    @Transactional
    @Override
    public void updateClientPayInfo(UpdateClientPayInfoDTO updateClientPayInfoDTO) {
        ClientPayInfo clientPayInfo = clientPayInfoRepository.findById(updateClientPayInfoDTO.clientPayInfoId())
                .orElseThrow(() -> new IllegalArgumentException(NO_CLIENT_PAY_INFO_MSG));
        clientPayInfo.updateClientPayInfo(updateClientPayInfoDTO);
    }
}
