package com.puntonet.ticket.core.negocio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.puntonet.ticket.core.negocio.TecnicoBO;
import com.puntonet.ticket.core.persistencia.TecnicoDAO;
import com.puntonet.ticket.core.persistencia.entidad.Tecnico;

/**
 * Clase que implementa los metodos relacionados al tecnico.
 * @author mmrivera
 * @version 1.0
 */
@Stateless
@LocalBean
public class TecnicoBOImpl implements TecnicoBO{
	
	protected Log log = LogFactory.getLog(TecnicoBOImpl.class);
		
	@EJB
	private TecnicoDAO tecnicoDAO;
	
	@Override
	public List<Tecnico> obtenerTecnicos(Tecnico tecnicoBusqueda) throws Exception {
		log.info(":obtenerTecnico...");
		if(tecnicoBusqueda == null){
			throw new Exception("Se requiere el envío de información para realizar la búsqueda.");
		}
		return tecnicoDAO.obtenerTecnicos(tecnicoBusqueda);
	}	
}
