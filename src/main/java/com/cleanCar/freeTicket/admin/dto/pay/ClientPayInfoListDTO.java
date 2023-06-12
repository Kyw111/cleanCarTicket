package com.cleanCar.freeTicket.admin.dto.pay;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

/**
 * 고객 주유 정보 목록 DTO
 */
public record ClientPayInfoListDTO(
        Long clientPayInfoId,
        String carNumber,
        int payOfGas,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdDt,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime expiredDt,
        Long gasStationId,
        String gasStationName

) {
    @QueryProjection
    public ClientPayInfoListDTO(Long clientPayInfoId, String carNumber, int payOfGas, LocalDateTime createdDt,
                                LocalDateTime expiredDt, Long gasStationId, String gasStationName) {
        this.clientPayInfoId = clientPayInfoId;
        this.carNumber = carNumber;
        this.payOfGas = payOfGas;
        this.createdDt = createdDt;
        this.expiredDt = expiredDt;
        this.gasStationId = gasStationId;
        this.gasStationName = gasStationName;
    }
}
