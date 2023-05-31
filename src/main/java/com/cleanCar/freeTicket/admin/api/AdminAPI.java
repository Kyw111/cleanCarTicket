package com.cleanCar.freeTicket.admin.api;

import com.cleanCar.freeTicket.admin.dto.station.AdmSaveGasStationDTO;
import com.cleanCar.freeTicket.admin.dto.station.AdmSaveGasStationResponse;
import com.cleanCar.freeTicket.admin.dto.station.AdmUpdateGasStationDTO;
import com.cleanCar.freeTicket.admin.dto.type.AdmSaveCleanCarTypeDTO;
import com.cleanCar.freeTicket.admin.dto.type.AdmSaveCleanCarTypeResponse;
import com.cleanCar.freeTicket.admin.dto.type.AdmUpdateCleanCarTypeDTO;
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

    /****** 이하 관리자 세차 종류 및 가격 정보 관련 API **********************************************************/

    /**
     * 관리자 - 세차 종류 및 가격 정보 저장(등록) API
     * @param admSaveCleanCarTypeDTO
     * @return
     */
    @PostMapping("/clean")
    public ResponseEntity saveCleanCarType(@RequestBody AdmSaveCleanCarTypeDTO admSaveCleanCarTypeDTO) {
        return ResponseEntity.ok().body(gasStationService.saveCleanCarType(admSaveCleanCarTypeDTO));
    }

    /**
     * 관리자 - 세차 종류 및 가격 정보 수정 API
     * @param admUpdateCleanCarTypeDTO
     * @return
     */
    @PutMapping("/clean")
    public ResponseEntity updateCleanCarType(@RequestBody AdmUpdateCleanCarTypeDTO admUpdateCleanCarTypeDTO) {
        gasStationService.updateCleanCarType(admUpdateCleanCarTypeDTO);
        return ResponseEntity.ok().body(null);
    }

    /**
     * 관리자 - 세차 종류 및 가격 정보 삭제 API
     * @param cleanCarTypeId
     * @return
     */
    @DeleteMapping("/clean/{cleanCarTypeId}")
    public ResponseEntity deleteCleanCarType(@PathVariable("cleanCarTypeId") Long cleanCarTypeId) {
        gasStationService.deleteCleanCarType(cleanCarTypeId);
        return ResponseEntity.ok().body(null);
    }

    /**
     * 관리자 - 세차 종류 및 가격 정보 목록 API
     * @param pageable
     * @return
     */
    @GetMapping("/clean")
    public ResponseEntity cleanCarTypeList(Pageable pageable) {
        return ResponseEntity.ok().body(gasStationService.cleanCarTypeList(pageable));
    }

}
