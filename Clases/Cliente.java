package Clases;

public class Cliente {
    
    
    private String nombre;
    private String apellido;
    private String e_mail;
    private int numero_de_cliente;
    private int cuil;
    private String domicilio;
    private String telefono;
    private int documento;
    private TipoDocumento tipodocumento;
    
    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String e_mail, int numero_de_cliente, int cuil, String domicilio,
                String telefono, int documento, TipoDocumento tipodocumento) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.e_mail = e_mail;
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
    public String getE_mail() {
        return e_mail;
    }
    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
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
