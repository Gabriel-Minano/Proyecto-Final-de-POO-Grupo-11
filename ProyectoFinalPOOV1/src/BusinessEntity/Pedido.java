package BusinessEntity;

import java.util.Date;

public class Pedido {

    private String id_pedido;
    private String id_cliente;
    private Date pedido_fecha;
    private String pedido_estado;
    private double pedido_subtotal;
    private double pedido_total;

    public Pedido() {
    }

    public String getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Date getPedido_fecha() {
        return pedido_fecha;
    }

    public void setPedido_fecha(Date pedido_fecha) {
        this.pedido_fecha = pedido_fecha;
    }

    public String getPedido_estado() {
        return pedido_estado;
    }

    public void setPedido_estado(String pedido_estado) {
        this.pedido_estado = pedido_estado;
    }

    public double getPedido_subtotal() {
        return pedido_subtotal;
    }

    public void setPedido_subtotal(double pedido_subtotal) {
        this.pedido_subtotal = pedido_subtotal;
    }

    public double getPedido_total() {
        return pedido_total;
    }

    public void setPedido_total(double pedido_total) {
        this.pedido_total = pedido_total;
    }

}
