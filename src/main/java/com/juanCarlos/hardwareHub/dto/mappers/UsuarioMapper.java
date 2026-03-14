package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.UsuarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.UsuarioResponseDto;
import com.juanCarlos.hardwareHub.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class)
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuariosSeguidos", ignore = true)
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "reacciones", ignore = true)
    UsuarioEntity toEntity(UsuarioRequestDto requestDto);

    @Mapping(target = "followersCount",
             expression = "java(entity.getSeguidores() != null ? entity.getSeguidores().size() : 0)")
    @Mapping(target = "followingCount",
             expression = "java(entity.getUsuariosSeguidos() != null ? entity.getUsuariosSeguidos().size() : 0)")
    UsuarioResponseDto toResponseDto(UsuarioEntity entity);

    List<UsuarioResponseDto> toResponseDtoList(List<UsuarioEntity> entities);
}
