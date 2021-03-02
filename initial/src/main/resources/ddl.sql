create table public.customer (
	id bigint GENERATED ALWAYS AS identity,
	firstName text null default null,
	lastName  text null default null
);

create table public."attribute" (
	id bigint GENERATED ALWAYS AS identity,
	"type" text not null,
	name text not null,
	value json,
	CONSTRAINT attribute_pk PRIMARY KEY (id)
);

create table public."user" (
	id bigint GENERATED ALWAYS AS identity,
	email text not null,
	CONSTRAINT user_pk PRIMARY KEY (id)
);

create table public.country (
	id bigint not null generated ALWAYS AS identity,
	name text not null,
	CONSTRAINT country_pk PRIMARY KEY (id)
);

create table public.category (
	id bigint not null generated ALWAYS AS identity,
	name text not null,
	CONSTRAINT category_pk PRIMARY KEY (id)
);

create table "policy" (
	id bigint not null generated ALWAYS AS identity,
	name text not null,
	CONSTRAINT policy_pk PRIMARY KEY (id)
);

create table "rule" (
	id bigint not null generated ALWAYS AS identity,
	policy_id bigint not null,
	action_type text not null,
	"operator" text not null,
	CONSTRAINT rule_pk PRIMARY KEY (id),
	CONSTRAINT rule_policy_fk FOREIGN KEY (policy_id) REFERENCES "policy"(id) ON DELETE cascade
);

create table entity_attribute (
	id bigint not null generated ALWAYS AS identity,
	entity_id bigint not null,
	entity_type text not null,
	attribute_id bigint not null,
	CONSTRAINT entity_attribute_pk PRIMARY KEY (id)
);

alter table customer
add column category_id bigint not null;

alter table category
add column country_id bigint not null,
add CONSTRAINT category_fk FOREIGN KEY (country_id) REFERENCES country(id) ON DELETE restrict;

alter table "user"
add column policy_id bigint;


