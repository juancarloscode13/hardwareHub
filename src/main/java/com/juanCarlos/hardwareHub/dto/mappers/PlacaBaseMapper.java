package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.PlacaBaseRequestDto;
import com.juanCarlos.hardwareHub.dto.response.PlacaBaseResponseDto;
import com.juanCarlos.hardwareHub.entity.PlacaBaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class, uses = EntityReferenceMapper.class)
public interface PlacaBaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fabricante", source = "fabricanteId")
    PlacaBaseEntity toEntity(PlacaBaseRequestDto requestDto);

    @Mapping(target = "fabricanteId", source = "fabricante.id")
    PlacaBaseResponseDto toResponseDto(PlacaBaseEntity entity);

    List<PlacaBaseResponseDto> toResponseDtoList(List<PlacaBaseEntity> entities);
}
