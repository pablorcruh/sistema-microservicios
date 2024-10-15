ALTER TABLE public.cliente_events ADD created_at timestamp(6) NOT NULL;

ALTER TABLE public.personas ADD phone varchar(255) NULL;

-- Drop table

-- DROP TABLE public.personas;

CREATE TABLE public.personas (
	age int4 NOT NULL,
	id uuid NOT NULL,
	address varchar(255) NULL,
	gender varchar(255) NULL,
	"name" varchar(255) NOT NULL,
	phone varchar(255) NULL,
	CONSTRAINT personas_name_key UNIQUE (name),
	CONSTRAINT personas_pkey PRIMARY KEY (id)
);

ALTER TABLE public.cliente_events ADD updated_at timestamp(6) NULL;

-- Drop table

-- DROP TABLE public.clientes;

CREATE TABLE public.clientes (
	status bool NULL,
	id uuid NOT NULL,
	"password" varchar(255) NULL,
	CONSTRAINT clientes_pkey PRIMARY KEY (id),
	CONSTRAINT fktk4yna9cqo54xshjc9dpgbmc8 FOREIGN KEY (id) REFERENCES public.personas(id)
);

-- Drop table

-- DROP TABLE public.cliente_events;

CREATE TABLE public.cliente_events (
	created_at timestamp(6) NOT NULL,
	id int8 NOT NULL,
	updated_at timestamp(6) NULL,
	event_id varchar(255) NOT NULL,
	event_type varchar(255) NULL,
	payload text NOT NULL,
	CONSTRAINT cliente_events_event_id_key UNIQUE (event_id),
	CONSTRAINT cliente_events_event_type_check CHECK (((event_type)::text = 'CLIENTE_CREATED'::text)),
	CONSTRAINT cliente_events_pkey PRIMARY KEY (id)
);

ALTER TABLE public.cliente_events ADD id int8 NOT NULL;

ALTER TABLE public.clientes ADD "password" varchar(255) NULL;

ALTER TABLE public.cliente_events ADD event_id varchar(255) NOT NULL;

ALTER TABLE public.cliente_events ADD event_type varchar(255) NULL;

ALTER TABLE public.personas ADD id uuid NOT NULL;

ALTER TABLE public.clientes ADD status bool NULL;

ALTER TABLE public.cliente_events ADD payload text NOT NULL;

ALTER TABLE public.clientes ADD id uuid NOT NULL;

ALTER TABLE public.personas ADD "name" varchar(255) NOT NULL;

ALTER TABLE public.personas ADD gender varchar(255) NULL;

ALTER TABLE public.personas ADD address varchar(255) NULL;

ALTER TABLE public.personas ADD age int4 NOT NULL;
