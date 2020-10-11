package com.puntonet.ticket.core.enums;

/**
 * Clase que tiene los estados utilizados en el sistema
 *
 * @author mmrivera
 * @version 1.0
 */
public enum EstadosEnum {

    /**
     * Estado Activo
     */
    ACTIVO("ACT"),
    /**
     * Estado Inactivo, para eliminaciones lógicas
     */
    INACTIVO("INA"),
    /**
     * Estado Pendiente, para procesos aún no finalizados
     */
    PENDIENTE("PEN"),
    /**
     * Estado Iniciado, para procesos iniciados
     */
    INICIADO("INI"),
    /**
     * Estado Finalizado, para finalizacion de procesos
     */
    FINALIZADO("FIN");
    /**
     * Variable que contiene el código de la variable a obtener
     */
    private String value;

    /**
     * Constructor de la enumeracion
     */
    private EstadosEnum(final String value) {
        this.value = value;
    }

    /**
     * @return el valor
     */
    public String getValue() {
        return this.value;
    }
}
