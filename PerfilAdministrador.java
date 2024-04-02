import java.util.ArrayList;
import java.util.Scanner;;

public class PerfilAdministrador extends Perfil {

    private ArrayList<Materia> listaMaterias = new ArrayList<Materia>();
    
    public PerfilAdministrador(String nombreU,String contrasenaU){
        this.contrasena = contrasenaU;
        this.nombreUsuario = nombreU;
        this.horarios = new ArrayList<>();
    }

    public ArrayList<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(ArrayList<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public void darDeAltaMateria(){
        Materia nuevaMateria = new Materia();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese los datos de la nueva materia");
        System.out.print("Nombre:");

        nuevaMateria.setNombre(entrada.nextLine());

        
        System.out.println("Ingrese los datos del maestro");
        Maestro nuevoMaestro = new Maestro();
        
        System.out.println("Nombre:");
        nuevoMaestro.setNombre(entrada.nextLine());

        entrada.nextLine();

        System.err.println("Id:");
        nuevoMaestro.setId(entrada.nextInt());

        entrada.nextLine();

        nuevaMateria.asignarMaestro(nuevoMaestro);

        this.listaMaterias.add(nuevaMateria);

        this.verFunciones();
    }

    public void cargarHorario(){
        if (this.listaMaterias.isEmpty()) {
            System.out.println("No hay materias para asiganar al horario, cree una e intentelo de nuevo");
            this.verFunciones();
        } else {
            String dias[] = {"Lunes","Martes","Miercoles","Jueves","Viernes"};
            String modulos[] = {"7:00-7:50","7:50-8:40","8:40-9:30","10:00-10:50","10:50-11:40","11:40-12:30","12:30-13:20","13:20-14:10"};
            Scanner entrada = new Scanner(System.in);
            Horario objHorario = new Horario();
            String busqueda;

            System.out.println("\nIngrese los datos del nuevo horario:\n");
            
            System.out.println("\nGrupo:\n");
            
            objHorario.setGrupo(entrada.nextLine());

            entrada.nextLine();

            System.out.println("\nGeneracion:\n");
            objHorario.setGeneracion(entrada.nextLine());

            entrada.nextLine();

            System.out.println("\nIngrese las materias:\n");

            for (Materia materia : this.listaMaterias) {
                System.out.println(materia.getNombre());
            }
            System.out.println("moduloLibre");

            Materia[][] auxHorario = objHorario.getMaterias();
            boolean encontrado = true;
            
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    do{
                        System.out.println("Escriba la materia para el dia: " + dias[i] + " en la hora: " + modulos[j] + ": ");
                        busqueda = entrada.nextLine();
                        for (Materia materia : this.listaMaterias) {
                            if (materia.getNombre().equals(busqueda)) {
                                encontrado = false;
                                auxHorario[i][j] = materia;
                            }
                            if(busqueda.equals("moduloLibre")){
                                Materia moduloLibre = new Materia();
                                moduloLibre.setNombre("moduloLibre");
                                auxHorario[i][j] = moduloLibre;
                                encontrado = false;
                            }
                        }
                        if (encontrado) {
                            System.out.println("No se encontro el nombre de esa materia");
                        }

                    }while(encontrado);
                }
            }
            
            objHorario.setMaterias(auxHorario);
            this.horarios.add(objHorario);
        }
              
