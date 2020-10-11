package com.puntonet.ticket.core.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tecnico database table.
 * 
 */
@Entity
@Table(name="tecnico")
@NamedQuery(name="Tecnico.findAll", query="SELECT t FROM Tecnico t")
public class Tecnico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TECNICO_ID_GENERATOR", sequenceName="seq_tecnico", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TECNICO_ID_GENERATOR")
	private Integer id;

	private String celular;

	private String email;

	private String estado;

	private String nombre;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="tecnico", fetch=FetchType.LAZY)
	private List<Ticket> tickets;

	public Tecnico() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}