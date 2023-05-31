package com.cleanCar.freeTicket.admin.repository;


import com.cleanCar.freeTicket.admin.dto.type.CleanCarTypeListResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdmCleanCarTypeRepositoryCustom {
    Page<CleanCarTypeListResponseDTO> cleanCarTypeList(Pageable pageable);

    void deleteCarType(Long cleanCarTypeId);

}
