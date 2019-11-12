
--TEAMS
INSERT INTO public."team"
(id, description, title)
VALUES (1,'Test description', 'NK Maribor');

INSERT INTO public."team"
(id, description, title)
VALUES (2,'Mura description', 'NK Mura');

INSERT INTO public."team"
(id, description, title)
VALUES (3,'Celje description', 'NK Celje');


--MEMBERS
INSERT INTO public."player"
(id, birthday, email, first_name, last_name, phone, team_id)
VALUES(1, '1960-12-12', 'janez.kranjski@test.si', 'Janez', 'Kranjski', '00-00-0000', 1);

INSERT INTO public."player"
(id, birthday, email, first_name, last_name, phone, team_id)
VALUES(2, '1970-1-1', 'joze.starerski@test.si', 'Jože', 'Štajerski', '00-00-1234', 1);

INSERT INTO public."player"
(id, birthday, email, first_name, last_name, phone, team_id)
VALUES(3, '1975-4-15', 'jaka.prekmurski@test.si', 'Jaka', 'Prekmurski', '22-22-1234',2);


INSERT INTO public."player"
(id, birthday, email, first_name, last_name, phone, team_id)
VALUES(4, '1980-8-12', 'zlatko.novak@test.si', 'Zlatko', 'Novak', '22-22-1234', 2);




