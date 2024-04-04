import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;;

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
    
    public int decidir(){
        int indice = 0;
        boolean excepcion = true;
        do{
            try{
                System.out.println("Numero: ");
                indice = validarEleccion();
                excepcion = false;
            }catch(InputMismatchException e){
                System.out.println("Introduzca un numero entero");
            }
            
        }while(excepcion);
        
        return indice;
       
    } 

    public int validarEleccion() throws InputMismatchException{
        Scanner entrada = new Scanner(System.in);
        int eleccion = entrada.nextInt();
        return eleccion;
    }


    public abstract void verFunciones();
    
}