package com.becfernandezp.best_travel.domain.repositories;

import com.becfernandezp.best_travel.domain.entities.FlyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


public interface FlyRepository extends JpaRepository <FlyEntity,Long > {

    @Query("Select f from fly f where f.price<:price ")
    Set<FlyEntity> selectLessPrice(BigDecimal price);

    @Query("Select f from fly f where f.price between :min and :max ")
    Set<FlyEntity> selectBetweenPrice(BigDecimal min, BigDecimal max);


    @Query("Select f from fly f where f.originName =:origin and f.destinyName=:destiny")
    Set<FlyEntity> selectOriginDestiny(String origin, String destiny);

    @Query("Select f from fly f join fetch f.tickets t where t.id=:id ")
    Optional<FlyEntity> findByTicketId(UUID id);

}
