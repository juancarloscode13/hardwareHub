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
    @Mapping(target = "usuario", source = "usuarioId")
    PublicacionEntity toEntity(PublicacionRequestDto requestDto);

    @Mapping(target = "usuarioId", source = "usuario.id")
    PublicacionResponseDto toResponseDto(PublicacionEntity entity);

    List<PublicacionResponseDto> toResponseDtoList(List<PublicacionEntity> entities);
}
