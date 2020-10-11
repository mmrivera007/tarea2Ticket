package com.puntonet.ticket.app.bean.ticket;

import java.text.MessageFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpServletRequest;

import com.puntonet.ticket.app.ControladorBase;
import com.puntonet.ticket.app.bean.data.TicketDM;
import com.puntonet.ticket.core.enums.EstadosEnum;
import com.puntonet.ticket.core.enums.TiposCatalogoEnum;
import com.puntonet.ticket.core.negocio.CatalogoBO;
import com.puntonet.ticket.core.negocio.ClienteBO;
import com.puntonet.ticket.core.negocio.OfertaBO;
import com.puntonet.ticket.core.negocio.TecnicoBO;
import com.puntonet.ticket.core.negocio.TicketBO;
import com.puntonet.ticket.core.persistencia.entidad.Catalogo;
import com.puntonet.ticket.core.persistencia.entidad.Cliente;
import com.puntonet.ticket.core.persistencia.entidad.Oferta;
import com.puntonet.ticket.core.persistencia.entidad.Tecnico;
import com.puntonet.ticket.core.persistencia.entidad.Ticket;
import com.puntonet.ticket.core.persistencia.entidad.TicketDetalle;


/**
* Clase que permite administrar la información de los tickets
* @author mmrivera 
* @since 1.0
* @version 1.0
*/
@ManagedBean(name = "adminTicket")
@ViewScoped
public class AdminTicket extends ControladorBase{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{ticketDM}")
	private TicketDM ticketDM;
	
	@EJB
	private TicketBO ticketBO;
	@EJB
	private CatalogoBO catalogoBO;
	@EJB
	private ClienteBO clienteBO;
	@EJB
	private TecnicoBO tecnicoBO;
	@EJB
	private OfertaBO ofertaBO;
    
    @PostConstruct
    public void inicializar(){ 
    	log.info(":inicializar...");
    	HttpServletRequest request =(HttpServletRequest) getExternalContext().getRequest();
    	
    	getTicketDM().setOpcion(request.getParameter("opcion"));    	
    	this.inicializarObjetoBusqueda();    	
    	this.obtenerListadosFormularioTicket(true);    	
    	this.buscarTicket(false);

    	getTicketDM().setPantallaActual("principal");
    	getTicketDM().setEdicion(false);
    }
    /**
     * Busca la lista de tickets registrados
     * @param presentaMensaje Indicador
     */
	public void buscarTicket(boolean presentaMensaje){
    	log.info(":buscarTicket...");
    	try {    		
    		getTicketDM().setTicketList(ticketBO.obtenerTickets(getTicketDM().getTicketBusqueda()));
			//Si no hay resultados se agrega un mensaje informativo
			if (getTicketDM().getTicketList().isEmpty()) {				
				if(presentaMensaje){
					addInfoMessage(obtenerMensajeRecurso("busqueda.lista.vacia"), null);
				}
			}
		} catch (Exception e) {
			log.info(e);
		}    	
    }
    /**
     * Limpia el formulario de búsqueda del ticket
     */
    public void limpiarBusqueda(){
    	log.info(":limpiarBusqueda...");
    	this.inicializarObjetoBusqueda();
    }
    /**
     * Inicializa el objeto de busqueda
     */
	private void inicializarObjetoBusqueda() {
		getTicketDM().setTicketBusqueda(new Ticket());
		getTicketDM().getTicketBusqueda().setCatalogoEstado(new Catalogo());
		getTicketDM().getTicketBusqueda().setCatalogoPrioridad(new Catalogo());
	}
    /**
     * Presenta la pantalla para el registro de un nuevo ticket
     */
    public void nuevoTicket(){
    	log.info(":nuevoTicket...");	
    	getTicketDM().setTicketEdicion(new Ticket());
    	getTicketDM().getTicketEdicion().setCatalogoEstado(new Catalogo());
		getTicketDM().getTicketEdicion().setCatalogoPrioridad(new Catalogo());
		getTicketDM().getTicketEdicion().setCliente(new Cliente());
		getTicketDM().getTicketEdicion().setTecnico(new Tecnico());
		getTicketDM().getTicketEdicion().setOferta(new Oferta());
    	getTicketDM().getTicketEdicion().setEstado(EstadosEnum.ACTIVO.getValue());
    	
    	//Obtenemos las listas requeridas para la pantalla
    	this.obtenerListadosFormularioTicket(false);
    	getTicketDM().setEdicion(true);
    	getTicketDM().setPantallaActual("registro");
    }

