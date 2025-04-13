CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE roles_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE users (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('users_seq'),
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE roles (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('roles_seq'),
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES roles(id)
);