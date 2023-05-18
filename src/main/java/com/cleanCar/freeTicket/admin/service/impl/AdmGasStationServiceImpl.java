package com.cleanCar.freeTicket.admin.service.impl;

import com.cleanCar.freeTicket.admin.domain.GasStation;
import com.cleanCar.freeTicket.admin.dto.AdmSaveGasStationDTO;
import com.cleanCar.freeTicket.admin.dto.AdmUpdateGasStationDTO;
import com.cleanCar.freeTicket.admin.dto.GasStationDetailResponseDTO;
import com.cleanCar.freeTicket.admin.dto.GasStationListResponseDTO;
import com.cleanCar.freeTicket.admin.repository.AdmGasStationRepository;
import com.cleanCar.freeTicket.admin.service.AdmGasStationService;
import com.cleanCar.freeTicket.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.cleanCar.freeTicket.utils.Constant.NO_GAS_STATION_MSG;

/**
 * 관리자 - 주유소 서비스 impl
 */
@Service
@RequiredArgsConstructor
public class AdmGasStationServiceImpl implements AdmGasStationService {

    private final AdmGasStationRepository admGasStationRepository;

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
    public String saveGasStation(AdmSaveGasStationDTO saveGasStationDTO) {

        String kakaoMapJsonData = getXYByKaKaoMapAPI(saveGasStationDTO.gasStationAddress());

        try {
            JSONObject parse = (JSONObject) jsonParser.parse(kakaoMapJsonData);
            JSONArray documents = (JSONArray) parse.get("documents");
            JSONObject jsonObj = (JSONObject) documents.get(0);
            JSONObject address = (JSONObject) jsonObj.get("address");
            String x = address.get("x").toString();
            String y = address.get("y").toString();
            String roadAddress = address.get("address_name").toString();

            System.out.println("!!! " + jsonObj);
            System.out.println("!!! " + address);
            System.out.println("!!! roadAddress " + roadAddress);
            System.out.println("!!! saveGasStationDTO.address " + saveGasStationDTO.gasStationAddress());

            //todo : JSON 데이터 파싱 후 도로명, 지번 주소 모두 저장시킬지 결정해야함

            GasStation gasStation = GasStation.builder()
                    .gasStationName(saveGasStationDTO.gasStationName())
                    .gasStationAddress(roadAddress)
                    .longX(x)
                    .latY(y)
                    .build();
            admGasStationRepository.save(gasStation);

            return roadAddress;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
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

        try {
            String xyByKaKaoMapAPI = getXYByKaKaoMapAPI(updateGasStationDTO.gasStationAddress());
            JSONObject parse = (JSONObject) jsonParser.parse(xyByKaKaoMapAPI);
            JSONArray documents = (JSONArray) parse.get("documents");
            JSONObject jsonObj = (JSONObject) documents.get(0);
            JSONObject address = (JSONObject) jsonObj.get("address");
            String x = address.get("x").toString();
            String y = address.get("y").toString();
            String roadAddress = address.get("address_name").toString();

            gasStation.updateGasStation(updateGasStationDTO.gasStationName(),roadAddress,x,y);

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

    @Transactional(readOnly = true)
    @Override
    public GasStationDetailResponseDTO detailGasStation(Long gasStationId) {
        return admGasStationRepository.detailGasStation(gasStationId);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<GasStationListResponseDTO> gasStationList(Pageable pageable) {
        return admGasStationRepository.gasStationList(pageable);
    }


    /**
     * 카카오 지도 api - 좌표값(x,y), 주소 받아오기
     * @param inputAddress
     * @return
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

}
