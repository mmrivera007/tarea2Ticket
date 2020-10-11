function validarLargo(e,field,max){
	if(field.value.length>max){
	key = e.keyCode ? e.keyCode : e.which;
			if (key == 192){
				return false;
				}
				// alert(key);
	// Teclas especiales
			// Flechas direccionales 39 derecha 37 izq
			// 8 retroceso, copiar 99, pegar 118, 46 suprimir, 36 inicio, 35 fin
			if (key == 39 || key == 37 || key == 8 
					|| key == 35 || key == 36 || key == 38 || key == 40){

					if (e.shiftKey && (key == 36 || key == 38)){
					return false
					}
					
				if (e.altKey){
					return false
					}
				return true
				}
						
				// tab
			if (key == 9)
				return true

				// backspace
			if (key == 8)
				return true

		return false;
		}
	return true
}

function mostrarError(){
	var element = document.getElementById('errorTamanioArchivo');
	element.style.display = 'none';
	
	var element1 = document.getElementById('errorTipoArchivo');
	element1.style.display = 'none';
	
	var element1 = document.getElementById('errorUrlArchivo');
	element1.style.display = 'block';
	
}

function mostrarCargaCorrecta(){
	var element = document.getElementById('errorTamanioArchivo');
	element.style.display = 'none';
	
	var element1 = document.getElementById('errorTipoArchivo');
	element1.style.display = 'none';
	
}
function mostrarErrorTamanio(){
		var element = document.getElementById('errorTipoArchivo');
		element.style.display = 'none';
		var element = document.getElementById('errorTamanioArchivo');
		element.style.display = 'block';
	
}

function mostrarErrorTipo(){
	var element = document.getElementById('errorTamanioArchivo');
	element.style.display = 'none';
	var element = document.getElementById('errorTipoArchivo');
	element.style.display = 'block';
}

function formatearNumeroCienLimpiarComplemento(obj,decimal,idTxtDetalle,idTxtComplemento,idtxtDetalleComplemento){
	if(obj.value>100){
		obj.value=100;
	}
	
	if(obj.value==100){
		var txtDetalle = document.getElementById(idTxtDetalle);
		var txtComplemento = document.getElementById(idTxtComplemento);
		var txtDetalleComplemento = document.getElementById(idtxtDetalleComplemento);
		
		if(txtDetalle.disabled){
			txtDetalle.disabled=false;
		}
		txtComplemento.value='0.00';
		txtDetalleComplemento.value='NO APLICA';
		txtDetalleComplemento.disabled=true;
	}
	
	formatValueDecimalPositions(obj, decimal);
	
}

function formatValueDecimalPositions(obj, decimal){
	
	
	var value = obj.value;
	value = value.replace(",", "");
	var anynum;
	var sign = "";
	try {
		if (value == "") {
			anynum = null;
		} else {
			anynum = parseFloat(value);
		}
		if (anynum < 0) {
			sign = "-";
		}
	} catch (error) {
		anynum = null;
	}
	if (anynum != null) {
		var divider = 1;
		if (decimal) {
			divider = 100;
		}
		var workNum = Math.abs((Math.round(anynum * divider) / divider));
		var workStr = "" + workNum;
		if (workStr.indexOf(".") == -1) {
			workStr += ".";
		}
		var dStr = workStr.substr(0, workStr.indexOf("."));
		var dNum = dStr - 0;
		var pStr = workStr.substr(workStr.indexOf("."));
		if (decimal) {
			while (pStr.length - 1 < 2) {
				pStr += "0";
			}
		}
		if (pStr == ',')
			pStr = '';
		// --- Adds a comma in the thousands place.
		if (dNum >= 1000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000)) + ","
					+ dStr.substring(dLen - 3, dLen);
		}
		// -- Adds a comma in the millions place.

		if (dNum >= 1000000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000000)) + ","
					+ dStr.substring(dLen - 7, dLen);
		}

		if (dNum >= 1000000000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000000000)) + ","
					+ dStr.substring(dLen - 11, dLen);
		}

		if (dNum >= 1000000000000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000000000000)) + ","
					+ dStr.substring(dLen - 15, dLen);
		}

		var retval = dStr + pStr;

		// -- Put numbers in parentheses if negative.
		// if (anynum < 0) {
		// retval = "(" + retval + ")";
		// }
		// You could include a dollar sign in the return value.
		// retval = "$"+retval
		if (!decimal) {
			retval = retval.replace(".", "");
		}
		obj.value = sign + retval;
	}
	
}

function formatNumberCienDos(obj, decimal, validar) {
		var idTransfTec="proyectoNuevoForm:transfTecDecorate:transfTec";
		var textAreaTransfTec = document.getElementById(idTransfTec);
		textAreaTransfTec.disabled=true;
		
		if(obj.value>100){
			obj.value=100;
		}
		
		if(obj.value==100){
			id="proyectoNuevoForm:consumoNacionalDecorate:consumoNacional";
			idDetalle="proyectoNuevoForm:consumoDetalleDecorate:consumoDetalle";
			idDet="proyectoNuevoForm:consumoDetalleImportadoDecorate:consumoDetalle";
			var textArea = document.getElementById(id);
			textArea.value='0.00';
			textArea = document.getElementById(idDet);
			textArea.disabled=false;
			textArea = document.getElementById(idDetalle);
			textArea.value='NO APLICA';
			textArea.disabled=true;
		} else if(validar) {
			id="proyectoNuevoForm:consumoNacionalDecorate:consumoNacional";
			idDetalle="proyectoNuevoForm:consumoDetalleDecorate:consumoDetalle";
			idDet="proyectoNuevoForm:consumoDetalleImportadoDecorate:consumoDetalle";
			var textArea = document.getElementById(id);
			textArea.value=100 - obj.value;
			formatNumberCien(textArea,2,false);
			textArea = document.getElementById(idDetalle);
			if (textArea.value=='NO APLICA') {
				textArea.value= '';
			}
			textArea.disabled=false;
		}
		
		if(obj.value>0){
			textAreaTransfTec.disabled=false;
		} else {
			textAreaTransfTec.value="";
		}
	formatearNumero(obj, decimal);
}


