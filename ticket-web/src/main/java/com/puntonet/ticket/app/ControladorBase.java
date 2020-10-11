package com.puntonet.ticket.app;

import java.io.File;
import java.io.Serializable;
import java.util.Iterator;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.activation.MimetypesFileTypeMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase ...
 * 
 * @author mmrivera
 * @version 2.0
 */

public class ControladorBase implements Serializable {
	
	private static final long serialVersionUID = 7943283135425976766L;
	
	protected static Log log = LogFactory.getLog(ControladorBase.class);

	private static final String NOMBRE_RECURSO_MENSAJES = "messages";

	protected ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	protected static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Obtiene el archivo MessageResources.properties, donde se encuentran los
	 * mensajes del sistema
	 *
	 * @return MessageResources.properties
	 */
	public static ResourceBundle getResourceBundle() {
		return getFacesContext().getApplication().getResourceBundle(
				getFacesContext(), NOMBRE_RECURSO_MENSAJES);
	}

	/**
	 * Permite añadir un mensaje de tipo ERROR para despliegue en pantalla
	 * 
	 * @param summary
	 *            valor a desplegar
	 * @param detail
	 *            detalle del valor a desplegar
	 */
	protected static void addErrorMessage(final String summary, final String detail) {
		getFacesContext().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
	}

	/**
	 * Permite añadir un mensaje de tipo INFORMACION para despliegue en pantalla
	 * 
	 * @param summary
	 *            valor a desplegar
	 * @param detail
	 *            detalle del valor a desplegar
	 */
	protected static void addInfoMessage(final String summary, final String detail) {
		getFacesContext().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}

	/**
	 * Permite añadir un mensaje de tipo INFORMACION para despliegue en pantalla
	 * 
	 * @param summary
	 *            valor a desplegar
	 * @param detail
	 *            detalle del valor a desplegar
	 */
	protected static void addWarnMessage(final String summary, final String detail) {
		getFacesContext().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
	}

	/**
	 * Verifica si existen parámetros de sessión
	 * 
	 * @param key
	 * @return
	 */
	protected Boolean existeParametroSession(final String key) {
		return getExternalContext().getSessionMap().containsKey(key);
	}

	/**
	 * Retorna el parámetro de sessión según la clave enviada
	 * 
	 * @param key
	 * @return Object
	 */
	protected Object obtenerParametroSession(final String key) {
		return getExternalContext().getSessionMap().get(key);
	}
	
	/**
	 * Verifica si existen mensajes de error
	 * 
	 * @return boolean
	 */
	public boolean isExistenErrores() {
		if (FacesContext.getCurrentInstance().getMessages() != null) {
			for (Iterator<FacesMessage> iterator = FacesContext.getCurrentInstance().getMessages(); iterator.hasNext();) {
				FacesMessage fm = iterator.next();
				if (fm.getSeverity().equals(FacesMessage.SEVERITY_ERROR)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Obtiene el mensaje desde el archivo de properties.
	 *
	 * @param mensaje
	 *            Nombre de mensaje
	 * @return Valor de mensaje
	 * @throws MissingResourceException
	 *             : Excepcion lanzada cuando falta el recurso
	 */
	public static String obtenerMensajeRecurso(final String mensaje){
		try {
			return getResourceBundle().getString(mensaje);
		} catch (MissingResourceException e) {
			log.info(e);
			return mensaje;
		}
	}

	/**
	 * Cierra la sesión del usuario
	 * 
	 * @return String
	 */
	public String cerrarSesion() {
		try {
			log.info("cerrarSesion...");
			getExternalContext().redirect(getExternalContext().getRequestContextPath()+ "/login.jsf");
			getExternalContext().getSessionMap().clear();
			HttpSession sesion = (HttpSession) getExternalContext().getSession(false);
			sesion.invalidate();

		} catch (Exception ex) {
			log.info(ex);
			log.info("cerrando sesion error:" + ex.getCause());
		}
		return null;
	}

	/**
	 * Descarga del archivo
	 * @param file	Archivo a descargar
	 */
	public void descargaArchivo(final File file) {
		try {
			String mimeType = obtenerMimeType(file);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("file", file);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("archivoNombre", file.getName());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("archivoTipo", mimeType);
			log.info(":mimeType:" + mimeType);
		} catch (Exception e) {
			log.info(e);
		}
	}

	/**
	 * Limpia los datos enviados al servlet
	 */
	public void limpiarDatosDescargadorServlet() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("file");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("archivoNombre");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("archivoTipo");
	}
	/**
	 * Setear la información para la descarga del archivo mediante el Servlet
	 * @param nombreArchivo Nombre del archivo
	 */
	public void setearDatosDescargaServlet(String nombreArchivo) {
		log.info(":setearDatosDescargaServlet..." + nombreArchivo);
		File temporal = new File(nombreArchivo);
		this.descargaArchivo(temporal);
	}
	/**
	 * Obtiene el tipo de archivo
	 * @param file Archivo a procesar
	 * @return String
	 */
	public String obtenerMimeType(final File file) {
		log.info(":obtenerMimeType..." + file);
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		String mimeType = mimeTypesMap.getContentType(file);
		if (mimeType == null || mimeType.isEmpty()) {
			mimeType = "application/octet-stream";
		} 
		else if (mimeType.compareTo("xls") == 0) {
			mimeType = "application/vnd.ms-excel";				
		}
		else if(mimeType.compareTo("xlsx") == 0){
			mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		}
		else if(mimeType.compareTo("pdf") == 0){
			mimeType = "application/pdf";
		}
		else if (mimeType.compareTo("txt") == 0) {
			mimeType = "text/plain";
		}
		else if (mimeType.compareTo("doc") == 0) {
			mimeType = "application/msword";
		}
		else if (mimeType.compareTo("docx") == 0) {
			mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		}			
		else {
			mimeType = "application/force-download";
		}
		return mimeType;
	}

	
}
