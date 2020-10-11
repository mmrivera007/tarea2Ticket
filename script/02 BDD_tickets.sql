
CREATE SEQUENCE public.seq_cliente;

CREATE TABLE public.cliente (
                id INTEGER NOT NULL DEFAULT nextval('public.seq_cliente'),
                nombre VARCHAR(128) NOT NULL,
                celular VARCHAR(16),
                email VARCHAR(64) NOT NULL,
                estado VARCHAR(8) NOT NULL,
                CONSTRAINT pk_cliente PRIMARY KEY (id)
);
COMMENT ON TABLE public.cliente IS 'Almacena los datos de los clientes';
COMMENT ON COLUMN public.cliente.id IS 'Código secuencial del cliente.';
COMMENT ON COLUMN public.cliente.nombre IS 'Nombre del técnico.';
COMMENT ON COLUMN public.cliente.celular IS 'Número de celular del técnico.';
COMMENT ON COLUMN public.cliente.email IS 'email del técnico';
COMMENT ON COLUMN public.cliente.estado IS 'Estado del cliente. Ej. ACT, INA';


ALTER SEQUENCE public.seq_cliente OWNED BY public.cliente.id;

CREATE SEQUENCE public.seq_oferta;

CREATE TABLE public.oferta (
                id INTEGER NOT NULL DEFAULT nextval('public.seq_oferta'),
                Nombre VARCHAR(64) NOT NULL,
                descripcion VARCHAR(128) NOT NULL,
                estado VARCHAR(8) NOT NULL,
                CONSTRAINT pk_oferta PRIMARY KEY (id)
);
COMMENT ON TABLE public.oferta IS 'Almacena los datos de las ofertas.';
COMMENT ON COLUMN public.oferta.id IS 'Código secuencial de la oferta.';
COMMENT ON COLUMN public.oferta.Nombre IS 'Nombre de la oferta';
COMMENT ON COLUMN public.oferta.descripcion IS 'descripcion de la oferta';
COMMENT ON COLUMN public.oferta.estado IS 'Estado de la oferta. Ej. ACT, INA';


ALTER SEQUENCE public.seq_oferta OWNED BY public.oferta.id;

CREATE SEQUENCE public.seq_tecnico;

CREATE TABLE public.tecnico (
                id INTEGER NOT NULL DEFAULT nextval('public.seq_tecnico'),
                nombre VARCHAR(128) NOT NULL,
                celular VARCHAR(16) NOT NULL,
                email VARCHAR(64) NOT NULL,
                estado VARCHAR(8) NOT NULL,
                CONSTRAINT pk_tecnico PRIMARY KEY (id)
);
COMMENT ON TABLE public.tecnico IS 'Almacena los datos de los técnicos';
COMMENT ON COLUMN public.tecnico.id IS 'Código secuencial del técnico.';
COMMENT ON COLUMN public.tecnico.nombre IS 'Nombre del técnico.';
COMMENT ON COLUMN public.tecnico.celular IS 'Número de celular del técnico.';
COMMENT ON COLUMN public.tecnico.email IS 'email del técnico';
COMMENT ON COLUMN public.tecnico.estado IS 'Estado del técnico. Ej. ACT, INA';


ALTER SEQUENCE public.seq_tecnico OWNED BY public.tecnico.id;

CREATE SEQUENCE public.seq_tipo_catalogo;

CREATE TABLE public.tipo_catalogo (
                id INTEGER NOT NULL DEFAULT nextval('public.seq_tipo_catalogo'),
                nombre VARCHAR(64) NOT NULL,
                descripcion VARCHAR(128),
                nemonico VARCHAR(64) NOT NULL,
                CONSTRAINT pk_tipo_catalogo PRIMARY KEY (id)
);
COMMENT ON TABLE public.tipo_catalogo IS 'Alamcena los Tipos de catálogos del sistema.';
COMMENT ON COLUMN public.tipo_catalogo.id IS 'Código secuencial del tipo de catálogo.';
COMMENT ON COLUMN public.tipo_catalogo.nombre IS 'Nombre del tipo de catálogo.';
COMMENT ON COLUMN public.tipo_catalogo.descripcion IS 'Descripción del tipo de catálogo.';
COMMENT ON COLUMN public.tipo_catalogo.nemonico IS 'Nemonico del tipo de catálogo.';


ALTER SEQUENCE public.seq_tipo_catalogo OWNED BY public.tipo_catalogo.id;

CREATE UNIQUE INDEX uk_tipo_catalogo_nemonico
 ON public.tipo_catalogo
 ( nemonico );

CREATE SEQUENCE public.seq_catalogo;

