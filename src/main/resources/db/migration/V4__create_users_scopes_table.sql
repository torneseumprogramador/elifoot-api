CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE scopes_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE users (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('users_seq'),
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE scopes (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('scopes_seq'),
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users_scopes (
    user_id BIGINT NOT NULL,
    scope_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, scope_id),
    CONSTRAINT fk_user_scopes_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_scopes_scope FOREIGN KEY (scope_id) REFERENCES scopes(id)
);