    /**
     * Obtiene la lista de valores requeridos en la pantalla de registro o edición de tickets
     * @param busqueda Indicador
     */
	public void obtenerListadosFormularioTicket(boolean busqueda) {
		log.info(":obtenerListadosFormularioTicket...");
		try {
			if(getTicketDM().getEstadosList() == null){
				getTicketDM().setEstadosList(catalogoBO.obtenerCatalogos(TiposCatalogoEnum.ESTADOS.getValue(), null, false));
			}
			if(getTicketDM().getPrioridadList() == null){
				getTicketDM().setPrioridadList(catalogoBO.obtenerCatalogos(TiposCatalogoEnum.PRIORIDADES.getValue(), null, false));
			}
			if(getTicketDM().getClienteList() == null){
				getTicketDM().setClienteList(clienteBO.obtenerClientes(new Cliente()));
			}
			if(getTicketDM().getTecnicoList() == null){
				getTicketDM().setTecnicoList(tecnicoBO.obtenerTecnicos(new Tecnico()));
			}
			if(getTicketDM().getOfertaList() == null){
				getTicketDM().setOfertaList(ofertaBO.obtenerOfertas(new Oferta()));
			}
		} catch (Exception e) {
			log.info(e);
		}
	}
    /**
     * Presenta la pantalla de edición con datos del ticket seleccionado
     * @param ticket a editar
     */
    public void editarTicket(Ticket ticket){
    	log.info(":editarTicket...");
    	try {
	    	//Inicializa los valores necesarios para la edición
    		getTicketDM().setTicketEdicion(ticket);
    		if(getTicketDM().getTicketEdicion().getCatalogoEstado() == null){
    			getTicketDM().getTicketEdicion().setCatalogoEstado(new Catalogo());
    		}
    		if(getTicketDM().getTicketEdicion().getCatalogoPrioridad() == null){
    			getTicketDM().getTicketEdicion().setCatalogoPrioridad(new Catalogo());
    		}
    		if(getTicketDM().getTicketEdicion().getCliente() == null){
    			getTicketDM().getTicketEdicion().setCliente(new Cliente());
    		}
    		if(getTicketDM().getTicketEdicion().getTecnico() == null){
    			getTicketDM().getTicketEdicion().setTecnico(new Tecnico());
    		}
    		if(getTicketDM().getTicketEdicion().getOferta() == null){
    			getTicketDM().getTicketEdicion().setOferta(new Oferta());
    		}

    		this.obtenerListadosFormularioTicket(false);
    		
    		getTicketDM().setEdicion(true);
    		getTicketDM().setPantallaActual("registro");
		} catch (Exception e) {
			log.info(e);
		}
    }
    /**
     * Cancelar la edicion
     */
    public void cancelarEdicion(){
    	log.info(":cancelarEdicion...");
    	
    	if(getTicketDM().getPantallaActual().equals("registro")){
	    	getTicketDM().setPantallaActual("principal");
	    	getTicketDM().setTicketEdicion(null);
    	}else{
    		//ticketDetalles
    		getTicketDM().setTicketDetalleEdicion(null);
    	}
    	
    	getTicketDM().setEdicion(false);
    }
	
