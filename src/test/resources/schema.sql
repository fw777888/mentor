DROP TABLE IF EXISTS mentor;
DROP TABLE IF EXISTS student;

CREATE TABLE IF NOT EXISTS mentor(
                                     id BIGSERIAL PRIMARY KEY,
                                     name VARCHAR(30),
                                     last_name VARCHAR(30),
                                     phone VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS student(
                                     id BIGSERIAL PRIMARY KEY,
                                     name VARCHAR(30),
                                     last_name VARCHAR(30),
                                     phone VARCHAR(30)
);