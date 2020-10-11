package com.puntonet.ticket.core.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ticket database table.
 * 
 */
@Entity
@Table(name="ticket")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TICKET_ID_GENERATOR", sequenceName="seq_ticket", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TICKET_ID_GENERATOR")
	private Integer id;

	private String descripcion;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	private Date fechaFin;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_estado")
	private Catalogo catalogoEstado;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_prioridad")
	private Catalogo catalogoPrioridad;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Oferta
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_oferta")
	private Oferta oferta;

	//bi-directional many-to-one association to Tecnico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tecnico")
	private Tecnico tecnico;

	//bi-directional many-to-one association to TicketDetalle
	@OneToMany(mappedBy="ticket", fetch=FetchType.LAZY)
	private List<TicketDetalle> ticketDetalles;

	
	@Transient
	private Integer totalNotas;
	
	public Ticket() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Catalogo getCatalogoEstado() {
		return catalogoEstado;
	}

	public void setCatalogoEstado(Catalogo catalogoEstado) {
		this.catalogoEstado = catalogoEstado;
	}

	public Catalogo getCatalogoPrioridad() {
		return catalogoPrioridad;
	}

	public void setCatalogoPrioridad(Catalogo catalogoPrioridad) {
		this.catalogoPrioridad = catalogoPrioridad;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Oferta getOferta() {
		return this.oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public Tecnico getTecnico() {
		return this.tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public List<TicketDetalle> getTicketDetalles() {
		return this.ticketDetalles;
	}

	public void setTicketDetalles(List<TicketDetalle> ticketDetalles) {
		this.ticketDetalles = ticketDetalles;
	}

	public Integer getTotalNotas() {
		return totalNotas;
	}

	public void setTotalNotas(Integer totalNotas) {
		this.totalNotas = totalNotas;
	}

}