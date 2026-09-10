package com.hotel404.repository;

import com.hotel404.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    List<Usuarios> findByEstadoTrue();

    boolean existsByNombreIgnoreCaseAndUsuarioIgnoreCaseAndCorreoIgnoreCase(String nombre,String correo,String usuario);
}

