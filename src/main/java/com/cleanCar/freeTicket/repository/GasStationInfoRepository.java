package com.cleanCar.freeTicket.repository;

import com.cleanCar.freeTicket.domain.gasStation.GasStationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GasStationInfoRepository extends JpaRepository<GasStationInfo, Long> {
}
