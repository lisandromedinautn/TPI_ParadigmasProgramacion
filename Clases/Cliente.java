package Clases;

public class Cliente extends Persona{
    
    private int numeroCliente;
    private Long cuil;
    
    public Cliente() {}
    
    public Cliente(int numeroCliente, Long cuil, String nombre, String apellido, String eMail, String domicilio,
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
    public Long getCuil() {
        return cuil;
    }
    public void setCuil(Long cuil) {
        this.cuil = cuil;
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() + 
           "\nApellido: " + getApellido() + 
           "\neMail: " + geteMail() +
           "\nDomicilio: " + getDomicilio() +
           "\nTeléfono: " + getTelefono() + 
           "\nDocumento: " + getDocumento() + 
           "\nTipo de Documento: " + getTipodocumento();
    }

}
