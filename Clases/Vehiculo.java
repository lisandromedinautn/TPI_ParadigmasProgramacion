package Clases;

// Clase Vehiculo
class Vehiculo {
    
    private String marca;
    private String modelo;
    private String patente;
    private int numeroChasis;
    private int añoFabricacion;
    private int peso;
    private Cliente cliente;

    public Vehiculo() {
        }

    public Vehiculo(String marca, String modelo, String patente, int numeroChasis, int añoFabricacion, int peso, Cliente cliente) {
        this.marca = marca;
        this.modelo = modelo;
        this.patente = patente;
        this.numeroChasis = numeroChasis;
        this.añoFabricacion = añoFabricacion;
        this.peso = peso;
        this.cliente = cliente;
    }

    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(int numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public int getAñoFabricacion() {
        return añoFabricacion;
    }

    public void setAñoFabricacion(int añoFabricacion) {
        this.añoFabricacion = añoFabricacion;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public String toString() {
        return "Marca: " + marca + "\nModelo: " + modelo + "\nPatente: " + patente +
                "\nNúmero de Chasis: " + numeroChasis + "\nAño de Fabricación: " + añoFabricacion +
                "\nPeso: " + peso + " kg\nCliente: " + cliente;
    }

    public String toCSV() {
        return marca + "," + modelo + "," + patente + "," + numeroChasis + "," + añoFabricacion + "," + peso + "," + cliente;
    }

}