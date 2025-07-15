package DataAccesObject;

import BusinessEntity.Cliente;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

public class ClienteDAO extends ConexionMySQL implements IBaseDAO<Cliente> {

    @Override
    public boolean Create(Cliente input) {
        boolean result = false;
        try {
            String SQL = "INSERT INTO Cliente (id_cliente, cliente_nombre, cliente_telefono, cliente_direccion) VALUES (?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            String uuid = UUID.randomUUID().toString();
            input.setId_cliente(uuid);
            pst.setString(1, uuid);
            pst.setString(2, input.getCliente_nombre());
            pst.setString(3, input.getCliente_telefono());
            pst.setString(4, input.getCliente_direccion());

            result = pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error en ClienteDAO.Create: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Cliente Read(String id) {
        Cliente cliente = new Cliente();
        try {
            PreparedStatement pst = getConexion().prepareStatement("SELECT * FROM Cliente WHERE id_cliente=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                cliente.setId_cliente(id);
                cliente.setCliente_nombre(res.getString("cliente_nombre"));
                cliente.setCliente_telefono(res.getString("cliente_telefono"));
                cliente.setCliente_direccion(res.getString("cliente_direccion"));
            }
        } catch (Exception e) {
            System.out.println("Error en ClienteDAO.Read: " + e.getMessage());
        }
        return cliente;
    }

    @Override
    public List<Cliente> ReadAll() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM Cliente");

            while (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(res.getString("id_cliente"));
                cliente.setCliente_nombre(res.getString("cliente_nombre"));
                cliente.setCliente_telefono(res.getString("cliente_telefono"));
                cliente.setCliente_direccion(res.getString("cliente_direccion"));

                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error en ClienteDAO.ReadAll: " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public boolean Update(Cliente input) {
        boolean result = false;
        try {
            String SQL = "UPDATE Cliente SET cliente_nombre=?, cliente_telefono=?, cliente_direccion=? WHERE id_cliente=?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, input.getCliente_nombre());
            pst.setString(2, input.getCliente_telefono());
            pst.setString(3, input.getCliente_direccion());
            pst.setString(4, input.getId_cliente());

            result = pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error en ClienteDAO.Update: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean Delete(String id) {
        boolean result = false;
        try {
            PreparedStatement pst = getConexion().prepareStatement("DELETE FROM Cliente WHERE id_cliente=?");
            pst.setString(1, id);
            result = pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error en ClienteDAO.Delete: " + e.getMessage());
        }
        return result;
    }
}
