package DataAccesObject;

import BusinessEntity.Empleado;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

public class EmpleadoDAO extends ConexionMySQL implements IBaseDAO<Empleado> {

    @Override
    public boolean Create(Empleado input) {
        boolean result = false;
        try {
            String SQL = "INSERT INTO Empleado (id_empleado, empleado_nombre, empleado_telefono, empleado_direccion, empleado_cargo) VALUES (?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            String uuid = UUID.randomUUID().toString();
            input.setId_empleado(uuid);
            pst.setString(1, uuid);
            pst.setString(2, input.getEmpleado_nombre());
            pst.setString(3, input.getEmpleado_telefono());
            pst.setString(4, input.getEmpleado_direccion());
            pst.setString(5, input.getEmpleado_cargo());

            result = pst.execute();
        } catch (Exception e) {
            System.out.println("Error en EmpleadoDAO.Create: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Empleado Read(String id) {
        Empleado empleado = new Empleado();
        try {
            PreparedStatement pst = getConexion().prepareStatement("SELECT * FROM Empleado WHERE id_empleado=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                empleado.setId_empleado(id);
                empleado.setEmpleado_nombre(res.getString("empleado_nombre"));
                empleado.setEmpleado_telefono(res.getString("empleado_telefono"));
                empleado.setEmpleado_direccion(res.getString("empleado_direccion"));
                empleado.setEmpleado_cargo(res.getString("empleado_cargo"));
            }
        } catch (Exception e) {
            System.out.println("Error en EmpleadoDAO.Read: " + e.getMessage());
        }
        return empleado;
    }

    @Override
    public List<Empleado> ReadAll() {
        List<Empleado> empleados = new ArrayList<>();
        try {
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM Empleado");
            
            while (res.next()) {
                Empleado empleado = new Empleado();
                empleado.setId_empleado(res.getString("id_empleado"));
                empleado.setEmpleado_nombre(res.getString("empleado_nombre"));
                empleado.setEmpleado_telefono(res.getString("empleado_telefono"));
                empleado.setEmpleado_direccion(res.getString("empleado_direccion"));
                empleado.setEmpleado_cargo(res.getString("empleado_cargo"));
                
                empleados.add(empleado);
            }
        } catch (Exception e) {
            System.out.println("Error en EmpleadoDAO.ReadAll: " + e.getMessage());
        }
        return empleados;
    }

    @Override
    public boolean Update(Empleado input) {
        boolean result = false;
        try {
            String SQL = "UPDATE Empleado SET empleado_nombre=?, empleado_telefono=?, empleado_direccion=?, empleado_cargo=? WHERE id_empleado=?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, input.getEmpleado_nombre());
            pst.setString(2, input.getEmpleado_telefono());
            pst.setString(3, input.getEmpleado_direccion());
            pst.setString(4, input.getEmpleado_cargo());
            pst.setString(5, input.getId_empleado());
            
            result = pst.execute();
        } catch (Exception e) {
            System.out.println("Error en EmpleadoDAO.Update: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean Delete(String id) {
        boolean result = false;
        try {
            PreparedStatement pst = getConexion().prepareStatement("DELETE FROM Empleado WHERE id_empleado=?");
            pst.setString(1, id);
            result = pst.execute();
        } catch (Exception e) {
            System.out.println("Error en EmpleadoDAO.Delete: " + e.getMessage());
        }
        return result;
    }
}