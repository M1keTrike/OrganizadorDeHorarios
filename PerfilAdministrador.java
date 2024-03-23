import java.util.ArrayList;
public class PerfilAdministrador extends Perfil {

    ArrayList<Materia> listaMaterias = new ArrayList<Materia>();
    
    public PerfilAdministrador(String nombreU,String contrasenaU){
        this.contrasena = contrasenaU;
        this.nombreUsuario = nombreU;
    }

    public ArrayList<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(ArrayList<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }
}