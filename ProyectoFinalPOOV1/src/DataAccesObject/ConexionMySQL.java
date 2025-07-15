package DataAccesObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

public class ConexionMySQL {
//mysql://root:nZSFUSLsoiMaEzRVQiqjjjEFTemxpWde@interchange.proxy.rlwy.net:47104/railway
    //propiedades conexion
    //mysql://root:gGIGLdbFnLgSQVPoXKVHGETejUbvnBVp@shuttle.proxy.rlwy.net:44776/railway

    private String StrConxMySQL = "jdbc:mysql://localhost/proyectofinal"; //Apartir del //pones
    private String StrUserMySQL = "root";
    private String StrPassMySQL = ""; //Contraseña
    private Connection Conexion;

    //Constructor vacio
    public ConexionMySQL() {
        System.out.println("Llamaste a este constructor");
        try { //control error
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            DriverManager.setLoginTimeout(300);
            Conexion = DriverManager.getConnection(StrConxMySQL, StrUserMySQL, StrPassMySQL);
            if (Conexion != null) {
                DatabaseMetaData dm = Conexion.getMetaData();
                System.out.println("Driver Name:" + dm.getDriverName());
                System.out.println("Driver Version:" + dm.getDriverVersion());
                System.out.println("Product Name:" + dm.getDatabaseProductName());
                System.out.println("Product Version:" + dm.getDatabaseProductVersion());

            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public Connection getConexion() {
        return Conexion;
    }

    public static void main(String[] args) {
        System.out.println("Haciendo una instancia de clase");
        ConexionMySQL cn = new ConexionMySQL();
    }

}
