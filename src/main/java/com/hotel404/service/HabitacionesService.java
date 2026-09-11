
package com.hotel404.service;

import com.hotel404.dto.HabitacionesDTO;
import com.hotel404.entity.Habitaciones;
import com.hotel404.repository.HabitacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HabitacionesService {

    @Autowired
    private HabitacionesRepository repository;

   private HabitacionesDTO convertirADTO(Habitaciones entidad) {
    HabitacionesDTO dto = new HabitacionesDTO();
    dto.setIdHabitacion(entidad.getIdHabitacion());
    dto.setNumero(entidad.getNumero());
    dto.setTipo(entidad.getTipo());
    dto.setPrecioNoche(entidad.getPrecioNoche());
    dto.setEstado(entidad.getEstado());
    dto.setEstadoHabitacion(entidad.getEstadoHabitacion());
    return dto;
}

private Habitaciones convertirAEntidad(HabitacionesDTO dto) {
    Habitaciones entidad = new Habitaciones();
    entidad.setIdHabitacion(dto.getIdHabitacion());
    entidad.setNumero(dto.getNumero());
    entidad.setTipo(dto.getTipo());
    entidad.setPrecioNoche(dto.getPrecioNoche());
    entidad.setEstado(dto.isEstado());
    entidad.setEstadoHabitacion(dto.getEstadoHabitacion()); // Ya no llegará null
    return entidad;
}

    public List<HabitacionesDTO> obtenerTodas() {
        return repository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public HabitacionesDTO obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::convertirADTO)
                .orElse(null);
    }

    public HabitacionesDTO guardar(HabitacionesDTO dto) {
        Habitaciones entidad = convertirAEntidad(dto);
        Habitaciones guardada = repository.save(entidad);
        return convertirADTO(guardada);
    }

    public HabitacionesDTO actualizar(Integer id, HabitacionesDTO dto) {
        if (repository.existsById(id)) {
            Habitaciones entidad = convertirAEntidad(dto);
            entidad.setIdHabitacion(id);
            return convertirADTO(repository.save(entidad));
        }
        return null;
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
