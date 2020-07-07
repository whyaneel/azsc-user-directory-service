DROP TABLE IF EXISTS user_directory cascade;

CREATE TABLE user_directory (
  id VARCHAR(250) NOT NULL PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  contact_number VARCHAR(250),
  is_deleted BOOLEAN DEFAULT FALSE
);

DROP TABLE IF EXISTS address_directory;

CREATE TABLE address_directory (
  user_id VARCHAR(250) NOT NULL,
  address_line1 VARCHAR(250),
  address_line2 VARCHAR(250),
  city VARCHAR(250),
  country VARCHAR(250) NOT NULL,
  postal_code VARCHAR(250) NOT NULL
);

INSERT INTO user_directory (id, first_name, last_name) VALUES
  ('S8823678E', 'Kylie', 'Humphrey'),
  ('S7993823H', 'Julianne', 'Henderson'),
  ('S1502746J', 'Samson', 'Villegas'),
  ('S1788749A', 'Frances', 'Dillon'),
  ('S5773720D', 'Julie', 'Magnate'),
  ('G2313040N', 'Joselyn', 'Henderson'),
  ('G6363972P', 'Natalee', 'Magnate'),
  ('G6463972P', 'Darcie', 'Magnate'),
  ('G6563972P', 'Beatrice', 'Magnate'),
  ('G6663972P', 'Beatrice', 'Henderson'),
  ('G6763972P', 'Cora', 'Magnate'),
  ('G6863972P', 'Cleo', 'Magnate'),
  ('G6963972P', 'Kye', 'Magnate'),
  ('G6163972P', 'Bethan', 'Magnate'),
  ('G6263972P', 'Robbie', 'Magnate'),
  ('G6343972P', 'Samson', 'Magnate'),
  ('G8333471T', 'Jefferson', 'Magnate');

INSERT INTO user_directory (id, first_name, last_name, is_deleted) VALUES
  ('T1917402B', 'Camren', 'Humphrey', true),
  ('F5399943M', 'Alessandro', 'Magnate', true);

INSERT INTO address_directory (user_id, country, postal_code) VALUES
  ('S1788749A', 'Singapore', '520337'),
  ('S5773720D', 'Singapore', '520257'),
  ('G2313040N', 'Singapore', '520357'),
  ('S8823678E', 'Singapore', '520237'),
  ('S8823678E', 'Malaysia', '520241'),
  ('F5399943M', 'Singapore', '520247'),
  ('T1917402B', 'Singapore', '520248'),
  ('G8333471T', 'Malaysia', '520249'),
  ('G6363972P', 'Singapore', '520253'),
  ('G6463972P', 'Singapore', '520253'),
  ('G6563972P', 'Singapore', '520253'),
  ('G6663972P', 'Singapore', '520253'),
  ('G6763972P', 'Singapore', '520253'),
  ('G6863972P', 'Malaysia', '520253'),
  ('G6963972P', 'Singapore', '520253'),
  ('G6163972P', 'Singapore', '520253'),
  ('G6263972P', 'Singapore', '520253'),
  ('G6343972P', 'Singapore', '520253'),
  ('G6363972P', 'Singapore', '520253'),
  ('S1502746J', 'Malaysia', '520241'),
  ('S7993823H', 'Malaysia', '123456');