DROP TABLE IF EXISTS mentor;

CREATE TABLE IF NOT EXISTS mentor(
                                     id BIGSERIAL PRIMARY KEY,
                                     name VARCHAR(30),
                                     last_name VARCHAR(30),
                                     phone VARCHAR(30)
);