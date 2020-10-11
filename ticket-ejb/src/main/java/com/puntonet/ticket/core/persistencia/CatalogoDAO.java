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

import com.puntonet.ticket.core.enums.EstadosEnum;
import com.puntonet.ticket.core.persistencia.entidad.Catalogo;

/**
 * Clase que implementa los metodos de Catalogo.
 * @author mmrivera
 * @version 1.0
 */
@Stateless
@LocalBean
public class CatalogoDAO extends GenericDAO<Catalogo, Integer>{

	private static final Log log = LogFactory.getLog(CatalogoDAO.class);

	/**
     * Constructor por defecto de la clase
     */
    public CatalogoDAO() {
        super(Catalogo.class);
    }
	/**
	 * Obtiene la lista de catálogos
	 * @param tipoCatalogo Tipo
	 * @param catalogo Catálogo
	 * @param todos True:Registros sin tomar en cuenta la relación del padre
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	@SuppressWarnings("unchecked")
	public List<Catalogo> obtenerCatalogos(Integer tipoCatalogo, Integer catalogo, boolean todos) throws Exception {
		log.info(":obtenerCatalogos DAO...");
		Criteria criteria = getSession().createCriteria(Catalogo.class, "c");
				
		if(tipoCatalogo != null){
			criteria.add(Restrictions.eq("c.tipoCatalogo.id", tipoCatalogo));
		}
		if(!todos){
			if(catalogo != null){
				criteria.add(Restrictions.eq("c.catalogoPadre.id", catalogo));
			}else{
				criteria.add(Restrictions.isNull("c.catalogoPadre.id"));
			}
		}else{
			criteria.createCriteria("c.catalogoPadre", "cp",JoinType.LEFT_OUTER_JOIN);
		}
		criteria.add(Restrictions.eq("c.estado", EstadosEnum.ACTIVO.getValue()));
		
		criteria.addOrder(Order.asc("c.id"));
		
		log.info("criteria:" + criteria.toString());
		return criteria.list();
	}
	/**
	 * Obtiene la lista de catálogos padre
	 * @param tipoCatalogo Tipo
	 * @param catalogo Catálogo
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	@SuppressWarnings("unchecked")
	public List<Catalogo> obtenerCatalogosPadre(Integer tipoCatalogo, Integer catalogo) throws Exception {
		log.info(":obtenerCatalogosPadre DAO...");
		Criteria criteria = getSession().createCriteria(Catalogo.class, "c");
		if(tipoCatalogo != null){
			criteria.add(Restrictions.eq("c.tipoCatalogo.id", tipoCatalogo));
		}
		if(catalogo != null){
			criteria.add(Restrictions.ne("c.id", catalogo));
		}
		criteria.add(Restrictions.eq("c.estado", EstadosEnum.ACTIVO.getValue()));
		log.info("criteria:" + criteria.toString());
		return criteria.list();
	}
	/**
	 * Lista de catálogos con paginación
	 * @param catalogoBusqueda Objeto con los filtros de búsqueda
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	@SuppressWarnings("unchecked")
	public List<Catalogo> obtenerCatalogos(Catalogo catalogoBusqueda) throws Exception {
		log.info(":obtenerCatalogoPagina DAO...");
		Criteria criteria = getSession().createCriteria(Catalogo.class, "c");
		criteria.createCriteria("c.tipoCatalogo", "tc",JoinType.INNER_JOIN);
		criteria.createCriteria("c.catalogoPadre", "cp",JoinType.LEFT_OUTER_JOIN);
		//prepara las condiciones adicionales según los parametros enviados
		List<Criterion> criterios = new ArrayList<Criterion>();
		if(catalogoBusqueda.getTipoCatalogo()!= null && catalogoBusqueda.getTipoCatalogo().getId() != null){
			criterios.add(Restrictions.eq("tc.id", catalogoBusqueda.getTipoCatalogo().getId()));
		}
		if(catalogoBusqueda.getNombre()!= null && !catalogoBusqueda.getNombre().isEmpty()){
			if(catalogoBusqueda.getNombre().contains("%")){
				criterios.add(Restrictions.like("c.nombre", catalogoBusqueda.getNombre()));
			}else{
				criterios.add(Restrictions.eq("c.nombre", catalogoBusqueda.getNombre()));
			}			
		}
		if(catalogoBusqueda.getEstado()!= null && !catalogoBusqueda.getEstado().isEmpty()){
			criterios.add(Restrictions.eq("c.estado", catalogoBusqueda.getEstado()));
		}
				
		//Añade los criterios a la búsqueda, se mantiene en caso de añadir más de un parámetro
		for (Criterion criterion : criterios) {
			criteria.add(criterion);
		}				
		criteria.addOrder(Order.asc("c.nombre"));				
		log.info("criteria:" + criteria.toString());
			
		return criteria.list();
	}
	/**
	 * Obtiene el catálogo por el ID
	 * @param codCatalogo Código
	 * @return Catalogo Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	public Catalogo obtenerCatalogoByID(Integer codCatalogo) throws Exception {
		Criteria criteria = getSession().createCriteria(Catalogo.class, "c");
		criteria.createCriteria("c.tipoCatalogo", "tc",JoinType.INNER_JOIN);
		criteria.createCriteria("c.catalogoPadre", "cp",JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("c.id", codCatalogo));
		
		log.info("criteria:" + criteria.toString());
		return (Catalogo)criteria.uniqueResult();
	}
}
