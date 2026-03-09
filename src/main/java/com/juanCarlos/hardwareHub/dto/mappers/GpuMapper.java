package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.GpuRequestDto;
import com.juanCarlos.hardwareHub.dto.response.GpuResponseDto;
import com.juanCarlos.hardwareHub.entity.GpuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class, uses = EntityReferenceMapper.class)
public interface GpuMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fabricante", source = "fabricanteId")
    GpuEntity toEntity(GpuRequestDto requestDto);

    @Mapping(target = "fabricanteId", source = "fabricante.id")
    GpuResponseDto toResponseDto(GpuEntity entity);

    List<GpuResponseDto> toResponseDtoList(List<GpuEntity> entities);
}
