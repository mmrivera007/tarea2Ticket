package com.puntonet.ticket.core.negocio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.puntonet.ticket.core.common.TicketException;
import com.puntonet.ticket.core.negocio.CatalogoBO;
import com.puntonet.ticket.core.persistencia.CatalogoDAO;
import com.puntonet.ticket.core.persistencia.TipoCatalogoDAO;
import com.puntonet.ticket.core.persistencia.entidad.Catalogo;
import com.puntonet.ticket.core.persistencia.entidad.TipoCatalogo;

/**
 * Clase que implementa los metodos relacionados a los catálogos de la aplicacion
 * @author mmrivera
 * @version 1.0
 */
@Stateless
@LocalBean
public class CatalogoBOImpl implements CatalogoBO{
	
	protected Log log = LogFactory.getLog(CatalogoBOImpl.class);
		
	@EJB
	private CatalogoDAO catalogoDAO;
	@EJB
	private TipoCatalogoDAO tipoCatalogoDAO;
	
	@Override
	public List<TipoCatalogo> obtenerTiposCatalogo() throws Exception {
		log.info(":obtenerTiposCatalogo...");
		return tipoCatalogoDAO.obtenerTiposCatalogo();
	}
	@Override
	public List<Catalogo> obtenerCatalogos(Integer tipoCatalogo, Integer catalogo, boolean todos) throws Exception{
		log.info(":obtenerCatalogos..." + tipoCatalogo + " - " + catalogo+ " - " + todos);
		return catalogoDAO.obtenerCatalogos(tipoCatalogo, catalogo, todos);
	}
	@Override
	public List<Catalogo> obtenerCatalogosPadre(Integer tipoCatalogo, Integer catalogo) throws Exception{
		log.info(":obtenerCatalogosPadre..." + tipoCatalogo + " - " + catalogo);
		return catalogoDAO.obtenerCatalogosPadre(tipoCatalogo, catalogo);
	}
	@Override
	public List<Catalogo> obtenerCatalogos(Catalogo catalogoBusqueda) throws Exception {
		log.info(":obtenerCatalogoPagina...");
		if(catalogoBusqueda == null || catalogoBusqueda.getTipoCatalogo() == null){
			throw new Exception("Se requiere el envío de información para realizar la búsqueda.");
		}
		return catalogoDAO.obtenerCatalogos(catalogoBusqueda);
	}
	@Override
	public void guardarTipoCatalogo(TipoCatalogo tipoCatalogo) throws TicketException {
		log.info(":guardarTipoCatalogo... ");
		if(tipoCatalogo == null || tipoCatalogo.getNombre() == null){
			throw new TicketException("Se requiere el envío de los datos obligatorios del objeto tipo catálogo para poder almacenar.");
		}
		if(tipoCatalogo.getId() == null){
			tipoCatalogoDAO.crear(tipoCatalogo);
		}else{
			tipoCatalogoDAO.actualizar(tipoCatalogo);
		}		
	}
	@Override
	public void guardarCatalogo(Catalogo catalogo) throws TicketException {
		log.info(":guardarCatalogo... ");
		if(catalogo == null || catalogo.getNombre() == null || catalogo.getTipoCatalogo() == null 
				|| catalogo.getTipoCatalogo().getId() == null || catalogo.getEstado() == null){
			throw new TicketException("Se requiere el envío de los datos obligatorios del objeto catálogo para poder almacenar.");
		}
		try {
			
			if(catalogo.getId() == null){
				catalogoDAO.crear(catalogo);
			}else{
				catalogoDAO.actualizar(catalogo);
			}	
		} catch (Exception e) {
			log.info(e);
			throw new TicketException(e.getMessage());
		}
	}
	
}
