package com.puntonet.ticket.app.bean.data;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.puntonet.ticket.core.persistencia.entidad.Catalogo;
import com.puntonet.ticket.core.persistencia.entidad.Cliente;
import com.puntonet.ticket.core.persistencia.entidad.Oferta;
import com.puntonet.ticket.core.persistencia.entidad.Tecnico;
import com.puntonet.ticket.core.persistencia.entidad.Ticket;
import com.puntonet.ticket.core.persistencia.entidad.TicketDetalle;

/**
 * Clase para el manejo de los datos del ticket
 * 
 * @author mmrivera
 * @version 1.0
 */
@ManagedBean(name="ticketDM")
@ViewScoped
public class TicketDM  implements Serializable{

	private static final long serialVersionUID = -2909030587461033087L;
    //Variables para administrar los tickets
    private Ticket ticketEdicion;
    private TicketDetalle ticketDetalleEdicion;
  	private boolean edicion = false;
  	private boolean confirma = false;
  	
  	//Posibles valores: principal, registro, detalle
  	private String pantallaActual;
  	//El mismo ManageBean funciona para tres opciones de menú, variable para controlar
  	private String opcion;
    
	//Variables usadas en la búsqueda de la pantalla
  	private Ticket ticketBusqueda;
  	private List<Ticket> ticketList;
  	private boolean consultaLista = false;
  	
  	//Listados a presentar en los formularios de registro/edición
  	private List<Catalogo> estadosList;
  	private List<Catalogo> prioridadList;
  	private List<Cliente> clienteList;
  	private List<Tecnico> tecnicoList;
  	private List<Oferta> ofertaList;
  	private List<TicketDetalle> ticketDetalleList;
	public Ticket getTicketEdicion() {
		return ticketEdicion;
	}
	public void setTicketEdicion(Ticket ticketEdicion) {
		this.ticketEdicion = ticketEdicion;
	}
	public TicketDetalle getTicketDetalleEdicion() {
		return ticketDetalleEdicion;
	}
	public void setTicketDetalleEdicion(TicketDetalle ticketDetalleEdicion) {
		this.ticketDetalleEdicion = ticketDetalleEdicion;
	}
	public boolean isEdicion() {
		return edicion;
	}
	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}
	public String getPantallaActual() {
		return pantallaActual;
	}
	public void setPantallaActual(String pantallaActual) {
		this.pantallaActual = pantallaActual;
	}
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	public Ticket getTicketBusqueda() {
		return ticketBusqueda;
	}
	public void setTicketBusqueda(Ticket ticketBusqueda) {
		this.ticketBusqueda = ticketBusqueda;
	}
	public List<Ticket> getTicketList() {
		return ticketList;
	}
	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}
	public boolean isConsultaLista() {
		return consultaLista;
	}
	public void setConsultaLista(boolean consultaLista) {
		this.consultaLista = consultaLista;
	}
	public List<Catalogo> getEstadosList() {
		return estadosList;
	}
	public void setEstadosList(List<Catalogo> estadosList) {
		this.estadosList = estadosList;
	}
	public List<Catalogo> getPrioridadList() {
		return prioridadList;
	}
	public void setPrioridadList(List<Catalogo> prioridadList) {
		this.prioridadList = prioridadList;
	}
	public List<Cliente> getClienteList() {
		return clienteList;
	}
	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}
	public List<Tecnico> getTecnicoList() {
		return tecnicoList;
	}
	public void setTecnicoList(List<Tecnico> tecnicoList) {
		this.tecnicoList = tecnicoList;
	}
	public List<Oferta> getOfertaList() {
		return ofertaList;
	}
	public void setOfertaList(List<Oferta> ofertaList) {
		this.ofertaList = ofertaList;
	}
	public List<TicketDetalle> getTicketDetalleList() {
		return ticketDetalleList;
	}
	public void setTicketDetalleList(List<TicketDetalle> ticketDetalleList) {
		this.ticketDetalleList = ticketDetalleList;
	}
	public boolean isConfirma() {
		return confirma;
	}
	public void setConfirma(boolean confirma) {
		this.confirma = confirma;
	}
 }
