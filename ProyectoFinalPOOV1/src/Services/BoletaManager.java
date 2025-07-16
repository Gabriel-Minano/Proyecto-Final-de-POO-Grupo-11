package Services;

import BusinessEntity.*;
import DataAccesObject.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BoletaManager {

    private final List<ItemPedido> carrito;
    private final ProductoDAO productoDAO;
    private final ClienteDAO clienteDAO;
    private final EmpleadoDAO empleadoDAO;
    private final PedidoDAO pedidoDAO;
    private final PagoDAO pagoDAO;
    private final VoucherDAO voucherDAO;
    private final ItemPedidoDAO itemPedidoDAO;
    private Empleado empleadoSeleccionado;

    public BoletaManager() {
        this.carrito = new ArrayList<>();
        this.productoDAO = new ProductoDAO();
        this.clienteDAO = new ClienteDAO();
        this.empleadoDAO = new EmpleadoDAO();
        this.pedidoDAO = new PedidoDAO();
        this.pagoDAO = new PagoDAO();
        this.voucherDAO = new VoucherDAO();
        this.itemPedidoDAO = new ItemPedidoDAO();
    }

    public boolean agregarProducto(String idProducto, int cantidad) {
        Producto producto = productoDAO.Read(idProducto);
        if (producto == null || producto.getProducto_stock() < cantidad) {
            return false;
        }

        double subtotal = producto.getProducto_precio() * cantidad;
        ItemPedido item = new ItemPedido();
        item.setId_producto(idProducto);
        item.setItem_cantidad(cantidad);
        item.setItem_precio_unitario(producto.getProducto_precio());
        item.setItem_subtotal(subtotal);

        carrito.add(item);
        return true;
    }

    public void seleccionarEmpleado(String idEmpleado) {
        Empleado empleado = empleadoDAO.Read(idEmpleado);
        if (empleado != null) {
            this.empleadoSeleccionado = empleado;
            System.out.println("Empleado seleccionado: " + empleado.getEmpleado_nombre());
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    public void registrarCliente(Scanner sc) {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(UUID.randomUUID().toString());
        System.out.print("Nombre del cliente: ");
        cliente.setCliente_nombre(sc.nextLine());
        System.out.print("DNI del cliente: ");
        cliente.setCliente_dni(sc.nextLine());
        System.out.print("Teléfono del cliente: ");
        cliente.setCliente_telefono(sc.nextLine());
        System.out.print("Dirección del cliente: ");
        cliente.setCliente_direccion(sc.nextLine());

        if (clienteDAO.Create(cliente)) {
            System.out.println("Cliente registrado con éxito. ID: " + cliente.getId_cliente());
        } else {
            System.out.println("Error al registrar cliente.");
        }
    }

    public void registrarProducto(Scanner sc) {
        Producto producto = new Producto();
        producto.setId_producto(UUID.randomUUID().toString());
        System.out.print("Nombre del producto: ");
        producto.setProducto_nombre(sc.nextLine());
        System.out.print("Precio: ");
        producto.setProducto_precio(sc.nextDouble());
        System.out.print("Stock: ");
        producto.setProducto_stock(sc.nextInt());
        sc.nextLine();

        if (productoDAO.Create(producto)) {
            System.out.println("Producto registrado con éxito. ID: " + producto.getId_producto());
        } else {
            System.out.println("Error al registrar producto.");
        }
    }

    public void registrarEmpleado(Scanner sc) {
        Empleado empleado = new Empleado();
        empleado.setId_empleado(UUID.randomUUID().toString());
        System.out.print("Nombre del empleado: ");
        empleado.setEmpleado_nombre(sc.nextLine());
        System.out.print("Teléfono del empleado: ");
        empleado.setEmpleado_telefono(sc.nextLine());
        System.out.print("Dirección del empleado: ");
        empleado.setEmpleado_direccion(sc.nextLine());
        System.out.print("Cargo del empleado: ");
        empleado.setEmpleado_cargo(sc.nextLine());

        if (empleadoDAO.Create(empleado)) {
            System.out.println("Empleado registrado con éxito. ID: " + empleado.getId_empleado());
        } else {
            System.out.println("Error al registrar empleado.");
        }
    }

    public void mostrarClientes() {
        List<Cliente> clientes = clienteDAO.ReadAll();
        System.out.println("--- Lista de Clientes ---");
        for (Cliente c : clientes) {
            System.out.println(c.getId_cliente() + ": " + c.getCliente_nombre() + ", Tel: " + c.getCliente_telefono());
        }
    }

    public void mostrarProductos() {
        List<Producto> productos = productoDAO.ReadAll();
        System.out.println("--- Lista de Productos ---");
        for (Producto p : productos) {
            System.out.println(p.getId_producto() + ": " + p.getProducto_nombre() + ", Stock: " + p.getProducto_stock());
        }
    }

    public void mostrarEmpleados() {
        List<Empleado> empleados = empleadoDAO.ReadAll();
        System.out.println("--- Lista de Empleados ---");
        for (Empleado e : empleados) {
            System.out.println(e.getId_empleado() + ": " + e.getEmpleado_nombre() + ", Cargo: " + e.getEmpleado_cargo());
        }
    }

    public double calcularTotal() {
        return carrito.stream().mapToDouble(ItemPedido::getItem_subtotal).sum();
    }

    public void generarBoleta(String idCliente, String tipoPago, double montoEntregado) {
        Pedido pedido = new Pedido();
        String idPedido = UUID.randomUUID().toString();
        pedido.setId_pedido(idPedido);
        pedido.setId_cliente(idCliente);
        pedido.setPedido_fecha(new Date());
        pedido.setPedido_estado("Pagado");
        pedido.setPedido_subtotal(calcularTotal());
        pedido.setPedido_total(calcularTotal());

        if (!pedidoDAO.Create(pedido)) {
            System.out.println("No se pudo registrar el pedido.");
            return;
        }
        System.out.println("Pedido registrado con ID: " + idPedido);

        for (ItemPedido item : carrito) {
            item.setId_pedido(idPedido);
            if (!itemPedidoDAO.Create(item)) {
                System.out.println("No se pudo registrar el item del pedido.");
            }
        }

        Pago pago = new Pago();
        pago.setId_pedido(idPedido);
        pago.setPago_tipo(tipoPago);
        pago.setPago_monto(montoEntregado);
        pago.setPago_vuelto(montoEntregado - calcularTotal());
        pago.setPago_fecha(new Date());

        boolean exitoPago = pagoDAO.Create(pago);
        if (!exitoPago) {
            System.out.println("No se pudo registrar el pago.");
            return;
        }
        String idPago = pago.getId_pago();
        System.out.println("Pago registrado con ID: " + idPago);

        Voucher voucher = new Voucher();
        voucher.setId_voucher(UUID.randomUUID().toString());
        voucher.setId_pago(idPago);
        voucher.setVoucher_fecha(new Date());
        voucher.setVoucher_contenido(generarContenidoVoucher(pedido, pago));

        if (!voucherDAO.Create(voucher)) {
            System.out.println("No se pudo registrar el voucher.");
            return;
        }
        System.out.println("Voucher generado correctamente.");

        System.out.println("----- VOUCHER -----");
        System.out.println(voucher.getVoucher_contenido());
        System.out.println("-------------------");

        carrito.clear();
    }

    private String generarContenidoVoucher(Pedido pedido, Pago pago) {
        StringBuilder sb = new StringBuilder();
        sb.append("------ BOLETA DE VENTA ------\n");
        sb.append("Fecha: ").append(pedido.getPedido_fecha()).append("\n");
        sb.append("Cliente ID: ").append(pedido.getId_cliente()).append("\n");
        if (empleadoSeleccionado != null) {
            sb.append("Atendido por: ").append(empleadoSeleccionado.getEmpleado_nombre()).append(" (Cargo: ")
                    .append(empleadoSeleccionado.getEmpleado_cargo()).append(")\n");
        }
        carrito.forEach(item -> {
            Producto p = productoDAO.Read(item.getId_producto());
            sb.append(p.getProducto_nombre())
                    .append(" x")
                    .append(item.getItem_cantidad())
                    .append(" - S/ ")
                    .append(item.getItem_subtotal())
                    .append("\n");
        });
        sb.append("TOTAL: S/ ").append(pedido.getPedido_total()).append("\n");
        sb.append("PAGADO: S/ ").append(pago.getPago_monto()).append("\n");
        sb.append("VUELTO: S/ ").append(pago.getPago_vuelto()).append("\n");
        sb.append("-----------------------------\n");
        return sb.toString();
    }

    public List<Producto> listarProductosConStockBajo(int umbral) {
        List<Producto> productos = productoDAO.ReadAll();
        Predicate<Producto> filtro = p -> p.getProducto_stock() < umbral;
        return productos.stream().filter(filtro).collect(Collectors.toList());
    }

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
