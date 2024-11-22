package Clases;

public class Revision {
    private int numeroDeRevision;
    private String fechaRealizacion;
    private Boolean pago;
    private Vehiculo vehiculo;
    private Empleado empleado;
    private String fechaVencimiento;
    private int nroOblea;
    private Medicion medicion;
    private EstadoResultado resultado;
    private DefectoVisual defectoVisual;
    

    public Revision(){

    }

    public Revision(int numeroDeRevision, String fechaRealizacion, Vehiculo vehiculo, Empleado empleado){
        this.numeroDeRevision = numeroDeRevision;
        this.fechaRealizacion = fechaRealizacion;
        this.vehiculo = vehiculo;
        this.empleado = empleado;
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

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
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

    public int getNroOblea() {
        return nroOblea;
    }

    public void setNroOblea(int nroOblea) {
        this.nroOblea = nroOblea;
    }

    public Medicion getMedicion() {
        return medicion;
    }

    public void setMedicion(Medicion medicion) {
        this.medicion = medicion;
    }

    public EstadoResultado getResultado() {
        return resultado;
    }

    public void setResultado(EstadoResultado resultado) {
        this.resultado = resultado;
    }

    public DefectoVisual getDefectoVisual() {
        return defectoVisual;
    }

    public void setDefectoVisual(DefectoVisual defectoVisual) {
        this.defectoVisual = defectoVisual;
    }

    
    
}
