/**
 * Verifica si se aplicó la tecla ENTER
 */
function verifyKeyEnter(event){
	if(event.keyCode == 13)
		return true;
	else
		return false;
}
/**
 * Realiza el bloqueo de las teclas principales del browser F5 y F11
 */
function bloquearTeclas(e){
	var tecla;
	if (window.event) {
		//IE
		tecla = window.event.keyCode;
		if(tecla == 116 || tecla == 122){
			event.keyCode=0;
			event.returnValue=false;
		}
	}else{
		//firefox
		tecla = e.which;
		if(tecla == 116 || tecla == 122)
			return true;
	}
}

function createLayouts(westSize, eastSize,northSize,southSize,spacing,overflow){	
	if(westSize == ''){
		westSize = 230;
	}
	if(eastSize == ''){
		eastSize = 230;
	}
	if(southSize == undefined){
		southSize= 15;
	}		
	if(overflow == undefined){
		overflow= true;
	}	
	myLayout = jQuery('body').layout({
		 noRoomToOpenTip: '',
		//opciones de los layouts
		north: {			
			size:northSize,
			resizable: false,
			showOverflowOnHover:overflow,
			spacing_open: spacing
		},
		west:{
			size: westSize,
			showOverflowOnHover:false,
			spacing_open: spacing
		},
		east:{
			size: eastSize,
			showOverflowOnHover:false,
			spacing_open: spacing
		},
		south:{
			size: southSize,
			showOverflowOnHover:false,
			spacing_open: 0 //espacio entre centro y sur
		}
	});
	myLayout = window.myLayout;
}
