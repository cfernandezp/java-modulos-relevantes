package com.becfernandezp.best_travel.infraestructure.helpers;

import com.becfernandezp.best_travel.domain.entities.*;
import com.becfernandezp.best_travel.domain.repositories.ReservationRepository;
import com.becfernandezp.best_travel.domain.repositories.TicketRepository;
import com.becfernandezp.best_travel.infraestructure.services.ReservationService;
import com.becfernandezp.best_travel.infraestructure.services.TicketService;
import com.becfernandezp.best_travel.util.BestTravelUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Transactional
@Component
@AllArgsConstructor
public class TourHelper {
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;

    public Set<TicketEntity> createTickets (Set<FlyEntity> fligths, CustomerEntity customer){
        var response = new HashSet<TicketEntity>(fligths.size());
        fligths.forEach(fly ->{
            var ticketToPersist = TicketEntity.builder()
                    .id(UUID.randomUUID())
                    .fly(fly)
                    .customer(customer)
                    .price(fly.getPrice().multiply(TicketService.charger_price_percentage))
                    .purchaseDate(LocalDate.now())
                    .departureDate(BestTravelUtil.getRandomSoon())
                    .arrivalDate(BestTravelUtil.getRandomLatter())
                    .build();
            response.add(this.ticketRepository.save(ticketToPersist));
        });
        return response;
    }

    public Set<ReservationEntity> createReservations (HashMap<HotelEntity,Integer> hotels, CustomerEntity customer){
        var response = new HashSet<ReservationEntity>(hotels.size());
        hotels.forEach((hotel,totalDays)->{
            var reservationToPersist = ReservationEntity.builder()
                    .id(UUID.randomUUID())
                    .hotel(hotel)
                    .customer(customer)
                    .dateTimeReservation(LocalDateTime.now())
                    .totalDays(totalDays)
                    .dateStart(LocalDate.now())
                    .dateEnd(LocalDate.now().plusDays(totalDays))
                    .price(hotel.getPrice().add(hotel.getPrice().multiply(ReservationService.charges_price_percentage)))
                    .build();
            response.add(this.reservationRepository.save(reservationToPersist));
        });
        return response;
    }
}
