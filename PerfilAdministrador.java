import java.util.ArrayList;
import java.util.Scanner;;

public class PerfilAdministrador extends Perfil {

    ArrayList<Materia> listaMaterias = new ArrayList<Materia>();
    
    public PerfilAdministrador(String nombreU,String contrasenaU){
        this.contrasena = contrasenaU;
        this.nombreUsuario = nombreU;
    }

    public ArrayList<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(ArrayList<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public void darDeAltaMateria(Materia nuevaMateria){
        if (nuevaMateria == null) {
            nuevaMateria = new Materia();
        }
        String dias[] = {"Lunes","Martes","Miercoles","Jueves","Viernes"};
        String modulos[] = {"7:00-7:50","7:50-8:40","8:40-9:30","10:00-10:50","10:50-11:40","11:40-12:30","12:30-13:20","13:20-14:10"};

        System.out.println("-------------------INDICES----------------");

        System.out.println("Dias:");
        for(int i=0;i<dias.length ;i++){
            System.err.print(i+1 + " " + dias[i] + "\t");
        }
        System.out.println("");
        System.out.println("Horas:");
        for(int i=0;i<modulos.length;i++){
            System.err.println(i+1 + " " + modulos[i] + "\t");
        }

        
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese los datos de la nueva materia(Guiese por los indices anteriores)");
        System.out.print("Nombre:");
        nuevaMateria.setNombre(entrada.nextLine());

        entrada.nextLine();

        System.out.println("Ingrese el dia (por indice) de la materia");
        nuevaMateria.setDia(entrada.nextInt());

        entrada.nextLine();

        System.out.println("Ingrese la hora(indice) de la materia");
        nuevaMateria.setHora(entrada.nextInt());

        entrada.nextLine();

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
    }

    public void cargarHorario(){
        if (this.listaMaterias.isEmpty()) {
            System.out.println("No hay materias para asiganar al horario, cree una e intentelo de nuevo");
        } else {
            Scanner entrada = new Scanner(System.in);
            Horario objHorario = new Horario();

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
            int continuar;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    do{ 
                        entrada.nextLine();
                        
                        String nombre;

                        System.out.println("Escriba el nombre de la materia que quiere agregar al horario");
                        nombre = entrada.nextLine();
                        

                        for(Materia materia : this.listaMaterias){
                            if (nombre.equals(materia.getNombre())) {
                                objHorario.getMaterias()
                            }
                        }
                        
                        entrada.nextLine();

                        System.out.println("Quiere agregar otra materia?: 1.Si 2.No");
                        continuar = entrada.nextInt();
                    }while(continuar == 1);
                }
            }
            
            

        }
        


    }

    public void modificarHorario(){
        if (this.horarios.isEmpty()) {
            System.out.println("No hay horarios para modificar, cree uno e intentelo de nuevo");
        } else{
            Scanner entrada = new Scanner(System.in);
            String nombre;

            System.out.println("Escriba el grupo del horario a modificar");
            for(Horario horario : this.horarios){
                System.out.println(horario.getGrupo());
            }
            nombre = entrada.nextLine();

            for(Horario horario : this.horarios){
                if (nombre.equals(horario.getGrupo())) {
                    System.out.println("Estos son los datos actuales del horario");
                    System.out.println("Grupo: " + horario.getGrupo());
                    System.out.println("Generacion: " + horario.getGeneracion());
                    for(Materia materia: horario.getMaterias()){
                        System.out.println(materia.getNombre());
                        System.out.println("Dia: " + materia.getDia() + "Hora: " + materia.getHora());
                    }
                    Horario aux = horario;
                    this.cargarHorario();

                    entrada.nextLine();
                    int eleccion;
                    System.out.println("Actualizar Horario? 1.Si 2.No");
                    eleccion = entrada.nextInt();
                    if (eleccion == 1) {
                        horario = aux;
                        this.horarios.remove(aux);
                    } else {
                        this.horarios.remove(aux);
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
            System.out.println("Escriba el grupo del horario a eliminar");
            for(Horario horario : this.horarios){
                System.out.println(horario.getGrupo());
            }
            nombre = entrada.nextLine();

            for(Horario horario : this.horarios){
                if (nombre.equals(horario.getGrupo())) {
                    System.out.println("Estas seguro de eliminar el horario del grupo" + horario.getGrupo() + " ? 1.Si 2.No");
                    confirmacion = entrada.nextInt();
                    if (confirmacion == 1) {
                        this.horarios.remove(horario);
                    }
                }
            }

        }

        
    }

    public void modificarMaterias(){
        String dias[] = {"Lunes","Martes","Miercoles","Jueves","Viernes"};
        String modulos[] = {"7:00-7:50","7:50-8:40","8:40-9:30","10:00-10:50","10:50-11:40","11:40-12:30","12:30-13:20","13:20-14:10"};

        if (this.listaMaterias.isEmpty()) {
            System.out.println("No hay materias a modificar, cree una e intentelo de nuevo");
        } else{
            Scanner entrada = new Scanner(System.in);
            String nombre;
            int eleccion;
            System.out.println("Estas son las materias actuales");
            for(Materia materia: this.listaMaterias){
                System.out.println(materia.getNombre());
                System.out.println("Dia: " + materia.getDia() + "Hora: " + materia.getHora());
                System.out.println("Datos del maestro: " + "Nombre: " + materia.getMaestro().getNombre() + "Id" + materia.getMaestro().getId());
            }
            System.out.println("Escriba el nombre de la materia a modificar");
            nombre = entrada.nextLine();
            for(Materia materia: this.listaMaterias){
                if(nombre.equals(materia.getNombre())){
                    System.out.println("Cual es el dato a modificar de la materia: " + materia.getNombre());
                    System.out.println("");
                    System.out.println("1.Nombre \t 2.Hora \t 3.Dia \t 4.Maestro \t (Otro numero).Salir");
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
                        case 2:
                            int nuevaHora;
                        System.out.println("Escriba la hora por los indices siguientes: ");
                            for(int i=0;i<modulos.length;i++){
                                System.out.println(i+1 + "\t" + modulos[i]);
                            }
                            System.out.println("Hora actual: " + materia.getHora());
                            System.out.println("Nueva hora: ");
                            nuevaHora = entrada.nextInt();
                            materia.setHora(nuevaHora);
                            break;
                        case 3:
                            int nuevoDia;
                            System.out.println("Escriba el dia por los indices siguientes: ");
                            for(int i=0;i<dias.length;i++){
                                System.out.println(i+1 + "\t" + dias[i]);
                            }
                            System.out.println("Dia actual: " + materia.getDia());
                            System.out.println("Nuevo dia: ");
                            nuevoDia = entrada.nextInt();
                            materia.setDia(nuevoDia);
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
                            break;
                    }
                }
            }
        }
    }

    

    public void eliminarMaterias(){
        Scanner entrada = new Scanner(System.in);
        String nombre;
        int eleccion;
        System.out.println("Estas son las materias actuales");
        for(Materia materia: this.listaMaterias){
            System.out.println(materia.getNombre());
            System.out.println("Dia: " + materia.getDia() + "Hora: " + materia.getHora());
            System.out.println("Datos del maestro: " + "Nombre: " + materia.getMaestro().getNombre() + "Id" + materia.getMaestro().getId());
        }
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

    public void verFunciones(){
        Scanner entrada = new Scanner(System.in);
        int eleccion;
        System.out.println("---------------------Seleccione la accion-------------------------- ");
        System.out.println("");
        System.out.println("1.Alta materia\t 2.Cargar Horario\t 3.Modificar horario\t 4.Eliminar horario\t 5.Modificar materia \t6.Eliminar materia\t (otro numero).Salir\t");
        eleccion = entrada.nextInt();
        switch (eleccion) {
            case 1:
                this.darDeAltaMateria(null);
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