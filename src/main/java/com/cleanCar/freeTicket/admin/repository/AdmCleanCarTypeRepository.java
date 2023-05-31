package com.cleanCar.freeTicket.admin.repository;

import com.cleanCar.freeTicket.admin.domain.CleanCarType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmCleanCarTypeRepository extends JpaRepository<CleanCarType, Long>, AdmCleanCarTypeRepositoryCustom {
}
