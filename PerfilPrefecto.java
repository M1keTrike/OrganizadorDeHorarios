import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PerfilPrefecto extends Perfil{
    
    
    public PerfilPrefecto(String nombreU,String contrasenaU){
        this.contrasena = contrasenaU;
        this.nombreUsuario = nombreU;
    }

    public void verHorario(){
        Scanner entrada = new Scanner(System.in);
        String seleccion;

        System.out.println("Escriba el nombre del horario a visualizar");
        if(horarios.isEmpty()){
            System.out.println("la lista de horarios esta vacio");
        }else{
            for(Horario elemento  : this.horarios){
                System.out.println(elemento.getGrupo());
            }
            seleccion = entrada.nextLine();
            for(Horario elemento  : this.horarios){
                if(elemento.getGrupo().equals(seleccion)){
                    System.out.println(elemento.getGrupo());
                    System.out.println(elemento.getGeneracion());
                    for(Materia materia : elemento.getMaterias()){
                        System.out.println(materia.getNombre() + " " + materia.getDia() + " " + materia.getHora());
                    }
                }
            }
        }



    }

    public void verInterfazEspecial(){
        Scanner entrada = new Scanner(System.in);
        String seleccion;
        int horaTReal = 1;
        int diaTReal = LocalDate.now().getDayOfWeek().getValue();
        Horario horarioVisualizar = this.horarios.get(0);

        System.out.println("Escriba el nombre del horario a visualizar");
        for(Horario elemento  : this.horarios){
            System.out.println(elemento.getGrupo());
        }
        
        seleccion = entrada.nextLine();

        for(Horario elemento : this.horarios){
            if (elemento.getGrupo().equals(seleccion)) {
                horarioVisualizar = elemento;
            }
        }

       

        LocalTime hora = LocalTime.now();
        LocalTime horaInicio = LocalTime.of(7,00);
        LocalTime horaFin = LocalTime.of(14,10);
        
        if (hora.isAfter(horaInicio) && hora.isBefore(horaFin)) {
            String horas[] = {"7:00","7:50","8:40","10:00","10:50","11:40","12:30","13:20"};
            boolean flag = true;

            for(int i = 0; i<7 && flag; i++){
                LocalTime horaComparar = LocalTime.parse(horas[i], DateTimeFormatter.ofPattern("H:mm"));
                if(hora.isAfter(horaComparar)){
                    horaTReal = i + 1;
                    flag = false;
                }
            }
                
            
        }

        if (diaTReal == 7 || diaTReal == 6) {
            diaTReal = 1;
        }

        mostrarMateria(horarioVisualizar, horaTReal, diaTReal);

        
    }

    public static void mostrarMateria(Horario horarioVisualizar,int horaTReal, int diaTReal){
        Scanner entrada = new Scanner(System.in);
        String dias[] = {"Lunes","Martes","Miercoles","Jueves","Viernes"};
        String modulos[] = {"7:00-7:50","7:50-8:40","8:40-9:30","10:00-10:50","10:50-11:40","11:40-12:30","12:30-13:20","13:20-14:10"};
        int controles;

        System.out.println(horarioVisualizar.getGrupo());
        String diaDisplay,horaDisplay;
        for(Materia materia : horarioVisualizar.getMaterias()){
            if (materia.getHora() == horaTReal && materia.getDia() == diaTReal) {
                System.out.println(materia.getNombre());
                diaDisplay = dias[materia.getDia()];
                horaDisplay = modulos[materia.getHora()];
                System.out.println(diaDisplay);
                System.out.println(horaDisplay);
            }
        }
        System.out.println("1.Hora siguinete 2.Hora anterior 3.Dia siguiente 4.Dia anterior (Otro numero).Salir");
        controles = entrada.nextInt();
        switch (controles) {
            case 1:
                horaTReal ++;
                if(horaTReal > 8){
                    horaTReal = 1;
                }
                mostrarMateria(horarioVisualizar, horaTReal, diaTReal);   
                break;
            case 2:
                horaTReal --;
                if (horaTReal < 1) {
                    horaTReal = 8;
                }
                mostrarMateria(horarioVisualizar, horaTReal, diaTReal);
                break;
            case 3:
                diaTReal ++;
                if (diaTReal > 5) {
                    diaTReal = 1;
                }
                mostrarMateria(horarioVisualizar, horaTReal, diaTReal);
                break;
            case 4:
                diaTReal --;
                if (diaTReal < 1) {
                    diaTReal = 5;
                }
                mostrarMateria(horarioVisualizar, horaTReal, diaTReal);
                break;
        
            default:
                break;
        }
    }


    public void verFunciones(){
        Scanner entrada = new Scanner(System.in);
        int eleccion;
        System.out.println("Seleccione la accion 1.Ver Horarios 2.Ver interfaz especial 3.Salir");
        eleccion = entrada.nextInt();
        if (eleccion == 1) {
            this.verHorario();
        } else if (eleccion == 2) {
            this.verInterfazEspecial();
        } 
    }
    
}