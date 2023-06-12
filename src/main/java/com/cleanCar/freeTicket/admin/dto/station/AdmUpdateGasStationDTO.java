package com.cleanCar.freeTicket.admin.dto.station;

/**
 * 관리자 - 주유소 정보 수정 DTO
 */
public record AdmUpdateGasStationDTO(
        Long gasStationId,
        String gasStationName,
        String gasStationAddress,
        Integer cleanCarFreePeriod

) {
}
