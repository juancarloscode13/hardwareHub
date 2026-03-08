package com.juanCarlos.hardwareHub.entity;

import com.juanCarlos.hardwareHub.entity.enums.CpuSocket;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseChipset;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseFormato;
import com.juanCarlos.hardwareHub.entity.enums.PlacaBaseWifiSoportado;
import com.juanCarlos.hardwareHub.entity.enums.RamTipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "placa_base")
public class PlacaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "modelo")
    private String modelo;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_fabricante")
    private FabricanteEntity fabricante;

    @Null
    @Column(name = "precio", precision = 7, scale = 2)
    private BigDecimal precio;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "socket_compatible")
    private CpuSocket socketCompatible;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "chipset")
    private PlacaBaseChipset chipset;

    @Null
    @Column(name = "memoria_maxima")
    private Integer memoriaMaxima;

    @Null
    @Column(name = "espacios_ram")
    private Integer espaciosRam;

    @Null
    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo_ram_soportada")
    private RamTipo tipoRamSoportada;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "formato")
    private PlacaBaseFormato formato;

    @Null
    @Column(name = "ranuras_expansion")
    private Integer ranurasExpansion;

    @Null
    @Column(name = "ranuras_almacenamiento")
    private Integer ranurasAlmacenamiento;

    @Null
    @Column(name = "puertos_usb")
    private Integer puertosUsb;

    @Null
    @JdbcTypeCode(value = SqlTypes.JSON)
    @Column(name = "conectividad_interna")
    private Map<String, Object> conectividadInterna;

    @Null
    @Enumerated(value = EnumType.STRING)
    @Column(name = "wifi_soportado")
    private PlacaBaseWifiSoportado wifiSoportado;
}
