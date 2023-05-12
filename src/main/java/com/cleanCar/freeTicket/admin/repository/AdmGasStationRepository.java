package com.cleanCar.freeTicket.admin.repository;

import com.cleanCar.freeTicket.admin.domain.GasStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmGasStationRepository extends JpaRepository<GasStation, Long> {
}
