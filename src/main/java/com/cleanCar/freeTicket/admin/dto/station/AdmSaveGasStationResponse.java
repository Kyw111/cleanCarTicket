package com.cleanCar.freeTicket.admin.dto.station;

import lombok.Builder;

/**
 * 관리자 - 주유소 정보 저장 후 응답 DTO
 * @param gasStationId
 * @param gasStationName
 * @param gasStationAddress
 */
public record AdmSaveGasStationResponse(
        Long gasStationId,
        String gasStationName,
        String gasStationAddress,
        Integer cleanCarFreePeriod
) {
    @Builder
    public AdmSaveGasStationResponse(Long gasStationId, String gasStationName, String gasStationAddress, Integer cleanCarFreePeriod) {
        this.gasStationId = gasStationId;
        this.gasStationName = gasStationName;
        this.gasStationAddress = gasStationAddress;
        this.cleanCarFreePeriod = cleanCarFreePeriod;
    }
}
