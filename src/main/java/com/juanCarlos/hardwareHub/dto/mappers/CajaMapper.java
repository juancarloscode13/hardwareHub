package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.CajaRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CajaResponseDto;
import com.juanCarlos.hardwareHub.entity.CajaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class, uses = EntityReferenceMapper.class)
public interface CajaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fabricante", source = "fabricanteId")
    CajaEntity toEntity(CajaRequestDto requestDto);

    @Mapping(target = "fabricanteId", source = "fabricante.id")
    CajaResponseDto toResponseDto(CajaEntity entity);

    List<CajaResponseDto> toResponseDtoList(List<CajaEntity> entities);
}
