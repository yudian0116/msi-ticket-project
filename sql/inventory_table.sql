create table inventory (
	ticket_id int primary key,
    quantity int
);

insert into inventory (ticket_id, quantity) values (1, 30);
insert into inventory (ticket_id, quantity) values (2, 10);
insert into inventory (ticket_id, quantity) values (3, 0);