package proyectofinalpoov1;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner Lector = new Scanner(System.in);

        Restaurante restaurante = new Restaurante("El Buen Sabor", "Av. Sabor 123", "12345678901", "(01) 555-1234");

        System.out.print("Ingrese nombre del cliente: ");
        String nombreCliente = Lector.nextLine();
        System.out.print("Ingrese DNI del cliente: ");
        String dniCliente = Lector.nextLine();
        Cliente cliente = new Cliente(nombreCliente, dniCliente);

        ItemPedido[] items = new ItemPedido[10]; // max 10, pero se puede ampliar
        int contador = 0;
        String opcion;

        do {
            System.out.print("\nNombre del producto: ");
            String nombreProducto = Lector.nextLine();
            System.out.print("Precio unitario: ");
            double precio = Double.parseDouble(Lector.nextLine());
            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(Lector.nextLine());

            Producto producto = new Producto(nombreProducto, precio);
            ItemPedido item = new ItemPedido(producto, cantidad);
            items[contador++] = item;

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            opcion = Lector.nextLine();
        } while (opcion.equalsIgnoreCase("s") && contador < 10);

        ItemPedido[] itemsFinal = new ItemPedido[contador];
        System.arraycopy(items, 0, itemsFinal, 0, contador);

        Pedido pedido = new Pedido(421, itemsFinal, cliente);

        Pago pago;
        System.out.println("\nSeleccione método de pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta");
        int metodo = Integer.parseInt(Lector.nextLine());
        double total = pedido.CalcularMonto() * 1.18;

        if (metodo == 1) {
            System.out.print("Ingrese monto entregado: ");
            double entregado = Double.parseDouble(Lector.nextLine());
            pago = new PagoEfectivo(entregado);
        } else {
            System.out.print("Ingrese código de lector POS: ");
            String lectorId = Lector.nextLine();
            pago = new PagoTarjeta(lectorId, total);
        }

        Voucher voucher = restaurante.recibirPago(pedido, pago);
        if (voucher != null) {
            voucher.imprimir(restaurante.getNombre(), restaurante.getRuc(), restaurante.getTelefono());
        }

        Lector.close();
    }
}
