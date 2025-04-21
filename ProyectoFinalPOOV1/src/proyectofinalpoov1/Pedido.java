package proyectofinalpoov1;
//Hay ncapsulamiento y abstracción, este último porque no se explica los datos irrelevantes

public class Pedido {

    private int numero;
    private ItemPedido[] items; //contiene Items de ItemPedido
    private Cliente cliente;

    public Pedido(int numero, ItemPedido[] items, Cliente cliente) {
        this.numero = numero;
        this.items = items;
        this.cliente = cliente;
    }


    public double CalcularMonto() {
        double total = 0;
        for (int i = 0; i < items.length; i++) {
            total += items[i].getSubtotal();

        }
        return total;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ItemPedido[] getItems() {
        return items;
    }

    public void setItems(ItemPedido[] items) {
        this.items = items;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

   

}
