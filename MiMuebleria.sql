CREATE USER 'userMuebleria'@'localhost' IDENTIFIED BY 'MiMuebleria123.';
GRANT USAGE,INSERT,DELETE,UPDATE,SELECT ON Mi_Muebleria.* TO 'userMuebleria'@'localhost';

mysql -u root2 -p
CREATE DATABASE Mi_Muebleria;
USE Mi_Muebleria;

CREATE TABLE usuario(
	usuario VARCHAR(45) NOT NULL,
	password VARCHAR(45) NOT NULL,
	tipo INT NOT NULL,
	estado BOOLEAN NOT NULL,
	CONSTRAINT PK_USUARIO PRIMARY KEY(usuario)
);	
INSERT INTO usuario(usuario,password,tipo,estado) VALUES('userfabrica','userFabrica','2','1');
INSERT INTO usuario(usuario,password,tipo,estado) VALUES('userventas','userVentas','2','1');
INSERT INTO usuario(usuario,password,tipo,estado) VALUES('useradministracion','userAdministracion','3','1');

CREATE TABLE mueble(
	nombreMueble VARCHAR(50) NOT NULL,
	precio VARCHAR(20) NOT NULL,	
	CONSTRAINT PK_MUEBLE PRIMARY KEY(nombreMueble)
);
CREATE TABLE pieza(
	tipo VARCHAR(50) NOT NULL,
	id INT NOT NULL,
	costo VARCHAR(20) NOT NULL,	
	CONSTRAINT PK_PIEZA PRIMARY KEY(tipo,id)
);

CREATE TABLE ensamble_pieza(
	mueble VARCHAR(50) NOT NULL,
	tipo VARCHAR(50) NOT NULL,
	cantidad INT NOT NULL,
	costo VARCHAR(20) NOT NULL,
	CONSTRAINT PK_ENSAMBLE_PIEZA PRIMARY KEY(mueble,tipo,cantidad),
	CONSTRAINT FK_TO_PIEZA FOREIGN KEY(tipo) REFERENCES pieza(tipo) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FK_TO_MUEBLE FOREIGN KEY(mueble) REFERENCES mueble(nombreMueble) ON DELETE CASCADE ON UPDATE CASCADE
);
	
CREATE TABLE ensamble_mueble(
	mueble VARCHAR(50) NOT NULL,
	usuarioEnsamble VARCHAR(45) NOT NULL,
	fecha DATE NOT NULL,
	costo VARCHAR(20) NOT NULL,
	CONSTRAINT FK_TO_ENSAMBLE_PIEZA FOREIGN KEY(mueble)
	REFERENCES ensamble_pieza(mueble),
	CONSTRAINT FK_TO_USUARIO FOREIGN KEY(usuarioEnsamble)
	REFERENCES usuario(usuario),
	CONSTRAINT PK_ENSAMBLE_MUEBLE PRIMARY KEY(mueble)
);

CREATE TABLE cliente(
	nombre VARCHAR(50) NOT NULL,
	nit VARCHAR(20) NOT NULL,
	direccion VARCHAR(50) NOT NULL,
	municipio VARCHAR(45),
	departamento VARCHAR(45),
	CONSTRAINT PK_CLIENTE PRIMARY KEY(nit)
);

CREATE TABLE venta(
	mueble VARCHAR(50) NOT NULL,
	nitVenta VARCHAR(20) NOT NULL,
	fecha DATE NOT NULL,
	user VARCHAR(45) NOT NULL,
	CONSTRAINT FK_TO_USER FOREIGN KEY(user)
	REFERENCES usuario(usuario),
	CONSTRAINT FK_TO_CLIENT FOREIGN KEY(nitVenta)
	REFERENCES cliente(nit),
	CONSTRAINT FK_TO_ENSAMBLE_MUEBLE FOREIGN KEY(mueble)
	REFERENCES ensamble_mueble(mueble),
	CONSTRAINT PK_VENTA PRIMARY KEY(mueble,nitVenta)
);

CREATE TABLE devolucion(
	mueble VARCHAR(50) NOT NULL,
	nitVenta VARCHAR(20) NOT NULL,
	fecha DATE NOT NULL,
	perdida VARCHAR(20) NOT NULL,
	CONSTRAINT FK_TO_CLIEN FOREIGN KEY(nitVenta)
	REFERENCES cliente(nit),
	CONSTRAINT FK_TO_ENSA_MUEBLE FOREIGN KEY(mueble)
	REFERENCES ensamble_mueble(mueble),
	CONSTRAINT PK_VENTA PRIMARY KEY(mueble,nitVenta)
);	

