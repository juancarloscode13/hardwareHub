package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.AlmacenamientoRequestDto;
import com.juanCarlos.hardwareHub.dto.response.AlmacenamientoResponseDto;
import com.juanCarlos.hardwareHub.entity.AlmacenamientoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class, uses = EntityReferenceMapper.class)
public interface AlmacenamientoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fabricante", source = "fabricanteId")
    AlmacenamientoEntity toEntity(AlmacenamientoRequestDto requestDto);

    @Mapping(target = "fabricanteId", source = "fabricante.id")
    AlmacenamientoResponseDto toResponseDto(AlmacenamientoEntity entity);

    List<AlmacenamientoResponseDto> toResponseDtoList(List<AlmacenamientoEntity> entities);
}
