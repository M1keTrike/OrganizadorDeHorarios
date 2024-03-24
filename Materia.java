public class Materia {
    Maestro objMaestro = new Maestro();
    private int hora;
    private int dia;
    private String nombre;
    
    public Maestro getMaestro() {
        return objMaestro;
    }
    public void asignarMaestro(Maestro objMaestro) {
        this.objMaestro = objMaestro;
    }
    public int getHora() {
        return hora;
    }
    public void setHora(int hora) {
        this.hora = hora;
    }
    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}