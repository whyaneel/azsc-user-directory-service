DROP TABLE IF EXISTS address_directory;
DROP TABLE IF EXISTS user_directory cascade;

CREATE TABLE user_directory (
  id VARCHAR(250) NOT NULL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  contact_number VARCHAR(250),
  is_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE address_directory (
  user_id VARCHAR(250) NOT NULL,
  address_line1 VARCHAR(250),
  address_line2 VARCHAR(250),
  city VARCHAR(250),
  country VARCHAR(250) NOT NULL,
  postal_code VARCHAR(250) NOT NULL
);