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