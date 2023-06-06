package com.cleanCar.freeTicket.admin.api;

import com.cleanCar.freeTicket.admin.dto.pay.SaveClientPayInfoDTO;
import com.cleanCar.freeTicket.admin.service.ClientPayInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 고객 주유 정보 API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
public class ClientPayAPI {

    private final ClientPayInfoService clientPayInfoService;


    @PostMapping
    public ResponseEntity saveClientPayInfo(@RequestBody SaveClientPayInfoDTO saveClientPayInfoDTO) {
        clientPayInfoService.saveClientPayInfo(saveClientPayInfoDTO);
        return ResponseEntity.ok().body("저장 완료");
    }

    @GetMapping("/{gasStationId}")
    public ResponseEntity clientPayInfoList(@PathVariable("gasStationId") Long gasStationId, @RequestParam String carNumber, Pageable pageable) {
        return ResponseEntity.ok().body(clientPayInfoService.clientPayInfoList(gasStationId, carNumber, pageable));
    }

}
