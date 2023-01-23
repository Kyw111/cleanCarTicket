package com.cleanCar.freeTicket.repository;

import com.cleanCar.freeTicket.domain.price.CleanCarPriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CleanCarPriceInfoRepository extends JpaRepository<CleanCarPriceInfo, Long> {
}
