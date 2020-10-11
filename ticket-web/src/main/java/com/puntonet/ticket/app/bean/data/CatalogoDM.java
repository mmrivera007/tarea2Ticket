package com.puntonet.ticket.app.bean.data;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.puntonet.ticket.core.persistencia.entidad.Catalogo;
import com.puntonet.ticket.core.persistencia.entidad.TipoCatalogo;

/**
 * Clase para el manejo de los datos de los catálogos
 * 
 * @author  mmrivera
 * @version 1.0
 */
@ManagedBean(name="catalogoDM")
@ViewScoped
public class CatalogoDM  implements Serializable{

	private static final long serialVersionUID = -2909030587461033087L;
    //Variables para administrar un tipo catalogo y sus detalles
    private TipoCatalogo tipoCatalogoEdicion;
    private Catalogo catalogoEdicion;
  	private boolean edicion = false;
    
  	//Posibles valores: principal, registro, detalle
  	private String pantallaActual;
  	
	//Variables usadas en la búsqueda de la pantalla
  	private Catalogo catalogoBusqueda;
  	private List<Catalogo> catalogosList;
  	private boolean consultaLista = false;
  	
  	//Lista de tipos catálogos para administrar
  	private List<TipoCatalogo> tipoCatalogoList;
  	private List<Catalogo> catalogoPadreList;

	public TipoCatalogo getTipoCatalogoEdicion() {
		return tipoCatalogoEdicion;
	}

	public void setTipoCatalogoEdicion(TipoCatalogo tipoCatalogoEdicion) {
		this.tipoCatalogoEdicion = tipoCatalogoEdicion;
	}

	public Catalogo getCatalogoEdicion() {
		return catalogoEdicion;
	}

	public void setCatalogoEdicion(Catalogo catalogoEdicion) {
		this.catalogoEdicion = catalogoEdicion;
	}

	public boolean getEdicion() {
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

	public Catalogo getCatalogoBusqueda() {
		return catalogoBusqueda;
	}

	public void setCatalogoBusqueda(Catalogo catalogoBusqueda) {
		this.catalogoBusqueda = catalogoBusqueda;
	}

	public boolean getConsultaLista() {
		return consultaLista;
	}

	public void setConsultaLista(boolean consultaLista) {
		this.consultaLista = consultaLista;
	}

	public List<TipoCatalogo> getTipoCatalogoList() {
		return tipoCatalogoList;
	}

	public void setTipoCatalogoList(List<TipoCatalogo> tipoCatalogoList) {
		this.tipoCatalogoList = tipoCatalogoList;
	}

	public List<Catalogo> getCatalogoPadreList() {
		return catalogoPadreList;
	}

	public void setCatalogoPadreList(List<Catalogo> catalogoPadreList) {
		this.catalogoPadreList = catalogoPadreList;
	}

	public List<Catalogo> getCatalogosList() {
		return catalogosList;
	}

	public void setCatalogosList(List<Catalogo> catalogosList) {
		this.catalogosList = catalogosList;
	}
	
}
