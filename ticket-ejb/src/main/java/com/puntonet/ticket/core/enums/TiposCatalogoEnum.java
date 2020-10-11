package com.puntonet.ticket.core.enums;

/**
 * Clase que tiene los tipos de catálogos 
 *
 * @author mmrivera
 * @version 1.0
 */
public enum TiposCatalogoEnum {
	
    ESTADOS(1),
    PRIORIDADES(2);
    /**
     * Variable que contiene el c�digo de la variable a obtener
     */
    private Integer value;

    /**
     * Constructor de la enumeracion
     */
    private TiposCatalogoEnum(final Integer value) {
        this.value = value;
    }

    /**
     * @return el valor
     */
    public Integer getValue() {
        return this.value;
    }
}
