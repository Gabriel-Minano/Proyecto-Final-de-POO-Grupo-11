package proyectofinalpoov1;
//Hay encapsulamiento por los private y los getters and setters
//Hay encapsulamiento por los private y los getters and setters

public class ItemPedido {

    private Producto producto; //Esto sirve pare referenciar a la clase Productoo y aprovechar sus atributos
    private int cantidad;

    public ItemPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return producto.getPrecioUnitario() * cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


}
