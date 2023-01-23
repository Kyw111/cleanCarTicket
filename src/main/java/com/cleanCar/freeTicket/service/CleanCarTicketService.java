package com.cleanCar.freeTicket.service;

import com.cleanCar.freeTicket.domain.clientCarInfo.ClientCarInfo;
import com.cleanCar.freeTicket.domain.gasStation.GasStationInfo;
import com.cleanCar.freeTicket.domain.price.CleanCarPriceInfo;
import com.cleanCar.freeTicket.domain.ticket.CleanCarTicket;
import com.cleanCar.freeTicket.dto.AdminCreateTicketDTO;
import com.cleanCar.freeTicket.repository.CleanCarPriceInfoRepository;
import com.cleanCar.freeTicket.repository.CleanCarTicketRepository;
import com.cleanCar.freeTicket.repository.ClientCarInfoRepository;
import com.cleanCar.freeTicket.repository.GasStationInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CleanCarTicketService {

    private final CleanCarTicketRepository cleanCarTicketRepository;
    private final ClientCarInfoRepository clientCarInfoRepository;
    private final GasStationInfoRepository gasStationInfoRepository;
    private final CleanCarPriceInfoRepository cleanCarPriceInfoRepository;

    /**
     * 세차권 등록(저장)
     * @param adminCreateTicketDTO
     */
    public void createCleanCarTicket(AdminCreateTicketDTO adminCreateTicketDTO) {

        Optional<ClientCarInfo> carInfo = clientCarInfoRepository.getClientCarInfoByCarNumber(adminCreateTicketDTO.clientCarNumber());

        if (carInfo.isPresent()) { // 이미 등록된 차량 번호일 경우 - 티켓만 생성 되도록
            Long clientCarInfoId = carInfo.get().getClientCarInfoId();
            // 구현중...........
        } else {
            ClientCarInfo savedClientCarInfo = clientCarInfoRepository.save(adminCreateTicketDTO.toClientCarInfoEntity()); // 고객차량정보
            CleanCarTicket savedCleanCarTicket = cleanCarTicketRepository.save(adminCreateTicketDTO.toCleanCarTicketEntity(savedClientCarInfo)); // 세차권
            GasStationInfo savedGasStationInfo = gasStationInfoRepository.save(adminCreateTicketDTO.toGasStationInfoEntity(savedCleanCarTicket)); // 주유소 정보
            cleanCarPriceInfoRepository.save(adminCreateTicketDTO.toCleanCarPriceInfoEntity(savedGasStationInfo));          // 세차 가격정보
        }


    }
}
