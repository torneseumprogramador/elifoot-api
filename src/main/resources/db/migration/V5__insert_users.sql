insert into roles(id, name) values(nextval('roles_seq'), 'admin');
insert into roles(id, name) values(nextval('roles_seq'), 'user');

insert into users(id, name, email, password, active) values(nextval('users_seq'), 'admin', 'admin@java10x', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);
insert into users(id, name, email, password, active) values(nextval('users_seq'), 'user', 'user@java10x', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);

insert into users_roles(user_id, role_id) values(1, 1);
insert into users_roles(user_id, role_id) values(2, 2);