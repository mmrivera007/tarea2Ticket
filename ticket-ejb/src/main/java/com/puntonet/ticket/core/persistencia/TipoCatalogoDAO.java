package com.puntonet.ticket.core.persistencia;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.puntonet.ticket.core.persistencia.entidad.TipoCatalogo;

/**
 * Clase que implementa los metodos de Catalogo tipo.
 * @author mmrivera
 * @version 1.0
 */
@Stateless
@LocalBean
public class TipoCatalogoDAO extends GenericDAO<TipoCatalogo, Integer>{

	private static final Log log = LogFactory.getLog(TipoCatalogoDAO.class);

	/**
     * Constructor por defecto de la clase
     */
    public TipoCatalogoDAO() {
        super(TipoCatalogo.class);
    }
	
	/**
	 * Obtiene la lista de los tipos de catáĺogos
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	@SuppressWarnings("unchecked")
	public List<TipoCatalogo> obtenerTiposCatalogo() throws Exception {
		log.info(":obtenerTiposCatalogo DAO...");
		Criteria criteria = getSession().createCriteria(TipoCatalogo.class, "tc");
		criteria.addOrder(Order.asc("tc.nombre"));
		log.info("criteria:" + criteria.toString());
		return criteria.list();
	}
}
