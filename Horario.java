
public class Horario {
    private String generacion;
    private String Grupo;
    private  Materia[][] materias;
    public Horario (){
        materias = new Materia[5][8];
    }
    
    public String getGeneracion() {
        return generacion;
    }
    public void setGeneracion(String generacion) {
        this.generacion = generacion;
    }
    public String getGrupo() {
        return Grupo;
    }
    public void setGrupo(String grupo) {
        Grupo = grupo;
    }
    public Materia[][] getMaterias() {
        return materias;
    }

    public void setMaterias(Materia[][] materias) {
        this.materias = materias;
    }
    
    
}