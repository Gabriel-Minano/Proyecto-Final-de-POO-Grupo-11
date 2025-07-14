CREATE DATABASE proyectofinal;
USE proyectofinal;

CREATE TABLE Cliente (
    id_cliente VARCHAR(50) PRIMARY KEY, 
    cliente_nombre VARCHAR(150),
    cliente_dni CHAR(8),
    cliente_telefono VARCHAR(50),
    cliente_direccion VARCHAR(250)
);

CREATE TABLE Empleado (
    id_empleado VARCHAR(50) PRIMARY KEY, 
    empleado_nombre VARCHAR(150),
    empleado_telefono VARCHAR(50),
    empleado_direccion VARCHAR(250),
    empleado_cargo VARCHAR(50)
);

CREATE TABLE Producto (
    id_producto VARCHAR(50) PRIMARY KEY,
    producto_nombre VARCHAR(150) NOT NULL,
    producto_precio DECIMAL(10,2) NOT NULL,
    producto_stock INT NOT NULL
);

CREATE TABLE Pedido (
    id_pedido VARCHAR(50) PRIMARY KEY,
    id_cliente VARCHAR(50) NOT NULL,
    pedido_fecha DATETIME NOT NULL,
    pedido_estado VARCHAR(20) NOT NULL,
    pedido_subtotal DECIMAL(12,2) NOT NULL,
    pedido_total DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

CREATE TABLE ItemPedido (
    id_item VARCHAR(50) PRIMARY KEY,
    id_pedido VARCHAR(50) NOT NULL,
    id_producto VARCHAR(50) NOT NULL,
    item_cantidad INT NOT NULL,
    item_precio_unitario DECIMAL(10,2) NOT NULL,
    item_subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);

CREATE TABLE Pago (
    id_pago VARCHAR(50) PRIMARY KEY,
    id_pedido VARCHAR(50) NOT NULL,
    pago_tipo VARCHAR(20) NOT NULL,
    pago_monto DECIMAL(10,2) NOT NULL,
    pago_vuelto DECIMAL (10,2) NOT NULL,
    pago_fecha DATETIME NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido)
);

CREATE TABLE Voucher (
    id_voucher VARCHAR(50) PRIMARY KEY,
    id_pago VARCHAR(50) NOT NULL,
    voucher_fecha DATETIME NOT NULL,
    voucher_contenido LONGTEXT NOT NULL,
    FOREIGN KEY (id_pago) REFERENCES Pago(id_pago)
);