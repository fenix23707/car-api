create table if not exists dealers (
    id bigserial,
    company_name varchar(150) not null,
    director_name varchar(150) not null ,
    phone varchar(15) not null unique,

    primary key (id)
);

create table if not exists engines (
    id bigserial,
    type varchar(30) not null ,
    power int check (power > 0),

    primary key (id)
);

create table if not exists vehicles (
    id bigserial,
    make varchar(80) not null,
    model varchar(80) not null,
    "year" int,
    price numeric(12,3) not null,
    engine_id bigint not null unique, -- 1 to 1 relation

    primary key (id),
    foreign key (engine_id) references engines (id) on delete restrict on update cascade,
    constraint check_positive check ("year" > 1800),
    unique(make, model, "year")
);

create table if not exists colors (
    id bigserial,
    hex varchar(6) not null ,
    vehicle_id bigint not null,

    primary key (id),
    foreign key (vehicle_id) references vehicles (id) on delete restrict on update cascade
);

create table if not exists dealers_vehicles (
    dealer_id bigint,
    vehicle_id bigint,

    primary key (dealer_id, vehicle_id),
    foreign key (dealer_id) references dealers (id) on delete cascade on update cascade,
    foreign key (vehicle_id) references vehicles (id) on delete cascade on update cascade
);
