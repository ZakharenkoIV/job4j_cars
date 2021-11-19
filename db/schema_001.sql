create table roles
(
    id   serial primary key,
    name varchar(255) not null unique
);

create table users
(
    id       serial primary key,
    name     varchar(255) not null,
    role_id  integer      not null references roles (id),
    email    varchar(255) not null unique,
    password varchar(255) not null
);

create table ads
(
    id          serial primary key,
    description text         not null,
    brand_car   varchar(255) not null,
    body_type   varchar(255) not null,
    created     Timestamp    not null,
    done        boolean DEFAULT false,
    user_id     integer      not null references users (id)
);

create table ad_photos
(
    id     serial primary key,
    path   text    not null unique,
    ads_id integer not null references ads (id)
);