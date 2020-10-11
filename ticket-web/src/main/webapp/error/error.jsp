<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>    
	    <script type="text/javascript">
           	function cerrarName(){            		            		
        		localStorage.setItem("mens", "");            		
            }
		</script>
	    
	    <title>ERROR</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     	<meta HTTP-EQUIV="REFRESH" content="3;url=${pageContext.servletContext.contextPath}/ticketMain.jsf">
    </head>
    <body onload="cerrarName()">
        <hr />
        <center><h1><a style="color:#043C8F">NOTIFICACIÓN</a> <br> 
        </h1></center>
        <center><h1> Existe un problema con el proceso.<br> 
        Lamentamos lo ocurrido, intente nuevamente
        <br> 
        o póngase en contacto para solventar el inconveniente</h1>
        </center>
        <p></p> 
        <center>
         <h2>Redireccionando:</h2>
            <h1>Por favor espere...</h1>
        </center>
        <hr />
    </body>

</html>