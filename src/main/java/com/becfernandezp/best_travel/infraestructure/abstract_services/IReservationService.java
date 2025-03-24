package com.becfernandezp.best_travel.infraestructure.abstract_services;

import com.becfernandezp.best_travel.api.models.request.ReservationRequest;
import com.becfernandezp.best_travel.api.models.responses.ReservationResponse;
import com.becfernandezp.best_travel.domain.entities.ReservationEntity;

import java.math.BigDecimal;
import java.util.UUID;

public interface IReservationService extends CrudService <ReservationRequest, ReservationResponse, UUID>{

    BigDecimal findPrice(Long hotelId);
}
