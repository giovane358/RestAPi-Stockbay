CREATE TABLE auth(

    id       VARCHAR(50) PRIMARY KEY,
    login    VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    photo    VARCHAR(50),
    role     VARCHAR(50) NOT NULL
                 );