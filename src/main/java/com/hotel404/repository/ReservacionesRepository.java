package com.hotel404.repository;

import com.hotel404.entity.Reservaciones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservacionesRepository
        extends JpaRepository<Reservaciones, Integer> {

    List<Reservaciones> findByEstadoTrue();

    List<Reservaciones> findByEstadoReservacionIgnoreCase(String estadoReservacion);

    List<Reservaciones> findByIdHuesped_IdHuesped(Integer idHuesped);
}