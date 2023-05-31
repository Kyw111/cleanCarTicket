package com.cleanCar.freeTicket.admin.dto.type;

import com.querydsl.core.annotations.QueryProjection;

/**
 * 관리자 - 세차 종류 및 가격정보 목록 조회 DTO
 */
public record CleanCarTypeListResponseDTO(
        Long cleanCarTypeId,
        String cleanType,
        int price,
        String defaultCondition,
        Long gasStationId
) {
    @QueryProjection
    public CleanCarTypeListResponseDTO(Long cleanCarTypeId, String cleanType, int price, String defaultCondition, Long gasStationId) {
        this.cleanCarTypeId = cleanCarTypeId;
        this.cleanType = cleanType;
        this.price = price;
        this.defaultCondition = defaultCondition;
        this.gasStationId = gasStationId;
    }
}
