package com.cleanCar.freeTicket.repository;

import com.cleanCar.freeTicket.domain.clientCarInfo.ClientCarInfo;

import java.util.Optional;

public interface ClientCarInfoCustom {
    Optional<ClientCarInfo> getClientCarInfoByCarNumber(String carNumber);
}
