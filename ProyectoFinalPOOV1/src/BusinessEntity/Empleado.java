package BusinessEntity;

public class Empleado {

    private String id_empleado;
    private String empleado_nombre;
    private String empleado_telefono;
    private String empleado_direccion;
    private String empleado_cargo;

    public Empleado() {
    }

    public String getEmpleado_cargo() {
        return empleado_cargo;
    }

    public void setEmpleado_cargo(String empleado_cargo) {
        this.empleado_cargo = empleado_cargo;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getEmpleado_nombre() {
        return empleado_nombre;
    }

    public void setEmpleado_nombre(String empleado_nombre) {
        this.empleado_nombre = empleado_nombre;
    }

    public String getEmpleado_telefono() {
        return empleado_telefono;
    }

    public void setEmpleado_telefono(String empleado_telefono) {
        this.empleado_telefono = empleado_telefono;
    }

    public String getEmpleado_direccion() {
        return empleado_direccion;
    }

    public void setEmpleado_direccion(String empleado_direccion) {
        this.empleado_direccion = empleado_direccion;
    }
    
}
