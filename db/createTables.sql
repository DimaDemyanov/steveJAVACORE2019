CREATE TABLE profiles(
 user_id serial PRIMARY KEY,
 name VARCHAR (50) UNIQUE NOT NULL,
 dna VARCHAR (256),
 fingerprint VARCHAR (256)
);