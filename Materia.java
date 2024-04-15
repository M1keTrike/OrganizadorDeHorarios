import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Materia {
    Maestro objMaestro = new Maestro();
    
    private String nombre;

    public Materia(){}
    

    
    public Maestro getMaestro() {
        return objMaestro;
    }
    public void asignarMaestro(ArrayList<Materia> listaMaterias) {
        Scanner entrada = new Scanner (System.in);
        Maestro nuevoMaestro = new Maestro();
        
        System.out.print("Nombre: ");
        nuevoMaestro.setNombre(entrada.nextLine());
        boolean bandera2 = false, bandera = false;
        int idAsignarMaestro;
        
        do{
            do{
                System.out.println("introduzca el id: ");;
                bandera2 = false;
                idAsignarMaestro = this.decidir();
                for(Materia materiaBuscarDup : listaMaterias){
                    if (idAsignarMaestro == materiaBuscarDup.getMaestro().getId()) {
                        bandera2 = true;
                    }
                    if (bandera2) {
                        System.out.println("\n--Ya existe una profesor con ese id, escriba otro--\n");
                    }
                }
            }while(bandera2);
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