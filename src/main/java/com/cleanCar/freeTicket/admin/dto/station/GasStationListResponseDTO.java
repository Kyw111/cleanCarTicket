package com.cleanCar.freeTicket.admin.dto.station;

import com.querydsl.core.annotations.QueryProjection;

/**
 * 주유소 목록 조회용 DTO
 */
public record GasStationListResponseDTO(
        Long gasStationId,
        String gasStationName
) {
    @QueryProjection
    public GasStationListResponseDTO(Long gasStationId, String gasStationName) {
        this.gasStationId = gasStationId;
        this.gasStationName = gasStationName;
    }
}
