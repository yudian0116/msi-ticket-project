create type role as enum ('admin', 'customer');

create table users(
	id int primary key,
	email varchar(100) not null,
	password varchar(100) not null,
	role role,
	active boolean default true
);

create sequence public.user_seq
	increment 1
	start 1
	minvalue 1
	maxvalue 9223372036854775807
	cache 1;

alter sequence public.user_seq
	owner to postgres;

CREATE OR REPLACE FUNCTION public.USER_ID()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
 BEGIN
   New.id:=nextval('USER_SEQ');
   Return NEW;
 END;
 
$BODY$;

ALTER FUNCTION public.USER_ID()
    OWNER TO postgres;

insert into users (id, email, password, role) values (1, 'admin@ticket.com', '$2a$11$aL.ou06hFDE1p23WLTf6..yeq879FxCWZEE8ATEzkU/lw/Utaut2m', 'admin');
insert into users (id, email, password, role) values (2, 'customer@ticket.com', '$2a$11$D031sn4yBKa8m3KmUc.fGuvjCwwyadyrVgfU3SH23McMenLj9chF.', 'customer');

----------------------------------------------------------------------------------------------------------------------------------------------------------

create table carts (
	id int primary key,
	user_id int not null,
	total numeric(10, 2)
);

create sequence public.cart_seq
	increment 1
	start 1
	minvalue 1
	maxvalue 9223372036854775807
	cache 1;

alter sequence public.cart_seq
	owner to postgres;

CREATE OR REPLACE FUNCTION public.CART_ID()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
 BEGIN
   New.id:=nextval('cart_SEQ');
   Return NEW;
 END;
 
$BODY$;

ALTER FUNCTION public.cart_ID()
    OWNER TO postgres;

----------------------------------------------------------------------------------------------------------------------------------------------------------

create table cart_items (
	id int primary key,
	cart_id int not null,
	ticket_id int not null,
	quantity int,
	subtotal numeric(10, 2)
);

create sequence public.cart_item_seq
	increment 1
	start 1
	minvalue 1
	maxvalue 9223372036854775807
	cache 1;

alter sequence public.cart_item_seq
	owner to postgres;

CREATE OR REPLACE FUNCTION public.CART_item_ID()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
 BEGIN
   New.id:=nextval('cart_item_SEQ');
   Return NEW;
 END;
 
$BODY$;

ALTER FUNCTION public.cart_item_ID()
    OWNER TO postgres;
