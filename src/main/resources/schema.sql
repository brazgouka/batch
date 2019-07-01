DROP TABLE IF EXISTS policy;

create table policy
(
    id         varchar(50) not null
        constraint policy_pk
            primary key,
    policy_id  varchar(50),
    state_code varchar(50),
    country    varchar(50)
);