package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.CpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.CpuResponseDto;
import com.juanCarlos.hardwareHub.entity.CpuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class, uses = EntityReferenceMapper.class)
public interface CpuMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fabricante", source = "fabricanteId")
    CpuEntity toEntity(CpuRequestDto requestDto);

    @Mapping(target = "fabricanteId", source = "fabricante.id")
    CpuResponseDto toResponseDto(CpuEntity entity);

    List<CpuResponseDto> toResponseDtoList(List<CpuEntity> entities);
}
