package BusinessEntity;

import java.util.Date;

public class Voucher {

    private String id_voucher;
    private String id_pago;
    private Date voucher_fecha;
    private String voucher_contenido;

    public Voucher() {
    }

    public String getId_voucher() {
        return id_voucher;
    }

    public void setId_voucher(String id_voucher) {
        this.id_voucher = id_voucher;
    }

    public String getId_pago() {
        return id_pago;
    }

    public void setId_pago(String id_pago) {
        this.id_pago = id_pago;
    }

    public Date getVoucher_fecha() {
        return voucher_fecha;
    }

    public void setVoucher_fecha(Date voucher_fecha) {
        this.voucher_fecha = voucher_fecha;
    }

    public String getVoucher_contenido() {
        return voucher_contenido;
    }

    public void setVoucher_contenido(String voucher_contenido) {
        this.voucher_contenido = voucher_contenido;
    }

}
