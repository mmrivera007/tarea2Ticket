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

import com.puntonet.ticket.core.persistencia.entidad.Oferta;

/**
 * Clase que implementa los metodos de Oferta.
 * @author mmrivera
 * @version 1.0
 */
@Stateless
@LocalBean
public class OfertaDAO extends GenericDAO<Oferta, Integer>{

	private static final Log log = LogFactory.getLog(OfertaDAO.class);

	/**
     * Constructor por defecto de la clase
     */
    public OfertaDAO() {
        super(Oferta.class);
    }
	/**
	 * Obtener la lista de tecnicos
	 * @param tecnicoBusqueda Objeto con los filtros de la búsqueda
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	@SuppressWarnings("unchecked")
	public List<Oferta> obtenerOfertas(Oferta tecnicoBusqueda) throws Exception {
		log.info(":obtenerOfertas DAO...");
		Criteria criteria = getSession().createCriteria(Oferta.class, "o");
		
		//prepara las condiciones adicionales según los parametros enviados
		List<Criterion> criterios = new ArrayList<Criterion>();
		if(tecnicoBusqueda.getNombre()!= null && !tecnicoBusqueda.getNombre().isEmpty()){
			if(tecnicoBusqueda.getNombre().contains("%")){
				criterios.add(Restrictions.like("o.nombre", tecnicoBusqueda.getNombre()));
			}else{
				criterios.add(Restrictions.eq("o.nombre", tecnicoBusqueda.getNombre()));
			}
		}
		if(tecnicoBusqueda.getEstado()!= null){
			criterios.add(Restrictions.eq("o.estado", tecnicoBusqueda.getEstado()));
		}
						
		//Añade los criterios a la búsqueda, se mantiene en caso de añadir más de un parámetro
		for (Criterion criterion : criterios) {
			criteria.add(criterion);
		}
		
		criteria.addOrder(Order.asc("o.id"));
		
		log.info("criteria:" + criteria.toString());
			
		return criteria.list();
	}
}
