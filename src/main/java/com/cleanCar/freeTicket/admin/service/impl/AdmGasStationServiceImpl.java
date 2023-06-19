package com.cleanCar.freeTicket.admin.service.impl;

import com.cleanCar.freeTicket.admin.domain.CleanCarType;
import com.cleanCar.freeTicket.admin.domain.GasStation;
import com.cleanCar.freeTicket.admin.dto.station.*;
import com.cleanCar.freeTicket.admin.dto.type.AdmSaveCleanCarTypeDTO;
import com.cleanCar.freeTicket.admin.dto.type.AdmSaveCleanCarTypeResponse;
import com.cleanCar.freeTicket.admin.dto.type.AdmUpdateCleanCarTypeDTO;
import com.cleanCar.freeTicket.admin.dto.type.CleanCarTypeListResponseDTO;
import com.cleanCar.freeTicket.admin.repository.AdmCleanCarTypeRepository;
import com.cleanCar.freeTicket.admin.repository.AdmGasStationRepository;
import com.cleanCar.freeTicket.admin.service.AdmGasStationService;
import com.cleanCar.freeTicket.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.cleanCar.freeTicket.utils.Constant.NO_CLEAN_CAR_TYPE_MSG;
import static com.cleanCar.freeTicket.utils.Constant.NO_GAS_STATION_MSG;

/**
 * 관리자 - 주유소 서비스 impl
 */
@Service
@RequiredArgsConstructor
public class AdmGasStationServiceImpl implements AdmGasStationService {

    private final AdmGasStationRepository admGasStationRepository;
    private final AdmCleanCarTypeRepository admCleanCarTypeRepository;

    private final JSONParser jsonParser;

    private WebClient webClient;

    @PostConstruct
    public void initWebClient() {
        webClient = WebClient.create("http://localhost:8080");
    }


