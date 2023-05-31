package com.cleanCar.freeTicket.admin.dto.type;

import com.cleanCar.freeTicket.admin.domain.CleanCarType;

/**
 * 관리자 - 세차 종류 및 가격정보 저장 DTO
 */
public record AdmSaveCleanCarTypeDTO(
        String cleanType,
        int price,
        String defaultCondition,
        Long gasStationId
) {
    public CleanCarType toEntity() {
        return CleanCarType.builder()
                .cleanType(cleanType)
                .price(price)
                .defaultCondition(defaultCondition)
                .build();
    }

}
