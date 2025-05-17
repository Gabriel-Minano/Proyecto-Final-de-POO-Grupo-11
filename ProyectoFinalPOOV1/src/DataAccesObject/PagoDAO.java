package DataAccesObject;

import BusinessEntity.Pago;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.UUID;

public class PagoDAO extends ConexionMySQL implements IBaseDAO<Pago> {

    @Override
    public boolean Create(Pago input) {
        boolean result = false;
        try {
            String SQL = "INSERT INTO Pago (id_pago, id_pedido, pago_tipo, pago_monto, pago_vuelto, pago_fecha) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            String uuid = UUID.randomUUID().toString();
            input.setId_pago(uuid);
            pst.setString(1, uuid);
            pst.setString(2, input.getId_pedido());
            pst.setString(3, input.getPago_tipo());
            pst.setDouble(4, input.getPago_monto());
            pst.setDouble(5, input.getPago_vuelto());
            pst.setTimestamp(6, new Timestamp(input.getPago_fecha().getTime()));

            result = pst.execute();
        } catch (Exception e) {
            System.out.println("Error en PagoDAO.Create: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Pago Read(String id) {
        Pago pago = new Pago();
        try {
            PreparedStatement pst = getConexion().prepareStatement("SELECT * FROM Pago WHERE id_pago=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                pago.setId_pago(id);
                pago.setId_pedido(res.getString("id_pedido"));
                pago.setPago_tipo(res.getString("pago_tipo"));
                pago.setPago_monto(res.getDouble("pago_monto"));
                pago.setPago_vuelto(res.getDouble("pago_vuelto"));
                pago.setPago_fecha(res.getTimestamp("pago_fecha"));
            }
        } catch (Exception e) {
            System.out.println("Error en PagoDAO.Read: " + e.getMessage());
        }
        return pago;
    }

    @Override
    public List<Pago> ReadAll() {
        List<Pago> pagos = new ArrayList<>();
        try {
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM Pago");

            while (res.next()) {
                Pago pago = new Pago();
                pago.setId_pago(res.getString("id_pago"));
                pago.setId_pedido(res.getString("id_pedido"));
                pago.setPago_tipo(res.getString("pago_tipo"));
                pago.setPago_monto(res.getDouble("pago_monto"));
                pago.setPago_vuelto(res.getDouble("pago_vuelto"));
                pago.setPago_fecha(res.getTimestamp("pago_fecha"));

                pagos.add(pago);
            }
        } catch (Exception e) {
            System.out.println("Error en PagoDAO.ReadAll: " + e.getMessage());
        }
        return pagos;
    }

    @Override
    public boolean Update(Pago input) {
        boolean result = false;
        try {
            String SQL = "UPDATE Pago SET id_pedido=?, pago_tipo=?, pago_monto=?, pago_vuelto=?, pago_fecha=? WHERE id_pago=?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, input.getId_pedido());
            pst.setString(2, input.getPago_tipo());
            pst.setDouble(3, input.getPago_monto());
            pst.setDouble(4, input.getPago_vuelto());
            pst.setTimestamp(5, new Timestamp(input.getPago_fecha().getTime()));
            pst.setString(6, input.getId_pago());

            result = pst.execute();
        } catch (Exception e) {
            System.out.println("Error en PagoDAO.Update: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean Delete(String id) {
        boolean result = false;
        try {
            PreparedStatement pst = getConexion().prepareStatement("DELETE FROM Pago WHERE id_pago=?");
            pst.setString(1, id);
            result = pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error en PagoDAO.Delete: " + e.getMessage());
        }
        return result;
    }

}
