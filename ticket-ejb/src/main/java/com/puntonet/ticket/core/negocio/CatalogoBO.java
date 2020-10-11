package com.puntonet.ticket.core.negocio;

import java.util.List;

import javax.ejb.Local;

import com.puntonet.ticket.core.common.TicketException;
import com.puntonet.ticket.core.persistencia.entidad.Catalogo;
import com.puntonet.ticket.core.persistencia.entidad.TipoCatalogo;

/**
 * Interfaz que expone los servicios relacionados a los catálogos de la aplicacion
 *
 * @author mmrivera
 * @version 1.0
 */
@Local
public interface CatalogoBO {
	
	/**
	 * Obtiene la lista de los tipos de catálogos
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	List<TipoCatalogo> obtenerTiposCatalogo() throws Exception;
	/**
	 * Obtiene la lista de catálogos
	 * @param tipoCatalogo Tipo
	 * @param catalogo Catálogo
	 * @param todos True:Registros sin tomar en cuenta la relación del padre
	 * @return List Resultado de la búsqueda
	 * @throws Exception Excepción
	 */
	public List<Catalogo> obtenerCatalogos(Integer tipoCatalogo, Integer catalogo, boolean todos) throws Exception;
	/**
	 * Obtiene la lista de catálogos padre
	 * @param tipoCatalogo Tipo
	 * @param catalogo Catálogo
	 * @return List Resultado de la búsqueda
	 * @throws Exception En caso de presentar errores en la búsqueda
	 */
	List<Catalogo> obtenerCatalogosPadre(Integer tipoCatalogo, Integer catalogo) throws Exception;
	/**
	 * Obtiene la lista de catálogos registrados conforme los parámetros de búsqueda
	 * @param catalogoBusqueda Objeto con los filtros de la búsqueda
	 * @return List Resultado de la búsqueda
	 * @throws Exception En caso de presentar errores en la búsqueda
	 */
	List<Catalogo> obtenerCatalogos(Catalogo catalogoBusqueda) throws Exception;
	/**
	 * Guarda la información del tipo catálogo
	 * @param tipoCatalogo Objeto a almacenar
	 * @throws TicketException En caso de presentar errores al guardar
	 */
	void guardarTipoCatalogo(TipoCatalogo tipoCatalogo) throws TicketException;
	/**
	 * Guarda la información del catálogo
	 * @param catalogo Objeto a almacenar
	 * @param procesaAuditoria Indicador
	 * @throws TicketException En caso de presentar errores al guardar
	 */
	void guardarCatalogo(Catalogo catalogo) throws TicketException;
}
