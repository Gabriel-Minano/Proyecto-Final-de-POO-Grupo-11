package DataAccesObject;

import BusinessEntity.Producto;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

public class ProductoDAO extends ConexionMySQL implements IBaseDAO<Producto> {

    @Override
    public boolean Create(Producto input) {
        boolean result = false;
        try {
            String SQL = "INSERT INTO Producto (id_producto, producto_nombre, producto_descripcion, producto_precio, producto_stock) VALUES (?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            String uuid = UUID.randomUUID().toString();
            input.setId_producto(uuid);
            pst.setString(1, uuid);
            pst.setString(2, input.getProducto_nombre());
            pst.setString(3, input.getProducto_descripcion());
            pst.setDouble(4, input.getProducto_precio());
            pst.setInt(5, input.getProducto_stock());

            result = pst.execute();
        } catch (Exception e) {
            System.out.println("Error en ProductoDAO.Create: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Producto Read(String id) {
        Producto producto = new Producto();
        try {
            PreparedStatement pst = getConexion().prepareStatement("SELECT * FROM Producto WHERE id_producto=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                producto.setId_producto(id);
                producto.setProducto_nombre(res.getString("producto_nombre"));
                producto.setProducto_descripcion(res.getString("producto_descripcion"));
                producto.setProducto_precio(res.getDouble("producto_precio"));
                producto.setProducto_stock(res.getInt("producto_stock"));
            }
        } catch (Exception e) {
            System.out.println("Error en ProductoDAO.Read: " + e.getMessage());
        }
        return producto;
    }

    @Override
    public List<Producto> ReadAll() {
        List<Producto> productos = new ArrayList<>();
        try {
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM Producto");

            while (res.next()) {
                Producto producto = new Producto();
                producto.setId_producto(res.getString("id_producto"));
                producto.setProducto_nombre(res.getString("producto_nombre"));
                producto.setProducto_descripcion(res.getString("producto_descripcion"));
                producto.setProducto_precio(res.getDouble("producto_precio"));
                producto.setProducto_stock(res.getInt("producto_stock"));

                productos.add(producto);
            }
        } catch (Exception e) {
            System.out.println("Error en ProductoDAO.ReadAll: " + e.getMessage());
        }
        return productos;
    }

    @Override
    public boolean Update(Producto input) {
        boolean result = false;
        try {
            String SQL = "UPDATE Producto SET producto_nombre=?, producto_descripcion=?, producto_precio=?, producto_stock=? WHERE id_producto=?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, input.getProducto_nombre());
            pst.setString(2, input.getProducto_descripcion());
            pst.setDouble(3, input.getProducto_precio());
            pst.setInt(4, input.getProducto_stock());
            pst.setString(5, input.getId_producto());

            result = pst.execute();
        } catch (Exception e) {
            System.out.println("Error en ProductoDAO.Update: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean Delete(String id) {
        boolean result = false;
        try {
            PreparedStatement pst = getConexion().prepareStatement("DELETE FROM Producto WHERE id_producto=?");
            pst.setString(1, id);
            result = pst.execute();
        } catch (Exception e) {
            System.out.println("Error en ProductoDAO.Delete: " + e.getMessage());
        }
        return result;
    }
}
