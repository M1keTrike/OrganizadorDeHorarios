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
            materiaDefault.setobjMaestro(nulo);
            listaMaterias.add(materiaDefault);
        }
        Materia nuevaMateria = new Materia();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese los datos de la nueva materia");

        boolean bandera = false;
        String nombreAsignarMateria;
        do{
            bandera = false;
            System.out.print("Nombre: ");
            nombreAsignarMateria = entrada.nextLine();
            for(Materia materia : this.listaMaterias){
                if (nombreAsignarMateria.equals(materia.getNombre())) {
                    bandera = true;
                }
                if (bandera) {
                    System.out.println("\n--Ya existe una materia con ese nombre, escriba otro--\n");
                }
            }
        }while(bandera);

        nuevaMateria.setNombre(nombreAsignarMateria);

        
        System.out.println("Ingrese los datos del maestro");
        nuevaMateria.asignarMaestro(this.listaMaterias);
        

        

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
            

            System.out.println("\n-------------Ingrese los datos del nuevo horario---------------");
            
            System.out.print("Grupo: ");
            
            objHorario.setGrupo(entrada.nextLine());

            System.out.print("Generacion: ");

            objHorario.setGeneracion(entrada.nextLine());

            System.out.println("\n-----------Lista de materias cargadas-------------\n --Ingrese materias usando los indices-- \n");

            for (int i = 0; i < this.listaMaterias.size(); i++) {
                System.out.println((i+1) + " " + listaMaterias.get(i).getNombre());
            }

            Materia[][] auxHorario = objHorario.getMaterias();
            boolean bandera = true;
            int eleccion;
            
            for (int i = 0; i < 5; i++) {
                System.out.println("\n--Se cargaran las materias del dia " + dias[i] + " Proceder? 1 = SI 2 = NO--\n");
                eleccion = this.decidir();
                if (eleccion == 1) {
                    for (int j = 0; j < 8; j++) {
                        eleccion = -1;
                        bandera = true;
                        do{
                            System.out.println("Seleccione la materia para el dia " + dias[i] + " en horario de  " + modulos[j]);
                            System.out.print("ingresando el indice de la materia ");
                            
                            eleccion = this.decidir();

                            if (!(eleccion > this.listaMaterias.size() || eleccion <= 0)) {
                                auxHorario[i][j] = this.listaMaterias.get(eleccion-1);
                                bandera = false;
                            }
                            if (bandera) {
                                System.out.println("\n-----El indice ingresado no coincide con ninguna materia cargada-----\n");
                            }
                        }while(bandera);
                    }
                    bandera = true;
                }else{
                    
                    System.out.println("\n---Puede cargar las materias faltantes del dia en el apartado de MODIFICAR HORARIO---\n--Los espacios faltantes seran rellenados con MODULO LIBRE--\n");
                    
                        for(int k = 0; k < 8; k++){
                            auxHorario[i][k] = this.listaMaterias.get(0);
                        }
                    
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
                System.out.println("\n-- " + horario.getGrupo() + " --");
            }
            System.out.print("Ingrese el nombre del horario a modificar: ");
            nombre = entrada.nextLine();

            for(Horario horario : this.horarios){
                if (nombre.equals(horario.getGrupo())) {
                    Materia[][] auxMaterias = horario.getMaterias();
                    System.out.println("\n--Estos son los datos actuales del horario--\n");
                    System.out.println("\n--Grupo: " + horario.getGrupo() + " --\n");
                    System.out.println("\n--Generacion: " + horario.getGeneracion() + " --\n");
                    System.out.println("\n-- Materias --\n");
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
                    

                    System.out.println("Que datos desea modificar:\n1 = Grupo \t2 = Generacion \t3 = Modificar Materia \t4 = Salir");

                    eleccion = this.decidir();

                    switch (eleccion) {
                        case 1:
                            System.out.print("Cual es el nuevo grupo para el horario: ");
                            nombre = entrada.nextLine();
                            horario.setGrupo(nombre);
                            break;
                        case 2:
                            System.out.print("Cual es la nueva generacion para el horario: ");
                            nombre = entrada.nextLine();
                            horario.setGeneracion(nombre);
                            break;
                        case 3:
                            Materia[][] auxHorario = horario.getMaterias();
                            int auxDia,auxHora;
                            boolean bandera;

                            String dias2[] = {"Lunes","Martes","Miercoles","Jueves","Viernes"};
                            String modulos2[] = {"7:00-7:50","7:50-8:40","8:40-9:30","10:00-10:50","10:50-11:40","11:40-12:30","12:30-13:20","13:20-14:10"};


                            do{
                                System.out.println("Ingrese el indice del dia de la materia a modificar: ");
                                for (int i = 0; i < dias2.length; i++) {
                                    System.out.println((i+1) + " = " + dias2[i]);
                                }
                                auxDia = this.decidir();

                                System.out.println("Ingrese el indice de la hora de la materia a modificar");
                                for (int i = 0; i < modulos2.length; i++) {
                                    System.out.println((i+1) + " = " + modulos2[i]);
                                }
                                auxHora = this.decidir();
                            }while(auxDia <= 0 || auxDia > 5 || auxHora <= 0 || auxHora > 8);
                            auxHora--;
                            auxDia--;

                            eleccion = -1;
                            bandera = true;

                            do{ 
                                System.out.println("\n---Materias---\n");

                                for (int i = 0; i < this.listaMaterias.size(); i++) {
                                    System.out.println((i+1) + " " + listaMaterias.get(i).getNombre());
                                }

                                System.out.println("\n");

                                System.out.println("Seleccione la materia para el dia " + dias2[auxDia] + " en horario de  " + modulos2[auxHora]);
                                System.out.print("ingresando el indice de la materia ");


                                
                                eleccion = this.decidir();
    
                                if (!(eleccion > this.listaMaterias.size() || eleccion <= 0)) {
                                    auxHorario[auxDia][auxHora] = this.listaMaterias.get(eleccion-1);
                                    bandera = false;
                                }
                                if (bandera) {
                                    System.out.println("\n-----El indice ingresado no coincide con ninguna materia cargada-----\n");
                                }
                            }while(bandera);
                            
                            bandera = true;
                            horario.setMaterias(auxHorario);

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
                            System.out.println("Nombre actual: " + materia.getNombre());
                            System.out.print("\n--Ingrese el nuevo nombre-- \n");
                            boolean bandera = false;
                            String nombreAsignarMateria;
                            do{
                                bandera = false;
                                System.out.print("Nombre: ");
                                nombreAsignarMateria = entrada.nextLine();
                                for(Materia materiaBuscarDup : this.listaMaterias){
                                    if (nombreAsignarMateria.equals(materiaBuscarDup.getNombre())) {
                                        bandera = true;
                                    }
                                    if (bandera) {
                                        System.out.println("\n--Ya existe una materia con ese nombre, escriba otro--\n");
                                    }
                                }
                            }while(bandera);
                            materia.setNombre(nombreAsignarMateria);
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
                            boolean bandera2 = false;
                            do{

                                do{
                                    System.out.println("introduzca el id: ");;
                                    bandera2 = false;
                                    nuevoIdM = this.decidir();
                                    for(Materia materiaBuscarDup : this.listaMaterias){
                                        if (nuevoIdM == materiaBuscarDup.getMaestro().getId()) {
                                            bandera2 = true;
                                        }
                                        if (bandera2) {
                                            System.out.println("\n--Ya existe una profesor con ese id, escriba otro--\n");
                                        }
                                    }
                                }while(bandera2);
                                if (nuevoIdM > 99) {
                                    System.out.println("\n--El id del maestro debe de ser menor a 3 digitos--\n");
                                    materia.getMaestro().setId(nuevoIdM);
                                }
                            }while(nuevoIdM > 99);
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