function formatNumberCien(obj, decimal, validar) {
		var idTransfTec="proyectoNuevoForm:transfTecDecorate:transfTec";
		var textAreaTransfTec = document.getElementById(idTransfTec);
	
		if(obj.value>100){
			obj.value=100;
		}
		
		if(obj.value==100){
			id="proyectoNuevoForm:consumoImportadoDecorate:consumoImportado";
			idDetalle="proyectoNuevoForm:consumoDetalleDecorate:consumoDetalle";
			idDet="proyectoNuevoForm:consumoDetalleImportadoDecorate:consumoDetalle";
			var textArea = document.getElementById(id);
			textArea.value='0.00';
			textArea = document.getElementById(idDetalle);
			textArea.disabled=false;
			textArea = document.getElementById(idDet);
			textArea.value='NO APLICA';
			textArea.disabled=true;
			textAreaTransfTec.disabled=true;
			textAreaTransfTec.value="";
		} else if(validar) {
			id="proyectoNuevoForm:consumoImportadoDecorate:consumoImportado";
			idDetalle="proyectoNuevoForm:consumoDetalleDecorate:consumoDetalle";
			idDet="proyectoNuevoForm:consumoDetalleImportadoDecorate:consumoDetalle";
			var textArea = document.getElementById(id);
			textArea.value= 100 - obj.value;
			formatNumberCienDos(textArea,2,false);
			textArea = document.getElementById(idDet);
			if (textArea.value=='NO APLICA') {
				textArea.value= '';
			}
			textArea.disabled=false;
		}
	formatearNumero(obj, decimal);
}

function formatearNumero(obj, decimal) {
		
	var value = obj.value;
	value = value.replace(",", "");
	var anynum;
	var sign = "";
	try {
		if (value == "") {
			anynum = null;
		} else {
			anynum = parseFloat(value);
		}
		if (anynum < 0) {
			sign = "-";
		}
	} catch (error) {
		anynum = null;
	}
	if (anynum != null) {
		var divider = 1;
		if (decimal) {
			divider = 100;
		}
		var workNum = Math.abs((Math.round(anynum * divider) / divider));
		var workStr = "" + workNum;
		if (workStr.indexOf(".") == -1) {
			workStr += ".";
		}
		var dStr = workStr.substr(0, workStr.indexOf("."));
		var dNum = dStr - 0;
		var pStr = workStr.substr(workStr.indexOf("."));
		if (decimal) {
			while (pStr.length - 1 < 2) {
				pStr += "0";
			}
		}
		if (pStr == ',')
			pStr = '';
		// --- Adds a comma in the thousands place.
		if (dNum >= 1000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000)) + ","
					+ dStr.substring(dLen - 3, dLen);
		}
		// -- Adds a comma in the millions place.

		if (dNum >= 1000000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000000)) + ","
					+ dStr.substring(dLen - 7, dLen);
		}

		if (dNum >= 1000000000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000000000)) + ","
					+ dStr.substring(dLen - 11, dLen);
		}

		if (dNum >= 1000000000000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000000000000)) + ","
					+ dStr.substring(dLen - 15, dLen);
		}

		var retval = dStr + pStr;

		if (!decimal) {
			retval = retval.replace(".", "");
		}
		obj.value = sign + retval;
	}
}

function validarNumeroTresEnterosDosDec(e,field){

	key = e.keyCode ? e.keyCode : e.which

			if (key == 192){
				return false
				}
	
			// Teclas especiales
			// Flechas direccionales 39 derecha 37 izq
			// 8 retroceso, copiar 99, pegar 118, 46 suprimir, 36 inicio, 35 fin
			if (key == 39 || key == 37 || key == 8 
					|| key == 35 || key == 36){

				if (e.shiftKey){
					return false
					}
				
				if (e.altKey){
					return false
					}
				return true
				}
						
				// tab
			if (key == 9)
				return true

				// backspace
			if (key == 8)
				return true
				// 0-9
			if (key > 47 && key < 58) {
				if (field.value == "")
					return true

				if(field.value.indexOf(".")!=-1){
					var split = field.value.split(".");
				
					if(split[1].length>0){
						regexpDec = /^[0-9][0-9]$/
						// return !(regexpDec.test(split[1]));
					}
				}
				
					regexp = /^[0-9][0-9][0-9]$/
					resp=!(regexp.test(field.value))
					
				return resp;
			}
			// .
			if (key == 46) {
				var split = field.value.split(".");
				if(split.length > 1){
					return false;
				}
				if (field.value == "")
					return false
				return true
			}

			// other key
			return false	
}


function validarNumeroDosDec(e,field){

	key = e.keyCode ? e.keyCode : e.which
			
			if (key == 192){
				return false
				}
	
			// Teclas especiales
			// Flechas direccionales 39 derecha 37 izq
			// 8 retroceso, copiar 99, pegar 118, 46 suprimir, 36 inicio, 35 fin
			if (key == 39 || key == 37 || key == 8 
					|| key == 35 || key == 36){

				if (e.shiftKey){
					return false
					}
				
				if (e.altKey){
					return false
					}
				return true
				}
						
				// tab
			if (key == 9)
				return true

				// backspace
			if (key == 8)
				return true
				// 0-9
			if (key > 47 && key < 58) {
				if (field.value == "")
					return true
				

				if(field.value.indexOf(".")!=-1){
					var split = field.value.split(".");
				
					if(split[1].length>0){
						regexp = /^[0-9][0-9]$/
						// return !(regexp.test(split[1]));
					}
				}
				regexp = /^[0-9][0-9]$/
				respuest=!(regexp.test(field.value));
				return respuest;


			}
			// .
			if (key == 46) {
				var split = field.value.split(".");
				if(split.length > 1){
					return false;
				}
				if (field.value == "")
					return false
				return true
			}

			// other key
			return false	
}

function validarNumeroTresDec(e,field){

	key = e.keyCode ? e.keyCode : e.which

			if (key == 192){
				return false
				}
	
			// Teclas especiales
			// Flechas direccionales 39 derecha 37 izq
			// 8 retroceso, copiar 99, pegar 118, 46 suprimir, 36 inicio, 35 fin
			if (key == 39 || key == 37 || key == 8 
					|| key == 35 || key == 36){

				if (e.shiftKey){
					return false
					}
				
				if (e.altKey){
					return false
					}
				return true
				}
						
				// tab
			if (key == 9)
				return true

				// backspace
			if (key == 8)
				return true
				// 0-9
			if (key > 47 && key < 58) {
				if (field.value == "")
					return true
				regexp = /^[0-9][0-9][0-9]$/

				if(field.value.indexOf(".")!=-1){
					var split = field.value.split(".");
				
					if(split[1].length>0){
						return !(regexp.test(split[1]));
					}
				}
				return !(regexp.test(field.value));
			}
			// .
			if (key == 46) {
				var split = field.value.split(".");
				if(split.length > 1){
					return false;
				}
				if (field.value == "")
					return false
				return true
			}

			// other key
			return false
}

