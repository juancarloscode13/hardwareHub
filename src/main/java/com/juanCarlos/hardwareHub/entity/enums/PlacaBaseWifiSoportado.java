package com.juanCarlos.hardwareHub.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlacaBaseWifiSoportado {

    PLACA_BASE_WIFI_SOPORTADO_WIFI_1("WI-FI 1"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_2("WI-FI 2"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_3("WI-FI 3"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_4("WI-FI 4"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_5("WI-FI 5"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_6("WI-FI 6"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_6E("WI-FI 6E"),
    PLACA_BASE_WIFI_SOPORTADO_WIFI_7("WI-FI 7");

    private String desc;
}