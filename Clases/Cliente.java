package Clases;

public class Cliente extends Persona{
    
    private int numeroCliente;
    private int cuil;
    
    public Cliente() {}
    
    public Cliente(int numeroCliente, int cuil, String nombre, String apellido, String eMail, String domicilio,
    String telefono, int documento, TipoDocumento tipodocumento) {
        super(nombre, apellido, eMail, domicilio, telefono, documento, tipodocumento);
        this.numeroCliente = numeroCliente;
        this.cuil = cuil;
    }
    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }
    public int getNumeroCliente() {
        return numeroCliente;
    }
    public int getCuil() {
        return cuil;
    }
    public void setCuil(int cuil) {
        this.cuil = cuil;
    }
}
