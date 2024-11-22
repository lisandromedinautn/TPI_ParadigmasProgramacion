package Clases;

public class Revision {
    private int numeroDeRevision;
    private String fechaRealizacion;
    private Vehiculo vehiculo;
    private Empleado empleado;
    private String fechaVencimiento;

    public Revision(){

    }

    public Revision(int numeroDeRevision, String fechaRealizacion, Vehiculo vehiculo, Empleado empleado, String fechaVencimiento){
        this.numeroDeRevision = numeroDeRevision;
        this.fechaRealizacion = fechaRealizacion;
        this.vehiculo = vehiculo;
        this.empleado = empleado;
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getNumeroDeRevision() {
        return numeroDeRevision;
    }

    public void setNumeroDeRevision(int numeroDeRevision) {
        this.numeroDeRevision = numeroDeRevision;
    }

    public String getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(String fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }
    
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
