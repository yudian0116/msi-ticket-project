create table orders (
	id int primary key,
	user_id int,
	total numeric(10, 2),
	date_time timestamp with time zone,
	status VARCHAR(10)
);

create sequence public.order_seq
	increment 1
	start 5
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

insert into orders (id, user_id, total, date_time, status) values (1, 2, 100.00, '2023-8-15 20:30:00-07', 'open');

----------------------------------------------------------------------------

create table order_item (
	id int primary key,
	order_id int,
	ticket_id int,
	price numeric(10, 2),
	quantity int,
	subtotal numeric(10, 2)
);

create sequence public.order_item_seq
	increment 15
	start 5
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

insert into order_item (id, order_id, ticket_id, price, quantity, subtotal) values (1, 1, 1, 50.00, 1, 50.00);
insert into order_item (id, order_id, ticket_id, price, quantity, subtotal) values (2, 1, 2, 25.00, 2, 50.00);

--------------------------------------------------------------------

create type edit_status as enum ('open', 'approved', 'denied', 'canceled');

create table edit_order_request (
	id int primary key,
	user_id int,
	order_id int,
	old_order_item_id int,
	new_order_item_id int,
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