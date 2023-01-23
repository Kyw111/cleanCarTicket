package com.cleanCar.freeTicket.repository;

import com.cleanCar.freeTicket.domain.ticket.CleanCarTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CleanCarTicketRepository extends JpaRepository<CleanCarTicket, Long> {
}
