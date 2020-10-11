
-- Tipo catalogo
INSERT INTO public.tipo_catalogo VALUES (1, 'ESTADOS','Tipos de cuerpos colegiados.','ESTADO');
INSERT INTO public.tipo_catalogo VALUES (2, 'PRIORIDAD','Tipos de convocatoria de los cuerpos colegiados.','PRIORIDAD');

-- Catalogo
-- 1. Catálogo ESTADOS
INSERT INTO public.catalogo VALUES (1, 1, null, 'Open','ACT');
INSERT INTO public.catalogo VALUES (2, 1, null, 'Escalated','ACT');
INSERT INTO public.catalogo VALUES (3, 1, null, 'Closed','ACT');
-- 2. Catálogo PRIORIDAD
INSERT INTO public.catalogo VALUES (4, 2, null, 'High','ACT');
INSERT INTO public.catalogo VALUES (5, 2, null, 'Midium','ACT');
INSERT INTO public.catalogo VALUES (6, 2, null, 'Low','ACT');

--Clientes
INSERT INTO public.cliente VALUES (NEXTVAL('public.seq_cliente'), 'José Pozo', '098987876', 'josep@hotmail.com','ACT');
INSERT INTO public.cliente VALUES (NEXTVAL('public.seq_cliente'), 'María Duarte', '098987877', 'mariad@hotmail.com','ACT');

--Técnico
INSERT INTO public.tecnico VALUES (NEXTVAL('public.seq_tecnico'), 'Diana Arias', '099987877', 'dianaad@gmail.com','ACT');
INSERT INTO public.tecnico VALUES (NEXTVAL('public.seq_tecnico'), 'Pedro Caiza', '097787877', 'pedroc@gmail.com','ACT');

--Oferta
INSERT INTO public.oferta VALUES (NEXTVAL('public.seq_oferta'), 'Mobile Deal', 'Oferta 1','ACT');