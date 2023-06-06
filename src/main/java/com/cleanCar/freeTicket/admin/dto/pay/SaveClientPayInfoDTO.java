package com.cleanCar.freeTicket.admin.dto.pay;

import com.cleanCar.freeTicket.admin.domain.ClientPayInfo;

import java.time.LocalDateTime;

/**
 * 고객 주유정보 저장 DTO
 */
public record SaveClientPayInfoDTO(
        String carNumber,
        int payOfGas,
        Long gasStationId

) {
    public ClientPayInfo toEntity(int freePeriod) {
        return ClientPayInfo.builder()
                .carNumber(carNumber)
                .payOfGas(payOfGas)
                .expiredDt(LocalDateTime.now().plusDays(freePeriod))
                .build();
    }

}
