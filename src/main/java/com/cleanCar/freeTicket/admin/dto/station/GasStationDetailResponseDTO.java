package com.cleanCar.freeTicket.admin.dto.station;

import com.querydsl.core.annotations.QueryProjection;

/**
 * 주유소 정보 상세 조회 DTO
 */
public record GasStationDetailResponseDTO(
        Long gasStationId,
        String gasStationName,
        String gasStationAddress,
        String longX,
        String latY
) {
    @QueryProjection
    public GasStationDetailResponseDTO(Long gasStationId, String gasStationName, String gasStationAddress, String longX, String latY) {
        this.gasStationId = gasStationId;
        this.gasStationName = gasStationName;
        this.gasStationAddress = gasStationAddress;
        this.longX = longX;
        this.latY = latY;
    }
}
