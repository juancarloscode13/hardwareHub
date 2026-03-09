package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.RamRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RamResponseDto;
import com.juanCarlos.hardwareHub.entity.RamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class, uses = EntityReferenceMapper.class)
public interface RamMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fabricante", source = "fabricanteId")
    RamEntity toEntity(RamRequestDto requestDto);

    @Mapping(target = "fabricanteId", source = "fabricante.id")
    RamResponseDto toResponseDto(RamEntity entity);

    List<RamResponseDto> toResponseDtoList(List<RamEntity> entities);
}
