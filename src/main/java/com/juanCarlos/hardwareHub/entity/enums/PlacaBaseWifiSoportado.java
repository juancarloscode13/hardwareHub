package com.juanCarlos.hardwareHub.entity.enums;

import com.juanCarlos.hardwareHub.entity.PlacaBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Contiene valores normalizados del wifi que puede soportar una placa base.
 *
 * @author Juan Carlos
 * @see PlacaBaseEntity
 */

@AllArgsConstructor
@Getter
public enum PlacaBaseWifiSoportado implements DescribedEnum {

    PLACA_BASE_WIFI_SOPORTADO_WIFI_1("WiFi 1"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_2("WiFi 2"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_3("WiFi 3"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_4("WiFi 4"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_5("WiFi 5"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_6("WiFi 6"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_6E("WiFi 6E"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_7("WiFi 7");

    private String desc;
}