function validarNumero(e, field) {
	field.value=field.value.replace(/\,/g, "");
	key = e.keyCode ? e.keyCode : e.which
			
			
	// Teclas especiales
	// Flechas direccionales 39 derecha, 37 izquierda
	// 8 retroceso, copiar 99, pegar 118, 46 suprimir, 36 inicio, 35 fin
			if (key == 39 || key == 37 || key == 8 
					|| key == 35 || key == 36){

				if (e.shiftKey){
					return false
					}
				
				if (e.altKey){
					return false
					}
				return true
				}

		// tab
	if (key == 9)
		return true

		// backspace
	if (key == 8)
		return true
		// 0-9
	
	if (key > 47 && key < 58) {
		if (field.value == "")
			return true
		

		if(field.value.indexOf(".")!=-1){
			var split = field.value.split(".");
			regexp = /^[0-9][0-9]$/
		
			if(split[1].length>0){
				// return !(regexp.test(split[1]));
			}
		}
		return true;
	}
	// .
	if (key == 46) {
		var split = field.value.split(".");
		if(split.length > 1){
			return false;
		}
		if (field.value == "")
			return false
		regexp = /^[0-9]+$/
		var value = field.value.replace(/\,/g, "");
		return regexp.test(value)
	}

	// other key
	return false
}

// Verifica que en un campo solo se ingrese numeros
function validarSoloNumero(e, field) {

	key = e.keyCode ? e.keyCode : e.which

	// Teclas especiales
	// Flechas direccionales 39 derecha, 37 izquierda
	// 8 retroceso, copiar 99, pegar 118, 46 suprimir, 36 inicio, 35 fin

	if (key == 39 || key == 37 || key == 8 || key == 99 || key == 118
			|| key == 46 || key == 35 || key == 36)
		return true

		// tab
	if (key == 9)
		return true

		// backspace
	if (key == 8)
		return true
		// 0-9
	if (key > 47 && key < 58) {
		if (field.value == "")
			return true
		return true;
	}

	// other key
	return false
}

//Verifica que en un campo solo se ingrese numeros para el ruc
function validarSoloNumeroRuc(e, field) {

	key = e.keyCode ? e.keyCode : e.which

	// Teclas especiales
	// Flechas direccionales 39 derecha, 37 izquierda
	// 8 retroceso, copiar 99, pegar 118, 46 suprimir, 36 inicio, 35 fin

	if (key == 39 || key == 37 || key == 8
			|| key == 46 || key == 35 || key == 36)
		return true

		// tab
	if (key == 9)
		return true

		// backspace
	if (key == 8)
		return true
		// 0-9
	if (key > 47 && key < 58) {
		if (field.value == "")
			return true
		return true;
	}

	// other key
	return false
}

function stripFormat(obj, decimal) {
	obj.value = obj.value.replace(",", "") ;

	if (obj.value) {
		var value = trimString(obj.value);
		if (value != null) {
			value = value.replace(/\./g, "");
			value = value.replace(/\,/g, "");
			value = value.replace(/\(/g, "");
			value = value.replace(/\)/g, "");
			var numValue = parseInt(value, 10);
			if (decimal) {
				numValue = numValue / 100;
			}
			obj.value = numValue;
		}
	}
}

// decimal - the number of decimals after the digit from 0 to 3

// -- Returns the passed number as a string in the xxx,xxx.xx format.

function formatNumber(obj, decimal) {
	var value = obj.value;
	value = value.replace(/\,/g, "");
	var anynum;
	var sign = "";
	try {
		if (value == "") {
			anynum = null;
		} else {
			anynum = parseFloat(value);
		}
		if (anynum < 0) {
			sign = "-";0
		}
	} catch (error) {
		anynum = null;
	}
	if (anynum != null) {
		var divider = 1;
		if (decimal) {
			divider = 100;
		}
		var workNum = Math.abs((Math.round(anynum * divider) / divider));
		var workStr = "" + workNum;
		if (workStr.indexOf(".") == -1) {
			workStr += ".";
		}
		var dStr = workStr.substr(0, workStr.indexOf("."));
		var dNum = dStr - 0;
		var pStr = workStr.substr(workStr.indexOf("."));
		if (decimal) {
			while (pStr.length - 1 < 2) {
				pStr += "0";
			}
		}
		if (pStr == ',')
			pStr = '';
		// --- Adds a comma in the thousands place.
		if (dNum >= 1000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000)) + ","
					+ dStr.substring(dLen - 3, dLen);
		}
		// -- Adds a comma in the millions place.

		if (dNum >= 1000000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000000)) + ","
					+ dStr.substring(dLen - 7, dLen);
		}

		if (dNum >= 1000000000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000000000)) + ","
					+ dStr.substring(dLen - 11, dLen);
		}
		            
		if (dNum >= 1000000000000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000000000000)) + ","
					+ dStr.substring(dLen - 15, dLen);
		}

		if (dNum >= 1000000000000000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000000000000000)) + ","
					+ dStr.substring(dLen - 19, dLen);
		}
		
		var retval = dStr + pStr;

		// -- Put numbers in parentheses if negative.
		// if (anynum < 0) {
		// retval = "(" + retval + ")";
		// }
		// You could include a dollar sign in the return value.
		// retval = "$"+retval
		if (!decimal) {
			retval = retval.replace(".", "");
		}
		obj.value = sign + retval;
	}
}

function aceptarSoloNumeros(evt) {
	var key = null;
	if (esIE()) {
		key = evt.keyCode;
	} else {
		key = evt.which;
		
	}
	return (key == 42 || key == 45 || key == 95 || key == 9 || key == 0 || key == 190
			|| key == 127 || key == 8 || key == 11 || (key >= 48 && key <= 57));
}

