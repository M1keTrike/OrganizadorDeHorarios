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
            System.out.println("Desea salir  1.Si(Esta accion limpiara la memoria y se perderan los horarios)\t 2.No \t");
            eleccion = entrada.nextInt();
        }while(eleccion == 2);
        
    }

    public static void seleccionarPerfil() {
        if (perfiles.isEmpty()) {
            System.out.println("\n\n-----------No hay perfiles, cree uno para poder manipular la información-------------");
            System.out.println("");
            crearNuevoPerfil();
        }

        System.out.println("-----------------------------------Bienvenido-------------------------------------------");
        System.out.println(" - ¿Que desea hacer?");
        System.out.println("1.Iniciar sesión\t  2. Crear nuevo perfil\t (otra tecla). salir \t");
        System.out.print("Opcion: ");
        eleccion = entrada.nextInt();
        switch (eleccion) {
            case 1:
                boolean NoEncontrado = true;
                String nombreIngresado;
                entrada.nextLine();
                System.out.print("Ingrese el nombre del usuario: ");
                nombreIngresado = entrada.nextLine();
                for (Perfil perfil : perfiles) {
                    if (nombreIngresado.equals(perfil.getNombreUsuario())){
                        perfil.iniciarSesion(perfil.getContrasena());
                        NoEncontrado = false; 
                    }
                }
                if (NoEncontrado) {
                    System.out.println("\nUsuario no encontrado\n");
                }
                
                break;
            case 2:
                crearNuevoPerfil();
                break;
            default:
                break;
        }
    }

    public static void crearNuevoPerfil() {
        String nuevoNombreU, nuevaContrasenaU, aux;
        Perfil nuevoPerfil;

        System.out.println(" - - ¿Qué tipo de perfil será?");
        if (perfiles.isEmpty()) {
            System.out.println("\n\nPor defecto, solo se permite crear como primer perfil del sistema uno del tipo ADMINISTRADOR\n\n");
            eleccion = 2;
        }else{
            int count = 0;
            do{
                if(count > 0){
                    System.out.println("Error ------ Seleccione por indice una de las siguientes opciones:");
                }
                System.out.println("\t1.Prefecto\t2.Administrador\t");
                System.out.print("Opcion: ");
                eleccion = entrada.nextInt();
                entrada.nextLine(); 
                count++;
            }while(eleccion > 2 || eleccion < 1);
        }
    
        

        System.out.println("Ingrese las credenciales del nuevo perfil empezando por: ");
        System.out.print("Nombre de usuario: ");
        nuevoNombreU = entrada.nextLine();

        do {
            System.out.print("Contraseña: ");
            nuevaContrasenaU = entrada.nextLine();

            System.out.print("Confirme la contraseña:");
            aux = entrada.nextLine();
        } while (!aux.equals(nuevaContrasenaU));
        
        entrada.nextLine();

        if (eleccion == 1) {
            nuevoPerfil = new PerfilPrefecto(nuevoNombreU, nuevaContrasenaU);
            System.out.println("\n\nAsigne un ADMINISTRADOR a este perfil\n\n");
            System.out.println("Escriba el nombre del administador, guiese por la siguiente lista de administradores: \n\n");
            for(Perfil perfil: perfiles){
                if (perfil instanceof PerfilAdministrador) {
                    System.out.println("\t" + perfil.getNombreUsuario());
                }
            }
            aux = entrada.nextLine();
            for(Perfil perfil: perfiles){
                if (perfil instanceof PerfilAdministrador) {
                    if (perfil.getNombreUsuario().equals(aux)) {
                        nuevoPerfil.setHorarios(perfil.getHorarios());
                    }
                }
            }

            
        } else {
            nuevoPerfil = new PerfilAdministrador(nuevoNombreU, nuevaContrasenaU);
        }

        perfiles.add(nuevoPerfil);
        seleccionarPerfil();
    }

    
    
}
