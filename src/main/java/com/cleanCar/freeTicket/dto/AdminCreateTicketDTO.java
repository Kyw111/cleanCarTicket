package com.cleanCar.freeTicket.dto;

import com.cleanCar.freeTicket.domain.clientCarInfo.ClientCarInfo;
import com.cleanCar.freeTicket.domain.gasStation.GasStationInfo;
import com.cleanCar.freeTicket.domain.price.CleanCarPriceInfo;
import com.cleanCar.freeTicket.domain.ticket.CleanCarTicket;

import java.time.LocalDate;

/**
 * 관리자 - 세차권 등록 DTO
 */
public record AdminCreateTicketDTO(

        String clientCarNumber,

        // 주유소 정보 - 추후 admin 로그인 시 로그인 정보로 주유소정보 연동될 수 있도록 구현하기
        String gasStationName,
        String gasStationAddress,

        long expirationDay,

        int ticketPrice,
        int normalPrice

) {

    public ClientCarInfo toClientCarInfoEntity() {
        return ClientCarInfo.builder()
                .carNumber(clientCarNumber)
                .build();
    }

    public CleanCarTicket toCleanCarTicketEntity(ClientCarInfo clientCarInfo) {
        return CleanCarTicket.builder()
                .clientCarInfo(clientCarInfo)
                .expiredDt(LocalDate.now().plusDays(expirationDay))
                .build();
    }

    public GasStationInfo toGasStationInfoEntity(CleanCarTicket cleanCarTicket) {
        return GasStationInfo.builder()
                .gasStationName(gasStationName)
                .gasStationAddress(gasStationAddress)
                .cleanCarTicket(cleanCarTicket)
                .build();
    }

    public CleanCarPriceInfo toCleanCarPriceInfoEntity(GasStationInfo gasStationInfo) {
        return CleanCarPriceInfo.builder()
                .ticketPrice(ticketPrice)
                .normalPrice(normalPrice)
                .gasStationInfo(gasStationInfo)
                .build();
    }

}
