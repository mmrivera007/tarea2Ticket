package com.puntonet.ticket.core.negocio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.puntonet.ticket.core.negocio.OfertaBO;
import com.puntonet.ticket.core.persistencia.OfertaDAO;
import com.puntonet.ticket.core.persistencia.entidad.Oferta;

/**
 * Clase que implementa los metodos relacionados al oferta.
 * @author mmrivera
 * @version 1.0
 */
@Stateless
@LocalBean
public class OfertaBOImpl implements OfertaBO{
	
	protected Log log = LogFactory.getLog(OfertaBOImpl.class);
		
	@EJB
	private OfertaDAO ofertaDAO;
	
	@Override
	public List<Oferta> obtenerOfertas(Oferta ofertaBusqueda) throws Exception {
		log.info(":obtenerOferta...");
		if(ofertaBusqueda == null){
			throw new Exception("Se requiere el envío de información para realizar la búsqueda.");
		}
		return ofertaDAO.obtenerOfertas(ofertaBusqueda);
	}	
}
