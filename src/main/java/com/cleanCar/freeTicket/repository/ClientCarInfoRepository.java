package com.cleanCar.freeTicket.repository;

import com.cleanCar.freeTicket.domain.clientCarInfo.ClientCarInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientCarInfoRepository extends JpaRepository<ClientCarInfo, Long>, ClientCarInfoCustom {
}
