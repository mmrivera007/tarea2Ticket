package com.puntonet.ticket.core.negocio;

import java.util.List;

import javax.ejb.Local;

import com.puntonet.ticket.core.persistencia.entidad.Oferta;

/**
 * Interfaz que expone los servicios relacionados al oferta
 *
 * @author mmrivera
 * @version 1.0
 */
@Local
public interface OfertaBO {
	
	/**
	 * Obtiene la lista de ofertas registrados conforme los parámetros de búsqueda
	 * @param ofertaBusqueda  Objeto con los filtros de búsqueda
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	List<Oferta> obtenerOfertas(Oferta ofertaBusqueda) throws Exception;
	
	
}
