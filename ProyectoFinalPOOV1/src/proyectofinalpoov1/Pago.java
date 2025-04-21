package proyectofinalpoov1;
//Aquí hay polimorfismo, ya que usaremos procesar de distintas maneras para las distintas formas de pagas

public abstract class Pago { //Sirve como modelo para las otras formas de pago, para ello usaré override

    protected double monto; //Solo lo puse para cumplir con el uso de esta forma de protección, solo lo puede usar la misma clase o clases hijas

    public abstract boolean procesar(double montoTotal); //Se reutilizará en las otras formas de pago. Devuelte true o false si el pago tuvo éxito

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

}
