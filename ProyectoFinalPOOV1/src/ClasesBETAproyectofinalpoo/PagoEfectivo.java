package ClasesBETAproyectofinalpoo;

public class PagoEfectivo extends Pago {

    private double montoEntregado;

    public PagoEfectivo(double montoEntregado) {
        this.montoEntregado = montoEntregado;
        this.monto = montoEntregado;
    }

    @Override
    public boolean procesar(double montoTotal) {
        return montoEntregado >= montoTotal;
    }

    public double getVuelto(double montoTotal) {
        return montoEntregado - montoTotal;
    }

    public double getMontoEntregado() {
        return montoEntregado;
    }

}
