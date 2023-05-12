package com.cleanCar.freeTicket.admin.dto;

import com.cleanCar.freeTicket.admin.domain.GasStation;

/**
 * 관리자 - 주유소 정보 저장(등록) DTO
 */
public record AdmSaveGasStationDTO(

        String gasStationName,
        String gasStationAddress

) {
    public GasStation toEntity() {
        return GasStation.builder()
                .gasStationName(gasStationName)
                .gasStationAddress(gasStationAddress)
                .build();
    }

}
