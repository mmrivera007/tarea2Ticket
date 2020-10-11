	
package com.puntonet.ticket.core.persistencia;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.puntonet.ticket.core.common.TicketException;


/**
 * Clase con métodos de acceso genéricos
 * 
 * @author mmrivera
 * @version 2.0
 */
@SuppressWarnings("unchecked")
public class GenericDAO<T, PK extends Serializable> {

	private static final Log log = LogFactory.getLog(GenericDAO.class);
	
	@PersistenceContext(unitName = "ticketPU")
	protected EntityManager em;

	private final Class<T> type;

	/**
	 * Instancia un nuevo generic dao.
	 *
	 * @param type the type
	 */
	public GenericDAO(final Class<T> type) {
		this.type = type;
	}
	/**
     * Metodo que asigna Entity Manager
     *
     * @param em EntityManager
     */
    public void setEm(final EntityManager em) {
        this.em = em;
    }

	public Session getSession() {
		return (Session) em.getDelegate();
	}
	/**
	 * Crea el registro
	 * @param o Objeto
	 * @throws TicketException Excepción
	 */
	public void crear(final T o) throws TicketException {
		try {
			em.persist(o);
		} catch (final PersistenceException e) {
			throw new TicketException("Error al grabar: ".concat(o.toString()), e);
		}
	}

	/**
	 * Recuperar el registro
	 * @param id del registro
	 * @return T Resultado
	 * @throws TicketException Excepción
	 */
	public T recuperar(final PK id) throws TicketException {
		log.info("recuperar...");
		final T entidad = em.find(type, id);
		if (entidad == null) {
			final StringBuilder msg = new StringBuilder();
			msg.append(type.getSimpleName());
			msg.append('[');
			msg.append(id.toString());
			msg.append("] no encontrada.");
			throw new TicketException(msg.toString());
		}
		return entidad;
	}

	/**
	 * Actualizar el registro
	 * @param o Objeto
	 * @throws TicketException Excepción
	 */
	public void actualizar(final T o) throws TicketException {
		em.merge(o);
	}

	/**
	 * Eliminar registro
	 * @param o Objeto
	 * @throws TicketException Excepción
	 */
	public void eliminar(T o) throws TicketException {
		o = em.merge(o);
		em.remove(o);
	}

	/**
	 * Obtener todos los registros
	 * @return List Resultado
	 */
	public List<T> obtenerTodos() {
		log.info("obtenerTodos los registros...");
		final String className = type.getSimpleName();
		final StringBuilder sql = new StringBuilder();
		sql.append("from ").append(className);
		final Query query = em.createQuery(sql.toString());
		return query.getResultList();
	}

	/**
	 * Total de registros
	 * @return Long Resultado
	 */
	public Long contar() {
		final String tableName = type.getSimpleName();
		final Query query = em.createQuery("select count(*) from " + tableName);
		return (Long) query.getSingleResult();
	}

	/**
	 * Obtener registros páginados
	 * @param firstRow primer registro
	 * @param maxResults último registro
	 * @return List Resultado
	 */
	public List<T> encontrarPagina(final Integer firstRow, final Integer maxResults) {
		final String className = type.getSimpleName();
		final StringBuilder sql = new StringBuilder();
		sql.append("from ").append(className);
		final Query query = em.createQuery(sql.toString());
		query.setFirstResult(firstRow);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	/**
	 * Obtener registros por Query
	 * @param sql a ejecutar
	 * @param parametros de búsqueda
	 * @return List Resultado
	 */
	public List<T> encontrarPorQuery(final String sql, final Map<String, Object> parametros) {
		final Query query = em.createQuery(sql);
		final Iterator<String> claves = parametros.keySet().iterator();
		while (claves.hasNext()) {
			final String clave = claves.next();
			query.setParameter(clave, parametros.get(clave));
		}
		return query.getResultList();
	}

	/**
	 * Refrescar transacción
	 * @param o Objeto
	 */
	public void refrescar(final T o) {
		em.refresh(o);
	}

	/**
	 * Obtiene registros por query nativo
	 * @param statement a ejecutar
	 * @param parameters de búsqueda
	 * @return List Resultados
	 */
	public List<T> selectNativeQuery(final String statement, final Object[] parameters) {
		final Query query = em.createNativeQuery(statement, type);
		// parametros 
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i + 1, parameters[i]);
			}
		}
		return query.getResultList();
	}
	/**
	 * Quey nativo
	 * @param statement a ejecutar
	 * @param tipo Clase
	 * @return Query Resultado
	 */
	public Query nativeQuery(final String statement, Class tipo) {
		return em.createNativeQuery(statement, tipo);
	}

	/**
	 * Obtiene registros por nombre de query
	 * @param namedQuery a ejecutar
	 * @param parameters de búsqueda
	 * @return List Resultado
	 */
	public List<T> selectNamedQuery(final String namedQuery, final Map<String, Object> parameters) {
		final Query query = em.createNamedQuery(namedQuery);
		if (parameters != null) {
			for (final String key : parameters.keySet()) {
				query.setParameter(key, parameters.get(key));
			}
		}
		return query.getResultList();
	}
}