    /**
     * Guarda la información del ticket nuevo o en edición
     */
    public void guardarTicket(){
    	log.info(":guardarTicket...");
    	try {
    		Ticket ticket = getTicketDM().getTicketEdicion();
    		if(ticket.getCatalogoPrioridad().getId() == null){
    			ticket.setCatalogoPrioridad(null);
    		}
    		if(ticket.getCliente().getId() == null){
    			ticket.setCliente(null);
    		}
    		if(ticket.getTecnico().getId() == null){
    			ticket.setTecnico(null);
    		}
    		if(ticket.getOferta().getId() == null){
    			ticket.setOferta(null);
    		}
    		
			ticketBO.guardarTicket(ticket);
			
			addInfoMessage(MessageFormat.format(obtenerMensajeRecurso("ticket.guardar.ticket.exito"), ticket.getDescripcion()), null);
    	
			this.buscarTicket(false);
			
			getTicketDM().setTicketEdicion(null);
			getTicketDM().setEdicion(false);
			getTicketDM().setPantallaActual("principal");
						
    	} catch (Exception e) {
    		log.info(e);    		
    		addErrorMessage(e.getMessage(), null);
		} 
    }
    /**
     * A fin de implementar del CRUP se realiza un eleminado físico
     * @param ticket
     */
    public void eliminarTicket(Ticket ticket){
    	log.info(":eliminarTicket...");
    	try{
	    	ticketBO.eliminarTicket(ticket);
	    	addInfoMessage(obtenerMensajeRecurso("ticket.eliminar.ticket.exito"), null);
	    	
	    	this.buscarTicket(false);
	    } catch (Exception e) {
			log.info(e);    		
			addErrorMessage(e.getMessage(), null);
		}
    }
    /**
     * Presenta la pantalla para administrar los Detalles del ticket
     * @param ticket a visualizar ticketDetalles
     */
    public void administrarTicketDetalles(Ticket ticket){
    	log.info(":administrarTicketDetalles...");
    	try {
        	getTicketDM().setTicketEdicion(ticket);
        	getTicketDM().setTicketDetalleList(this.obtenerListaTicketDetalle());
        	
        	getTicketDM().setPantallaActual("ticketDetalles");
		} catch (Exception e) {
			log.info(e);
		}
    }
    /**
     * Nuevo ticketDetalle del ticket
     */
    public void nuevoTicketDetalle(){
    	log.info(":nuevoTicketDetalle...");
    	try {
	    	//Inicializa los valores necesarios para la edición
    		getTicketDM().setTicketDetalleEdicion(new TicketDetalle());
    		getTicketDM().getTicketDetalleEdicion().setEstado(EstadosEnum.ACTIVO.getValue());
    		   		
    		getTicketDM().setEdicion(true);
		} catch (Exception e) {
			log.info(e);
		}
    }
	/**
	 * Edita ticketDetalle del ticket
	 * @param ticketDetalle a editar
	 */
    public void editarTicketDetalle(TicketDetalle ticketDetalle){
    	log.info(":editarTicketDetalle...");
    	try {
	    	//Inicializa los valores necesarios para la edición
    		getTicketDM().setTicketDetalleEdicion(ticketDetalle);
    		
    		getTicketDM().setEdicion(true);
		} catch (Exception e) {
			log.info(e);
		}
    }
    /**
     * Guarda la información del ticketDetalle del ticket
     */
    public void guardarTicketDetalle(){
    	try{
    		log.info(":guardarTicketDetalle..." + getTicketDM().getTicketDetalleEdicion().getId());
    		getTicketDM().getTicketDetalleEdicion().setTicket(getTicketDM().getTicketEdicion());
    		
			ticketBO.guardarTicketDetalle(getTicketDM().getTicketDetalleEdicion());

			addInfoMessage(MessageFormat.format(obtenerMensajeRecurso("ticket.guardar.ticket.ticketDetalle.exito"), 
					getTicketDM().getTicketDetalleEdicion().getNota()), null);

			getTicketDM().setTicketDetalleEdicion(null);
			getTicketDM().setEdicion(false);

			getTicketDM().setTicketDetalleList(this.obtenerListaTicketDetalle());
    		
    	} catch (Exception e) {
    		log.info(e);
    		addErrorMessage(e.getMessage(), null);
    	}
    }
    /**
     * Obtiene los ticketDetalles del ticket seleccionado
     * @throws Exception
     */
	private List<TicketDetalle> obtenerListaTicketDetalle(){
		try {
			TicketDetalle ticketDetalle = new TicketDetalle();
			ticketDetalle.setTicket(getTicketDM().getTicketEdicion());			
		
			return ticketBO.obtenerTicketDetalles(ticketDetalle);
		} catch (Exception e) {
			log.info(e);
			return null;
		}
	}
    /**
     * Regresa a la pantalla principal
     */
    public void regresarPrincipal(){
    	log.info(":regresarPrincipal...");
		getTicketDM().setTicketEdicion(null);
		getTicketDM().setTicketDetalleList(null);
		getTicketDM().setPantallaActual("principal");
		
		this.buscarTicket(false);
    }
    
    public void verInfoCliente(Ticket ticket){
    	log.info(":verInfoCliente...");
		getTicketDM().setTicketEdicion(ticket);
		getTicketDM().setConfirma(true);
    }
    public void cerrarInfoCliente(){
    	log.info(":cerrarInfoCliente...");
		getTicketDM().setTicketEdicion(null);
		getTicketDM().setConfirma(false);
    }
    
    
    /* 
     * Getters y Setters
     */
	public TicketDM getTicketDM() {
		return ticketDM;
	}
	public void setTicketDM(TicketDM ticketDM) {
		this.ticketDM = ticketDM;
	}
}
