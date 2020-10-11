package com.puntonet.ticket.core.negocio;

import java.util.List;

import javax.ejb.Local;

import com.puntonet.ticket.core.persistencia.entidad.Cliente;

/**
 * Interfaz que expone los servicios relacionados al cliente
 *
 * @author mmrivera
 * @version 1.0
 */
@Local
public interface ClienteBO {
	
	/**
	 * Obtiene la lista de clientes registrados conforme los parámetros de búsqueda
	 * @param clienteBusqueda  Objeto con los filtros de búsqueda
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	List<Cliente> obtenerClientes(Cliente clienteBusqueda) throws Exception;
	
	
}
