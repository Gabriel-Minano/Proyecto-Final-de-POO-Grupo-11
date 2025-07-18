package DataAccesObject;

import BusinessEntity.ItemPedido;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

public class ItemPedidoDAO extends ConexionMySQL implements IBaseDAO<ItemPedido> {

    @Override
    public boolean Create(ItemPedido input) {
        boolean result = false;
        try {
            String SQL = "INSERT INTO ItemPedido (id_item, id_pedido, id_producto, item_cantidad, item_precio_unitario, item_subtotal) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);

            // Validación para evitar errores por claves foráneas nulas
            if (input.getId_pedido() == null || input.getId_pedido().isEmpty()) {
                throw new IllegalArgumentException("ID de pedido no puede ser nulo o vacío");
            }

            String uuid = UUID.randomUUID().toString();
            input.setId_item(uuid); // ID del ítem generado por el DAO
            pst.setString(1, uuid);
            pst.setString(2, input.getId_pedido());
            pst.setString(3, input.getId_producto());
            pst.setInt(4, input.getItem_cantidad());
            pst.setDouble(5, input.getItem_precio_unitario());
            pst.setDouble(6, input.getItem_subtotal());

            result = pst.executeUpdate() > 0;
        } catch (IllegalArgumentException e) {
            System.out.println("Validación en ItemPedidoDAO.Create: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error en ItemPedidoDAO.Create: " + e.getMessage());
        }
        return result;
    }

    @Override
    public ItemPedido Read(String id) {
        ItemPedido item = new ItemPedido();
        try {
            PreparedStatement pst = getConexion().prepareStatement("SELECT * FROM ItemPedido WHERE id_item=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                item.setId_item(id);
                item.setId_pedido(res.getString("id_pedido"));
                item.setId_producto(res.getString("id_producto"));
                item.setItem_cantidad(res.getInt("item_cantidad"));
                item.setItem_precio_unitario(res.getDouble("item_precio_unitario"));
                item.setItem_subtotal(res.getDouble("item_subtotal"));
            }
        } catch (Exception e) {
            System.out.println("Error en ItemPedidoDAO.Read: " + e.getMessage());
        }
        return item;
    }

    @Override
    public List<ItemPedido> ReadAll() {
        List<ItemPedido> items = new ArrayList<>();
        try {
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM ItemPedido");

            while (res.next()) {
                ItemPedido item = new ItemPedido();
                item.setId_item(res.getString("id_item"));
                item.setId_pedido(res.getString("id_pedido"));
                item.setId_producto(res.getString("id_producto"));
                item.setItem_cantidad(res.getInt("item_cantidad"));
                item.setItem_precio_unitario(res.getDouble("item_precio_unitario"));
                item.setItem_subtotal(res.getDouble("item_subtotal"));
                items.add(item);
            }
        } catch (Exception e) {
            System.out.println("Error en ItemPedidoDAO.ReadAll: " + e.getMessage());
        }
        return items;
    }

    @Override
    public boolean Update(ItemPedido input) {
        boolean result = false;
        try {
            String SQL = "UPDATE ItemPedido SET id_pedido=?, id_producto=?, item_cantidad=?, item_precio_unitario=?, item_subtotal=? WHERE id_item=?";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, input.getId_pedido());
            pst.setString(2, input.getId_producto());
            pst.setInt(3, input.getItem_cantidad());
            pst.setDouble(4, input.getItem_precio_unitario());
            pst.setDouble(5, input.getItem_subtotal());
            pst.setString(6, input.getId_item());
            result = pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error en ItemPedidoDAO.Update: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean Delete(String id) {
        boolean result = false;
        try {
            PreparedStatement pst = getConexion().prepareStatement("DELETE FROM ItemPedido WHERE id_item=?");
            pst.setString(1, id);
            result = pst.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error en ItemPedidoDAO.Delete: " + e.getMessage());
        }
        return result;
    }

}
