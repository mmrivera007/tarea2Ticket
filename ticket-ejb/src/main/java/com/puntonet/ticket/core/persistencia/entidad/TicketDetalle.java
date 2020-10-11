package com.puntonet.ticket.core.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ticket_detalle database table.
 * 
 */
@Entity
@Table(name="ticket_detalle")
@NamedQuery(name="TicketDetalle.findAll", query="SELECT t FROM TicketDetalle t")
public class TicketDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TICKET_DETALLE_ID_GENERATOR", sequenceName="seq_ticket_detalle", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TICKET_DETALLE_ID_GENERATOR")
	private Integer id;

	private String estado;

	private String nota;

	//bi-directional many-to-one association to Ticket
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_ticket")
	private Ticket ticket;

	public TicketDetalle() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNota() {
		return this.nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}