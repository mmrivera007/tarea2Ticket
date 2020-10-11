package com.puntonet.ticket.core.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the catalogo database table.
 * 
 */
@Entity
@Table(name="catalogo")
@NamedQuery(name="Catalogo.findAll", query="SELECT c FROM Catalogo c")
public class Catalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CATALOGO_ID_GENERATOR", sequenceName="seq_catalogo", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATALOGO_ID_GENERATOR")
	private Integer id;

	private String estado;

	private String nombre;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_catalogo_padre")
	private Catalogo catalogoPadre;

	//bi-directional many-to-one association to TipoCatalogo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo_catalogo")
	private TipoCatalogo tipoCatalogo;
	
	//bi-directional many-to-one association to Catalogo
	@OneToMany(mappedBy="catalogoPadre", fetch=FetchType.LAZY)
	private List<Catalogo> catalogos;



	public Catalogo() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Catalogo getCatalogoPadre() {
		return catalogoPadre;
	}

	public void setCatalogoPadre(Catalogo catalogoPadre) {
		this.catalogoPadre = catalogoPadre;
	}

	public List<Catalogo> getCatalogos() {
		return this.catalogos;
	}

	public void setCatalogos(List<Catalogo> catalogos) {
		this.catalogos = catalogos;
	}

	public TipoCatalogo getTipoCatalogo() {
		return this.tipoCatalogo;
	}

	public void setTipoCatalogo(TipoCatalogo tipoCatalogo) {
		this.tipoCatalogo = tipoCatalogo;
	}

}