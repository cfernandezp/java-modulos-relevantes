package com.becfernandezp.best_travel.api.models.responses;

import com.becfernandezp.best_travel.domain.entities.TicketEntity;
import com.becfernandezp.best_travel.util.Aeroline;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlyResponse implements Serializable {

    private Long id;
    private Double originLat;
    private Double originLng;
    private Double destinyLat;
    private Double destinyLng;
    private String originName;
    private String destinyName;
    private BigDecimal price;
    private Aeroline aeroLine;


}
