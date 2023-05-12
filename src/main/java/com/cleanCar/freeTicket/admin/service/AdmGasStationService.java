package com.cleanCar.freeTicket.admin.service;

import com.cleanCar.freeTicket.admin.dto.AdmSaveGasStationDTO;
import org.json.simple.parser.ParseException;

import java.net.URISyntaxException;

public interface AdmGasStationService {

    void saveGasStation(AdmSaveGasStationDTO saveGasStationDTO) throws URISyntaxException, ParseException;
}
