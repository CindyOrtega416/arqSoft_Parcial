-- You can use this file to load seed data into the database using SQL statements
-- You can use this file to load seed data into the database using SQL statements
insert into PELICULA (TITULO, SINOPSIS, ANIO, GENERO) values ('Resident Evil','excelente', 2002, 0);

insert into ALQUILER (FECHA_ALQUILER, SOCIO_ID, PELICULA_ID) values (current_date(), 1, 1);

insert into SOCIO (NOMBRE , APELLIDO , DNI, EMAIL) values ('Cindy', 'Ortega', '40402552', 'cindyortega416@gmail.com');