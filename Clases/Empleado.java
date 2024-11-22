package Clases;

public class Empleado extends Persona {

    private int legajo;
    private String rol;

    public Empleado() {}

    public Empleado(int legajo, String rol, String nombre, String apellido, String eMail, String domicilio,
    String telefono, int documento, TipoDocumento tipodocumento) {
        this.legajo = legajo;
        this.rol = rol;
        super(nombre, apellido, eMail, domicilio, telefono, documento, tipodocumento);
    }
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }
    public int getLegajo() {
        return legajo;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public String getRol() {
        return rol;
    }
}