function aceptarNumeros(e, field) {
	key = e.keyCode ? e.keyCode : e.which
		    // Teclas especiales
		    // Flechas direccionales 39 derecha, 37 izquierda
		    // 8 retroceso, copiar 99, pegar 118, 46 suprimir, 36 inicio, 35 fin
		    if (key == 39 || key == 37 || key == 8
		                    || key == 35 || key == 36)
		            return true

		            // tab
		    if (key == 9)
		            return true

		            // backspace
		    if (key == 8)
		            return true
		            // 0-9
		    if (key > 47 && key < 58) {
		            if (field.value == "")
		                    return true
		            // regexp = /[.]{1}[0-9]{2}$/
		            return true;// !(regexp.test(field.value))
		    }
		    // .
		    if (key == 46) {
		            var split = field.value.split(".");
		            if(split.length > 1){
		                    return false;
		            }
		            if (field.value == "")
		                    return false
		            regexp = /^[0-9]+$/
		            return regexp.test(field.value)
		    }

		    // other key
		    return false
		}


		function stripFormat(obj, decimal) {
		    if (obj.value) {

		            var value = trimString(obj.value);
		            if (value != null) {
		                    value = value.replace(/\./g, "");
		                    value = value.replace(/\,/g, "");
		                    value = value.replace(/\(/g, "");
		                    value = value.replace(/\)/g, "");
		                    var numValue = parseInt(value, 10);
		                    if (decimal) {
		                            numValue = numValue / 10000;
		                    }
		                    obj.value = numValue;
		            }
		    }
		}

/*
		function formatNumber(obj, decimal) {

		    var value = obj.value;
		    value = value.replace(",", "");

		    var anynum;
		    var sign = "";
		    try {
		            if (value == "") {
		                    anynum = null;
		            } else {
		                    anynum = parseFloat(value);
		            }
		            if (anynum < 0) {
		                    sign = "-";
		            }
		    } catch (error) {
		            anynum = null;
		    }

		    if (anynum != null) {

		            var divider = 1;

		            if (decimal) {
		                    divider = 100;
		            }

		            var workNum = Math.abs((Math.round(anynum * divider) / divider));

		            var workStr = "" + workNum;

		            if (workStr.indexOf(".") == -1) {
		                    workStr += ".";
		            }

		            var dStr = workStr.substr(0, workStr.indexOf("."));
		            var dNum = dStr - 0;

		            var pStr = workStr.substr(workStr.indexOf("."));

		            if (decimal) {
		                    while (pStr.length - 1 < 2) {
		                            pStr += "0";
		                    }
		            }

		            if (pStr == ',')
		                    pStr = '';
		            
		            dStr = parseInt("" + (dNum / 1));

		            var retval = dStr + pStr;

		            // -- Put numbers in parentheses if negative.
		            // if (anynum < 0) {
		            // retval = "(" + retval + ")";
		            // }

		            // You could include a dollar sign in the return value.
		            // retval = "$"+retval

		            if (!decimal) {
		                    retval = retval.replace(".", "");
		            }

		            obj.value = retval;
		    }
		}

*/

function formatNumberWhit(obj, decimal) {
	var value = obj.value;
	value = value.replace(",", "");
	
	var expresion_regular_decimal;

 

  if(value.length == 1)
   expresion_regular_decimal =  /^[0-9]$/ ;
  if(value.length == 2)
   expresion_regular_decimal =  /^[0-9][0-9]$/ ;
  if(value.length == 3)
   expresion_regular_decimal =  /^[0-9][0-9][0-9]$/ ;
  if(value.length == 4)
   expresion_regular_decimal =  /^[0-9][0-9][0-9][,]$/ ;
  if(value.length == 5)
   expresion_regular_decimal =  /^[0-9][0-9][0-9][,][0-9]$/ ;
  if(value.length == 6)
   expresion_regular_decimal =  /^[0-9][0-9][0-9][,][0-9][0-9]$/ ;
  if(value.length == 7)
   expresion_regular_decimal =  /^[0-9][0-9][0-9][,][0-9][0-9][0-9]$/ ;
  if(value.length == 8)
   expresion_regular_decimal =  /^[0-9][0-9][0-9][,][0-9][0-9][0-9][,]$/ ;
  if(value.length == 9)
   expresion_regular_decimal =  /^[0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9]$/ ;
  if(value.length == 10)
   expresion_regular_decimal =  /^[0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9]$/ ;
  if(value.length == 11)
   expresion_regular_decimal =  /^[0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9]$/ ;
  if(value.length == 12)
   expresion_regular_decimal =  /^[0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][.]$/ ;
  if(value.length == 13)
   expresion_regular_decimal =  /^[0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][.][0-9]$/ ;
  if(value.length == 14)
   expresion_regular_decimal =  /^[0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][.][0-9][0-9]$/ ;
  if(value.length == 15)
   expresion_regular_decimal =  /^[,][0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][.][0-9][0-9]$/ ;
  if(value.length == 16)
   expresion_regular_decimal =  /^[0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][.][0-9][0-9]$/ ;
  if(value.length == 17)
   expresion_regular_decimal =  /^[0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][.][0-9][0-9]$/ ;
  if(value.length == 18)
   expresion_regular_decimal =  /^[0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][.][0-9][0-9]$/ ;
  if(value.length == 19)
   expresion_regular_decimal =  /^[,][0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][.][0-9][0-9]$/ ;
  if(value.length == 20)
   expresion_regular_decimal =  /^[0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][.][0-9][0-9]$/ ;
  if(value.length == 21)
   expresion_regular_decimal =  /^[0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][,][0-9][0-9][0-9][.][0-9][0-9]$/ ;
   
	
	if(expresion_regular_decimal != null && expresion_regular_decimal.test(value)==true)
      {

          return; // sale de la funci?n y NO env?a el formulario
       }   
	
	var anynum;
	var sign = "";
	try {
		if (value == "") {
			anynum = null;
		} else {
			anynum = parseFloat(value);
		}
		if (anynum < 0) {
			sign = "-";0
		}
	} catch (error) {
		anynum = null;
	}
	if (anynum != null) {
		var divider = 1;
		if (decimal) {
			divider = 100;
		}
		var workNum = Math.abs((Math.round(anynum * divider) / divider));
		var workStr = "" + workNum;
		if (workStr.indexOf(".") == -1) {
			workStr += ".";
		}
		var dStr = workStr.substr(0, workStr.indexOf("."));
		var dNum = dStr - 0;
		var pStr = workStr.substr(workStr.indexOf("."));
		if (decimal) {
			while (pStr.length - 1 < 2) {
				pStr += "0";
			}
		}
		if (pStr == ',')
			pStr = '';
		// --- Adds a comma in the thousands place.
		if (dNum >= 1000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000)) + ","
					+ dStr.substring(dLen - 3, dLen);
		}
		// -- Adds a comma in the millions place.

		if (dNum >= 1000000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000000)) + ","
					+ dStr.substring(dLen - 7, dLen);
		}

		if (dNum >= 1000000000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000000000)) + ","
					+ dStr.substring(dLen - 11, dLen);
		}

		if (dNum >= 1000000000000) {
			var dLen = dStr.length;
			dStr = parseInt("" + (dNum / 1000000000000)) + ","
					+ dStr.substring(dLen - 15, dLen);
		}

		var retval = dStr + pStr;

		// -- Put numbers in parentheses if negative.
		// if (anynum < 0) {
		// retval = "(" + retval + ")";
		// }
		// You could include a dollar sign in the return value.
		// retval = "$"+retval
		if (!decimal) {
			retval = retval.replace(".", "");
		}
		obj.value = sign + retval;
	}
}




