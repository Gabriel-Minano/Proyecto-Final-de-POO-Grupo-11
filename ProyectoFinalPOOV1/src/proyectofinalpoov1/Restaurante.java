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
            return new Voucher("0001", new Date(), pedido, pago);
        } else {
            System.out.println("Error: Pago insuficiente");
            return null;
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

}
