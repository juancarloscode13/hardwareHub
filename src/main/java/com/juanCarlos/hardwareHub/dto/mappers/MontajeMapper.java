package com.juanCarlos.hardwareHub.dto.mappers;

import com.juanCarlos.hardwareHub.dto.request.MontajeRequestDto;
import com.juanCarlos.hardwareHub.dto.response.MontajeResponseDto;
import com.juanCarlos.hardwareHub.entity.MontajeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructCentralConfig.class, uses = EntityReferenceMapper.class)
public interface MontajeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ram", source = "ramId")
    @Mapping(target = "cpu", source = "cpuId")
    @Mapping(target = "gpu", source = "gpuId")
    @Mapping(target = "refrigeracion", source = "refrigeracionId")
    @Mapping(target = "caja", source = "cajaId")
    @Mapping(target = "placaBase", source = "placaBaseId")
    @Mapping(target = "psu", source = "psuId")
    @Mapping(target = "almacenamiento", source = "almacenamientoId")
    @Mapping(target = "usuario", source = "usuarioId")
    MontajeEntity toEntity(MontajeRequestDto requestDto);

    @Mapping(target = "ramId", source = "ram.id")
    @Mapping(target = "cpuId", source = "cpu.id")
    @Mapping(target = "gpuId", source = "gpu.id")
    @Mapping(target = "refrigeracionId", source = "refrigeracion.id")
    @Mapping(target = "cajaId", source = "caja.id")
    @Mapping(target = "placaBaseId", source = "placaBase.id")
    @Mapping(target = "psuId", source = "psu.id")
    @Mapping(target = "almacenamientoId", source = "almacenamiento.id")
    @Mapping(target = "usuarioId", source = "usuario.id")
    MontajeResponseDto toResponseDto(MontajeEntity entity);

    List<MontajeResponseDto> toResponseDtoList(List<MontajeEntity> entities);
}
