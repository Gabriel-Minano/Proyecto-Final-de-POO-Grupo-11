package proyectofinalpoov1;

import java.util.Date;
import java.text.SimpleDateFormat;
//onj instanceof obj es una forma de obtener un valor true o false entre dos objetos, en otras palabras ver si es una instancia de una clase

public class Voucher {

    private String id;
    private Date fecha;
    private Pedido pedido;
    private Pago pago;

    public Voucher(String id, Date fecha, Pedido pedido, Pago pago) {
        this.id = id;
        this.fecha = fecha;
        this.pedido = pedido;
        this.pago = pago;
    }

    public void imprimir(String nombreRestaurante, String ruc, String telefono) {
        SimpleDateFormat FechaSimple = new SimpleDateFormat("dd/mm/yyyy");
        System.out.printf("""
                            RESTAURANTE: %s
                            RUC: %s
                            Tel: %s\n
                           """, nombreRestaurante.toUpperCase(), ruc, telefono);
        System.out.printf("""
                           \nCliente: %s
                           DNI: %s
                           Pedido N°: %d
                           Fecha: %s\n
                          """, pedido.getCliente().getNombre(),
                pedido.getCliente().getDocumento(), pedido.getNumero(), FechaSimple.format(fecha));

        System.out.println(" Productos:");
        for (ItemPedido item : pedido.getItems()) {
            String nombreProducto = item.getProducto().getNombre();
            int cantidad = item.getCantidad();
            double subtotal = item.getSubtotal();
            System.out.printf(" %d x %-18s S/. %.2f\n", cantidad, nombreProducto, subtotal);
        }
        double subtotal = pedido.CalcularMonto();
        double IGV = subtotal * 0.18;
        double total = subtotal + IGV;
        System.out.printf("""
                          \n Subtotal: S/. %.2f
                           IGV (18%%): S/. %.2f
                           Total: S/. %.2f\n
                          """, subtotal, IGV, total);

        if (pago instanceof PagoTarjeta) {
            System.out.println("\n Pagado con tarjeta POS");
        } else if (pago instanceof PagoEfectivo) {
            PagoEfectivo efectivo = (PagoEfectivo) pago; //Casting, para usar métodos de PagoEfectivo
            System.out.println("\n Pagado en efectivo");
            System.out.printf(" Monto Entregado: S/. %.2f\n", efectivo.getMonto());
            System.out.printf(" Vuelto: S/. %.2f\n", efectivo.getVuelto(total));
        }
        System.out.println("\n ¡Gracias por su preferencia!");
        System.out.println("-------------------------------------\n");
    }

}
