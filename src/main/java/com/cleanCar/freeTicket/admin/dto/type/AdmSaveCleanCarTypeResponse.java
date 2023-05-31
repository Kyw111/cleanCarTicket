package com.cleanCar.freeTicket.admin.dto.type;

import lombok.Builder;

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
