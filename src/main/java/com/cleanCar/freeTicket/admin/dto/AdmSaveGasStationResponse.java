package com.cleanCar.freeTicket.admin.dto;

import lombok.Builder;

public record AdmSaveGasStationResponse(
        Long gasStationId,
        String gasStationName,
        String gasStationAddress
) {
    @Builder
    public AdmSaveGasStationResponse(Long gasStationId, String gasStationName, String gasStationAddress) {
        this.gasStationId = gasStationId;
        this.gasStationName = gasStationName;
        this.gasStationAddress = gasStationAddress;
    }
}
