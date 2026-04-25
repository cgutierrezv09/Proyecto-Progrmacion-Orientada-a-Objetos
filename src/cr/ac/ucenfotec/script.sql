CREATE DATABASE bd_subastas;

USE bd_subastas;


CREATE TABLE t_usuarios (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    nombre              VARCHAR(100)    NOT NULL,
    apellido            VARCHAR(100)    NOT NULL,
    fecha_nacimiento    DATE            NOT NULL,
    contrasena          VARCHAR(255)    NOT NULL,
    correo              VARCHAR(150)    NOT NULL UNIQUE,
    rol                 ENUM('MODERADOR', 'VENDEDOR', 'COLECCIONISTA') NOT NULL
);


CREATE TABLE t_moderadores (
   id_usuario          INT             PRIMARY KEY,
   FOREIGN KEY (id_usuario)            REFERENCES t_usuarios(id)           ON DELETE CASCADE
);


CREATE TABLE t_vendedores (
  id_usuario          INT             PRIMARY KEY,
  puntuacion          INT             NOT NULL DEFAULT 0,
  direccion           VARCHAR(255)    NOT NULL,
  FOREIGN KEY (id_usuario)            REFERENCES t_usuarios(id)           ON DELETE CASCADE
);


CREATE TABLE t_coleccionistas (
  id_usuario          INT             PRIMARY KEY,
  puntuacion          INT             NOT NULL DEFAULT 0,
  direccion           VARCHAR(255)    NOT NULL,
  FOREIGN KEY (id_usuario)            REFERENCES t_usuarios(id)           ON DELETE CASCADE
);


CREATE TABLE t_objetos (
   id                  INT AUTO_INCREMENT PRIMARY KEY,
   nombre              VARCHAR(150)    NOT NULL,
   descripcion         TEXT,
   estado              VARCHAR(100)    NOT NULL,
   fecha_compra        DATE            NOT NULL,
   id_coleccionista    INT             NOT NULL,
   FOREIGN KEY (id_coleccionista)      REFERENCES t_coleccionistas(id_usuario) ON DELETE CASCADE
);


CREATE TABLE t_objetos_interes (
   id_coleccionista    INT             NOT NULL,
   id_objeto           INT             NOT NULL,
   PRIMARY KEY (id_coleccionista, id_objeto),
   FOREIGN KEY (id_coleccionista)      REFERENCES t_coleccionistas(id_usuario) ON DELETE CASCADE,
   FOREIGN KEY (id_objeto)             REFERENCES t_objetos(id)            ON DELETE CASCADE
);


CREATE TABLE t_subastas (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    fecha_vencimiento   DATETIME        NOT NULL,
    precio_minimo       DOUBLE          NOT NULL,
    estado              ENUM('ACTIVA', 'CERRADA') NOT NULL DEFAULT 'ACTIVA',
    id_creador          INT             NOT NULL,
    FOREIGN KEY (id_creador)            REFERENCES t_usuarios(id)           ON DELETE CASCADE
);


CREATE TABLE t_subasta_objetos (
   id_subasta          INT             NOT NULL,
   id_objeto           INT             NOT NULL,
   PRIMARY KEY (id_subasta, id_objeto),
   FOREIGN KEY (id_subasta)            REFERENCES t_subastas(id)           ON DELETE CASCADE,
   FOREIGN KEY (id_objeto)             REFERENCES t_objetos(id)            ON DELETE CASCADE
);


CREATE TABLE t_ofertas (
   id                  INT AUTO_INCREMENT PRIMARY KEY,
   precio_oferta       DOUBLE          NOT NULL,
   id_coleccionista    INT             NOT NULL,
   id_subasta          INT             NOT NULL,
   FOREIGN KEY (id_coleccionista)      REFERENCES t_coleccionistas(id_usuario) ON DELETE CASCADE,
   FOREIGN KEY (id_subasta)            REFERENCES t_subastas(id)           ON DELETE CASCADE
);