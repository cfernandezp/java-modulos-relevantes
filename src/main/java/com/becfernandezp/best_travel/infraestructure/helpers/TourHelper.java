package com.becfernandezp.best_travel.infraestructure.helpers;

import com.becfernandezp.best_travel.domain.entities.CustomerEntity;
import com.becfernandezp.best_travel.domain.entities.FlyEntity;
import com.becfernandezp.best_travel.domain.entities.TicketEntity;
import com.becfernandezp.best_travel.domain.repositories.ReservationRepository;
import com.becfernandezp.best_travel.domain.repositories.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Transactional
@Component
@AllArgsConstructor
public class TourHelper {
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;

    private Set<TicketEntity> createTickets (Set<FlyEntity> fligths, CustomerEntity customer){
        var response = new HashSet<TicketEntity>(fligths.size());
        return null;

    }
}
