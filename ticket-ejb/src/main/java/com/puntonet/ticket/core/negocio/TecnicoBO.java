package com.puntonet.ticket.core.negocio;

import java.util.List;

import javax.ejb.Local;

import com.puntonet.ticket.core.persistencia.entidad.Tecnico;

/**
 * Interfaz que expone los servicios relacionados al tecnico
 *
 * @author mmrivera
 * @version 1.0
 */
@Local
public interface TecnicoBO {
	
	/**
	 * Obtiene la lista de tecnicos registrados conforme los parámetros de búsqueda
	 * @param tecnicoBusqueda  Objeto con los filtros de búsqueda
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	List<Tecnico> obtenerTecnicos(Tecnico tecnicoBusqueda) throws Exception;
	
	
}