function toggleCheck(control, formName, id) {
	var formObject = document.getElementById(formName);
	var stop = control.id.indexOf(':' + id);
	var newId = control.id.substring(0, stop);
	if (formObject) {
		var elements = formObject.elements;
		for (i = 0; i < elements.length; i++) {
			var element = elements[i];
			if (element.type == 'checkbox') {
				if (element.id != null && element.id.indexOf(newId + ':') != -1) {
					element.checked = control.checked;
				}
			}
		}
	// toggleUncheckParent(newId, id);
 	// if (control.checked) {
 		// toggleCheckParent(newId, elements, id);
 	// }
	}
}

function deseleccionar_checkboxId(formName, id){
	var formObject = document.getElementById(formName);
	if(formObject){
		var elements = formObject.elements;		
		for (i=0;i<formObject.elements.length;i++){			
			var element = elements[i];
			if(formObject.elements[i].type == 'checkbox'){
				if (element.id != null)
					alert(element.id);
					formObject.elements[i].checked=0
			}
		}
	}
}

function deseleccionar_checkbox(formName){
var formObject = document.getElementById(formName);
	for (i=0;i<formObject.elements.length;i++)
		if(formObject.elements[i].type == 'checkbox')	
			formObject.elements[i].checked=0
}

function validaNumerosDecimales(evt, campo){
// NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57
 var key = evt.keyCode ? evt.keyCode : evt.which ;
 
 if (evt.shiftKey)
 {
	return false
 }

 if(key == 46)
 {
   var cadena = campo.value;
   var cad = cadena.split('.');
   
   if(cadena.length==0)
     return (key <= 40 || (key >= 48 && key <= 57));
   
   if(cad.length==1)
   {
     return (key <= 40 || (key >= 48 && key <= 57)|| key == 46);
   }
   else
   {
     return (key <= 40 || (key >= 48 && key <= 57));
   }
   
 }
 else
 {
   return (key <= 40 || (key >= 48 && key <= 57));
 }
}

function validaNumerosDecimales4(evt, campo){
// NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57
 var key = evt.keyCode ? evt.keyCode : evt.which ;
 
 if (evt.shiftKey)
 {
	return false
 }

 if(key == 46)
 {
   var cadena = campo.value;
   var cad = cadena.split('.');
   
     return (key <= 40 || (key >= 48 && key <= 57)|| key == 46);
 }
 else
 {
   return (key <= 40 || (key >= 48 && key <= 57));
 }
}

function validaNumerosSinDecimales(evt, campo){
// NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57
 var key = evt.keyCode ? evt.keyCode : evt.which ;

   return (key <= 40 || (key >= 48 && key <= 57));
}

// Funcion que entrega la cadena con el signo de pregunta, utilizado para los
// confirn
function obtenerCadenaPregunta(cadena)
{
	return cadena + '?'
}

function trim (myString)
{
  return myString.replace(/^\s+/g,'').replace(/\s+$/g,'')
}

function habilitaControlGenerar(idControlPeriodo, idControlBotonGenerar)
{
   valuePeriodo = document.getElementById(idControlPeriodo).value;
   
   if(trim(valuePeriodo)=="0P0")	
      document.getElementById(idControlBotonGenerar).disabled=true;
   else
   	  document.getElementById(idControlBotonGenerar).disabled=false;
}

function sumar()
{
	var a, total = 0; 
	var elementsH = document.getElementsByName('proyectoNuevoForm:beneficiarosHomDecorate:txtBeneficiariosHom');
 	var elementsM = document.getElementsByName('proyectoNuevoForm:beneficiariosFenDecorate:txtBeneficiariosFen');  
  
   // for(a=0; a<elements.length; a++){
	if ((elementsH[0].value!='') && (elementsM[0].value!='')){    
          total = (parseFloat(elementsH[0].value))+(parseFloat(elementsM[0].value));
		 // alert(elementsH[0].value);
		  // alert(elementsM[0].value);
          document.getElementById("proyectoNuevoForm:beneficiariosDirectosDecorate:beneficiariosDirectos").value = total;
	 }
   // }
}

function limpiarCampos(){
        if(parseFloat(document.getElementById('Num').value)>100){
            alert(document.getElementById('Num').value) 
            document.getElementById('Num').value = "";
            document.getElementById('Num').focus();
        }
  }
  
function KeyDownHandler(event, controlBusqueda) 
{ 
     // process only the Enter key
     if (event.keyCode == 13) 
     { 
         // cancel the default submit
    	 
    	 
         event.returnValue=false; 
         event.cancel = true; 
         // submit the form by programmatically clicking the specified button
         document.getElementById(controlBusqueda).click(); 
      }    
} 

function validarOnlyNumero(e, field) {
	key = e.keyCode ? e.keyCode : e.which
	
			if (key == 192){
				return false
				}			
				
	if (key == 39 || key == 37 || key == 8 
			|| key == 35 || key == 36){
			if (e.shiftKey){
				return false
				}
				
				if (e.altKey){
				return false
				}
			return true
			}			
		// tab
	if (key == 9)
		return true

		// backspace
	if (key == 8)
		return true
		// 0-9
	if (key > 47 && key < 58) {
		if (field.value == "")
			return true
		// regexp = /[.]{1}[0-9]{2}$/
		return true;// !(regexp.test(field.value))
	}
	
	// 35=# y 36=$
	if(key == 35 || key ==36){
		if(field.value == '$')
			return false
		if(field.value == '#')
			return false
		if(field.value == '#')
			return false
		return false;
	}

	return false
}


