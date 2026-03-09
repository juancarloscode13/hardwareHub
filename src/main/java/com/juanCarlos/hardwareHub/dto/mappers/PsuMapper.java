package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.PsuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PsuResponseDto;
import com.juanCarlos.hardwareHub.entity.PsuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class, uses = EntityReferenceMapper.class)
public interface PsuMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fabricante", source = "fabricanteId")
    PsuEntity toEntity(PsuRequestDto requestDto);

    @Mapping(target = "fabricanteId", source = "fabricante.id")
    PsuResponseDto toResponseDto(PsuEntity entity);

    List<PsuResponseDto> toResponseDtoList(List<PsuEntity> entities);
}
