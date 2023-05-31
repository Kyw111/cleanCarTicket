package com.cleanCar.freeTicket.admin.api;

import com.cleanCar.freeTicket.admin.dto.AdmSaveGasStationDTO;
import com.cleanCar.freeTicket.admin.dto.AdmSaveGasStationResponse;
import com.cleanCar.freeTicket.admin.dto.AdmUpdateGasStationDTO;
import com.cleanCar.freeTicket.admin.service.AdmGasStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 관리자
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gas-station")
public class AdminAPI {

    private final AdmGasStationService gasStationService;

    /**
     * 관리자 - 주유소 정보 저장
     * @param admSaveGasStationDTO
     * @return
     */
    @PostMapping
    public ResponseEntity saveGasStation(@RequestBody AdmSaveGasStationDTO admSaveGasStationDTO) {
        AdmSaveGasStationResponse response = gasStationService.saveGasStation(admSaveGasStationDTO);
        return ResponseEntity.ok().body(response);
    }

    /**
     * 관리자 - 주유소 정보 수정
     * @param admUpdateGasStationDTO
     * @return
     */
    @PutMapping
    public ResponseEntity updateGasStation(@RequestBody AdmUpdateGasStationDTO admUpdateGasStationDTO) {
        gasStationService.updateGasStation(admUpdateGasStationDTO);
        return ResponseEntity.ok().body("주유소 정보 수정 완료");
    }

    /**
     * 관리자 - 주유소 정보 삭제
     * @param gasStationIds
     * @return
     */
    @DeleteMapping("/{gasStationIds}")
    public ResponseEntity deleteGasStation(@PathVariable("gasStationIds") List<Long> gasStationIds) {
        gasStationService.deleteGasStation(gasStationIds);
        return ResponseEntity.ok().body("주유소 정보 삭제 완료");
    }

    /**
     * 관리자 - 주유소 정보 상세 조회
     * @param gasStationId
     * @return
     */
    @GetMapping("/{gasStationId}")
    public ResponseEntity detailGasStation(@PathVariable("gasStationId") Long gasStationId) {
        return ResponseEntity.ok().body(gasStationService.detailGasStation(gasStationId));
    }

    /**
     * 관리자 - 주유소 정보 목록 조회
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity gasStationList(Pageable pageable) {
        return ResponseEntity.ok().body(gasStationService.gasStationList(pageable));
    }
}
