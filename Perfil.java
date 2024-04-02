import java.util.Scanner;
import java.util.ArrayList;

public abstract class Perfil {

    protected String contrasena;
    protected String nombreUsuario;
    protected ArrayList<Horario> horarios;

    public void iniciarSesion(String contrasenaUs){
        String contrasena1;
        Scanner entrada = new Scanner(System.in);
        do{
            System.out.print("Ingrese su contrasena:");
            contrasena1 = entrada.nextLine();
        }while(!contrasenaUs.equals(contrasena1));
        this.verFunciones();
    }

    public String getNombreUsuario(){
        return this.nombreUsuario;
    }

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }
    
    public String getContrasena() {
        return contrasena;
    }

    public abstract void verFunciones();
    
}