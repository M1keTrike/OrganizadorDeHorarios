public class Materia {
    Maestro objMaestro = new Maestro();
    
    private String nombre;

    public Materia(){}
    

    
    public Maestro getMaestro() {
        return objMaestro;
    }
    public void asignarMaestro(Maestro objMaestro) {
        this.objMaestro = objMaestro;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}