package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.RefrigeracionRequestDto;
import com.juanCarlos.hardwareHub.dto.response.RefrigeracionResponseDto;
import com.juanCarlos.hardwareHub.entity.RefrigeracionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class, uses = EntityReferenceMapper.class)
public interface RefrigeracionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fabricante", source = "fabricanteId")
    RefrigeracionEntity toEntity(RefrigeracionRequestDto requestDto);

    @Mapping(target = "fabricanteId", source = "fabricante.id")
    RefrigeracionResponseDto toResponseDto(RefrigeracionEntity entity);

    List<RefrigeracionResponseDto> toResponseDtoList(List<RefrigeracionEntity> entities);
}
