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
  id BIGSERIAL PRIMARY KEY,
  name_cat VARCHAR,
  info_cat VARCHAR,
  photo_cat VARCHAR
);
CREATE TABLE shelter_cat
(
  id BIGSERIAL PRIMARY KEY,
  name varchar,
  welcomes_user_shelter_cat varchar,
  tell_about_shelter_cat varchar,
  schedule_work_shelter_cat varchar,
  address_shelter_cat varchar,
  contact_information_security_cat varchar,
  safety_recommendations_cat varchar
);
CREATE TABLE shelter_dog
(
  id BIGSERIAL PRIMARY KEY,
  name varchar,
  welcomes_user_shelter_dog varchar,
  tell_about_shelter_dog varchar,
  schedule_work_shelter_dog varchar,
  address_shelter_dog varchar,
  contact_information_security_dog varchar,
  safety_recommendations_dog varchar
);
CREATE TABLE volunteer
(
  id BIGSERIAL,
  name_volunteer VARCHAR,
  phone_volunteer VARCHAR
);
CREATE TABLE recom_shelter_cat
(
  id BIGSERIAL PRIMARY KEY,
  name varchar,
  rules_dating varchar,
  list_documents varchar,
  transport_animal varchar,
  home_improvement varchar,
  home_improvement_old_animal varchar,
  home_improvement_limited_capabilities varchar,
  reasons_refusal varchar
)
