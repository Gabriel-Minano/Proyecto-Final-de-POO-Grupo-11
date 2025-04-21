package proyectofinalpoov1;

import java.util.Date;

public class Restaurante {

    private String nombre;
    private String direccion;
    private String ruc;
    private String telefono;

    public Restaurante(String nombre, String direccion, String ruc, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ruc = ruc;
        this.telefono = telefono;
    }

    public Voucher recibirPago(Pedido pedido, Pago pago) {
        double total = pedido.CalcularMonto() * 1.18;
        if (pago.procesar(total)) {
            return new Voucher(new Date(), pedido, pago);
        } else {
            System.out.println("Error: Pago insuficiente");
            return null;
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getRuc() {
        return ruc;
    }

}
