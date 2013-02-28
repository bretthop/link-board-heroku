CREATE TABLE user_account (
  id serial PRIMARY KEY,
  username text NOT NULL,
  password text NOT NULL,
  email text,
  first_name text,
  last_name text
);

CREATE TABLE link_group (
  id serial PRIMARY KEY,
  user_account_id INT NOT NULL REFERENCES user_account,
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