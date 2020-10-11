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

import com.puntonet.ticket.core.persistencia.entidad.Cliente;

/**
 * Clase que implementa los metodos de Cliente.
 * @author mmrivera
 * @version 1.0
 */
@Stateless
@LocalBean
public class ClienteDAO extends GenericDAO<Cliente, Integer>{

	private static final Log log = LogFactory.getLog(ClienteDAO.class);

	/**
     * Constructor por defecto de la clase
     */
    public ClienteDAO() {
        super(Cliente.class);
    }
	/**
	 * Obtener la lista de clientes
	 * @param clienteBusqueda Objeto con los filtros de la búsqueda
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> obtenerClientes(Cliente clienteBusqueda) throws Exception {
		log.info(":obtenerClientes DAO...");
		Criteria criteria = getSession().createCriteria(Cliente.class, "c");
		
		//prepara las condiciones adicionales según los parametros enviados
		List<Criterion> criterios = new ArrayList<Criterion>();
		if(clienteBusqueda.getNombre()!= null && !clienteBusqueda.getNombre().isEmpty()){
			if(clienteBusqueda.getNombre().contains("%")){
				criterios.add(Restrictions.like("c.nombre", clienteBusqueda.getNombre()));
			}else{
				criterios.add(Restrictions.eq("c.nombre", clienteBusqueda.getNombre()));
			}
		}
		if(clienteBusqueda.getEstado()!= null){
			criterios.add(Restrictions.eq("c.estado", clienteBusqueda.getEstado()));
		}
						
		//Añade los criterios a la búsqueda, se mantiene en caso de añadir más de un parámetro
		for (Criterion criterion : criterios) {
			criteria.add(criterion);
		}
		
		criteria.addOrder(Order.asc("c.id"));		
		
		log.info("criteria:" + criteria.toString());
			
		return criteria.list();
	}
}
