package BusinessEntity;

public class Producto {
    private String id_producto;
    private String producto_nombre;
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
