package com.hotel404.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReservacionesDTO {

    private Integer idReservacion;
    private Date fechaReservacion;
    private Date fechaIngreso;
    private Date fechaSalida;
    private BigDecimal total;
    private boolean estado;
    private String estadoReservacion;
    private Integer idHuesped;
    private Integer idUsuario;
}