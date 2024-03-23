import java.util.ArrayList;
import java.util.Scanner;


public class Principal {
    ArrayList<Perfil> perfiles;
    int eleccion;
    Scanner entrada = new Scanner(System.in);

    public  void main(String[] args) { 
       seleccionarPerfil();
        
        
    }


    public void seleccionarPerfil(){
        if (perfiles.isEmpty()) {
            System.out.println("No hay perfiles,cree uno para poder manipular la informacion");
            crearNuevoPerfil();
        }

        System.out.println("Bienvenido 1.Iniciar sesion 2.Crear nuevo perfil");
        eleccion = entrada.nextInt();

        if (eleccion == 1) {
            String nombreIngresado;

            System.out.println("Ingrese el nombre del usuario");
            nombreIngresado = entrada.nextLine();
            
            for (Perfil perfil : perfiles) {
                if (nombreIngresado.equals(perfil.getNombreUsuario())) {
                    perfil.iniciarSesion();
                }
            }
        } else {
            crearNuevoPerfil();
        }
    }

    public void crearNuevoPerfil(){
        String nuevoNombreU,nuevaContrasenaU,aux;
        Perfil nuevoPerfil;
        
        System.out.println("Que tipo de perfil sera: 1.Prefecto 2.Administrador");
        eleccion = entrada.nextInt();

        entrada.nextLine();

        System.out.println("Ingrese las credenciales del nuevo perfil");
        System.out.println("Ingrese el nuevo nombre de usuario");
        nuevoNombreU = entrada.nextLine();

        entrada.nextLine();

        do{ 
            System.out.println("Ingrese la nueva contrasena");
            nuevaContrasenaU = entrada.nextLine();

            System.out.println("Confirme la contrasena");
            aux = entrada.nextLine();
        }while(!(aux.equals(nuevaContrasenaU)));
            
            if (eleccion == 1) {
                nuevoPerfil = new PerfilPrefecto(nuevoNombreU,nuevaContrasenaU);
            } else {
                nuevoPerfil = new PerfilAdministrador(nuevoNombreU, nuevaContrasenaU);
            }
        
        perfiles.add(nuevoPerfil);
            
    }
}