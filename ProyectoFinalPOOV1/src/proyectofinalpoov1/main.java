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
            double precio = Lector.nextDouble();
            System.out.print("Cantidad: ");
            int cantidad = Lector.nextInt();
            Lector.nextLine();
            Producto producto = new Producto(nombreProducto, precio);
            ItemPedido item = new ItemPedido(producto, cantidad);
            items[contador++] = item;

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            opcion = Lector.nextLine();
        } while (opcion.equalsIgnoreCase("s") && contador < 10);  // max 10, pero se puede ampliar

        ItemPedido[] itemsFinal = new ItemPedido[contador];
        System.arraycopy(items, 0, itemsFinal, 0, contador);

        System.out.println("Ingrese el número del pedido comprendido por fecha y hora ejemplo (214251236: 21 día, 4 mes, 25 año, 12 hora, 36 minutos): ");
        int numerop = Lector.nextInt();
        Lector.nextLine();
        Pedido pedido = new Pedido(numerop, itemsFinal, cliente);

        Pago pago;
        System.out.println("\nSeleccione método de pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta");
        int metodo = Lector.nextInt();
        Lector.nextLine();
        double total = pedido.CalcularMonto() * 1.18;

        if (metodo == 1) {
            System.out.print("Ingrese monto entregado: ");
            double entregado = Lector.nextDouble();
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
