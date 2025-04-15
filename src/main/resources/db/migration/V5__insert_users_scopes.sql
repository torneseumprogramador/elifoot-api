insert into scopes(id, name) values(nextval('scopes_seq'), 'admin:all');
insert into scopes(id, name) values(nextval('scopes_seq'), 'stadium:write');
insert into scopes(id, name) values(nextval('scopes_seq'), 'stadium:read');
insert into scopes(id, name) values(nextval('scopes_seq'), 'club:write');
insert into scopes(id, name) values(nextval('scopes_seq'), 'club:read');
insert into scopes(id, name) values(nextval('scopes_seq'), 'player:write');
insert into scopes(id, name) values(nextval('scopes_seq'), 'player:read');

insert into users(id, name, email, password, active) values(nextval('users_seq'), 'admin', 'admin@java10x', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);
insert into users(id, name, email, password, active) values(nextval('users_seq'), 'user', 'user@java10x', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);

insert into users_scopes(user_id, scope_id) values(1, 1);
insert into users_scopes(user_id, scope_id) values(2, 3);
insert into users_scopes(user_id, scope_id) values(2, 5);
insert into users_scopes(user_id, scope_id) values(2, 7);