-- formated sql

-- changeset dubrovsky:1
CREATE TABLE users
(
  id BIGSERIAL,
  name VARCHAR,
  chat_Id BIGSERIAL PRIMARY KEY,
  phone VARCHAR
);
CREATE TABLE cat
(
  id BIGSERIAL,
  name_cat VARCHAR,
  info_cat VARCHAR,
  photo_cat VARCHAR
);
CREATE TABLE shelter
(
  id BIGSERIAL PRIMARY KEY,
  name varchar,
  welcomes_user_shelter varchar,
  tell_about_shelter varchar,
  schedule_work_shelter varchar,
  address_shelter varchar,
  contact_information_security varchar,
  safety_recommendations varchar
);
CREATE TABLE travel_map
(
  id BIGSERIAL PRIMARY KEY,
  file_path VARCHAR,
  file_size BIGSERIAL,
  media_type VARCHAR,
  picture bytea
);
CREATE TABLE volunteer
(
  id BIGSERIAL,
  name_volunteer VARCHAR,
  phone_volunteer VARCHAR
)
