CREATE table product
(

    id             VARCHAR(50) PRIMARY KEY,
    name           VARCHAR(50) NOT NULL,
    dtCompra       DATE,
    price_in_cents INT         not null,
    active         BOOLEAN     not null
);