create table if not exists client
(
    id serial primary key,
    name varchar(50),
    genre varchar(50),
    age integer,
    identification varchar(50),
    address varchar(50),
    phone_number varchar(20),
    password varchar(50),
    status boolean
);

create table if not exists account
(
    id serial primary key,
    client_id int,
    account_number varchar(20),
    account_type varchar(20),
    balance decimal not null default 0,
    status boolean,
    constraint account_client_fk
        foreign key (client_id)
            references client (id)
);

create table if not exists movement
(
    id serial primary key,
    account_id int not null,
    created_date date not null default current_date,
    amount decimal not null,
    movement_type  varchar(20),
    previous_balance decimal,
    status boolean,
    constraint movement_account_fk
        foreign key (account_id)
            references account (id)
);