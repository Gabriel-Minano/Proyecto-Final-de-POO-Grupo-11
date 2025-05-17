package BusinessEntity;

public class ItemPedido {

    private String id_item;
    private String id_pedido;
    private String id_producto;
    private int item_cantidad;
    private double item_precio_unitario;
    private double item_subtotal;

    public ItemPedido() {
    }

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item = id_item;
    }

    public String getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public int getItem_cantidad() {
        return item_cantidad;
    }

    public void setItem_cantidad(int item_cantidad) {
        this.item_cantidad = item_cantidad;
    }

    public double getItem_precio_unitario() {
        return item_precio_unitario;
    }

    public void setItem_precio_unitario(double item_precio_unitario) {
        this.item_precio_unitario = item_precio_unitario;
    }

    public double getItem_subtotal() {
        return item_subtotal;
    }

    public void setItem_subtotal(double item_subtotal) {
        this.item_subtotal = item_subtotal;
    }



}
