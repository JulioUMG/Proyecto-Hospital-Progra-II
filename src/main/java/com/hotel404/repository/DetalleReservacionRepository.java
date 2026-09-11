package com.hotel404.repository;

import com.hotel404.entity.DetalleReservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleReservacionRepository extends JpaRepository<DetalleReservacion, Integer> {
}