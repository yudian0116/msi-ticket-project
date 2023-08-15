create type status as enum ('open', 'paid', 'completed', 'canceled');

create table order (
	id int primary key,
	user_id int not null,
	total numeric(10, 2),
	date_time timestamp with time zone,
	status status
);

create sequence public.order_seq
	increment 1
	start 1
	minvalue 1
	maxvalue 9223372036854775807
	cache 1;

alter sequence public.order_seq
	owner to postgres;

CREATE OR REPLACE FUNCTION public.order_ID()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
 BEGIN
   New.id:=nextval('order_SEQ');
   Return NEW;
 END;
 
$BODY$;

ALTER FUNCTION public.order_ID()
    OWNER TO postgres;

----------------------------------------------------------------------------

create table order_item (
	id int primary key,
	order_id int not null,
	ticket_id int not null,
	quantity int,
	subtotal numeric(10, 2)
);

create sequence public.order_item_seq
	increment 1
	start 1
	minvalue 1
	maxvalue 9223372036854775807
	cache 1;

alter sequence public.order_item_seq
	owner to postgres;

CREATE OR REPLACE FUNCTION public.order_item_ID()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
 BEGIN
   New.id:=nextval('order_item_SEQ');
   Return NEW;
 END;
 
$BODY$;

ALTER FUNCTION public.order_item_ID()
    OWNER TO postgres;

--------------------------------------------------------------------

create type edit_status as enum ('open', 'approved', 'denied', 'canceled');

create table edit_order_request (
	id int primary key,
	user_id int not null,
	order_id int not null,
	old_order_item_id int not null,
	new_order_item_id int not null,
	status edit_status
);

create sequence public.edit_order_request_seq
	increment 1
	start 1
	minvalue 1
	maxvalue 9223372036854775807
	cache 1;

alter sequence public.edit_order_request_seq
	owner to postgres;

CREATE OR REPLACE FUNCTION public.edit_order_request_ID()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
 BEGIN
   New.id:=nextval('edit_order_request_SEQ');
   Return NEW;
 END;
 
$BODY$;

ALTER FUNCTION public.edit_order_request_ID()
    OWNER TO postgres;