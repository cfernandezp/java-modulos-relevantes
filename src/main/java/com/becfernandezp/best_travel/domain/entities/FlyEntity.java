package com.becfernandezp.best_travel.domain.entities;

import com.becfernandezp.best_travel.util.Aeroline;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "fly")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlyEntity implements  Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double originLat;
    private Double originLng;
    private Double destinyLat;
    private Double destinyLng;

    @Column(length = 20)
    private String originName;

    @Column(length = 20)
    private String destinyName;
    private BigDecimal price; //Mejor para mapear dinero

    @Enumerated(EnumType.STRING)
    private Aeroline aeroLine;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
       cascade = CascadeType.ALL,
       fetch = FetchType.EAGER,
       orphanRemoval = true,
       mappedBy = "fly"
    )
    private Set<TicketEntity> tickets;

}
