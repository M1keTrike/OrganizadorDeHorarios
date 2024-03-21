import java.util.Scanner;
import java.util.ArrayList;

public abstract class Perfil {

    protected String contrasena;
    protected String nombreUsuario;
    protected ArrayList<Horario> horarios;

    public void iniciarSesion(){
        String contrasena;
        Scanner entrada = new Scanner(System.in);
            
        do{
                System.out.println("Ingrese su contrasena");
                contrasena = entrada.nextLine();
                
                entrada.nextLine();
            }while(this.contrasena != contrasena);
            
    }

    public String getNombreUsuario(){
        return this.nombreUsuario;
    }

    public void verFunciones(){}

    
}