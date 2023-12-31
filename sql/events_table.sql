 create table venue (
	id int primary key,
	name varchar(100),
	address varchar(200),
	owner varchar(50),
	phone varchar(20),
	capacity int,
	image varchar(255)
);

create sequence public.venue_seq
	increment 1
	start 1
	minvalue 1
	maxvalue 9223372036854775807
	cache 1;

alter sequence public.venue_seq
	owner to postgres;

CREATE OR REPLACE FUNCTION public.venue_ID()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
 BEGIN
   New.id:=nextval('venue_SEQ');
   Return NEW;
 END;
 
$BODY$;

ALTER FUNCTION public.venue_ID()
    OWNER TO postgres;

insert into venue (id, name, address, capacity) values (1, 'moonlight bar', '123 Minor Ave', 50);

----------------------------------------------------------------

create table event (
	id int primary key,
	name varchar(100),
	start_time timestamp with time zone,
	end_time timestamp with time zone,
	venue_id int,
	category_id int,
	image varchar(255),
	description varchar(1000)
);

create sequence public.event_seq
	increment 1
	start 1
	minvalue 1
	maxvalue 9223372036854775807
	cache 1;

alter sequence public.event_seq
	owner to postgres;

CREATE OR REPLACE FUNCTION public.event_ID()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
 BEGIN
   New.id:=nextval('event_SEQ');
   Return NEW;
 END;
 
$BODY$;

ALTER FUNCTION public.event_ID()
    OWNER TO postgres;

insert into event (id, name, venue_id, category_id, start_time, end_time) values (1, 'Yuki''s Solo Concert', 1, 1, TIMESTAMP '2023-10-15 20:30:00-07', TIMESTAMP '2023-10-15 23:30:00-07');

-----------------------------------------------------------------

create table category (
	id int primary key,
	name varchar(50)
);

create sequence public.category_seq
	increment 1
	start 1
	minvalue 1
	maxvalue 9223372036854775807
	cache 1;

alter sequence public.category_seq
	owner to postgres;

CREATE OR REPLACE FUNCTION public.category_ID()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
 BEGIN
   New.id:=nextval('category_SEQ');
   Return NEW;
 END;
 
$BODY$;

ALTER FUNCTION public.category_ID()
    OWNER TO postgres;

insert into category (id, name) values (1, 'concert');
insert into category (id, name) values (2, 'open class');

---------------------------------------------------------------

create table ticket (
	id int primary key,
	event_id int,
	price numeric(10, 2),
	type varchar(50)
);

create sequence public.ticket_seq
	increment 1
	start 1
	minvalue 1
	maxvalue 9223372036854775807
	cache 1;

alter sequence public.ticket_seq
	owner to postgres;

CREATE OR REPLACE FUNCTION public.ticket_ID()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
 BEGIN
   New.id:=nextval('ticket_SEQ');
   Return NEW;
 END;
 
$BODY$;

ALTER FUNCTION public.ticket_ID()
    OWNER TO postgres;

insert into ticket (id, event_id, price, type) values (1, 1, 50.00, 'VIP');
insert into ticket (id, event_id, price, type) values (2, 1, 25.00, 'Common');
insert into ticket (id, event_id, price, type) values (3, 1, 0.00, 'Test');