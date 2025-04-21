package proyectofinalpoov1;

public class PagoEfectivo extends Pago {

    double montoEntregado;

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

}
