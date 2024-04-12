import java.util.Scanner;
import java.util.InputMismatchException;

public class Materia {
    Maestro objMaestro = new Maestro();
    
    private String nombre;

    public Materia(){}
    

    
    public Maestro getMaestro() {
        return objMaestro;
    }
    public void asignarMaestro() {
        Scanner entrada = new Scanner (System.in);
        Maestro nuevoMaestro = new Maestro();
        
        System.out.print("Nombre: ");
        nuevoMaestro.setNombre(entrada.nextLine());

        int idAsignarMaestro;

        do{
            System.err.println("ingrese el id del maestro");
            idAsignarMaestro = this.decidir();
            if (idAsignarMaestro > 99) {
                System.out.println("\n--El id del maestro debe de ser menor a 3 digitos--\n");
            }
        }while(idAsignarMaestro > 99);

        nuevoMaestro.setId(idAsignarMaestro);
        this.objMaestro = nuevoMaestro;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setobjMaestro(Maestro maestro){
        this.objMaestro = maestro;
    }

    public int decidir(){
        int indice = 0;
        boolean excepcion = true;
        do{
            try{
                System.out.print("Eleccion: ");
                indice = validarEleccion();
                excepcion = false;
            }catch(InputMismatchException e){
                System.out.println("\n¡Debe ser un numero entero!\n");
                System.out.println("vuelva a introducir la opcion");
            }
            
        }while(excepcion);
        
        return indice;
       
    } 

    public int validarEleccion() throws InputMismatchException{
        Scanner entrada = new Scanner(System.in);
        int eleccion = entrada.nextInt();
        return eleccion;
    }
    
}