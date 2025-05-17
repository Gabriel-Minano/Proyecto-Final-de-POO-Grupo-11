CREATE DATABASE ProyectoFinal;
USE ProyectoFinal;

create table Cliente (
id_cliente VARCHAR(50) PRIMARY KEY, 
nombre VARCHAR(150),
dni CHAR(8),
telefono VARCHAR(50),
dirección VARCHAR(250)
);

CREATE TABLE Producto (
    id_producto VARCHAR(50) PRIMARY KEY,
    descripcion VARCHAR(150) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    categoria VARCHAR(50)
);

CREATE TABLE Pedido (
    id_pedido VARCHAR(50) PRIMARY KEY,
    id_cliente VARCHAR(50) NOT NULL,
    fecha DATETIME NOT NULL,
    estado VARCHAR(20) NOT NULL,
    subtotal DECIMAL(12,2) NOT NULL,
    total DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

CREATE TABLE ItemPedido (
    id_item VARCHAR(50) PRIMARY KEY,
    id_pedido VARCHAR(50) NOT NULL,
    id_producto VARCHAR(50) NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);

CREATE TABLE Pago (
id_pago VARCHAR(50) PRIMARY KEY,
ID_PEDIDO VARCHAR(50) NOT NULL,
tipo_pago VARCHAR(20) NOT NULL,
monto DECIMAL(10,2) NOT NULL,
vuelto DECIMAL (10,2) NOT NULL,
fecha DATETIME NOT NULL
);

CREATE TABLE voucher (
id_voucher VARCHAR(50) PRIMARY KEY,
id_pago VARCHAR(50) NOT NULL,
fecha DATETIME NOT NULL,
contenido LONGTEXT NOT NULL
);