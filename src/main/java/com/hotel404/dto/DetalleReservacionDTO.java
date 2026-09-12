package com.hotel404.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DetalleReservacionDTO {
    private Integer idDetalleReservacion;
    private BigDecimal precioPactado;
    private boolean estado;
    private Integer idHabitacion;
    private Integer idReservacion;
}
