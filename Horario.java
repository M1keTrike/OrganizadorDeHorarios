import java.util.ArrayList;
public class Horario {
    private String generacion;
    private String Grupo;
    private ArrayList<Materia> materias = new ArrayList<Materia>();
    
    
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
    public ArrayList<Materia> getMaterias() {
        return materias;
    }
    public void addMaterias(Materia materia) {
        this.materias.add(materia);
    }
}