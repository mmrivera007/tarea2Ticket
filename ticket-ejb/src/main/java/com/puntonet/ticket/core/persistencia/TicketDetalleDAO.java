package com.puntonet.ticket.core.persistencia;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.puntonet.ticket.core.persistencia.entidad.TicketDetalle;

/**
 * Clase que implementa los metodos de Ticket Detalle.
 * @author mmrivera
 * @version 1.0
 */
@Stateless
@LocalBean
public class TicketDetalleDAO extends GenericDAO<TicketDetalle, Integer>{

	private static final Log log = LogFactory.getLog(TicketDetalleDAO.class);

	/**
     * Constructor por defecto de la clase
     */
    public TicketDetalleDAO() {
        super(TicketDetalle.class);
    }
	/**
	 * Obtener la lista de ticketDetalles
	 * @param ticketDetalle Objeto con los filtros de búsqueda
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	@SuppressWarnings("unchecked")
	public List<TicketDetalle> obtenerTicketDetalles(TicketDetalle ticketDetalle) throws Exception {
		log.info(":obtenerTicketDetalles DAO...");
		Criteria criteria = getSession().createCriteria(TicketDetalle.class, "d");
		criteria.createCriteria("d.ticket", "t",JoinType.LEFT_OUTER_JOIN);
		
		if(ticketDetalle.getTicket() != null && ticketDetalle.getTicket().getId() != null){
			criteria.add(Restrictions.eq("t.id", ticketDetalle.getTicket().getId()));
		}
		if(ticketDetalle.getEstado() != null){
			criteria.add(Restrictions.eq("d.estado", ticketDetalle.getEstado()));
		}
		
		log.info("criteria:" + criteria.toString());
			
		return criteria.list();
	}
	/**
	 * Obtener ticketDetalle por ID
	 * @param id Código
	 * @return TicketDetalle Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	public TicketDetalle obtenerTicketDetalleByID(Integer id) throws Exception {
		log.info(":obtenerTicketDetalleByID DAO...");
		
		Criteria criteria = getSession().createCriteria(TicketDetalle.class, "td");
		criteria.createCriteria("td.ticket", "t",JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("td.id", id));
		
		log.info("criteria:" + criteria.toString());
		return (TicketDetalle)criteria.uniqueResult();
	}
	/**
	 * Obtiene los ticketDetalles dado el código del ticked
	 * @param id relacionado
	 * @return List resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	@SuppressWarnings("unchecked")
	public List<TicketDetalle> obtenerTicketDetallesByIdTicket(Integer id) throws Exception {
		log.info(":obtenerTicketDetallesByCodFuncionario DAO...");
		Criteria criteria = getSession().createCriteria(TicketDetalle.class, "td");
		criteria.createCriteria("td.ticket", "t",JoinType.LEFT_OUTER_JOIN);
		if(id != null){
			criteria.add(Restrictions.eq("t.id", id));
		}
		
		log.info("criteria:" + criteria.toString());
			
		return criteria.list();
	}
}