    /**
     * 관리자 - 주유소 정보 저장(등록)
     * @param saveGasStationDTO
     */
    @Transactional
    @Override
    public AdmSaveGasStationResponse saveGasStation(AdmSaveGasStationDTO saveGasStationDTO) {

        String kakaoMapJsonData = getXYByKaKaoMapAPI(saveGasStationDTO.gasStationAddress());

        try {
            GasStation gasStation = saveGasStation(
                    saveGasStationDTO,
                    parseJsonData(kakaoMapJsonData).get("x").toString(),
                    parseJsonData(kakaoMapJsonData).get("y").toString(),
                    parseJsonData(kakaoMapJsonData).get("address_name").toString()
            );

            return AdmSaveGasStationResponse.builder()
                    .gasStationId(gasStation.getGasStationId())
                    .gasStationName(gasStation.getGasStationName())
                    .gasStationAddress(gasStation.getGasStationAddress())
                    .cleanCarFreePeriod(gasStation.getCleanCarFreePeriod())
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }



    private GasStation saveGasStation(AdmSaveGasStationDTO saveGasStationDTO, String x, String y, String roadAddress) {
        GasStation gasStation = GasStation.builder()
                .gasStationName(saveGasStationDTO.gasStationName())
                .gasStationAddress(roadAddress)
                .cleanCarFreePeriod((saveGasStationDTO.cleanCarFreePeriod() == null) ?
                        0 : saveGasStationDTO.cleanCarFreePeriod())
                .longX(x)
                .latY(y)
                .build();
        admGasStationRepository.save(gasStation);
        return gasStation;
    }

    /**
     * 관리자 - 주유소 정보 수정
     * @param updateGasStationDTO
     */
    @Transactional
    @Override
    public void updateGasStation(AdmUpdateGasStationDTO updateGasStationDTO) {
        GasStation gasStation = admGasStationRepository.findById(updateGasStationDTO.gasStationId())
                .orElseThrow(() -> new IllegalArgumentException(NO_GAS_STATION_MSG));

        String kakaoMapJsonData = getXYByKaKaoMapAPI(updateGasStationDTO.gasStationAddress());
        
        try {
            gasStation.updateGasStation(
                    updateGasStationDTO.gasStationName(),
                    parseJsonData(kakaoMapJsonData).get("address_name").toString(),
                    parseJsonData(kakaoMapJsonData).get("x").toString(),
                    parseJsonData(kakaoMapJsonData).get("y").toString(),
                    updateGasStationDTO.cleanCarFreePeriod()
            );

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 관리자 - 주유소 정보 삭제
     * @param gasStationIds
     */
    @Transactional
    @Override
    public void deleteGasStation(List<Long> gasStationIds) {
        admGasStationRepository.deleteGasStations(gasStationIds);
    }

    /**
     * 관리자 - 주유소 정보 상세 조회
     * @param gasStationId
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public GasStationDetailResponseDTO detailGasStation(Long gasStationId) {
        return admGasStationRepository.detailGasStation(gasStationId);
    }

    /**
     * 관리자 - 주유소 정보 목록 조회
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Page<GasStationListResponseDTO> gasStationList(Pageable pageable) {
        return admGasStationRepository.gasStationList(pageable);
    }

    /**
     * 관리자 - 세차 종류 및 가격 정보 저장
     * @param admSaveCleanCarTypeDTO
     * @return
     */
    @Transactional
    @Override
    public AdmSaveCleanCarTypeResponse saveCleanCarType(AdmSaveCleanCarTypeDTO admSaveCleanCarTypeDTO) {

        GasStation gasStation = admGasStationRepository.findById(admSaveCleanCarTypeDTO.gasStationId())
                .orElseThrow(() -> new IllegalArgumentException(NO_GAS_STATION_MSG));

        CleanCarType cleanCarType = admSaveCleanCarTypeDTO.toEntity();
        cleanCarType.setGasStation(gasStation);
        CleanCarType savedCleanCarType = admCleanCarTypeRepository.save(cleanCarType);

        return AdmSaveCleanCarTypeResponse.builder()
                .cleanCarTypeId(savedCleanCarType.getCleanCarTypeId())
                .cleanType(savedCleanCarType.getCleanType())
                .price(savedCleanCarType.getPrice())
                .defaultCondition(savedCleanCarType.getDefaultCondition())
                .gasStationId(gasStation.getGasStationId())
                .build();
    }

    /**
     * 관리자 - 세차 종류 및 가격 정보 수정
     * @param admUpdateCleanCarTypeDTO
     */
    @Transactional
    @Override
    public void updateCleanCarType(AdmUpdateCleanCarTypeDTO admUpdateCleanCarTypeDTO) {
        CleanCarType cleanCarType = admCleanCarTypeRepository.findById(admUpdateCleanCarTypeDTO.cleanCarTypeId())
                .orElseThrow(() -> new IllegalArgumentException(NO_CLEAN_CAR_TYPE_MSG));
        cleanCarType.updateCleanCarType(admUpdateCleanCarTypeDTO);

    }

    /**
     * 관리자 - 세차 종류 및 가격 정보 삭제
     * @param cleanCarTypeId
     */
    @Transactional
    @Override
    public void deleteCleanCarType(Long cleanCarTypeId) {
        admCleanCarTypeRepository.deleteCleanCarType(cleanCarTypeId);
    }

    /**
     * 관리자 - 세차 종류 및 가격 정보 목록
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Page<CleanCarTypeListResponseDTO> cleanCarTypeList(Pageable pageable, Long gasStationId) {
        return admCleanCarTypeRepository.cleanCarTypeList(pageable, gasStationId);
    }

    @Transactional(readOnly = true)
    @Override
    public AdmSaveCleanCarTypeResponse detailCleanCarType(Long cleanCarTypeId) {
        return admCleanCarTypeRepository.detailCleanCarType(cleanCarTypeId);
    }


    /**
     * 카카오 지도 api - 좌표값(x,y), 주소 받아오기 ( 주유소 저장, 수정에서 사용 )
     */
    private String getXYByKaKaoMapAPI(String inputAddress) {
        String result = webClient.get()
                .uri("https://dapi.kakao.com/v2/local/search/address?query=" + inputAddress)
                .header("Authorization", "KakaoAK " + Constant.REST_API_KEY)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return result;
    }

    /**
     * JsonData 파싱 관련 메소드 - 주유소 저장, 수정에서 사용
     */
    private JSONObject parseJsonData(String kakaoMapJsonData) throws ParseException {
        JSONObject parse = (JSONObject) jsonParser.parse(kakaoMapJsonData);
        JSONArray documents = (JSONArray) parse.get("documents");
        JSONObject jsonObj = (JSONObject) documents.get(0);
        JSONObject address = (JSONObject) jsonObj.get("address");
        return address;
    }

}
