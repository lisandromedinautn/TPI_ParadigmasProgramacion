package Clases;

public class Persona {
  
    private String nombre;
    private String apellido;
    private String eMail;
    private String domicilio;
    private String telefono;
    private int documento;
    private TipoDocumento tipodocumento;

    public Persona() {
    }

  public Persona(String nombre, String apellido, String eMail, String domicilio,
                String telefono, int documento, TipoDocumento tipodocumento) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.eMail = eMail;
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
