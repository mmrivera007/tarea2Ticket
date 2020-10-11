package com.puntonet.ticket.core.persistencia.dto;

import java.io.Serializable;

import javax.persistence.Id;


/**
 * Campos requeridos para el reporte de los archivos convocatoria
 * 
 */
public class TicketDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	
	private Integer total;	
	
	private String etiqueta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
}
