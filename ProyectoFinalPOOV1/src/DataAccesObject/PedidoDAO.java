package DataAccesObject;

import BusinessEntity.Pedido;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.UUID;

public class PedidoDAO extends ConexionMySQL implements IBaseDAO<Pedido> {

    @Override
    public boolean Create(Pedido input) {
        boolean result = false;
        try {
            String SQL = "INSERT INTO Pedido (id_pedido, id_cliente, pedido_fecha, pedido_estado, pedido_subtotal, pedido_total) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            String uuid = UUID.randomUUID().toString();
            pst.setString(1, input.getId_pedido());
            pst.setString(2, input.getId_cliente());
            pst.setTimestamp(3, new Timestamp(input.getPedido_fecha().getTime()));
            pst.setString(4, input.getPedido_estado());
            pst.setDouble(5, input.getPedido_subtotal());
            pst.setDouble(6, input.getPedido_total());

            result = pst.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error en PedidoDAO.Create: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Pedido Read(String id) {
        Pedido pedido = new Pedido();
        try {
            PreparedStatement pst = getConexion().prepareStatement("SELECT * FROM Pedido WHERE id_pedido=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                pedido.setId_pedido(res.getString("id_pedido"));
                pedido.setId_cliente(res.getString("id_cliente"));
                pedido.setPedido_fecha(res.getTimestamp("pedido_fecha"));
                pedido.setPedido_estado(res.getString("pedido_estado"));
                pedido.setPedido_subtotal(res.getDouble("pedido_subtotal"));
                pedido.setPedido_total(res.getDouble("pedido_total"));
            }
        } catch (Exception e) {
            System.out.println("Error en PedidoDAO.Read: " + e.getMessage());
        }
        return pedido;
    }

    @Override
    public List<Pedido> ReadAll() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM Pedido");

            while (res.next()) {
                Pedido pedido = new Pedido();
                pedido.setId_pedido(res.getString("id_pedido"));
                pedido.setId_cliente(res.getString("id_cliente"));
                pedido.setPedido_fecha(res.getTimestamp("pedido_fecha"));
                pedido.setPedido_estado(res.getString("pedido_estado"));
                pedido.setPedido_subtotal(res.getDouble("pedido_subtotal"));
                pedido.setPedido_total(res.getDouble("pedido_total"));

                pedidos.add(pedido);
            }
        } catch (Exception e) {
            System.out.println("Error en PedidoDAO.ReadAll: " + e.getMessage());
        }
        return pedidos;
    }

    @Override
    public boolean Update(Pedido input) {
        boolean result = false;
        try {
            String SQL = "UPDATE Pedido SET id_cliente=?, pedido_fecha=?, pedido_estado=?, pedido_subtotal=?, pedido_total=? WHERE id_pedido=?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, input.getId_cliente());
            pst.setTimestamp(2, new Timestamp(input.getPedido_fecha().getTime()));
            pst.setString(3, input.getPedido_estado());
            pst.setDouble(4, input.getPedido_subtotal());
            pst.setDouble(5, input.getPedido_total());
            pst.setString(6, input.getId_pedido());
            result = pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error en PedidoDAO.Update: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean Delete(String id) {
        boolean result = false;
        try {
            PreparedStatement pst = getConexion().prepareStatement("DELETE FROM Pedido WHERE id_pedido=?");
            pst.setString(1, id);
            result = pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error en PedidoDAO.Delete: " + e.getMessage());
        }
        return result;
    }
    public String CreateAndReturnID(Pedido input) {
        String idGenerado = null;
        try {
            String SQL = "INSERT INTO Pedido (id_pedido, id_cliente, pedido_fecha, pedido_estado, pedido_subtotal, pedido_total) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);

            // Generar un UUID para el ID del pedido
            String uuid = UUID.randomUUID().toString();
            input.setId_pedido(uuid);

            pst.setString(1, uuid);
            pst.setString(2, input.getId_cliente());
            pst.setTimestamp(3, new Timestamp(input.getPedido_fecha().getTime()));
            pst.setString(4, input.getPedido_estado());
            pst.setDouble(5, input.getPedido_subtotal());
            pst.setDouble(6, input.getPedido_total());

            int filas = pst.executeUpdate();
            if (filas > 0) {
                idGenerado = uuid;
            }
        } catch (Exception e) {
            System.out.println("Error en PedidoDAO.CreateAndReturnID: " + e.getMessage());
        }
        return idGenerado;
    }

}
