package Clases;

public class Empleado extends Persona {

    private int legajo;
    private String rol;

    public Empleado() {}

    public Empleado(int legajo, String rol) {
        this.legajo = legajo;
        this.rol = rol;
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
