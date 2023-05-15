package com.cleanCar.freeTicket.admin.dto;

import com.querydsl.core.annotations.QueryProjection;

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
