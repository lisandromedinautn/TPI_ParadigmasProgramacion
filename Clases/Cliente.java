package Clases;

public class Cliente {
    
    private int numero_de_cliente;
    private int cuil;
    
    public Cliente() {}
    
    public Cliente(int numero_de_cliente, int cuil) {
            this.numero_de_cliente = numero_de_cliente;
            this.cuil = cuil;
        }
    public void setNumero_de_cliente(int numero_de_cliente) {
        this.numero_de_cliente = numero_de_cliente;
    }
    public int getNumero_de_cliente() {
        return numero_de_cliente;
    }
    public int getCuil() {
        return cuil;
    }
    public void setCuil(int cuil) {
        this.cuil = cuil;
    }
}
