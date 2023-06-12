package com.cleanCar.freeTicket.admin.dto.pay;

/**
 * 고객 주유정보 수정 DTO
 */
public record UpdateClientPayInfoDTO(

        Long clientPayInfoId,
        String carNumber,
        int payOfGas,
        Long gasStationId
) {
}
