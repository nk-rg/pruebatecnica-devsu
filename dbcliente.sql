CREATE TYPE genero_enum AS ENUM ('H', 'M', 'O');
CREATE SEQUENCE persona_sequence;

CREATE TABLE persona(
    id_persona INT DEFAULT nextval('persona_sequence') PRIMARY KEY,
    nombre VARCHAR(100),
    genero genero_enum,
    edad INT,
    identificacion VARCHAR(13),
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

CREATE TABLE cliente(
    id_cliente INT PRIMARY KEY REFERENCES persona(id_persona),
    clave VARCHAR(100) NOT NULL,
    estado BOOLEAN NOT NULL
);