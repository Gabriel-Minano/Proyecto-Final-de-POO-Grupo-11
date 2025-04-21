package proyectofinalpoov1;

public class PagoTarjeta extends Pago { //PagoTarjeta heredára los métodos y atributos de la clase pago

    private String lectorId;

    public PagoTarjeta(String lectorId, double monto) { //monto es un atributo heredado, lo mismo se hará en pago efectivo
        this.lectorId = lectorId;
        this.monto = monto;
    }

    @Override  //Con esto se rescribe el método en la clase Padre
    public boolean procesar(double montoTotal) {
        return monto >= montoTotal;
    }

    public String getLectorId() {
        return lectorId;
    }

}
