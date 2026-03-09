package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.ComentarioRequestDto;
import com.juanCarlos.hardwareHub.dto.response.ComentarioResponseDto;
import com.juanCarlos.hardwareHub.entity.ComentarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class, uses = EntityReferenceMapper.class)
public interface ComentarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "usuario", source = "usuarioId")
    @Mapping(target = "comentario", source = "comentarioId")
    @Mapping(target = "publicacion", source = "publicacionId")
    ComentarioEntity toEntity(ComentarioRequestDto requestDto);

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "comentarioId", source = "comentario.id")
    @Mapping(target = "publicacionId", source = "publicacion.id")
    ComentarioResponseDto toResponseDto(ComentarioEntity entity);

    List<ComentarioResponseDto> toResponseDtoList(List<ComentarioEntity> entities);
}
