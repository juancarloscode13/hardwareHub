package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.PublicacionMontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PublicacionMontajeResponseDto;
import com.juanCarlos.hardwareHub.entity.PublicacionMontajeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class, uses = EntityReferenceMapper.class)
public interface PublicacionMontajeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "montaje", source = "montajeId")
    @Mapping(target = "usuario", source = "usuarioId")
    PublicacionMontajeEntity toEntity(PublicacionMontajeRequestDto requestDto);

    @Mapping(target = "montajeId", source = "montaje.id")
    @Mapping(target = "usuarioId", source = "usuario.id")
    PublicacionMontajeResponseDto toResponseDto(PublicacionMontajeEntity entity);

    List<PublicacionMontajeResponseDto> toResponseDtoList(List<PublicacionMontajeEntity> entities);
}
