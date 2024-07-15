-- formated sql

-- changeset dubrovsky:2
 CREATE TABLE shelter_cat(id BIGSERIAL PRIMARY KEY, name varchar, welcomes_user_shelter varchar,
 tell_about_shelter varchar, schedule_work_shelter varchar, address_shelter varchar,
 contact_information_security varchar, safety_recommendations varchar)