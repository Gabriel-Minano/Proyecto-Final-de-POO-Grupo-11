package BusinessEntity;

public class Producto {
    private String id_producto;
    private String producto_nombre;
    private String producto_descripcion;
    private double producto_precio;
    private int producto_stock;

    public Producto() {
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getProducto_nombre() {
        return producto_nombre;
    }

    public void setProducto_nombre(String producto_nombre) {
        this.producto_nombre = producto_nombre;
    }

    public String getProducto_descripcion() {
        return producto_descripcion;
    }

    public void setProducto_descripcion(String producto_descripcion) {
        this.producto_descripcion = producto_descripcion;
    }

    public double getProducto_precio() {
        return producto_precio;
    }

    public void setProducto_precio(double producto_precio) {
        this.producto_precio = producto_precio;
    }

    public int getProducto_stock() {
        return producto_stock;
    }

    public void setProducto_stock(int producto_stock) {
        this.producto_stock = producto_stock;
    }

}
