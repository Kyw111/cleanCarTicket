package com.cleanCar.freeTicket.admin.dto.station;

/**
 * 관리자 - 주유소 정보 저장(등록) DTO
 */
public record AdmSaveGasStationDTO(

        String gasStationName,
        String gasStationAddress,
        Integer cleanCarFreePeriod

) {
}
