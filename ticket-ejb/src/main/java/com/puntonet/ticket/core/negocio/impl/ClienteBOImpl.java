package com.puntonet.ticket.core.negocio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.puntonet.ticket.core.negocio.ClienteBO;
import com.puntonet.ticket.core.persistencia.ClienteDAO;
import com.puntonet.ticket.core.persistencia.entidad.Cliente;

/**
 * Clase que implementa los metodos relacionados al cliente.
 * @author mmrivera
 * @version 1.0
 */
@Stateless
@LocalBean
public class ClienteBOImpl implements ClienteBO{
	
	protected Log log = LogFactory.getLog(ClienteBOImpl.class);
		
	@EJB
	private ClienteDAO clienteDAO;
	
	@Override
	public List<Cliente> obtenerClientes(Cliente clienteBusqueda) throws Exception {
		log.info(":obtenerCliente...");
		if(clienteBusqueda == null){
			throw new Exception("Se requiere el envío de información para realizar la búsqueda.");
		}
		return clienteDAO.obtenerClientes(clienteBusqueda);
	}	
}
