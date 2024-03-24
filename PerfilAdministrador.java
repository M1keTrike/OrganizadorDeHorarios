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

        System.out.println("INDICES");

        for(int i=0;i<5;i++){
            System.err.println(i+1 + " " + dias[i]);
        }

        for(int i=0;i<7;i++){
            System.err.println(i+1 + " " + modulos[i]);
        }

        
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese los datos de la nueva materia(Guiese por los indices anteriores)");
        System.out.println("Nombre:");
        nuevaMateria.setNombre(entrada.nextLine());

        entrada.nextLine();

        System.out.println("Ingrese el dia(indice) de la materia");
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

    public void cargarHorario(Horario nuevoHorario){
        if (nuevoHorario == null) {
            nuevoHorario = new Horario();
        }
        if (this.listaMaterias.isEmpty()) {
            System.out.println("No hay materias para asiganar al horario, cree una e intentelo de nuevo");
        } else {
            Scanner entrada = new Scanner(System.in);
            
            System.out.println("Ingrese los datos del nuevo horario");
        
            System.out.println("Grupo");
            nuevoHorario.setGrupo(entrada.nextLine());

            entrada.nextLine();

            System.out.println("Generacion:");
            nuevoHorario.setGeneracion(entrada.nextLine());

            entrada.nextLine();

            System.out.println("Ingrese las materias");

            
            ArrayList<Materia> aux = this.listaMaterias;
            int continuar;
            do{ 
                entrada.nextLine();
                
                String nombre;
                
                for(Materia materia : aux){
                    System.out.println(materia.getNombre());
                }

                System.out.println("Escriba el nombre de la materia que quiere agregar al horario");
                nombre = entrada.nextLine();
                

                for(Materia materia : aux){
                    if (nombre.equals(materia.getNombre())) {
                        nuevoHorario.addMaterias(materia);
                    }
                    aux.remove(materia);
                }
                
                entrada.nextLine();

                System.out.println("Quiere agregar otra materia?: 1.Si 2.No");
                continuar = entrada.nextInt();
            }while(continuar == 1);

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
                    this.cargarHorario(aux);

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
                Materia aux = materia;
                this.darDeAltaMateria(aux);
    
                entrada.nextLine();
                System.out.println("Actualizar materia?: 1.Si 2.No");
                eleccion = entrada.nextInt();
                if (eleccion == 1) {
                    materia = aux;
                    this.listaMaterias.remove(aux);
                } else {
                    this.listaMaterias.remove(aux);
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
        System.out.println("Seleccione la accion 1.Alta materia 2.Cargar Horario 3.Modificar horario 4.Eliminar horario 5.Modificar materia 6.Eliminar materia (otro numero).Salir");
        eleccion = entrada.nextInt();
        switch (eleccion) {
            case 1:
                this.darDeAltaMateria(null);
                break;
            case 2:
                this.cargarHorario(null);
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