package com.cleanCar.freeTicket.admin.dto.type;

/**
 * 관리자 - 세차 종류 및 가격정보 수정 DTO
 */
public record AdmUpdateCleanCarTypeDTO(
        Long cleanCarTypeId,
        String cleanType,
        int price,
        String defaultCondition
) {

}
