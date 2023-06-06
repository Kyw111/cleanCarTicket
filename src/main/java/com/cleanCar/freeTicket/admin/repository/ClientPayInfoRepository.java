package com.cleanCar.freeTicket.admin.repository;

import com.cleanCar.freeTicket.admin.domain.ClientPayInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientPayInfoRepository extends JpaRepository<ClientPayInfo, Long>, ClientPayInfoRepositoryCustom {
}
