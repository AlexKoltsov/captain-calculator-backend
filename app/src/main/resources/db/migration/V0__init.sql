CREATE TABLE IF NOT EXISTS images
(
    id        uuid         NOT NULL,
    file_name VARCHAR(64)  NOT NULL,
    url       VARCHAR(255) NOT NULL,
    CONSTRAINT images_id_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS items
(
    id          uuid        NOT NULL,
    name        VARCHAR(64) NOT NULL,
    description TEXT,
    state       VARCHAR(64) NOT NULL,
    image_id    uuid,
    CONSTRAINT items_id_pkey PRIMARY KEY (id),
    CONSTRAINT items_image_id_fkey FOREIGN KEY (image_id) REFERENCES images (id)
);