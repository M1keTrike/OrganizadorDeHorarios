import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class PerfilPrefecto extends Perfil{
    
    
    public PerfilPrefecto(String nombreU,String contrasenaU){
        this.contrasena = contrasenaU;
        this.nombreUsuario = nombreU;
        this.horarios = new ArrayList<>();
    }

    public void verHorario(){
        Scanner entrada = new Scanner(System.in);
        String seleccion;
        Materia[][] aux ;
        String dias[] = {"---------","--Lunes--","--Martes-","Miercoles","--Jueves-","-Viernes-", ""};
        String modulos[] = {"7:00-7:50","7:50-8:40","8:40-9:30","10:00-10:50","10:50-11:40","11:40-12:30","12:30-13:20","13:20-14:10"};
        if(horarios.isEmpty()){
            System.out.println("la lista de horarios esta vacia");
            this.verFunciones();
        }else{
            System.out.println("Escriba el grupo del horario a visualizar: ");
            for(Horario elemento  : this.horarios){
                System.out.println("\n-- " + elemento.getGrupo() + " --");
            }
            seleccion = entrada.nextLine();
            for(Horario elemento  : this.horarios){
                if(elemento.getGrupo().equals(seleccion)){
                    System.out.println("\n--Grupo: " + elemento.getGrupo() + "--\n");
                    System.out.println("\n--Generacion: " + elemento.getGeneracion() + "--\n");
                    aux = elemento.getMaterias();
                    for (int i = 0; i < 6; i++) {
                        System.out.print(dias[i] + "\t");
                    for (int j = 0; j < 8; j++) {
                        if(i==0){
                            System.out.print(modulos[j] + "\t");
                        }else{

                            System.out.print(aux[i-1][j].getNombre() + "\t");
                        }
                    }
                    System.out.println("\n");
                }
                }
            }
        }



    }

    public void verInterfazEspecial(){
        if (this.horarios.isEmpty()) {
            System.out.println("Aun no existen horarios, crea uno e intentalo de nuevo");
            this.verFunciones();
        }
        Scanner entrada = new Scanner(System.in);
        String busqueda;
        int horaTReal = 0;
        int diaTReal = LocalDate.now().getDayOfWeek().getValue()-1;
        Horario horarioVisualizar = this.horarios.get(0);


        boolean encontrado = true;
        do{
            System.out.println("Escriba el grupo del horario a visualizar");
            for(Horario elemento  : this.horarios){
                System.out.println("\n--" + elemento.getGrupo() + " --");
            }
            busqueda = entrada.nextLine();

            for(Horario elemento : this.horarios){
                if (elemento.getGrupo().equals(busqueda)) {
                    horarioVisualizar = elemento;
                    encontrado = false;
                }
            }
            if (encontrado) {
                int eleccion;
                System.out.println("No se encontro un horario con ese grupo, desea regresar al menu anterior? 1.Si 2.No");
                eleccion = this.decidir();
                if (eleccion == 1) {
                    this.verFunciones();
                }
            }
        }while(encontrado);

        
        
        

       

        LocalTime hora = LocalTime.now();
        LocalTime horaInicio = LocalTime.of(7,00);
        LocalTime horaFin = LocalTime.of(14,10);
        
        if (hora.isAfter(horaInicio) && hora.isBefore(horaFin)) {
            String horas[] = {"7:00","7:50","8:40","10:00","10:50","11:40","12:30","13:20"};
            boolean flag = true;
            LocalTime horaComparar;

            for(int i = 0; i<8 && flag; i++){
                    horaComparar = LocalTime.parse(horas[i], DateTimeFormatter.ofPattern("H:mm"));
                if(hora.isBefore(horaComparar)){
                    horaTReal = i-1;
                    flag = false;
                }
            }
                
            
        }else {
            System.out.println("Esta fuera del horario escolar, por defecto se muestra la primera hora del dia para ese grupo");
        }

        if (diaTReal == 7 || diaTReal == 6) {
            diaTReal = 0;
        }
        Materia[][] auxMaterias = horarioVisualizar.getMaterias();
        
        System.out.println("---------------------HORARIO EN TIEMPO REAL---------------------");

        mostrarMateria(horarioVisualizar.getGrupo(),auxMaterias, horaTReal, diaTReal);

        
    }

    public void mostrarMateria(String nombre,Materia[][] auxMaterias,int horaTReal, int diaTReal){
        
        String dias[] = {"Lunes","Martes","Miercoles","Jueves","Viernes"};
        String modulos[] = {"7:00-7:50","7:50-8:40","8:40-9:30","10:00-10:50","10:50-11:40","11:40-12:30","12:30-13:20","13:20-14:10"};
        int controles;

        System.out.println("\n--Grupo: " + nombre + " --");
        
        for (int i = 0; i < 5; i++) {
            for(int j = 0; j < 8 ; j++){
                if (i == diaTReal && j == horaTReal) {
                    System.out.println("\n--Materia: " + auxMaterias[i][j].getNombre() + " --");
                    System.out.println("\n--Maestro: " + auxMaterias[i][j].getMaestro().getNombre() + " -- ID: " + auxMaterias[i][j].getMaestro().getId() + " --");
                    System.out.println(dias[i]);
                    System.out.println(modulos[j]);
                }
            }
        }
        
        System.out.println("1.Hora siguiente 2.Hora anterior 3.Dia siguiente 4.Dia anterior (Otro numero).Salir");

        controles = this.decidir();
        switch (controles) {
            case 1:
                horaTReal ++;
                if(horaTReal > 7){
                    horaTReal = 0;
                }
                mostrarMateria(nombre,auxMaterias, horaTReal, diaTReal);   
                break;
            case 2:
                horaTReal --;
                if (horaTReal < 0) {
                    horaTReal = 7;
                }
                mostrarMateria(nombre,auxMaterias, horaTReal, diaTReal);
                break;
            case 3:
                diaTReal ++;
                if (diaTReal > 4) {
                    diaTReal = 0;
                }
                mostrarMateria(nombre,auxMaterias, horaTReal, diaTReal);
                break;
            case 4:
                diaTReal --;
                if (diaTReal < 0) {
                    diaTReal = 4;
                }
                mostrarMateria(nombre,auxMaterias, horaTReal, diaTReal);
                break;
        
            default:
                break;
        }
    }


    public void verFunciones(){
        Scanner entrada = new Scanner(System.in);
        int eleccion;
        System.out.println("------------------Bienvenido------------------");
        System.out.println("1 = Ver Horarios\t2 = Ver horario en tiempo real \t (otro numero) = cerrar sesion");
        eleccion = this.decidir();
        switch (eleccion) {
            case 1:
                this.verHorario();
                break;
            case 2: 
            this.verInterfazEspecial();
                break;
            default:
                System.out.println("¡Sesion cerrada!");
                break;
        }
        
    }
    
}