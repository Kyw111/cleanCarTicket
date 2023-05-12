package com.cleanCar.freeTicket.admin.api;

import com.cleanCar.freeTicket.admin.dto.AdmSaveGasStationDTO;
import com.cleanCar.freeTicket.admin.service.AdmGasStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clean-car")
public class AdminAPI {

    private final AdmGasStationService gasStationService;

    @PostMapping("/gas-station")
    public ResponseEntity saveGasStation(@RequestBody AdmSaveGasStationDTO admSaveGasStationDTO) {
        gasStationService.saveGasStation(admSaveGasStationDTO);
        return ResponseEntity.ok().body("주유소 등록(저장) 완료");
    }
    
}
