create table cart (
	id int primary key,
	user_id int,
	total numeric(10, 2)
);

create sequence public.cart_seq
	increment 1
	start 5
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

insert into cart (id, user_id, total) values (1, 2, 25.00);

----------------------------------------------------------------------------------------------------------------------------------------------------------

create table cart_item (
	id int primary key,
	cart_id int,
	ticket_id int,
	price numeric(10, 2),
	quantity int,
	subtotal numeric(10, 2)
);

create sequence public.cart_item_seq
	increment 15
	start 5
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

insert into cart_item (id, cart_id, ticket_id, price, quantity, subtotal) values (1, 1, 2, 25.00, 1, 25.00);