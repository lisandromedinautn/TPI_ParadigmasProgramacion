package Clases;

public class Cliente {
    
    
    private String nombre;
    private String apellido;
    private String eMail;
    private int numero_de_cliente;
    private int cuil;
    private String domicilio;
    private String telefono;
    private int documento;
    private TipoDocumento tipodocumento;
    
    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String eMail, int numero_de_cliente, int cuil, String domicilio,
                String telefono, int documento, TipoDocumento tipodocumento) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.eMail = eMail;
            this.numero_de_cliente = numero_de_cliente;
            this.cuil = cuil;
            this.domicilio = domicilio;
            this.telefono = telefono;
            this.documento = documento;
            this.tipodocumento = tipodocumento;
        }

    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String geteMail() {
        return eMail;
    }
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
    public int getNumero_de_cliente() {
        return numero_de_cliente;
    }
    public void setNumero_de_cliente(int numero_de_cliente) {
        this.numero_de_cliente = numero_de_cliente;
    }
    public int getCuil() {
        return cuil;
    }
    public void setCuil(int cuil) {
        this.cuil = cuil;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public int getDocumento() {
        return documento;
    }
    public void setDocumento(int documento) {
        this.documento = documento;
    }
    public TipoDocumento getTipodocumento() {
        return tipodocumento;
    }
    public void setTipodocumento(TipoDocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

}
