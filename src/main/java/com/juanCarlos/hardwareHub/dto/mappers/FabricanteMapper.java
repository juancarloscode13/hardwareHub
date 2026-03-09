package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.FabricanteRequestDto;
import com.juanCarlos.hardwareHub.dto.response.FabricanteResponseDto;
import com.juanCarlos.hardwareHub.entity.FabricanteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class)
public interface FabricanteMapper {

    @Mapping(target = "id", ignore = true)
    FabricanteEntity toEntity(FabricanteRequestDto requestDto);

    FabricanteResponseDto toResponseDto(FabricanteEntity entity);

    List<FabricanteResponseDto> toResponseDtoList(List<FabricanteEntity> entities);
}
