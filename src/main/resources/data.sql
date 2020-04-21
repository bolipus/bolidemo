/*DELETE FROM public."xuser_xrole";
DELETE FROM public."xuser";
DELETE FROM public."xrole"; 
DELETE FROM public."game" 
DELETE FROM public."position" 
DELETE FROM public."team";
DELETE FROM public."player" 
*/

INSERT INTO public."xuser" (id, username, password) VALUES 
(1, 'admin', '$2a$10$eMLmwZ5l7l94XC3XKB36huzccXNPcKCHrgyn4RSaTQ5AJZ4uA.oU6'),
(2,'manager','$2a$10$VMOGGFaadUzkaFxcCseSF.mxzLigDuR8PymYcAdCqVTk/BrBtfBne'),
(3,'demo','$2a$10$U8e3q6V/7NAPL7BaCqZbDeBafPm77siHh8uFjadIkh.J5vQozkjT.'),
(4,'demo2','$2a$10$E/ZT787UbiEbOBhhidbskeWEjAjiT5FnGlz8pvJXtNwZwli3o42V6');


INSERT INTO public."xrole" (id, name, description) VALUES 
(1, 'ROLE_ADMIN', 'Admin'),
(2, 'ROLE_MANAGE', 'Manager'),
(3, 'ROLE_USER', 'User');

INSERT INTO public."xuser_xrole" (user_id, role_id) VALUES
(1,1),
(1,2),
(1,3),
(2,2),
(3,3),
(4,3);
--DELETE FROM public.


--TEAMS
INSERT INTO public."team" (id, description, title) VALUES
(1,'Test description', 'NK Maribor'),
(2,'Mura description', 'NK Mura'),
(3,'Celje description', 'NK Celje'),
(4,'Ljubljana description', 'NK Olimpija'),
(5,'Aluminij description', 'NK Aluminij'),
(6,'Domžale description', 'NK Domžale');


INSERT INTO public."position" (id, name, description) VALUES
(1,'Goalkeeper', 'Goalkeeper'),
(2,'Defender','Defender'),
(3,'Centre-back','Centre-back'),
(4,'Sweeper','Sweeper'),
(5,'Full-back','Full-back'),
(6,'Wing-back','Wing-back'),
(7,'Midfielder','Midfielder'),
(8,'Centre midfield','Centre midfield'),
(9,'Defensive midfield','Defensive midfield'),
(10,'Defensive midfield','Defensive midfield'),
(11,'Wide midfield','Wide midfield'),
(12,'Forward','Forward'),
(13,'Centre forward','Centre forward'),
(14,'Centre forward','Centre forward'),
(15,'Winger','Winger');

--MEMBERS
INSERT INTO public."player" (id, birthday, email, first_name, last_name, phone, position_id, team_id) VALUES
(1, '1996-8-16', 'nino.pubec@test.si', 'Nino', 'Pubec', '00-00-1234', 1,1),
(3, '1984-1-15', 'milan.stajerski2@test.si', 'Milan', 'Štajerski', '00-00-1234', 8, 1),
(4, '1990-12-25', 'joze.mariborcan@test.si', 'Jože', 'Mariborčan', '00-00-1234', 14, 1),
(5, '1984-12-12', 'janez.prekmurski@test.si', 'Janez', 'Prekmurski', '00-00-0000', 1, 2),
(6, '1997-7-20', 'jaka.sobočan@test.si', 'Jaka', 'Sobočan', '22-22-1234',7,2),
(7, '1984-8-12', 'zlatko.novak@test.si', 'Zlatko', 'Novak', '22-22-1234',11, 2),
(8, '1995-5-4', 'janez.celjan@test.si', 'Janez', 'Celjan', '00-00-0000', 1, 3),
(9, '1981-8-5', 'jaka.celjan@test.si', 'Jaka', 'Savinjčan', '22-22-1234',8,3),
(10, '1983-8-12', 'zlatko.laščan@test.si', 'Zlatko', 'Laščan', '22-22-1234',15, 3),
(11, '1980-4-4', 'mirko.zmaj@test.si', 'Janez', 'Zmaj', '00-00-0000', 1, 4),
(12, '1986-10-5', 'jaka.ljubjancan@test.si', 'Jaka', 'Ljubljančan', '22-22-1234',5,4),
(13, '1983-9-18', 'boris.močvirnik@test.si', 'Boris', 'Močvirnik', '22-22-1234',10, 4),
(14, '1995-6-25', 'milan.kider@test.si', 'Milan', 'Kider', '00-00-0000', 1, 5),
(15, '1988-8-5', 'marko.strmek@test.si', 'Marko', 'Strmek', '22-22-1234',4,5),
(16, '1987-6-24', 'slavko.morski@test.si', 'Slavko', 'Morski', '22-22-1234',14, 5),
(17, '1994-3-15', 'milan.domzal@test.si', 'Milan', 'Domzal', '00-00-0000',1,6),
(18, '1987-9-29', 'jaka.jazbec@test.si', 'Jaka', 'Jazbec', '22-22-1234',6,6),
(19, '1984-8-24', 'tilen.kos@test.si', 'Tilen', 'Kos', '22-22-1234',13,6);



