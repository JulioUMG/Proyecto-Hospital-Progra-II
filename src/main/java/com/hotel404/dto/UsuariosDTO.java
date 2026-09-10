package com.hotel404.dto;

import lombok.Data;

@Data
public class UsuariosDTO {
    private Integer idUsuario;
    private String nombre;
    private String usuario;
    private String correo;
    private String contrasena;
    private Boolean estado;
    private Integer idPerfil;
}
