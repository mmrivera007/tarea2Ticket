package com.puntonet.ticket.core.negocio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.puntonet.ticket.core.common.TicketException;
import com.puntonet.ticket.core.enums.EstadosEnum;
import com.puntonet.ticket.core.negocio.TicketBO;
import com.puntonet.ticket.core.persistencia.TicketDAO;
import com.puntonet.ticket.core.persistencia.TicketDetalleDAO;
import com.puntonet.ticket.core.persistencia.entidad.Ticket;
import com.puntonet.ticket.core.persistencia.entidad.TicketDetalle;

/**
 * Clase que implementa los metodos relacionados al ticket.
 * @author mmrivera
 * @version 1.0
 */
@Stateless
@LocalBean
public class TicketBOImpl implements TicketBO{
	
	protected Log log = LogFactory.getLog(TicketBOImpl.class);
		
	@EJB
	private TicketDAO ticketDAO;
	@EJB
	private TicketDetalleDAO ticketDetalleDAO;
		
	@Override
	public void guardarTicket(Ticket ticket) throws TicketException{
		log.info(":guardarTicket... " + ticket);
		if(ticket == null || ticket.getCatalogoEstado() == null ){
			throw new TicketException("Se requiere el envío de los datos obligatorios del objeto para poder almacenar.");
		}

		try {			
			if(ticket.getId() == null){
				ticket.setEstado(EstadosEnum.ACTIVO.getValue());
				ticketDAO.crear(ticket);				
			}else{
				ticketDAO.actualizar(ticket);
			}
		} catch (Exception e) {
			log.info(e);
			throw new TicketException(e.getMessage());
		}
	}
	@Override
	public List<Ticket> obtenerTickets(Ticket ticketBusqueda) throws Exception {
		log.info(":obtenerTicket...");
		if(ticketBusqueda == null){
			throw new Exception("Se requiere el envío de información para realizar la búsqueda.");
		}
		List<Ticket> ticketList = ticketDAO.obtenerTickets(ticketBusqueda);
		ticketList.stream().forEach( (ticket) -> {
			if(ticket.getTicketDetalles() != null && !ticket.getTicketDetalles().isEmpty()){
				ticket.setTotalNotas(ticket.getTicketDetalles().size());
			}
		});
		
		return ticketList;
	}
	
	@Override
	public List<TicketDetalle> obtenerTicketDetalles(TicketDetalle ticketDetalle) throws Exception {
		log.info(":obtenerTicketDetalles...");
		if(ticketDetalle == null){
			throw new Exception("Se requiere el objeto ticketDetalle para poder obtener la información.");
		}
		return ticketDetalleDAO.obtenerTicketDetalles(ticketDetalle);
	}
	@Override
	public void guardarTicketDetalle(TicketDetalle ticketDetalle)throws TicketException{
		log.info(":guardarTicketDetalle... " + ticketDetalle);
		if(ticketDetalle == null || ticketDetalle.getTicket() == null || ticketDetalle.getNota() == null){
			throw new TicketException("Se requiere el envío de los datos obligatorios del objeto ticketDetalle para poder almacenar.");
		}
		try{
			if(ticketDetalle.getId() == null){
				ticketDetalle.setEstado(EstadosEnum.ACTIVO.getValue());
				ticketDetalleDAO.crear(ticketDetalle);
			}else{
				ticketDetalleDAO.actualizar(ticketDetalle);
			}
		} catch (Exception e) {
			log.info(e);
			throw new TicketException(e.getMessage());
		}
	}
	@Override
	public void eliminarTicket(Ticket ticket) throws TicketException {
		log.info(":eliminarTicket...");
		if(ticket == null || ticket.getId() == null){
			throw new TicketException("Se requiere el objeto a eliminar");
		}
		ticketDAO.eliminar(ticket);
	}
	
}
