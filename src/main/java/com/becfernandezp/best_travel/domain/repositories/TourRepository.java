package com.becfernandezp.best_travel.domain.repositories;

import com.becfernandezp.best_travel.domain.entities.TourEntity;
import org.springframework.data.repository.CrudRepository;


public interface TourRepository extends CrudRepository<TourEntity,Long > {
}
