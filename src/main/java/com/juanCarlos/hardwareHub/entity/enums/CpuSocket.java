package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.CpuEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Contiene valores normalizados sobre el socket que puede tener una cpu.
 *
 * @author Juan Carlos
 * @see CpuEntity
 */

@AllArgsConstructor
@Getter
public enum CpuSocket implements DescribedEnum {
    CPU_SOCKET_AM4("AM4"),
    CPU_SOCKET_AM5("AM5"),
    CPU_SOCKET_LGA1700("LGA1700"),
    CPU_SOCKET_LGA1851("LGA1851");

    private String desc;
}