        this.verFunciones();


    }

    public void modificarHorario(){
        if (this.horarios.isEmpty()) {
            System.out.println("No hay horarios para modificar, cree uno e intentelo de nuevo");
            this.verFunciones();
        } else{
            String dias[] = {"Lunes","Martes","Miercoles","Jueves","Viernes"};
            String modulos[] = {"7:00-7:50","7:50-8:40","8:40-9:30","10:00-10:50","10:50-11:40","11:40-12:30","12:30-13:20","13:20-14:10"};
            Scanner entrada = new Scanner(System.in);
            String nombre;
            int eleccion;

            System.out.println("Escriba el nombre del horario a modificar");
            for(Horario horario : this.horarios){
                System.out.println(horario.getGrupo());
            }
            nombre = entrada.nextLine();

            for(Horario horario : this.horarios){
                if (nombre.equals(horario.getGrupo())) {
                    Materia[][] auxMaterias = horario.getMaterias();
                    System.out.println("Estos son los datos actuales del horario: ");
                    System.out.println("Grupo: " + horario.getGrupo());
                    System.out.println("Generacion: " + horario.getGeneracion());
                    System.out.println("Materias: ");
                    for (int i = 0; i < 5; i++) {
                            System.out.println(dias[i]);
                        for (int j = 0; j < 8; j++) {
                            System.out.println(modulos[j]);
                            System.out.println(auxMaterias[i][j].getNombre());
                        }
                    }
                    
                    entrada.nextLine();

                    System.out.println("Que datos desea modificar 1.Nombre \t 2.Generacion \t 3.Modificar Materia \t 4.Salir");

                    eleccion = entrada.nextInt();

                    switch (eleccion) {
                        case 1:
                            entrada.nextLine();
                            System.out.println("Cual es el nuevo nombre para el horario: ");
                            nombre = entrada.nextLine();
                            horario.setGrupo(nombre);
                            break;
                        case 2:
                            entrada.nextLine();
                            System.out.println("Cual es la nueva generacion para el horario: ");
                            nombre = entrada.nextLine();
                            horario.setGeneracion(nombre);
                            break;
                        case 3:
                            entrada.nextLine();
                            int auxDia,auxHora;
                            do{
                                System.out.println("Ingrese el indice del dia de la materia a modificar: ");
                                for (int i = 0; i < dias.length; i++) {
                                    System.out.println(i + dias[i]);
                                }
                                auxDia = entrada.nextInt();
                                System.out.println("Ingrese el indice de la hora de la materia a modificar");
                                for (int i = 0; i < dias.length; i++) {
                                    System.out.println(i + modulos[i]);
                                }
                                auxHora = entrada.nextInt();
                            }while(auxDia <= 0 || auxDia > 5 || auxHora <= 0 || auxHora > 8);
                            System.out.println("Cual sera la materia que reemplazara el lugar del dia " + dias[auxDia] + "a la hora " + modulos[auxHora] + "?: ");
                            
                            for(Materia materia: this.listaMaterias){
                                System.out.println(materia.getNombre());
                            }

                            entrada.nextLine();
                            System.out.println("Escriba el nombre de la materia que quiere seleccionar: ");
                            nombre = entrada.nextLine();

                            for(Materia materia: this.listaMaterias){
                                if (materia.getNombre().equals(nombre)) {
                                    auxMaterias[auxDia][auxHora] = materia;
                                }
                            }
                            
                            horario.setMaterias(auxMaterias);
                            
                            break;
                        default:
                            this.verFunciones();
                            break;
                    }
                }
            }
        }
    }

    public void eliminarHorario(){
        Scanner entrada = new Scanner(System.in);
        String nombre;
        int confirmacion;
        if (this.horarios.isEmpty()) {
            System.out.println("No hay horarios a eliminar");
            this.verFunciones();
        } else {
            System.out.println("Escriba el grupo del horario a eliminar");
            for(Horario horario : this.horarios){
                System.out.println(horario.getGrupo());
            }
            nombre = entrada.nextLine();

            for(Horario horario : this.horarios){
                if (nombre.equals(horario.getGrupo())) {
                    System.out.println("Estas seguro de eliminar el horario nombrado: " + horario.getGrupo() + " ? 1.Si 2.No");
                    confirmacion = entrada.nextInt();
                    if (confirmacion == 1) {
                        this.horarios.remove(horario);
                    }
                }
            }

        }

        this.verFunciones();
    }

    public void modificarMaterias(){
        if (this.listaMaterias.isEmpty()) {
            System.out.println("No hay materias a modificar, cree una e intentelo de nuevo");
            this.verFunciones();
        } else{
            Scanner entrada = new Scanner(System.in);
            String nombre;
            int eleccion;
            System.out.println("Estas son las materias actuales");
            for(Materia materia: this.listaMaterias){
                System.out.println(materia.getNombre());
                System.out.println("Datos del maestro: " + "Nombre: " + materia.getMaestro().getNombre() + "Id" + materia.getMaestro().getId());
            }
            System.out.println("Escriba el nombre de la materia a modificar");
            nombre = entrada.nextLine();
            for(Materia materia: this.listaMaterias){
                if(nombre.equals(materia.getNombre())){
                    System.out.println("Cual es el dato a modificar de la materia: " + materia.getNombre());
                    System.out.println("");
                    System.out.println("1.Nombre 2.Maestro (Otro numero).Salir");
                    eleccion = entrada.nextInt();
                    
                    entrada.nextLine();

                    switch (eleccion) {
                        case 1:
                            String nuevoNombre;
                            System.out.println("Nombre actual: " + materia.getNombre());
                            System.out.println("Nuevo nombre: ");
                            nuevoNombre = entrada.nextLine();
                            materia.setNombre(nuevoNombre);
                            break;
                        case 4:
                            int nuevoIdM;
                            String nuevoNombreM;
                            System.out.println("Datos actuales del maestro: ");
                            System.out.println("Nombre: " + materia.getMaestro().getNombre());
                            System.out.println("Id: " + materia.getMaestro().getId());
                            System.out.println("Ingrese los nuevos datos del maestro");
                            System.out.println("Nombre: ");
                            nuevoNombreM = entrada.nextLine();
                            materia.getMaestro().setNombre(nuevoNombreM);
                            System.out.println("Id: ");
                            nuevoIdM = entrada.nextInt();
                            materia.getMaestro().setId(nuevoIdM);
                            break;
                    
                        default:
                            this.verFunciones();
                            break;
                    }
                }
            }
        }
    }

    

    public void eliminarMaterias(){
        if (this.listaMaterias.isEmpty()) {
            System.out.println("No hay materia a eliminar");
            this.verFunciones();
        } else{
            Scanner entrada = new Scanner(System.in);
            String nombre;
            int eleccion;
            System.out.println("Estas son las materias actuales");
            for(Materia materia: this.listaMaterias){
                System.out.println(materia.getNombre());
                System.out.println("Datos del maestro: " + "Nombre: " + materia.getMaestro().getNombre() + "Id" + materia.getMaestro().getId());
            }

            System.out.println("\n\n La materia eliminada SEGUIRA ACTIVA en los horarios creados antes de su eliminacion");

            System.out.println("Escriba el nombre de la materia a eliminar");
            nombre = entrada.nextLine();
            for(Materia materia: this.listaMaterias){
                if(nombre.equals(materia.getNombre())){
                    System.out.println("Eliminar la materia con nombre: " + materia.getNombre() + "1.Si 2.No");
                    eleccion = entrada.nextInt();
                    if (eleccion == 1) {
                        this.listaMaterias.remove(materia);
                    }
                }
            }
        }
        

        this.verFunciones();
    }

    public void verFunciones(){
        Scanner entrada = new Scanner(System.in);
        int eleccion;
        System.out.println("---------------------Seleccione la accion-------------------------- ");
        System.out.println("");
        System.out.println("1.Alta materia\t 2.Cargar Horario\t 3.Modificar horario\t 4.Eliminar horario\t 5.Modificar materia \t6.Eliminar materia\t (otro numero).Salir\t");
        eleccion = entrada.nextInt();
        switch (eleccion) {
            case 1:
                this.darDeAltaMateria();
                break;
            case 2:
                this.cargarHorario();
                break;
            case 3:
                this.modificarHorario();
                break;
            case 4:
                this.eliminarHorario();
                break;
            case 5:
                this.modificarMaterias();
                break;
            case 6:
                this.eliminarMaterias();
                break;
        
            default:
                break;
        }
    }
}