package com.becfernandezp.best_travel.infraestructure.services;

import com.becfernandezp.best_travel.api.models.request.TicketRequest;
import com.becfernandezp.best_travel.api.models.responses.FlyResponse;
import com.becfernandezp.best_travel.api.models.responses.TicketResponse;
import com.becfernandezp.best_travel.domain.entities.TicketEntity;
import com.becfernandezp.best_travel.domain.repositories.CustomerRepository;
import com.becfernandezp.best_travel.domain.repositories.FlyRepository;
import com.becfernandezp.best_travel.domain.repositories.TicketRepository;
import com.becfernandezp.best_travel.infraestructure.abstract_services.ITicketService;
import com.becfernandezp.best_travel.util.BestTravelUtil;
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
public class TicketService implements ITicketService {

    private final FlyRepository flyRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;

    @Override
    public TicketResponse create(TicketRequest request) {
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow();
        var customer = customerRepository.findById( request.getIdClient()).orElseThrow();

        var ticketToPersist = TicketEntity.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customer(customer)
                .price(fly.getPrice().multiply(charger_price_percentage))
                .purchaseDate(LocalDate.now())
                .departureDate(BestTravelUtil.getRandomSoon())
                .arrivalDate(BestTravelUtil.getRandomLatter())
                .build();

        var ticketPersisted= this.ticketRepository.save(ticketToPersist);
        log.info("Ticket saved with id: {} " , ticketPersisted.getId());
        return this.entityToResponse(ticketPersisted);
    }

    @Override
    public TicketResponse read(UUID id) {
        var ticketFromDB = this.ticketRepository.findById(id).orElseThrow();
        return this.entityToResponse(ticketFromDB) ;
    }

    @Override
    public TicketResponse update(TicketRequest request, UUID uuid) {
        var ticketToUpdate = ticketRepository.findById(uuid).orElseThrow();
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow();

        ticketToUpdate.setFly(fly);
        ticketToUpdate.setPrice(fly.getPrice().multiply(charger_price_percentage));
        ticketToUpdate.setDepartureDate(BestTravelUtil.getRandomSoon());
        ticketToUpdate.setArrivalDate(BestTravelUtil.getRandomLatter());


        var ticketUpdated = this.ticketRepository.save(ticketToUpdate);
        log.info("Ticket updatead with id: {} " , ticketUpdated.getId());

        return this.entityToResponse(ticketUpdated);
    }

    @Override
    public void delete(UUID uuid) {
        var ticketToDelete = ticketRepository.findById(uuid).orElseThrow();
        this.ticketRepository.delete(ticketToDelete);
    }

    @Override
    public BigDecimal findPrice(Long flyId) {
        var fly = this.flyRepository.findById(flyId).orElseThrow();
        return  fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage));

    }

    private TicketResponse entityToResponse(TicketEntity entity) {
        var response = new TicketResponse();
        BeanUtils.copyProperties(entity, response); // Machea valores de la clase Entity a response
        var flyResponse = new FlyResponse();
        BeanUtils.copyProperties(entity.getFly(), flyResponse);
        response.setFly(flyResponse);
        return response;


    }

   public static final BigDecimal charger_price_percentage = BigDecimal.valueOf(0.25);
}
