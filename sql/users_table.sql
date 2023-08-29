create table users (
	id int primary key,
	email varchar(100),
	password varchar(255),
	name varchar(50),
	role int,
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

insert into users (id, email, password, name, role) values (1, 'admin@ticket.com', '$2a$11$aL.ou06hFDE1p23WLTf6..yeq879FxCWZEE8ATEzkU/lw/Utaut2m', 'admin', 1);
insert into users (id, email, password, name, role) values (2, 'customer@ticket.com', '$2a$11$D031sn4yBKa8m3KmUc.fGuvjCwwyadyrVgfU3SH23McMenLj9chF.', 'customer', 2);

----------------------------------------------------------------------------------------------------------------------------------------------------------

create table tokens (
	id int primary key,
	user_id int,
	user_token varchar(255),
	create_at timestamp with time zone,
	expire_at timestamp with time zone
);

create sequence public.user_token_seq
	increment 1
	start 1
	minvalue 1
	maxvalue 9223372036854775807
	cache 1;

alter sequence public.user_token_seq
	owner to postgres;

CREATE OR REPLACE FUNCTION public.USER_token_ID()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
 BEGIN
   New.id:=nextval('USER_token_SEQ');
   Return NEW;
 END;
 
$BODY$;

ALTER FUNCTION public.USER_token_ID()
    OWNER TO postgres;

-------------------------------------------------------------------------------------------------------------------------------------------------------

create table roles (
	id int primary key,
	role VARCHAR(20)
);

insert into roles (id, role) values (1, 'admin');
insert into roles (id, role) values (2, 'customer');