function validarNumeroFF(e, field) {
		key = e.keyCode ? e.keyCode : e.which
	// Teclas especiales
	// Flechas direccionales 39 derecha, 37 izquierda
	// 8 retroceso, copiar 99, pegar 118, 46 suprimir, 36 inicio, 35 fin
	if (key == 39 || key == 37 || key == 8 
			|| key == 35 || key == 36)
		return true

		// tab
	if (key == 9)
		return true

		// backspace
	if (key == 8)
		return true
		// 0-9
	if (key > 47 && key < 58) {
		if (field.value == "")
			return true
		// regexp = /[.]{1}[0-9]{2}$/
		return true;// !(regexp.test(field.value))
	}
	// .
	if (key == 46) {
		var split = field.value.split(".");
		if(split.length > 1){
			return false;
		}
		if (field.value == "")
			return false
		regexp = /^[0-9]+$/
		return regexp.test(field.value)
	}


	// other key
	return false
}

function ingresoDecimal(sender, e)
{
	var keynum;
	var keychar;
	var numcheck;

	var decimales;
	
	var posicion;
	posicion = sender.value.indexOf(".", 0);
	
	if(e.shiftKey)
		return false;
	
	if(window.event) // IE
	{
		keynum = e.keyCode;
	}
	else if(e.which) // Netscape/Firefox/Opera
	{
		keynum = e.which;
	}

	if(keynum == 8 || keynum == 46 || keynum == 37 || keynum == 39 || keynum == 9)
		return true;
	if(keynum == 190 && sender.value.length >0)
	{
		if(posicion >= 0)
			return false;
		else
			return true;
	}

	if(posicion >= 0)
	{
		decimales = sender.value.substr(posicion);	
		if(decimales.length >= 3)
		{
			// RUTINA PARA MOVER LOS DECIMALES
			/*
			 * var parte1 = sender.value.substr(0, posicion); var parte2 =
			 * sender.value.substr(posicion+1); var parte1mod = parte1 +
			 * parte2.substr(0, 1); var parte2mod = parte2.substr(1);
			 * sender.value = parte1mod + "." + parte2mod; return true;
			 */
			var range = document.selection.createRange();
			var range2 = sender.createTextRange();
			range2.collapse(true);
			range2.moveEnd('character', 0);
			range2.setEndPoint('EndToStart', range);
			distancia=range2.text.length
			pos_final=parseFloat(sender.value.length)- parseFloat(distancia)
			range2.move('character', pos_final)
			
			if(distancia <= posicion)
				return true;
			else
				return false;
		}
	}


	keychar = String.fromCharCode(keynum);
	numcheck =  /\d/ ;
	return numcheck.test(keychar);
}

function desabilitaBoton(e, field, idControlBotonGenerar)
{
	var value = trim(e.value);		
	
  	if(value.length > 0)
  		document.getElementById(idControlBotonGenerar).disabled=false;
  	else
  		document.getElementById(idControlBotonGenerar).disabled=true;
}


function GanaFoco(sender, e)
{
	var texto = sender.value.replace(",","").replace(",","").replace(",","").replace(",","").replace(",","");
	sender.value = texto;
	if(texto.length)
	sender.select();
}

function disableCheck(field, causer) {
	if (causer.checked) {
		field.checked = false;
		field.disabled = true;
	}
	else{
		field.disabled = false;
	}
}

function verificaCampoAlfanumerico(e,campo)
{    
    tecla = (document.all) ? e.keyCode : e.which;
    
    if (tecla==8||tecla==32||tecla==13||tecla==0 ||tecla==46 ||tecla==44 || tecla==241|| tecla==209)
    	return true;
    if(tecla==94 || tecla==96)
    	return false;
     val = String.fromCharCode(tecla);     
     var RegExPattern = /[0-9-A-z]/g;
     if ((val.match(RegExPattern)) && (val!='')) {
         return true;
     } else {  
         return false;
     }

}

function limitText(limitField, limitNum) {
	
    if (limitField.value.length > limitNum) {
        limitField.value = limitField.value.substring(0, limitNum);
    } 
}
var newWindow=null;

function openChild(url){
	vector=window.open(url,'vector','width =1000,height=600');
	return true;
}

function closeChild(){
	if (newWindow && !newWindow.closed) {
		newWindow.close();
		}
	
}

function openWindowWithPost1(url,name,keys,values)
{

	newWindow = window.open(url,name,'width =1000,height=600'); 
	var arrayKeys=splitParameters(keys);
	var arrayValues=splitParameters(values);
	if (newWindow==null){ 
		url = url.replace('http://','');
		newWindow = window.open(url,name,'width =1000,height=600'); 
	}

	if (!newWindow){ 
	return false;
	}

	var html = "";
	html += "<html><head></head><body><form id='formid' method='post' action='" + url + "'>";
	if (arrayKeys && arrayValues && (arrayKeys.length == arrayValues.length))
	for (var i=0; i < arrayKeys.length; i++)
	html += "<input type='hidden' name='" + arrayKeys[i] + "' value='" + arrayValues[i] + "'/>";
	html += "</form><script type='text/javascript'>document.getElementById(\"formid\").submit()</script></body></html>";
	newWindow.document.write(html);

	return newWindow;
}

function Left(str, n){
	if (n <= 0)
	    return "";
	else if (n > String(str).length)
	    return str;
	else
	    return String(str).substring(0,n);
}


var arrayKeys=null;
var arrayValues=null;
var posturl='';

function openWindowWithPost(url,name,keys,values){

	loc1 = location.href;
	in1 = loc1.indexOf("gestionarTramites.jsf");
	var server1 = Left(loc1,in1);
	url1=server1+'vector.html';
	newWindow = window.open(url1,name,'width =1000,height=600'); 
	 arrayKeys=splitParameters(keys);
	 arrayValues=splitParameters(values);

	if (!newWindow){ 
		return false;
	}
	posturl=url;
	// setTimeout("writeToWindow()", 50);

	writeToWindow(url);

	return newWindow;
}

