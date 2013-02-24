CREATE TABLE link (
  id serial PRIMARY KEY,
  title text NOT NULL,
  href text NOT NULL,
  description text
);