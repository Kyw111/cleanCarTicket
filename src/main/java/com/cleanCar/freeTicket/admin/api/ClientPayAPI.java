package com.cleanCar.freeTicket.admin.api;

import com.cleanCar.freeTicket.admin.dto.pay.SaveClientPayInfoDTO;
import com.cleanCar.freeTicket.admin.dto.pay.UpdateClientPayInfoDTO;
import com.cleanCar.freeTicket.admin.service.ClientPayInfoService;
import com.cleanCar.freeTicket.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cleanCar.freeTicket.utils.Constant.SAVE_CLIENT_PAY_INFO_MSG;
import static com.cleanCar.freeTicket.utils.Constant.UPDATE_CLIENT_PAY_INFO_MSG;

/**
 * 고객 주유 정보 API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
public class ClientPayAPI {

    private final ClientPayInfoService clientPayInfoService;


    /**
     * 관리자 - 고객 주유 정보 저장
     */
    @PostMapping
    public ResponseEntity saveClientPayInfo(@RequestBody SaveClientPayInfoDTO saveClientPayInfoDTO) {
        clientPayInfoService.saveClientPayInfo(saveClientPayInfoDTO);
        return ResponseEntity.ok().body(SAVE_CLIENT_PAY_INFO_MSG);
    }

    /**
     * 고객 주유 정보 목록 조회
     */
    @GetMapping("/{gasStationId}")
    public ResponseEntity clientPayInfoList(@PathVariable("gasStationId") Long gasStationId,
                                            @RequestParam String carNumber,
                                            Pageable pageable) {
        return ResponseEntity.ok().body(clientPayInfoService.clientPayInfoList(gasStationId, carNumber, pageable));
    }

    /**
     * 관리자 - 고객 주유 정보 수정
     */
    @PutMapping
    public ResponseEntity updateClientPayInfo(@RequestBody UpdateClientPayInfoDTO updateClientPayInfoDTO) {
        clientPayInfoService.updateClientPayInfo(updateClientPayInfoDTO);
        return ResponseEntity.ok().body(UPDATE_CLIENT_PAY_INFO_MSG);
    }


}