function writeToWindow() {

	
   var html = "";
	html += "<html>";
	html += "<head></head><body><form id='formid' method='post' action='" + posturl + "'>";
	if (arrayKeys && arrayValues && (arrayKeys.length == arrayValues.length))
	for (var i=0; i < arrayKeys.length; i++)
	html += "<input type='hidden' name='" + arrayKeys[i] + "' value='" + arrayValues[i] + "'/>";
	html += "</form><script type='text/javascript'>document.getElementById(\"formid\").submit()</script></body></html>";
	try{
	newWindow.document.write(html);
	}catch(e){
		// alert(e);
	}
    // newWindow.document.close() // close layout stream
}

function splitParameters(value){
	var array=value.split(",");
	return array;
}


function deshabilita(field){ 
    field.disabled = true;     
} 


function ValidateNumberKeyPress(field, evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode

	if (charCode == 13)
		return true; // Enter
	if (charCode == 8)
		return true; // Delete
	if (charCode == 9)
		return true; // Tab
	if (charCode == 46)
		return true; // Del
	var keychar = String.fromCharCode(charCode);
	if ((charCode < 48 || charCode > 57) && keychar != ".") {
		return false;
	}
	psplit = field.value.split(".");

	var entero = psplit[0].replace(/,/gi, "");

	if (psplit[1] != null) {
		if (entero.length >= 3 && psplit[1].length >= 2) {
			return false;
		} else if (psplit[1].length >= 2) {
			return false;
		}
	} else if (entero.length >= 3 && keychar != ".") {
		return false;
	}
	if (keychar == "." && field.value.indexOf(".") != -1) {
		return false;
	}

	return true;
}

function ValidateNumberKeyUp(field, e) {
	if (document.selection) {

		if (document.selection.type == "Text") {
			return;
		}
	} else if (window.getSelection) {

		if (window.getSelection().type == "Text") {
			return;
		}
	}

	var fdlen = field.value.length;

	var IsFound = /^-?\d+\.{0,1}\d{0,2}$/.test(field.value);
	if (!IsFound) {
		
		return false;
	}

	fdlen = field.value.length - fdlen;

	
}

function aceptarLetras(evt) {
    var key = null;
    if (esIE()) {
            key = evt.keyCode;
    } else {
            key = evt.which;
    }
    return (key < 32 || key == 95 || key == 32 || (key >= 64 && key <= 90)
                    || (key >= 97 && key <= 122) || key == 241  
       || key == 225 || key == 233 || key == 237 || key == 243 || key == 250 //vocales con tildes minuscula
       || key == 193 || key == 201 || key == 205 || key == 211 || key == 218);  //vocales con tildes mayuscula

}

//
//para validar si hay errores o no y poder cerrar el modal panel de seleccion  de entidades participantes
function ajaxFileUploadRequestContainsErrors() {
	
//  var value = document.getElementById('maximumSeverity').value;
	var inputSeverity;
  var inputs = document.getElementsByTagName('input');
  
  for (var i in inputs) {
	  var item = inputs[i];
    if(item.id.indexOf("maximumSeverityFileUpload")!=-1){
  	  inputSeverity = item;
  	  break;
    }
    
  }
  
  //si no hay errores
  //el cero es el maximunSeverity para los InfoMessages lanzados
  if(inputSeverity.value == 0){
  	return true;
  }
  
  return false;

}

function validarNemonico(e, field) {

	key = e.keyCode ? e.keyCode : e.which

	// Teclas especiales
	// Flechas direccionales 39 derecha, 37 izquierda, 13 enter
	// 8 retroceso, copiar 99, pegar 118, 46 suprimir, 36 inicio, 35 fin

	if (key == 39 || key == 37 || key == 8
			|| key == 46 || key == 35 || key == 36)
		return true
		
	if (key == 9 || key == 8 || key == 13)
		return true
			
		// 0-9
	if (key > 47 && key < 58) {
		if (field.value == "")
			return true
		return true;
	}
	
	// other key
	return false
}

function blockNonNumbers(obj, e, allowDecimal, allowNegative)
{
	var key;
	var isCtrl = false;
	var keychar;
	var reg;
		
	if(window.event) {
		key = e.keyCode;
		isCtrl = window.event.ctrlKey
	}
	else if(e.which) {
		key = e.which;
		isCtrl = e.ctrlKey;
	}
	
	if (isNaN(key)) return true;
	
	keychar = String.fromCharCode(key);
	
	// check for backspace or delete, or if Ctrl was pressed
	if (key == 8 || isCtrl)
	{
		return true;
	}

	reg = /\d/;
	var isFirstN = allowNegative ? keychar == '-' && obj.value.indexOf('-') == -1 : false;
	var isFirstD = allowDecimal ? keychar == '.' && obj.value.indexOf('.') == -1 : false;
	
	return isFirstN || isFirstD || reg.test(keychar);
}
function extractNumber(obj, decimalPlaces, allowNegative)
{
	var temp = obj.value;
	
	// avoid changing things if already formatted correctly
	var reg0Str = '[0-9]*';
	if (decimalPlaces > 0) {
		reg0Str += '\\.?[0-9]{0,' + decimalPlaces + '}';
	} else if (decimalPlaces < 0) {
		reg0Str += '\\.?[0-9]*';
	}
	reg0Str = allowNegative ? '^-?' + reg0Str : '^' + reg0Str;
	reg0Str = reg0Str + '$';
	var reg0 = new RegExp(reg0Str);
	if (reg0.test(temp)) return true;

	// first replace all non numbers
	var reg1Str = '[^0-9' + (decimalPlaces != 0 ? '.' : '') + (allowNegative ? '-' : '') + ']';
	var reg1 = new RegExp(reg1Str, 'g');
	temp = temp.replace(reg1, '');

	if (allowNegative) {
		// replace extra negative
		var hasNegative = temp.length > 0 && temp.charAt(0) == '-';
		var reg2 = /-/g;
		temp = temp.replace(reg2, '');
		if (hasNegative) temp = '-' + temp;
	}
	
	if (decimalPlaces != 0) {
		var reg3 = /\./g;
		var reg3Array = reg3.exec(temp);
		if (reg3Array != null) {
			// keep only first occurrence of .
			//  and the number of places specified by decimalPlaces or the entire string if decimalPlaces < 0
			var reg3Right = temp.substring(reg3Array.index + reg3Array[0].length);
			reg3Right = reg3Right.replace(reg3, '');
			reg3Right = decimalPlaces > 0 ? reg3Right.substring(0, decimalPlaces) : reg3Right;
			temp = temp.substring(0,reg3Array.index) + '.' + reg3Right;
		}
	}
	
	obj.value = temp;
}
function blockNonNumbers(obj, e, allowDecimal, allowNegative)
{
	var key;
	var isCtrl = false;
	var keychar;
	var reg;
		
	if(window.event) {
		key = e.keyCode;
		isCtrl = window.event.ctrlKey
	}
	else if(e.which) {
		key = e.which;
		isCtrl = e.ctrlKey;
	}
	
	if (isNaN(key)) return true;
	
	keychar = String.fromCharCode(key);
	
	// check for backspace or delete, or if Ctrl was pressed
	if (key == 8 || isCtrl)
	{
		return true;
	}

	reg = /\d/;
	var isFirstN = allowNegative ? keychar == '-' && obj.value.indexOf('-') == -1 : false;
	var isFirstD = allowDecimal ? keychar == '.' && obj.value.indexOf('.') == -1 : false;
	
	return isFirstN || isFirstD || reg.test(keychar);
}
function aceptarSoloLetras(evt) {
    var key = null;
    if (esIE()) {
            key = evt.keyCode;
    } else {
            key = evt.which;
    }
    return (key < 32 || key == 95 || key == 32 || (key >= 65 && key <= 90)
                    || (key >= 97 && key <= 122) || key == 241  || key == 45 );  

}

