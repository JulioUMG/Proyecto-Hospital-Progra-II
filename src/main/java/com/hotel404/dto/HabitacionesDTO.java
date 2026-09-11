package com.hotel404.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class HabitacionesDTO {
    private Integer idHabitacion;
    private String numero;           
    private String tipo;             
    private BigDecimal precioNoche;  
    private boolean estado;          
    private String estadoHabitacion; 
}
