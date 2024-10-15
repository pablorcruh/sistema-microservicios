ALTER TABLE public.cliente_events ADD id int8 NOT NULL;

ALTER TABLE public.movimientos ADD balance float8 NULL;

ALTER TABLE public.movimientos ADD "date" date NULL;

ALTER TABLE public.cliente_events ADD created_at timestamp(6) NOT NULL;

ALTER TABLE public.cuentas ADD status bool NULL;

-- Drop table

-- DROP TABLE public.cliente_events;

CREATE TABLE public.cliente_events (
	created_at timestamp(6) NOT NULL,
	id int8 NOT NULL,
	updated_at timestamp(6) NULL,
	event_id varchar(255) NOT NULL,
	payload text NULL,
	CONSTRAINT cliente_events_event_id_key UNIQUE (event_id),
	CONSTRAINT cliente_events_pkey PRIMARY KEY (id)
);

ALTER TABLE public.cliente_events ADD updated_at timestamp(6) NULL;

ALTER TABLE public.movimientos ADD id uuid NOT NULL;

ALTER TABLE public.cuentas ADD id uuid NOT NULL;

ALTER TABLE public.movimientos ADD value float8 NULL;

ALTER TABLE public.cliente_events ADD event_id varchar(255) NOT NULL;

ALTER TABLE public.cuentas ADD cliente_name varchar(255) NULL;

ALTER TABLE public.cuentas ADD account_number varchar(255) NULL;

ALTER TABLE public.cuentas ADD account_type varchar(255) NULL;

ALTER TABLE public.movimientos ADD account_id uuid NULL;

-- Drop table

-- DROP TABLE public.movimientos;

CREATE TABLE public.movimientos (
	balance float8 NULL,
	"date" date NULL,
	value float8 NULL,
	account_id uuid NULL,
	id uuid NOT NULL,
	movement_type varchar(255) NULL,
	CONSTRAINT movimientos_pkey PRIMARY KEY (id),
	CONSTRAINT fkhd9027bdhwkjmiqwxwwvrqeq0 FOREIGN KEY (account_id) REFERENCES public.cuentas(id)
);

ALTER TABLE public.cuentas ADD initial_balance float8 NULL;

ALTER TABLE public.cliente_events ADD payload text NULL;

-- Drop table

-- DROP TABLE public.cuentas;

CREATE TABLE public.cuentas (
	initial_balance float8 NULL,
	status bool NULL,
	id uuid NOT NULL,
	account_number varchar(255) NULL,
	account_type varchar(255) NULL,
	cliente_name varchar(255) NULL,
	CONSTRAINT cuentas_pkey PRIMARY KEY (id)
);

ALTER TABLE public.movimientos ADD movement_type varchar(255) NULL;
