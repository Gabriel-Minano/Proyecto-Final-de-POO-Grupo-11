package ClasesBETAproyectofinalpoo;

/*Para el que va a revisar hay encapsulamiento por los private, getters y 
setters. Además, hay abstracción porque no se explica de que está hecho el producto
solo se muestran datos importantes como su nombre y precio*/
public class Producto {

    private String nombre;
    private double precioUnitario;

    public Producto(String nombre, double precioUnitario) {
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

}
