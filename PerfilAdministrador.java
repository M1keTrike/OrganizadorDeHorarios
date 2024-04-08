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
        if (listaMaterias.isEmpty()) {
            Materia materiaDefault = new Materia();
            materiaDefault.setNombre("moduloLibre");
            Maestro nulo = new Maestro();
            nulo.setNombre(".");
            nulo.setId(0);
            materiaDefault.asignarMaestro(nulo);
            listaMaterias.add(materiaDefault);
        }
        Materia nuevaMateria = new Materia();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese los datos de la nueva materia");
        System.out.print("Nombre: ");
        nuevaMateria.setNombre(entrada.nextLine());

        
        System.out.println("Ingrese los datos del maestro");
        Maestro nuevoMaestro = new Maestro();
        
        System.out.print("Nombre: ");
        nuevoMaestro.setNombre(entrada.nextLine());

        System.err.println("ingrese el id del maestro");
        nuevoMaestro.setId(this.decidir());

        nuevaMateria.asignarMaestro(nuevoMaestro);

        this.listaMaterias.add(nuevaMateria);

    }

    public void cargarHorario(){
        if (this.listaMaterias.isEmpty()) {
            System.out.println("No hay materias para asiganar al horario, cree una e intentelo de nuevo");
        } else {
            String dias[] = {"Lunes","Martes","Miercoles","Jueves","Viernes"};
            String modulos[] = {"7:00-7:50","7:50-8:40","8:40-9:30","10:00-10:50","10:50-11:40","11:40-12:30","12:30-13:20","13:20-14:10"};
            Scanner entrada = new Scanner(System.in);
            Horario objHorario = new Horario();
            String busqueda;

            System.out.println("\n-------------Ingrese los datos del nuevo horario---------------");
            
            System.out.print("Grupo: ");
            
            objHorario.setGrupo(entrada.nextLine());

            System.out.print("Generacion: ");

            objHorario.setGeneracion(entrada.nextLine());

            System.out.println("\n-----------Lista de materias cargadas-------------\n");

            for (Materia materia : this.listaMaterias) {
                System.out.println(materia.getNombre());
            }
            System.out.println("");

            Materia[][] auxHorario = objHorario.getMaterias();
            boolean encontrado;
            
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    encontrado = true;
                    do{
                        System.out.println("Seleccione la materia para el dia " + dias[i] + " en horario de  " + modulos[j]);
                        System.out.print("ingresando el nombre de la materia:");
                        busqueda = entrada.nextLine();
                        for (Materia materia : this.listaMaterias) {
                            if (materia.getNombre().equals(busqueda)) {
                                encontrado = false;
                                auxHorario[i][j] = materia;
                            }
                        }
                        if (encontrado) {
                            System.out.println("No se encontro el nombre de esa materia");
                        }

                    }while(encontrado);
                }
            }
            System.out.println("-------------Horario cargado-------------------");
            objHorario.setMaterias(auxHorario);
            this.horarios.add(objHorario);
        }

    }

    public void modificarHorario(){
        if (this.horarios.isEmpty()) {
            System.out.println("No hay horarios para modificar, cree uno e intentelo de nuevo");
        } else{
            String dias[] = {"---------","--Lunes--","--Martes-","Miercoles","--Jueves-","-Viernes-", ""};
            String modulos[] = {"7:00-7:50","7:50-8:40","8:40-9:30","10:00-10:50","10:50-11:40","11:40-12:30","12:30-13:20","13:20-14:10"};
            Scanner entrada = new Scanner(System.in);
            String nombre;
            int eleccion;

            System.out.println("--------------Horarios cargados---------------\n");
            for(Horario horario : this.horarios){
                System.out.println(horario.getGrupo());
            }
            System.out.print("Ingrese el nombre del horario a modificar: ");
            nombre = entrada.nextLine();

            for(Horario horario : this.horarios){
                if (nombre.equals(horario.getGrupo())) {
                    Materia[][] auxMaterias = horario.getMaterias();
                    System.out.println("Estos son los datos actuales del horario: ");
                    System.out.println("Grupo: " + horario.getGrupo());
                    System.out.println("Generacion: " + horario.getGeneracion());
                    System.out.println("Materias: ");
                    for (int i = 0; i < 6; i++) {
                            System.out.print(dias[i] + "\t");
                        for (int j = 0; j < 8; j++) {
                            if(i==0){
                                System.out.print(modulos[j] + "\t");
                            }else{

                                System.out.print(auxMaterias[i-1][j].getNombre() + "\t");
                            }
                        }
                        System.out.println("\n");
                    }
                    

                    System.out.println("Que datos desea modificar:\n1.Nombre \t2 = Generacion \t3 = Modificar Materia \t4 = Salir");

                    eleccion = this.decidir();

                    switch (eleccion) {
                        case 1:
                            System.out.print("Cual es el nuevo nombre para el horario: ");
                            nombre = entrada.nextLine();
                            horario.setGrupo(nombre);
                            break;
                        case 2:
                            System.out.print("Cual es la nueva generacion para el horario: ");
                            nombre = entrada.nextLine();
                            horario.setGeneracion(nombre);
                            break;
                        case 3:
                            int auxDia,auxHora;
                            do{
                                System.out.println("Ingrese el indice del dia de la materia a modificar: ");
                                for (int i = 0; i < dias.length; i++) {
                                    if (i != 0 && i < 6) {
                                        System.out.println((i) + " = " + dias[i]);
                                    }
                                }
                                auxDia = this.decidir();
                                System.out.println("Ingrese el indice de la hora de la materia a modificar");
                                for (int i = 0; i < modulos.length; i++) {
                                    System.out.println((i+1) + " = " + modulos[i]);
                                }
                                auxHora = this.decidir();
                            }while(auxDia <= 0 || auxDia > 5 || auxHora <= 0 || auxHora > 8);
                            auxHora--;
                            System.out.println("Cual sera la materia que reemplazara el lugar del dia " + dias[auxDia] + "a la hora " + modulos[auxHora] + "?: ");
                            auxDia--;
                            for(Materia materia: this.listaMaterias){
                                System.out.println(materia.getNombre());
                            }

                            boolean encontrado = true;
                            do{
                                System.out.print("Escriba el nombre de la materia que quiere seleccionar: ");
                                nombre = entrada.nextLine();

                                for(Materia materia: this.listaMaterias){
                                    if (materia.getNombre().equals(nombre)) {
                                        encontrado = false;
                                    }
                                }
                                if (encontrado) {
                                    System.out.println("\tNo se encontro una materia con ese nombre\t");
                                }
    
    
                                
                            }while (encontrado); 
                                
                            for(Materia materia: this.listaMaterias){
                                if (materia.getNombre().equals(nombre)) {
                                    auxMaterias[auxDia][auxHora] = materia;
                                }
                            }
                           
                            
                            horario.setMaterias(auxMaterias);

                            
                            break;
                        default:
                            System.out.println("selecciono salir");
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
        } else {
            System.out.println("--------------Horarios cargados----------------");
            for(Horario horario : this.horarios){
                System.out.println(horario.getGrupo());
            }
            System.out.println("\n");
            System.out.print("Nombre del horario a eliminar: ");
            nombre = entrada.nextLine();
            Horario auxEliminar = null;

            for(Horario horario : this.horarios){
                if (nombre.equals(horario.getGrupo())) {
                    System.out.println("Estas seguro de eliminar el horario con el grupo " + horario.getGrupo() + " \n 1 = Si\t2 = No");
                    confirmacion = this.decidir();
                    
                    if (confirmacion == 1) {
                       auxEliminar = horario;
                    }
                }
            }
            horarios.remove(auxEliminar);

        }
    }

    public void modificarMaterias(){
        if (this.listaMaterias.isEmpty()) {
            System.out.println("No hay materias a modificar, cree una e intentelo de nuevo");
        } else{
            Scanner entrada = new Scanner(System.in);
            String nombre;
            int eleccion;
            System.out.println("Estas son las materias actuales");
            for(Materia materia: this.listaMaterias){
                if (materia.getNombre() != "moduloLibre") {
                    System.out.println("\nNombre: " + materia.getNombre());
                    System.out.println("Maestro correspondiente: " + materia.getMaestro().getNombre() + " con Id: " + materia.getMaestro().getId() + "\n");
                }
            }
            System.out.print("Escriba el nombre de la materia a modificar: ");
            nombre = entrada.nextLine();
            for(Materia materia: this.listaMaterias){
                if(nombre.equals(materia.getNombre())){
                    System.out.println("Cual es el dato a modificar de la materia " + materia.getNombre() + "");
                    System.out.println("");
                    System.out.println("1 = Nombre 2 = Maestro (Otro numero) = Salir");
                    eleccion = this.decidir();

                    switch (eleccion) {
                        case 1:
                            String nuevoNombre;
                            System.out.println("Nombre actual: " + materia.getNombre());
                            System.out.print("Nuevo nombre: ");
                            nuevoNombre = entrada.nextLine();
                            materia.setNombre(nuevoNombre);
                            break;
                        case 2:
                            int nuevoIdM;
                            String nuevoNombreM;
                            System.out.println("Datos actuales del maestro: ");
                            System.out.println("Nombre: " + materia.getMaestro().getNombre());
                            System.out.println("Id: " + materia.getMaestro().getId());
                            System.out.println("Ingrese los nuevos datos del maestro");
                            System.out.print("Nombre: ");
                            nuevoNombreM = entrada.nextLine();
                            materia.getMaestro().setNombre(nuevoNombreM);
                            System.out.println("Introduzca el id ");
                            nuevoIdM = this.decidir();
                            materia.getMaestro().setId(nuevoIdM);
                            break;
                    
                        default:
                            System.out.println("selecciono salir");
                            break;
                    }
                }
            }
            System.out.println("------Cambio guardado-----------");
        }
    }

    public void eliminarMaterias(){
        if (this.listaMaterias.isEmpty()) {
            System.out.println("No hay materia a eliminar");
        } else{
            Scanner entrada = new Scanner(System.in);
            String nombre;
            int eleccion;
            System.out.println("\n\n---Estas son las materias actuales---");
            for(Materia materia: this.listaMaterias){
                System.out.println("\n\t" + materia.getNombre());
                System.out.println("\tDatos del maestro: " + "\tNombre: " + materia.getMaestro().getNombre() + "\tId: " + materia.getMaestro().getId());
            }

            System.out.println("\n\n La materia eliminada SEGUIRA ACTIVA en los horarios creados antes de su eliminacion");
            Materia auxEliminar = null;
            System.out.println("Escriba el nombre de la materia a eliminar: ");
            nombre = entrada.nextLine();
            for(Materia materia: this.listaMaterias){
                if(nombre.equals(materia.getNombre())){
                    System.out.println("Eliminar la materia con nombre: " + materia.getNombre() + " 1 = Si 2 = No");
                    eleccion = this.decidir();
                    if (eleccion == 1) {
                        auxEliminar = materia;
                    }
                }
            }
            this.listaMaterias.remove(auxEliminar);
        }
    }

    public void verFunciones(){
        Scanner entrada = new Scanner(System.in);
        int eleccion;
        do{

            System.out.println("\n-----------------------------Bienvenido Administrador-------------------------------- ");
            System.out.println("");
            System.out.println("1 = Alta materia\n2 = Cargar Horario\n3 = Modificar horario\n4 = Eliminar horario\n5 = Modificar materia \n6 = Eliminar materia\n (otro numero) = cerrar sesion\n");
            eleccion = this.decidir();
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
                case 7:
                    System.out.println("----------------------------¡Sesion cerrada!---------------------------------");
                    break;
                default:
                    System.out.println("se tomara la accion como cerra sesion");
                    System.out.println("----------------------------¡Sesion cerrada!---------------------------------");
                    eleccion = 7;
                break;
            }
        }while(eleccion!=7);
    }
}