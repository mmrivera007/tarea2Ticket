package com.puntonet.ticket.core.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_catalogo database table.
 * 
 */
@Entity
@Table(name="tipo_catalogo")
@NamedQuery(name="TipoCatalogo.findAll", query="SELECT t FROM TipoCatalogo t")
public class TipoCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_CATALOGO_ID_GENERATOR", sequenceName="seq_tipo_catalogo", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_CATALOGO_ID_GENERATOR")
	private Integer id;

	private String descripcion;

	private String nemonico;

	private String nombre;

	//bi-directional many-to-one association to Catalogo
	@OneToMany(mappedBy="tipoCatalogo", fetch=FetchType.LAZY)
	private List<Catalogo> catalogos;

	public TipoCatalogo() {
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

	public String getNemonico() {
		return this.nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Catalogo> getCatalogos() {
		return this.catalogos;
	}

	public void setCatalogos(List<Catalogo> catalogos) {
		this.catalogos = catalogos;
	}

}