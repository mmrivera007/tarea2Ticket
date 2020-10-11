
Gestión de Tickets

Modulos del proyecto ==== Raiz ==== Este modulo se empaqueta como un POM y sirve como el contenedor de los demas modulos del proyecto. 
Este administra las dependencias del proyecto y las configuraciones generales del mismo.

EJB ==== Este modulo se empaqueta como un JAR y porta el mapeo Objeto - Relacional de las entidades mas los componentes que manipulan estas entidades.

Web ==== Este modulo se empaqueta como un WAR y es desplegado directamente en el servidor de aplicaciones cuando no existen mas interfaces para el ambiente intranet. 
En caso de que exista otra interfaz para el ambiente intranet este modulo pasa a ser contenido dentro de un EAR.

EAR ==== Este modulo sirve para empaquetar el sistema en un archivo empresarial. 
Este tipo de empaquetamiento se utiliza cuando el sistema expone en el mismo ambiente mas de una interfaz.

Estructura de los modulos ======= Dependiendo de como se va a empacar el artefacto se genera su estructura interna, por ejemplo, el modulo web al ser un war tiene una carpeta para el codigo Java y otra para las paginas de la vista.

Se debe configurar el servidor Wildlfy 10, con PostgreSQL, por lo que se debe configurar el standalone.xml y agregar el módulo de la base de datos

1. standalone.xml
```
<datasource jta="true" jndi-name="java:jboss/datasources/ticketDS" pool-name="ticketDS" enabled="true" use-java-context="true">
	<connection-url>jdbc:postgresql://localhost:5432/ticket</connection-url>
	<driver>postgres</driver>
	<transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
	<pool>
		<min-pool-size>10</min-pool-size>
		<max-pool-size>100</max-pool-size>
		<prefill>true</prefill>
	</pool>
	<security>
		<user-name>ticket</user-name>
		<password>ticket</password>
	</security>
	<statement>
		<prepared-statement-cache-size>32</prepared-statement-cache-size>
		<share-prepared-statements>true</share-prepared-statements>
	</statement>
</datasource>
<drivers>
	<driver name="postgres" module="org.postgresql">
		<xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
	</driver>
</drivers>
```

2. module.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="org.postgresql">
    <resources>
        <resource-root path="postgresql-42.2.2.jre7.jar"/>
    </resources>

    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>
```

3. Se deberá ejecutar los Scripts de BDD preparados.
