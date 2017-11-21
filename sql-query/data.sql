CREATE TABLE person(
iduser serial PRIMARY KEY,
nama VARCHAR(255) NOT NULL,
kelamin VARCHAR(255) NOT NULL,
telepon VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
usia VARCHAR(255)
);

insert into person(nama, kelamin, telepon, email, usia) 
values('dickanirwansyah', 'Laki-Laki', '098727282828', 'dickanirwansyah@yahoo.com', '25');
