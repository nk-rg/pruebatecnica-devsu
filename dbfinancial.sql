
CREATE SEQUENCE cuenta_seq START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCACHE;
  
CREATE SEQUENCE movimiento_seq
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE
  NOCACHE;

CREATE TABLE cuenta(
    id_cuenta INT DEFAULT cuenta_seq.NEXTVAL PRIMARY KEY,
    numero_cuenta VARCHAR(30) NOT NULL,
    tipo VARCHAR2(100) NOT NULL,
    saldo NUMBER(12, 2) NOT NULL,
    estado NUMBER(1, 0) NOT NULL,
    id_cliente INT NOT NULL
);

CREATE TABLE movimiento(
    id_movimiento INT DEFAULT movimiento_seq.NEXTVAL PRIMARY KEY,
    id_cuenta INT NOT NULL REFERENCES cuenta(id_cuenta),
    fecha DATE NOT NULL,
    valor NUMBER(12, 2) NOT NULL,
    saldo_inicial NUMBER(12, 2) NOT NULL
);

