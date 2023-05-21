CREATE TABLE IF NOT EXISTS items
(
    id          uuid        NOT NULL,
    name        VARCHAR(64) NOT NULL,
    description TEXT,
    state       VARCHAR(64) NOT NULL
);