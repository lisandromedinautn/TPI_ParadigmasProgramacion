package Clases;

public class TipoDocumento
{
    private String nombre;

    public TipoDocumento(){

    }

    public TipoDocumento(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return nombre;
    }
}
