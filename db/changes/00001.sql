CREATE TABLE link_group (
  id serial PRIMARY KEY,
  title text NOT NULL,
  description text
);

CREATE TABLE link (
  id serial PRIMARY KEY,
  link_group_id INT NOT NULL REFERENCES link_group,
  title text NOT NULL,
  href text NOT NULL,
  description text
);