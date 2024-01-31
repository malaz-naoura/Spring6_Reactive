create table if not exists juice(
    id integer not null primary key auto_increment,
    name varchar(255),
    style varchar(255),
    upc varchar(25),
    quantity_on_hand integer,
    price decimal,
    created_date timestamp,
    last_modified_date timestamp
    );