create table users
(
	id serial,
	username character varying not null,
	display_name character varying,
	email character varying not null,
	created_at timestamp default now() not null
);

create unique index users_email_uindex
	on users (email);

create unique index users_id_uindex
	on users (id);

create unique index users_username_uindex
	on users (username);

alter table users
	add constraint users_pk
		primary key (id);

INSERT INTO users(username, email, created_at)
VALUES
    ('Carlos Fontana', 'carlos.fontana@redb.ee', '2021-07-16 00:00:00'),
    ('Deborah Mendez', 'deborah.mendez@redb.ee', '2021-07-16 00:00:00'),
    ('Florencia Do Nascimento', 'florencia.donascimento@redb.ee', '2021-07-16 00:00:00'),
    ('Guillermo Fernandez Kessler', 'guillermo.kessler@redb.ee', '2021-09-14 00:00:00'),
    ('Laura Carelli', 'laura.carelli@redb.ee', '2021-09-30 00:00:00'),
    ('Leonardo Dalmasso', 'leo.dalmasso@redb.ee', '2021-07-16 00:00:00'),
    ('Martin Britez', 'martin.britez@redb.ee', '2021-07-16 00:00:00'),
    ('Santiago Leiva', 'santiago.leiva@redb.ee', '2021-07-16 00:00:00'),
    ('Francisca Tapia', 'fdtapia@falabella.cl', '2021-07-20 00:00:00'),
    ('Nicolas Sarquis', 'nsarquisc@falabella.cl', '2021-07-20 00:00:00'),
    ('Matias Pinto', 'mandpinto@falabella.cl', '2021-07-20 00:00:00');
