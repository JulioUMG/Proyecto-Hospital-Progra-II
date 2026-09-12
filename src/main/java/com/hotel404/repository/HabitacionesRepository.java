package com.hotel404.repository;

import com.hotel404.entity.Habitaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionesRepository extends JpaRepository<Habitaciones, Integer> {
}
