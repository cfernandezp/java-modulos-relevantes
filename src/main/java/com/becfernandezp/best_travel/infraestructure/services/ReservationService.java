package com.becfernandezp.best_travel.infraestructure.services;

import com.becfernandezp.best_travel.api.models.request.ReservationRequest;
import com.becfernandezp.best_travel.api.models.responses.HotelResponse;
import com.becfernandezp.best_travel.api.models.responses.ReservationResponse;
import com.becfernandezp.best_travel.domain.entities.ReservationEntity;
import com.becfernandezp.best_travel.domain.repositories.CustomerRepository;
import com.becfernandezp.best_travel.domain.repositories.HotelRepository;
import com.becfernandezp.best_travel.domain.repositories.ReservationRepository;
import com.becfernandezp.best_travel.infraestructure.abstract_services.IReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public ReservationResponse create(ReservationRequest request) {
        var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow();
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow();

        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .dateTimeReservation(LocalDateTime.now())
                .totalDays(request.getTotalDays())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)))
                .build();

        var reservationPersisted = this.reservationRepository.save(reservationToPersist);
        log.info("Reservation saved with id : {}", reservationPersisted.getId());
        return this.entityToResponse(reservationPersisted);

    }

    @Override
    public ReservationResponse read(UUID uuid) {
        return null;
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID uuid) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }

    private ReservationResponse entityToResponse (ReservationEntity entity) {
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity, response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(),hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }

    private static final BigDecimal charges_price_percentage = BigDecimal.valueOf(0.20);
}



