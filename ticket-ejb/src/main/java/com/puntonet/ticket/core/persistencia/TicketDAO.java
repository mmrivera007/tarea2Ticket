package com.puntonet.ticket.core.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.puntonet.ticket.core.persistencia.entidad.Ticket;

/**
 * Clase que implementa los metodos de Ticket.
 * @author mmrivera
 * @version 1.0
 */
@Stateless
@LocalBean
public class TicketDAO extends GenericDAO<Ticket, Integer>{

	private static final Log log = LogFactory.getLog(TicketDAO.class);

	/**
     * Constructor por defecto de la clase
     */
    public TicketDAO() {
        super(Ticket.class);
    }
	/**
	 * Obtener la lista de tickets
	 * @param ticketBusqueda Objeto con los filtros de la búsqueda
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	@SuppressWarnings("unchecked")
	public List<Ticket> obtenerTickets(Ticket ticketBusqueda) throws Exception {
		log.info(":obtenerTicketPagina DAO...");
		Criteria criteria = getSession().createCriteria(Ticket.class, "t");
		criteria.createCriteria("t.catalogoEstado", "e",JoinType.LEFT_OUTER_JOIN);
		criteria.createCriteria("t.catalogoPrioridad", "p",JoinType.LEFT_OUTER_JOIN);
		criteria.createCriteria("t.cliente", "c",JoinType.LEFT_OUTER_JOIN);
		criteria.createCriteria("t.tecnico", "te",JoinType.LEFT_OUTER_JOIN);
		criteria.createCriteria("t.oferta", "o",JoinType.LEFT_OUTER_JOIN);
		
		//prepara las condiciones adicionales según los parametros enviados
		List<Criterion> criterios = new ArrayList<Criterion>();
		if(ticketBusqueda.getDescripcion()!= null && !ticketBusqueda.getDescripcion().isEmpty()){
			if(ticketBusqueda.getDescripcion().contains("%")){
				criterios.add(Restrictions.like("t.descripcion", ticketBusqueda.getDescripcion()));
			}else{
				criterios.add(Restrictions.eq("t.descripcion", ticketBusqueda.getDescripcion()));
			}
		}
		if(ticketBusqueda.getEstado()!= null){
			criterios.add(Restrictions.eq("t.estado", ticketBusqueda.getEstado()));
		}
		
		if(ticketBusqueda.getCatalogoEstado()!= null && ticketBusqueda.getCatalogoEstado().getId() != null){
			criterios.add(Restrictions.eq("e.id", ticketBusqueda.getCatalogoEstado().getId()));
		}
				
		//Añade los criterios a la búsqueda, se mantiene en caso de añadir más de un parámetro
		for (Criterion criterion : criterios) {
			criteria.add(criterion);
		}
		
		criteria.addOrder(Order.desc("t.id"));		
		
		log.info("criteria:" + criteria.toString());
			
		return criteria.list();
	}
	/**
	 * Obtener ticket por ID
	 * @param id Código
	 * @return Ticket Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	public Ticket obtenerTicketByID(Integer id) throws Exception {
		log.info(":obtenerTicketByID DAO...");
		
		Criteria criteria = getSession().createCriteria(Ticket.class, "t");
		criteria.createCriteria("t.catalogoEstado", "e",JoinType.LEFT_OUTER_JOIN);
		criteria.createCriteria("t.catalogoPrioridad", "p",JoinType.LEFT_OUTER_JOIN);
		criteria.createCriteria("t.cliente", "c",JoinType.LEFT_OUTER_JOIN);
		criteria.createCriteria("t.tecnico", "te",JoinType.LEFT_OUTER_JOIN);
		criteria.createCriteria("t.oferta", "o",JoinType.LEFT_OUTER_JOIN);
		
		criteria.add(Restrictions.eq("t.id", id));
		
		log.info("criteria:" + criteria.toString());
		return (Ticket)criteria.uniqueResult();
	}
	}