CREATE TABLE public.catalogo (
                id INTEGER NOT NULL DEFAULT nextval('public.seq_catalogo'),
                id_tipo_catalogo INTEGER NOT NULL,
                id_catalogo_padre INTEGER,
                nombre VARCHAR(128) NOT NULL,
                estado VARCHAR(8) NOT NULL,
                CONSTRAINT pk_catalogo PRIMARY KEY (id)
);
COMMENT ON TABLE public.catalogo IS 'Almacena los catálogos del sistema.';
COMMENT ON COLUMN public.catalogo.id IS 'Código secuencial del catálogo.';
COMMENT ON COLUMN public.catalogo.id_tipo_catalogo IS 'Código del tipo de catálogo.';
COMMENT ON COLUMN public.catalogo.id_catalogo_padre IS 'Código del catálogo padre.';
COMMENT ON COLUMN public.catalogo.nombre IS 'Nombre del catálogo.';
COMMENT ON COLUMN public.catalogo.estado IS 'Estado del catálogo. Ej. ACT, INA';


ALTER SEQUENCE public.seq_catalogo OWNED BY public.catalogo.id;

CREATE SEQUENCE public.seq_ticket;

CREATE TABLE public.ticket (
                id INTEGER NOT NULL DEFAULT nextval('public.seq_ticket'),
                descripcion VARCHAR(128) NOT NULL,
                id_estado INTEGER NOT NULL,
                id_prioridad INTEGER,
                id_cliente INTEGER,
                id_tecnico INTEGER,
                id_oferta INTEGER,
                fecha_fin DATE,
                estado VARCHAR(8) NOT NULL,
                CONSTRAINT pk_ticket PRIMARY KEY (id)
);
COMMENT ON TABLE public.ticket IS 'Almacena los tickets.';
COMMENT ON COLUMN public.ticket.id IS 'Código secuencial del ticket.';
COMMENT ON COLUMN public.ticket.descripcion IS 'descripción del ticket';
COMMENT ON COLUMN public.ticket.id_estado IS 'Código del estado';
COMMENT ON COLUMN public.ticket.id_prioridad IS 'Código de la prioridad';
COMMENT ON COLUMN public.ticket.id_cliente IS 'Código del cliente';
COMMENT ON COLUMN public.ticket.id_tecnico IS 'Código del técnico.';
COMMENT ON COLUMN public.ticket.id_oferta IS 'Código de la oferta';
COMMENT ON COLUMN public.ticket.fecha_fin IS 'Fecha de finalización del ticket';
COMMENT ON COLUMN public.ticket.estado IS 'Estado del catálogo. Ej. ACT, INA';


ALTER SEQUENCE public.seq_ticket OWNED BY public.ticket.id;

CREATE SEQUENCE public.seq_ticket_detalle;

CREATE TABLE public.ticket_detalle (
                id INTEGER NOT NULL DEFAULT nextval('public.seq_ticket_detalle'),
                id_ticket INTEGER NOT NULL,
                nota VARCHAR(128) NOT NULL,
                estado VARCHAR(8) NOT NULL,
                CONSTRAINT pk_ticket_detalle PRIMARY KEY (id)
);
COMMENT ON TABLE public.ticket_detalle IS 'Almacena los datos de las detalles en la atención del ticket.';
COMMENT ON COLUMN public.ticket_detalle.id IS 'Código secuencial del ticket detalle';
COMMENT ON COLUMN public.ticket_detalle.id_ticket IS 'Código del ticket.';
COMMENT ON COLUMN public.ticket_detalle.nota IS 'Nota';
COMMENT ON COLUMN public.ticket_detalle.estado IS 'Estado del detalle. Ej. ACT, INA';


ALTER SEQUENCE public.seq_ticket_detalle OWNED BY public.ticket_detalle.id;





ALTER TABLE public.ticket ADD CONSTRAINT fk_cliente_ticket
FOREIGN KEY (id_cliente)
REFERENCES public.cliente (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ticket ADD CONSTRAINT fk_oferta_ticket
FOREIGN KEY (id_oferta)
REFERENCES public.oferta (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ticket ADD CONSTRAINT fk_tecnico_ticket
FOREIGN KEY (id_tecnico)
REFERENCES public.tecnico (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.catalogo ADD CONSTRAINT fk_tipo_catalogo_catalogo
FOREIGN KEY (id_tipo_catalogo)
REFERENCES public.tipo_catalogo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ticket ADD CONSTRAINT fk_catalogo_prioridad
FOREIGN KEY (id_prioridad)
REFERENCES public.catalogo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.catalogo ADD CONSTRAINT fk_catalogo_catalogo
FOREIGN KEY (id_catalogo_padre)
REFERENCES public.catalogo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ticket ADD CONSTRAINT fk_catalogo_estado
FOREIGN KEY (id_estado)
REFERENCES public.catalogo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ticket_detalle ADD CONSTRAINT fk_ticket_ticket_detalle
FOREIGN KEY (id_ticket)
REFERENCES public.ticket (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;