-- formated sql

-- changeset dubrovsky:2
 CREATE TABLE shelterCat(id BIGSERIAL, name varchar, welcomes_user_shelter varchar,
 tell_about_shelter varchar, schedule_work_shelter varchar, address_shelter varchar,
 contact_information_security varchar, safety_recommendations varchar)

 CREATE TABLE travelMap(id BIGSERIAL, file_path VARCHAR, file_size BIGSERIAL,
 media_type VARCHAR, date TIMESTAMP)