create table if not exists public.users
(
    id               bigserial
        primary key,
    first_name       varchar(20),
    second_name      varchar(20),
    age              integer,
    email            varchar(200)
        constraint email
            unique,
    gender           varchar(1),
    telephone_number varchar(20),
    created_at       timestamp default now() not null,
    update_at        timestamp default now() not null,
    is_deleted       boolean   default false
);

alter table public.users
    owner to postgres;

create table if not exists public.security
(
    id         bigserial
        primary key,
    login      varchar(20)               not null
        constraint login
            unique,
    password   varchar(20)               not null,
    role       varchar(20) default 'USER'::character varying,
    created_at timestamp   default now() not null,
    updated_at timestamp   default now(),
    user_id    bigint                    not null
        constraint user___fk
            references public.users
            on update cascade on delete cascade
);

alter table public.security
    owner to postgres;

create table if not exists public.products
(
    id           bigserial
        constraint products_pk
            primary key,
    name_product varchar(60)             not null,
    price        double precision        not null,
    created_at   timestamp default now() not null,
    updated_at   timestamp default now()
);

alter table public.products
    owner to postgres;

create table if not exists public.l_users_product
(
    id         bigserial
        constraint l_users_product_pk
            primary key,
    user_id    bigint                  not null
        constraint l_users_product_users_id_fk
            references public.users
            on update cascade on delete cascade,
    product_id bigint                  not null
        constraint l_users_product_products_id_fk
            references public.products
            on update cascade on delete cascade,
    created_at timestamp default now() not null,
    updated_at timestamp default now()
);

alter table public.l_users_product
    owner to postgres;

