package BusinessEntity;

import java.util.Date;

public class Pago {

    private String id_pago;
    private String id_pedido;
    private String pago_tipo;
    private double pago_monto;
    private double pago_vuelto;
    private Date pago_fecha;

    public Pago() {
    }

    public String getId_pago() {
        return id_pago;
    }

    public void setId_pago(String id_pago) {
        this.id_pago = id_pago;
    }

    public String getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getPago_tipo() {
        return pago_tipo;
    }

    public void setPago_tipo(String pago_tipo) {
        this.pago_tipo = pago_tipo;
    }

    public double getPago_monto() {
        return pago_monto;
    }

    public void setPago_monto(double pago_monto) {
        this.pago_monto = pago_monto;
    }

    public double getPago_vuelto() {
        return pago_vuelto;
    }

    public void setPago_vuelto(double pago_vuelto) {
        this.pago_vuelto = pago_vuelto;
    }

    public Date getPago_fecha() {
        return pago_fecha;
    }

    public void setPago_fecha(Date pago_fecha) {
        this.pago_fecha = pago_fecha;
    }

}
