package com.hotel404.service;

import com.hotel404.dto.UsuariosDTO;
import com.hotel404.entity.Perfiles;
import com.hotel404.entity.Usuarios;
import com.hotel404.repository.PerfilesRepository;
import com.hotel404.repository.UsuariosRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuariosService {
    private final UsuariosRepository usuariosRepo;
    private final PerfilesRepository perfilesRepo;

    public UsuariosService(UsuariosRepository usuariosRepo, PerfilesRepository perfilesRepo) {
        this.perfilesRepo = perfilesRepo;
        this.usuariosRepo = usuariosRepo;
    }

    // Método para convertir a Dto
    private UsuariosDTO convertirDto (Usuarios usuarios) {
        UsuariosDTO dto = new UsuariosDTO();

        dto.setIdUsuario(usuarios.getIdUsuario());
        dto.setUsuario(usuarios.getUsuario());
        dto.setCorreo(usuarios.getCorreo());
        dto.setNombre(usuarios.getNombre());
        dto.setEstado(usuarios.getEstado());
        dto.setContrasena(usuarios.getContrasena());
        dto.setIdPerfil(usuarios.getIdPerfil() != null ? usuarios.getIdPerfil().getIdPerfil() : null);
        return dto;
    }

    // Método para converitr a Entidad
    private Usuarios convertirEntidad (UsuariosDTO dto) {
        Usuarios usuarios = new Usuarios();

        usuarios.setIdUsuario(dto.getIdUsuario());
        usuarios.setUsuario(dto.getUsuario());
        usuarios.setCorreo(dto.getCorreo());
        usuarios.setNombre(dto.getNombre());
        usuarios.setEstado(dto.getEstado());
        usuarios.setContrasena(dto.getContrasena());

        Perfiles perfil = perfilesRepo.findById(dto.getIdPerfil())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Perfil no encontrado con id: " + dto.getIdPerfil()));
        usuarios.setIdPerfil(perfil);

        return usuarios;
    }

    // listar Usuarios
    @Transactional
    public List<UsuariosDTO> listarUsuarios () {
        return  usuariosRepo.findAll()
                .stream()
                .map(this::convertirDto)
                .collect(Collectors.toList());
    }

    // listar Usuarios Activos
    @Transactional(readOnly = true)
    public List<UsuariosDTO> listarUsuariosActivos () {
        return  usuariosRepo.findByEstadoTrue()
                .stream()
                .map(this::convertirDto)
                .collect(Collectors.toList());

    }

    // Crear Usuario
    @Transactional
    public UsuariosDTO crearUsuario (UsuariosDTO usuarioDto) {
        if (usuariosRepo.existsByNombreIgnoreCaseAndUsuarioIgnoreCaseAndCorreoIgnoreCase(usuarioDto.getNombre(),usuarioDto.getUsuario(),usuarioDto.getCorreo())) {
            throw new RuntimeException("Usuario ya existente");
        }
        Usuarios usuario = convertirEntidad(usuarioDto);
        Usuarios usuarioGuardado = usuariosRepo.save(usuario);
        return  convertirDto(usuarioGuardado);
    }

    // Actualizar Usuario
    @Transactional
    public UsuariosDTO actualizarUsuario(Integer idUsuario, UsuariosDTO usuarioDto) {
        Usuarios usuarioExistente = usuariosRepo.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado con id: " + idUsuario
                ));

        if (usuarioDto.getEstado() != null) {
            usuarioExistente.setEstado(usuarioDto.getEstado());
        }

        if (usuarioDto.getCorreo() != null) {
            usuarioExistente.setCorreo(usuarioDto.getCorreo());
        }

        if (usuarioDto.getNombre() != null) {
            usuarioExistente.setNombre(usuarioDto.getNombre());
        }

        if (usuarioDto.getUsuario() != null) {
            usuarioExistente.setUsuario(usuarioDto.getUsuario());
        }

        Usuarios usuarioGuardado = usuariosRepo.save(usuarioExistente);

        return convertirDto(usuarioGuardado);
    }

    // Anular Borrado Lógico
    @Transactional
    public UsuariosDTO anularUsuario (Integer idUsuario) {
        Usuarios usuario = usuariosRepo.findById(idUsuario)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontro el usuario con id" + idUsuario));

        usuario.setEstado(false);

        Usuarios usuarioGuardado = usuariosRepo.save(usuario);
        return  convertirDto(usuarioGuardado);
    }

}
