package com.cleanCar.freeTicket.admin.dto.type;

import lombok.Builder;

/**
 * 관리자 - 세차 종류 및 가격정보 저장 후 응답 DTO
 */
public record AdmSaveCleanCarTypeResponse(

        Long cleanCarTypeId,
        String cleanType,
        int price,
        String defaultCondition,
        Long gasStationId

) {
    @Builder
    public AdmSaveCleanCarTypeResponse(Long cleanCarTypeId, String cleanType, int price, String defaultCondition, Long gasStationId) {
        this.cleanCarTypeId = cleanCarTypeId;
        this.cleanType = cleanType;
        this.price = price;
        this.defaultCondition = defaultCondition;
        this.gasStationId = gasStationId;
    }
}
