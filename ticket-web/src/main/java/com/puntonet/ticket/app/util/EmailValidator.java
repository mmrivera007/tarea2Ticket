package com.puntonet.ticket.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * <b> Validador para el formato de un email. </b>
 *  
 * @author mmrivera
 * @version 1.0
 */

@FacesValidator("emailValidator")
public class EmailValidator implements Validator{
	//[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}
	Pattern pattern = Pattern.compile("[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]");
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String identificacion = value.toString();
		if(identificacion != null && !identificacion.isEmpty()){
			Matcher matcher = pattern.matcher(identificacion);
			if (!matcher.matches()) {
				FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación de Email fallida", "Email inválido");
				throw new ValidatorException(fmsg);
			}
		}
	}
} 
