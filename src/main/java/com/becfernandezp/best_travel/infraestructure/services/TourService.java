package com.becfernandezp.best_travel.infraestructure.services;

import com.becfernandezp.best_travel.api.models.request.TourRequest;
import com.becfernandezp.best_travel.api.models.responses.TourResponse;
import com.becfernandezp.best_travel.domain.repositories.CustomerRepository;
import com.becfernandezp.best_travel.domain.repositories.FlyRepository;
import com.becfernandezp.best_travel.domain.repositories.HotelRepository;
import com.becfernandezp.best_travel.domain.repositories.TourRepository;
import com.becfernandezp.best_travel.infraestructure.abstract_services.ITourService;
import com.becfernandezp.best_travel.infraestructure.helpers.TourHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor
public class TourService implements ITourService {

    private final TourRepository tourRepository;
    private final FlyRepository flyRepository;
    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    private final TourHelper tourHelper;


    @Override
    public void removeTicket(UUID ticketId, Long tourId) {

    }

    @Override
    public UUID addTicket(Long flyId, Long tourId) {
        return null;
    }

    @Override
    public void removeReservation(UUID reservationId, Long tourId) {

    }

    @Override
    public UUID addTReservation(Long reservationId, Long tourId) {
        return null;
    }

    @Override
    public TourResponse create(TourRequest request) {
        return null;
    }

    @Override
    public TourResponse read(Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
