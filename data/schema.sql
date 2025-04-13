CREATE SEQUENCE stadium_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE club_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE player_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE stadium (
     id BIGINT PRIMARY KEY DEFAULT nextval('stadium_seq'),
     name VARCHAR(255) NOT NULL,
     city VARCHAR(255) NOT NULL,
     capacity INTEGER,
     url_img VARCHAR(255)
);

CREATE TABLE club (
    id BIGINT PRIMARY KEY DEFAULT nextval('club_seq'),
    name VARCHAR(255) NOT NULL,
    founded DATE NOT NULL,
    url_img VARCHAR(255),
    stadium_id BIGINT UNIQUE,
    CONSTRAINT fk_club_stadium FOREIGN KEY (stadium_id) REFERENCES stadium(id)
);

CREATE TABLE player (
    id BIGINT PRIMARY KEY DEFAULT nextval('player_seq'),
    name VARCHAR(255) NOT NULL,
    position VARCHAR(50) NOT NULL,
    shirt_number INTEGER NOT NULL,
    url_img VARCHAR(255),
    club_id BIGINT NOT NULL,
    CONSTRAINT fk_player_club FOREIGN KEY (club_id) REFERENCES club(id)
);

INSERT INTO stadium (id, name, city, capacity) VALUES
    (nextval('stadium_seq'), 'Estadio Centenario', 'Montevideo', 60000),
    (nextval('stadium_seq'), 'Gran Parque Central', 'Montevideo', 34000),
    (nextval('stadium_seq'), 'Estadio Campeón del Siglo', 'Montevideo', 40000),
    (nextval('stadium_seq'), 'Estadio Luis Franzini', 'Montevideo', 18000),
    (nextval('stadium_seq'), 'Estadio Belvedere', 'Montevideo', 10000),
    (nextval('stadium_seq'), 'Estadio Jardines del Hipódromo', 'Montevideo', 18000);

INSERT INTO club (id, name, founded, stadium_id) VALUES
    (nextval('club_seq'), 'Club Nacional de Football', '1899-05-14', 2),
    (nextval('club_seq'), 'Club Atlético Peñarol', '1891-09-28', 3),
    (nextval('club_seq'), 'Defensor Sporting Club', '1913-03-15', 4),
    (nextval('club_seq'), 'Liverpool Fútbol Club', '1915-06-15', 5),
    (nextval('club_seq'), 'Danubio Fútbol Club', '1932-03-01', 6),
    (nextval('club_seq'), 'Uruguay National Team', '1901-05-30', 1);

-- Jogadores do Nacional
INSERT INTO player (id, name, position, shirt_number, club_id) VALUES
    (nextval('player_seq'), 'Luis Suárez', 'FORWARD', 9, 1),
    (nextval('player_seq'), 'Rodrigo Erramuspe', 'DEFENDER', 2, 1);

-- Jogadores do Peñarol
INSERT INTO player (id, name, position, shirt_number, club_id) VALUES
    (nextval('player_seq'), 'Facundo Torres', 'MIDFIELDER', 10, 2),
    (nextval('player_seq'), 'Kevin Dawson', 'GOALKEEPER', 1, 2);

-- Jogadores do Defensor
INSERT INTO player (id, name, position, shirt_number, club_id) VALUES
    (nextval('player_seq'), 'Álvaro Navarro', 'FORWARD', 7, 3),
    (nextval('player_seq'), 'Emiliano Álvarez', 'DEFENDER', 4, 3);

-- Jogadores do Liverpool
INSERT INTO player (id, name, position, shirt_number, club_id) VALUES
    (nextval('player_seq'), 'Juan Ignacio Ramírez', 'FORWARD', 11, 4),
    (nextval('player_seq'), 'Camilo Cándido', 'MIDFIELDER', 6, 4);

-- Jogadores do Danubio
INSERT INTO player (id, name, position, shirt_number, club_id) VALUES
    (nextval('player_seq'), 'Santiago Mederos', 'MIDFIELDER', 8, 5),
    (nextval('player_seq'), 'Mauro Goicoechea', 'GOALKEEPER', 1, 5);

-- Jogadores da Seleção do Uruguai (Clube 6)
INSERT INTO player (id, name, position, shirt_number, club_id) VALUES
    (nextval('player_seq'), 'Federico Valverde', 'MIDFIELDER', 15, 6),
    (nextval('player_seq'), 'Ronald Araújo', 'DEFENDER', 4, 6);