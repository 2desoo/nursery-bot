-- formated sql

-- changeset dubrovsky:1
/**
  Create table for users in database
 */
CREATE TABLE users
(
  id BIGSERIAL,
  name VARCHAR,
  chat_Id BIGSERIAL PRIMARY KEY
);
/*
  Create table for cats in database
 */
CREATE TABLE shelter_cat
(
    id BIGSERIAL,
    name VARCHAR,
    tell_about_shelter VARCHAR,
    schedule_work_shelter VARCHAR,
    address_shelter VARCHAR
);

