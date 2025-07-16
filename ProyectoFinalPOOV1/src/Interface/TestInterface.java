package Interface;

import Services.BoletaManager;
import java.util.Scanner;

public class TestInterface {
        public static void main(String[] args) {
        BoletaManager manager = new BoletaManager();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("""
                               ----- MENÚ BOLETA  -----
                               1. Seleccionar empleado
                               2. Agregar producto al carrito
                               3. Ver total del carrito
                               4. Generar boleta
                               5. Registrar nuevo cliente
                               6. Registrar nuevo producto
                               7. Registrar nuevo empleado
                               8. Mostrar clientes
                               9. Mostrar productos
                               10. Mostrar empleados
                               0. Salir""");
            System.out.print("Seleccione opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("ID del empleado: ");
                    String idEmp = sc.nextLine();
                    manager.seleccionarEmpleado(idEmp);
                    break;
                case 2:
                    System.out.print("ID producto: ");
                    String idProd = sc.nextLine();
                    System.out.print("Cantidad: ");
                    int cant = sc.nextInt();
                    sc.nextLine();
                    if (manager.agregarProducto(idProd, cant)) {
                        System.out.println("Producto agregado.");
                    } else {
                        System.out.println("Error al agregar producto (stock insuficiente o no existe).\n");
                    }
                    break;
                case 3:
                    System.out.println("Total actual del carrito: S/ " + manager.calcularTotal());
                    break;
                case 4:
                    System.out.print("ID cliente: ");
                    String idCliente = sc.nextLine();
                    System.out.print("Tipo de pago: ");
                    String tipoPago = sc.nextLine();
                    System.out.print("Monto entregado: ");
                    double monto = sc.nextDouble();
                    sc.nextLine();
                    manager.generarBoleta(idCliente, tipoPago, monto);
                    break;
                case 5:
                    manager.registrarCliente(sc);
                    break;
                case 6:
                    manager.registrarProducto(sc);
                    break;
                case 7:
                    manager.registrarEmpleado(sc);
                    break;
                case 8:
                    manager.mostrarClientes();
                    break;
                case 9:
                    manager.mostrarProductos();
                    break;
                case 10:
                    manager.mostrarEmpleados();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

}
