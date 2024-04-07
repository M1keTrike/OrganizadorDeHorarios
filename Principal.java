import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    static ArrayList<Perfil> perfiles = new ArrayList<>(); 
    static int eleccion;
    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        int eleccion;
        do{
            seleccionarPerfil();
            System.out.println("--------------¿Desea salir del sistema?-------------------\n1 = Si (Esta accion limpiara la memoria y se perderan los horarios)\n2 = volver al menú \t");
            eleccion = decidir();
            
        }while(eleccion == 2);
        
    }


    public static int decidir(){
        int indice = 0;
        boolean excepcion = true;
        do{
            try{
                System.out.print("Introduzca el indice de la opcion del menu: ");
                indice = validarEleccion();
                excepcion = false;
            }catch(InputMismatchException e){
                System.out.print("Introduzca un numero entero: ");
            }
            
        }while(excepcion);
        
        return indice;
       
    } 

    public static int validarEleccion() throws InputMismatchException{
        Scanner entrada = new Scanner(System.in);
        int eleccion = entrada.nextInt();
        return eleccion;
    }

    public static void seleccionarPerfil() {
        if (perfiles.isEmpty()) {
            System.out.println("\n\n-----------No hay perfiles, cree uno para poder manipular la información-------------");
            System.out.println("");
            crearNuevoPerfil();
        }
        do{
            System.out.println("-----------------------------------Bienvenido-------------------------------------------\n\n");
            System.out.println(" - ¿Que desea hacer?");
            System.out.println("1. Iniciar sesión\t  2. Crear nuevo perfil\t (otro numero). salir \t");
            eleccion = decidir();
            switch (eleccion) {
                case 1:
                    boolean NoEncontrado = true;
                    String nombreIngresado;
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
            
            System.out.println("¿desea salir?");
            System.out.println("1 = si\t2 = no");
            eleccion = decidir();
        }while(eleccion == 2);
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
                eleccion = decidir();
                count++;
            }while(eleccion > 2 || eleccion < 1);
        }
    
        System.out.println("");

        System.out.println("Ingrese las credenciales del nuevo perfil empezando por: ");
        System.out.print("Nombre de usuario: ");
        nuevoNombreU = entrada.nextLine();

        do {
            System.out.print("Contraseña: ");
            nuevaContrasenaU = entrada.nextLine();

            System.out.print("Confirme la contraseña:");
            aux = entrada.nextLine();
        } while (!aux.equals(nuevaContrasenaU));
        
        if (eleccion == 1) {
            nuevoPerfil = new PerfilPrefecto(nuevoNombreU, nuevaContrasenaU);
            System.out.println("\n--------------Asigne un ADMINISTRADOR a este perfil---------------------\n");
            System.out.println("Tome en cuenta los siguientes: ");
            for(Perfil perfil: perfiles){
                if (perfil instanceof PerfilAdministrador) {
                    System.out.println( perfil.getNombreUsuario());
                }
            }
            System.out.println("\tEscriba el nombre del administador, guiese por la lista de administradores de arriva");
            System.out.print("\tNombre: ");
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
        System.out.println("-----------perfil Guardado----------------\n\n");
        perfiles.add(nuevoPerfil);
   
    }

    
    
}
