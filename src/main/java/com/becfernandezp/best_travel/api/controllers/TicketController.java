package com.becfernandezp.best_travel.api.controllers;

import com.becfernandezp.best_travel.api.models.request.TicketRequest;
import com.becfernandezp.best_travel.api.models.responses.TicketResponse;
import com.becfernandezp.best_travel.infraestructure.abstract_services.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "ticket")
@AllArgsConstructor
public class TicketController {
    private final ITicketService ticketService;

    @PostMapping
    public ResponseEntity <TicketResponse> post (@RequestBody TicketRequest request){
        return ResponseEntity.ok(ticketService.create(request));
    }

    @GetMapping (path = "{id}")
    public ResponseEntity <TicketResponse> get(@PathVariable UUID id){
        return ResponseEntity.ok(ticketService.read(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity <TicketResponse> put (@PathVariable UUID id, @RequestBody TicketRequest request){
        return  ResponseEntity.ok(this.ticketService.update(request,id ));

    }

    @DeleteMapping (path = "{id}")
    public ResponseEntity <Void> delete(@PathVariable UUID id){
        this.ticketService.delete(id);
        return ResponseEntity.noContent().build() ;
    }

    @GetMapping
    public ResponseEntity <Map<String, BigDecimal> >getFlyPrice(@RequestParam Long flyId){
        return ResponseEntity.ok(Collections.singletonMap("flyPrice",this.ticketService.findPrice(flyId)));
    }

}
