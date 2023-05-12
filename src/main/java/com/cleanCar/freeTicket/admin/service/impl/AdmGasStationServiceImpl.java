package com.cleanCar.freeTicket.admin.service.impl;

import com.cleanCar.freeTicket.admin.domain.GasStation;
import com.cleanCar.freeTicket.admin.dto.AdmSaveGasStationDTO;
import com.cleanCar.freeTicket.admin.repository.AdmGasStationRepository;
import com.cleanCar.freeTicket.admin.service.AdmGasStationService;
import com.cleanCar.freeTicket.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

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


    @Transactional
    @Override
    public void saveGasStation(AdmSaveGasStationDTO saveGasStationDTO) {

        String result = webClient.get()
                .uri("https://dapi.kakao.com/v2/local/search/address?query=" + saveGasStationDTO.gasStationAddress())
                .header("Authorization", "KakaoAK "+ Constant.REST_API_KEY)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            JSONObject parse = (JSONObject) jsonParser.parse(result);
            JSONArray documents = (JSONArray) parse.get("documents");
            JSONObject jsonObj = (JSONObject) documents.get(0);
            JSONObject address = (JSONObject) jsonObj.get("address");
            String x = address.get("x").toString();
            String y = address.get("y").toString();
            String roadAddress = address.get("address_name").toString();

            System.out.println("!!! "+jsonObj);
            System.out.println("!!! "+address);
            System.out.println("!!! roadAddress "+roadAddress);
            System.out.println("!!! saveGasStationDTO.address "+saveGasStationDTO.gasStationAddress());

            //todo : JSON 데이터 파싱 후 도로명, 지번 주소 모두 저장시킬지 결정해야함

            GasStation gasStation = GasStation.builder()
                    .gasStationName(saveGasStationDTO.gasStationName())
                    .gasStationAddress(roadAddress)
                    .longX(x)
                    .latY(y)
                    .build();
            admGasStationRepository.save(gasStation);

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
