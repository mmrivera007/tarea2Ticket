package com.puntonet.ticket.core.negocio;

import java.util.List;

import javax.ejb.Local;

import com.puntonet.ticket.core.common.TicketException;
import com.puntonet.ticket.core.persistencia.entidad.Ticket;
import com.puntonet.ticket.core.persistencia.entidad.TicketDetalle;

/**
 * Interfaz que expone los servicios relacionados al ticket
 *
 * @author mmrivera
 * @version 1.0
 */
@Local
public interface TicketBO {
	/**
	 * Guarda la información del ticket
	 * @param ticket Objeto a almacenar
	 * @throws TicketException Excepción
	 */
	void guardarTicket(Ticket ticket) throws TicketException;	
	/**
	 * Obtiene la lista de tickets registrados conforme los parámetros de búsqueda
	 * @param ticketBusqueda  Objeto con los filtros de búsqueda
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	List<Ticket> obtenerTickets(Ticket ticketBusqueda) throws Exception;
	
	/**
	 * Obtiene los ticketDetalles
	 * @param ticketDetalle Objeto con los filtros de búsqueda
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	List<TicketDetalle> obtenerTicketDetalles(TicketDetalle ticketDetalle) throws Exception;
	/**
	 * Guarda la información del ticketDetalle del ticket
	 * @param ticketDetalle Objeto a almacenar
	 * @throws TicketException Excepción
	 */
	void guardarTicketDetalle(TicketDetalle ticketDetalle)throws TicketException;
	/**
	 * Eliminar un ticket
	 * @param ticket a eliminar
	 * @throws TicketException
	 */
	void eliminarTicket(Ticket ticket)throws TicketException;
	
}
