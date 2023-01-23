package com.cleanCar.freeTicket.api;

import com.cleanCar.freeTicket.dto.AdminCreateTicketDTO;
import com.cleanCar.freeTicket.service.CleanCarTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clean-car")
public class ticketAPI {

    private final CleanCarTicketService cleanCarTicketService;

    @PostMapping("/ticket")
    public ResponseEntity createCleanCarTicket(@RequestBody AdminCreateTicketDTO adminCreateTicketDTO) {
        cleanCarTicketService.createCleanCarTicket(adminCreateTicketDTO);
        return ResponseEntity.ok().body("저장 완료");
    }
    
}