function aceptarLetrasNumeros(evt) {
    var key = null;
    if (esIE()) {
            key = evt.keyCode;
    } else {
            key = evt.which;
    }
    return (key < 32 || key == 95 || key == 32 || (key >= 65 && key <= 90)
                    || (key >= 97 && key <= 122) || key == 241  || key == 45 || (key > 47 && key < 58) );  
}

function validarBeneficiariosHombres(obj){
	var idTotal="proyectoNuevoForm:beneficiariosDirectosDecorate:beneficiariosDirectos";
	var textTotal = document.getElementById(idTotal);
	
	var idMujeres="proyectoNuevoForm:beneficiariosFenDecorate:txtBeneficiariosFen";
	var textMujeres = document.getElementById(idMujeres);
	
	if (parseFloat(obj.value) > parseFloat(textTotal.value)) {
		obj.value = textTotal.value;
	}
	textMujeres.value = textTotal.value - obj.value;
}

function validarBeneficiariosMujeres(obj){
	var idTotal="proyectoNuevoForm:beneficiariosDirectosDecorate:beneficiariosDirectos";
	var textTotal = document.getElementById(idTotal);
	
	var idHombres="proyectoNuevoForm:beneficiarosHomDecorate:txtBeneficiariosHom";
	var textHombres = document.getElementById(idHombres);
	if (parseFloat(obj.value) > parseFloat(textTotal.value)) {
		obj.value = textTotal.value;
	}
	textHombres.value = textTotal.value - obj.value;
}

function validarTransferenciaTecnologicaProyectos(){
	var idTransfTec="proyectoNuevoForm:transfTecDecorate:transfTec";
	var textAreaTransfTec = document.getElementById(idTransfTec);
	
	
	var idConsumoImportado = "proyectoNuevoForm:consumoImportadoDecorate:consumoImportado";
	var textAreaConsumoImportado = document.getElementById(idConsumoImportado);
	
	if (textAreaConsumoImportado.value == '0.00') {
		textAreaTransfTec.disabled=true;
	}
}

function extractNumber(obj, decimalPlaces, allowNegative)
{
	var temp = obj.value;
	
	// avoid changing things if already formatted correctly
	var reg0Str = '[0-9]*';
	if (decimalPlaces > 0) {
		reg0Str += '\\.?[0-9]{0,' + decimalPlaces + '}';
	} else if (decimalPlaces < 0) {
		reg0Str += '\\.?[0-9]*';
	}
	reg0Str = allowNegative ? '^-?' + reg0Str : '^' + reg0Str;
	reg0Str = reg0Str + '$';
	var reg0 = new RegExp(reg0Str);
	if (reg0.test(temp)) return true;

	// first replace all non numbers
	var reg1Str = '[^0-9' + (decimalPlaces != 0 ? '.' : '') + (allowNegative ? '-' : '') + ']';
	var reg1 = new RegExp(reg1Str, 'g');
	temp = temp.replace(reg1, '');

	if (allowNegative) {
		// replace extra negative
		var hasNegative = temp.length > 0 && temp.charAt(0) == '-';
		var reg2 = /-/g;
		temp = temp.replace(reg2, '');
		if (hasNegative) temp = '-' + temp;
	}
	
	if (decimalPlaces != 0) {
		var reg3 = /\./g;
		var reg3Array = reg3.exec(temp);
		if (reg3Array != null) {
			// keep only first occurrence of .
			//  and the number of places specified by decimalPlaces or the entire string if decimalPlaces < 0
			var reg3Right = temp.substring(reg3Array.index + reg3Array[0].length);
			reg3Right = reg3Right.replace(reg3, '');
			reg3Right = decimalPlaces > 0 ? reg3Right.substring(0, decimalPlaces) : reg3Right;
			temp = temp.substring(0,reg3Array.index) + '.' + reg3Right;
		}
	}
	
	obj.value = temp;
}
function blockNonNumbers(obj, e, allowDecimal, allowNegative)
{
	var key;
	var isCtrl = false;
	var keychar;
	var reg;
		
	if(window.event) {
		key = e.keyCode;
		isCtrl = window.event.ctrlKey
	}
	else if(e.which) {
		key = e.which;
		isCtrl = e.ctrlKey;
	}
	
	if (isNaN(key)) return true;
	
	keychar = String.fromCharCode(key);
	
	// check for backspace or delete, or if Ctrl was pressed
	if (key == 8 || isCtrl)
	{
		return true;
	}

	reg = /\d/;
	var isFirstN = allowNegative ? keychar == '-' && obj.value.indexOf('-') == -1 : false;
	var isFirstD = allowDecimal ? keychar == '.' && obj.value.indexOf('.') == -1 : false;
	
	return isFirstN || isFirstD || reg.test(keychar);
}