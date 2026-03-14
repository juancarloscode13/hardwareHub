package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.PublicacionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionResponseDto;
import com.juanCarlos.hardwareHub.entity.PublicacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class, uses = EntityReferenceMapper.class)
public interface PublicacionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "reacciones", ignore = true)
    @Mapping(target = "montaje", source = "montajeId")
    @Mapping(target = "usuario", source = "usuarioId")
    PublicacionEntity toEntity(PublicacionRequestDto requestDto);

    @Mapping(target = "montajeId", source = "montaje.id")
    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "likesCount", ignore = true)
    @Mapping(target = "dislikesCount", ignore = true)
    @Mapping(target = "loveCount", ignore = true)
    @Mapping(target = "funnyCount", ignore = true)
    @Mapping(target = "interestingCount", ignore = true)
    PublicacionResponseDto toResponseDto(PublicacionEntity entity);

    List<PublicacionResponseDto> toResponseDtoList(List<PublicacionEntity> entities);
}
