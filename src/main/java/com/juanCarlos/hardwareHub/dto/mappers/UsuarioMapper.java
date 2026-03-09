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
    UsuarioEntity toEntity(UsuarioRequestDto requestDto);

    UsuarioResponseDto toResponseDto(UsuarioEntity entity);

    List<UsuarioResponseDto> toResponseDtoList(List<UsuarioEntity> entities);
}
