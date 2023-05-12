package com.cleanCar.freeTicket.admin.api;

import com.cleanCar.freeTicket.admin.dto.AdmSaveGasStationDTO;
import com.cleanCar.freeTicket.admin.service.AdmGasStationService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gas-station")
public class AdminAPI {

    private final AdmGasStationService gasStationService;

    @PostMapping()
    public ResponseEntity saveGasStation(@RequestBody AdmSaveGasStationDTO admSaveGasStationDTO) throws URISyntaxException, ParseException {
        gasStationService.saveGasStation(admSaveGasStationDTO);
        return ResponseEntity.ok().body("주유소 등록(저장) 완료");
    }

}
