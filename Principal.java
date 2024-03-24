import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    static ArrayList<Perfil> perfiles = new ArrayList<>(); 
    static int eleccion;
    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        int eleccion;
        do{
            seleccionarPerfil();
            System.out.println("Desea regresar /t 1.Si 2.No /t");
            eleccion = entrada.nextInt();
        }while(eleccion == 1);
        
    }

    public static void seleccionarPerfil() {
        if (perfiles.isEmpty()) {
            System.out.println("No hay perfiles, cree uno para poder manipular la información");
            crearNuevoPerfil();
        }

        System.out.println("Bienvenido 1. Iniciar sesión 2. Crear nuevo perfil");
        eleccion = entrada.nextInt();

        if (eleccion == 1) {
            entrada.nextLine(); 

            String nombreIngresado;
            System.out.println("Ingrese el nombre del usuario");
            nombreIngresado = entrada.nextLine();

            for (Perfil perfil : perfiles) {
                if (nombreIngresado.equals(perfil.getNombreUsuario())) {
                    perfil.iniciarSesion();
                    break; 
                }
            }
        } else {
            crearNuevoPerfil();
        }
    }

    public static void crearNuevoPerfil() {
        String nuevoNombreU, nuevaContrasenaU, aux;
        Perfil nuevoPerfil;

        System.out.println("Qué tipo de perfil será: 1. Prefecto 2. Administrador");
        eleccion = entrada.nextInt();
        entrada.nextLine(); 

        System.out.println("Ingrese las credenciales del nuevo perfil");
        System.out.println("Ingrese el nuevo nombre de usuario");
        nuevoNombreU = entrada.nextLine();

        do {
            System.out.println("Ingrese la nueva contraseña");
            nuevaContrasenaU = entrada.nextLine();

            System.out.println("Confirme la contraseña");
            aux = entrada.nextLine();
        } while (!aux.equals(nuevaContrasenaU));
        

        if (eleccion == 1) {
            nuevoPerfil = new PerfilPrefecto(nuevoNombreU, nuevaContrasenaU);
            nuevoPerfil.setHorarios(perfiles.get(0).getHorarios());
        } else {
            nuevoPerfil = new PerfilAdministrador(nuevoNombreU, nuevaContrasenaU);

        }

        perfiles.add(nuevoPerfil);
        seleccionarPerfil();
    }

    
    
}
