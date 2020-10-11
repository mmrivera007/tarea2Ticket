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

import com.puntonet.ticket.core.persistencia.entidad.Tecnico;

/**
 * Clase que implementa los metodos de Tecnico.
 * @author mmrivera
 * @version 1.0
 */
@Stateless
@LocalBean
public class TecnicoDAO extends GenericDAO<Tecnico, Integer>{

	private static final Log log = LogFactory.getLog(TecnicoDAO.class);

	/**
     * Constructor por defecto de la clase
     */
    public TecnicoDAO() {
        super(Tecnico.class);
    }
	/**
	 * Obtener la lista de tecnicos
	 * @param tecnicoBusqueda Objeto con los filtros de la búsqueda
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	@SuppressWarnings("unchecked")
	public List<Tecnico> obtenerTecnicos(Tecnico tecnicoBusqueda) throws Exception {
		log.info(":obtenerTecnicos DAO...");
		Criteria criteria = getSession().createCriteria(Tecnico.class, "t");
		
		//prepara las condiciones adicionales según los parametros enviados
		List<Criterion> criterios = new ArrayList<Criterion>();
		if(tecnicoBusqueda.getNombre()!= null && !tecnicoBusqueda.getNombre().isEmpty()){
			if(tecnicoBusqueda.getNombre().contains("%")){
				criterios.add(Restrictions.like("t.nombre", tecnicoBusqueda.getNombre()));
			}else{
				criterios.add(Restrictions.eq("t.nombre", tecnicoBusqueda.getNombre()));
			}
		}
		if(tecnicoBusqueda.getEstado()!= null){
			criterios.add(Restrictions.eq("t.estado", tecnicoBusqueda.getEstado()));
		}
						
		//Añade los criterios a la búsqueda, se mantiene en caso de añadir más de un parámetro
		for (Criterion criterion : criterios) {
			criteria.add(criterion);
		}
		
		criteria.addOrder(Order.asc("t.id"));		
		
		log.info("criteria:" + criteria.toString());
			
		return criteria.list();
	}
}
