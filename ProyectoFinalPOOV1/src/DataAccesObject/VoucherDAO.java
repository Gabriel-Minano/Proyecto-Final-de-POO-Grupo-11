package DataAccesObject;

import BusinessEntity.Voucher;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.UUID;

public class VoucherDAO extends ConexionMySQL implements IBaseDAO<Voucher> {

    @Override
    public boolean Create(Voucher input) {
        boolean result = false;
        try {
            String SQL = "INSERT INTO Voucher (id_voucher, id_pago, voucher_fecha, voucher_contenido) VALUES (?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            String uuid = UUID.randomUUID().toString();
            input.setId_voucher(uuid);
            pst.setString(1, uuid);
            pst.setString(2, input.getId_pago());
            pst.setTimestamp(3, new Timestamp(input.getVoucher_fecha().getTime()));
            pst.setString(4, input.getVoucher_contenido());

            result = pst.execute();
        } catch (Exception e) {
            System.out.println("Error en VoucherDAO.Create: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Voucher Read(String id) {
        Voucher voucher = new Voucher();
        try {
            PreparedStatement pst = getConexion().prepareStatement("SELECT * FROM Voucher WHERE id_voucher=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                voucher.setId_voucher(id);
                voucher.setId_pago(res.getString("id_pago"));
                voucher.setVoucher_fecha(res.getTimestamp("voucher_fecha"));
                voucher.setVoucher_contenido(res.getString("voucher_contenido"));
            }
        } catch (Exception e) {
            System.out.println("Error en VoucherDAO.Read: " + e.getMessage());
        }
        return voucher;
    }

    @Override
    public List<Voucher> ReadAll() {
        List<Voucher> vouchers = new ArrayList<>();
        try {
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM Voucher");
            
            while (res.next()) {
                Voucher voucher = new Voucher();
                voucher.setId_voucher(res.getString("id_voucher"));
                voucher.setId_pago(res.getString("id_pago"));
                voucher.setVoucher_fecha(res.getTimestamp("voucher_fecha"));
                voucher.setVoucher_contenido(res.getString("voucher_contenido"));
                
                vouchers.add(voucher);
            }
        } catch (Exception e) {
            System.out.println("Error en VoucherDAO.ReadAll: " + e.getMessage());
        }
        return vouchers;
    }

    @Override
    public boolean Update(Voucher input) {
        boolean result = false;
        try {
            String SQL = "UPDATE Voucher SET id_pago=?, voucher_fecha=?, voucher_contenido=? WHERE id_voucher=?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, input.getId_pago());
            pst.setTimestamp(2, new Timestamp(input.getVoucher_fecha().getTime()));
            pst.setString(3, input.getVoucher_contenido());
            pst.setString(4, input.getId_voucher());
            
            result = pst.execute();
        } catch (Exception e) {
            System.out.println("Error en VoucherDAO.Update: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean Delete(String id) {
        boolean result = false;
        try {
            PreparedStatement pst = getConexion().prepareStatement("DELETE FROM Voucher WHERE id_voucher=?");
            pst.setString(1, id);
            result = pst.execute();
        } catch (Exception e) {
            System.out.println("Error en VoucherDAO.Delete: " + e.getMessage());
        }
        return result;
    }
    
}