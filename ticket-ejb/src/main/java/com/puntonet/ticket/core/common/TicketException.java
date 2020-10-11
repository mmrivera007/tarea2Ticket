package com.puntonet.ticket.core.common;

import javax.ejb.ApplicationException;

/**
 * Class TicketException.
 *
 * @author mmrivera
 * @version 1.0
 */
@ApplicationException(rollback = true)
public class TicketException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Instancia un nuevo entidad.
	 */
	public TicketException() {
		super();
	}

	/**
	 * Instancia un nuevo entidad.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public TicketException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * Instancia un nuevo entidad.
	 *
	 * @param arg0 the arg0
	 */
	public TicketException(final String arg0) {
		super(arg0);
	}

	/**
	 * Instancia un nuevo entidad no grabada exception.
	 *
	 * @param arg0 the arg0
	 */
	public TicketException(final Throwable arg0) {
		super(arg0);
	}
}
