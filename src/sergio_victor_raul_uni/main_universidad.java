package sergio_victor_raul_uni;

import java.util.Scanner;

public class main_universidad {
    public static void menuPrincipal(Universidad u){
        String num1="1";
        String num2="2";
        String num3="3";
        String num4="4";
        String num5="5";
        String num6="6";
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------------------------");
        System.out.println("Bienvenido a la Universidad de los Scrum Masters.");
        System.out.println("Operaciones:");
        System.out.println("1 - Universidad");
        System.out.println("2 - Departamentos");
        System.out.println("3 - Cursos");
        System.out.println("4 - Profesores");
        System.out.println("5 - Estudiantes");
        System.out.println("6 - Salir");
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("Por favor, ingrese el número correspondiente a la operación que desea realizar:");
        String num = sc.nextLine();
        //Comprobación del número ingresado.
        while (num.compareTo(num1)!=0&&num.compareTo(num2)!=0&&num.compareTo(num3)!=0&&num.compareTo(num4)!=0&&num.compareTo(num5)!=0&&num.compareTo(num6)!=0){ //Comprobación del número ingresado.
            System.out.println("");
            System.out.println("Por favor, ingrese un número que corresponda al rango de números permitidos.");
            num = sc.nextLine();     
        }
        switch(num){
            case "1":
                menuUniversidad(u);
                break;
            case "2":
                menuDepartamentos(u);
                break;
            case "3":
                menuCursos(u);
                break;
            case "4":
                menuProfesores(u);
                break;
            case "5":
                menuEstudiantes(u);
                break;
            default:
                break;
        }
    }
    public static void menuUniversidad(Universidad u){
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------------------------");
        System.out.println("a) Obtener número de departamentos.");
        System.out.println("b) Obtener número de cursos.");
        System.out.println("c) Obtener número de profesores.");
        System.out.println("d) Obtener número de estudiantes.");
        System.out.println("e) Obtener resumen de datos.");
        System.out.println("f) Listar todos los departamentos.");
        System.out.println("g) Listar todos los cursos.");
        System.out.println("h) Listar todos los profesores.");
        System.out.println("i) Listar todos los estudiantes.");
        System.out.println("Ingrese cualquier otra letra para volver al menú principal.");
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("Por favor, ingrese la letra correspondiente a la operación que desea realizar:");
        String letra = sc.nextLine();
        //Comprobación de la letra ingresada.
        while (letra.length()!=1||letra.trim().length()==0){ 
            System.out.println("");
            System.out.println("Por favor, ingrese una letra.");
            letra = sc.nextLine();
        }
        switch(letra){
            case "a":
                System.out.println("Hay un total de "+u.numDepartamentos()+" departamentos.");
                menuPrincipal(u);
                break;
            case "b":
                System.out.println("Hay un total de "+u.numCursos()+" cursos.");
                menuPrincipal(u);
                break;
            case "c":
                System.out.println("Hay un total de "+u.numProfesores()+" profesores.");
                menuPrincipal(u);
                break;
            case "d":
                System.out.println("Hay un total de "+u.numEstudiantes()+" estudiantes.");
                menuPrincipal(u);
                break;
            case "e":
                System.out.println("Hay un total de "+u.numDepartamentos()+" departamentos.");
                System.out.println("Hay un total de "+u.numCursos()+" cursos.");
                System.out.println("Hay un total de "+u.numProfesores()+" profesores.");
                System.out.println("Hay un total de "+u.numEstudiantes()+" estudiantes.");
                menuPrincipal(u);
                break;
            case "f":
                u.listarDepartamentos();
                menuPrincipal(u);
                break;
            case "g":
                u.listarCursos();
                menuPrincipal(u);
                break;
            case "h":
                u.listarProfesores();
                menuPrincipal(u);
                break;
            case "i":
                u.listarEstudiantes();
                menuPrincipal(u);
                break;
            default:
                menuPrincipal(u);
        }
    }
    public static void menuDepartamentos(Universidad u){
        Departamento d = new Departamento();
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------------------------");
        System.out.println("a) Crear nuevo departamento.");
        System.out.println("b) Obtener número de cursos.");
        System.out.println("c) Obtener número de estudiantes.");
        System.out.println("d) ¿Está lleno?");
        System.out.println("e) Añadir estudiante al departamento.");
        System.out.println("f) Obtener resumen de datos.");
        System.out.println("g) Listar cursos en departamentos.");
        System.out.println("h) Listar estudiantes en departamento.");
        System.out.println("Ingrese cualquier otra letra para volver al menú principal.");
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("Por favor, ingrese la letra correspondiente a la operación que desea realizar:");
        String letra = sc.nextLine().toLowerCase();
        Estudiante e = new Estudiante();
        //Comprobación de la letra ingresada.
        while (letra.length()!=1||letra.trim().length()==0){ 
            System.out.println("");
            System.out.println("Por favor, ingrese una letra.");
            letra = sc.nextLine().toLowerCase();
        }
        switch(letra){
            case "a":
                d = u.crearDepartamento();
                menuPrincipal(u);
                break;
            case "b":
                u.obtenerCursosDeLosQueEsResponsable(u);
                menuPrincipal(u);
                break;
            case "c":
                u.obtenerEstudiantesAdjuntos(u);
                menuPrincipal(u);
                break;
            case "d":
                d.estaLleno(u);
                menuPrincipal(u);
                break;
            case "e":
                d.asignarADept(u, e);
                menuPrincipal(u);
                break;
            case "f":
                d.informacionDepartamento(u);
                menuPrincipal(u);
                break;
            case "g":
                d.imprimirCursos(u);
                menuPrincipal(u);
                break;
            case "h":
                d.listarEstudiantes(u);
                menuPrincipal(u);
                break;
            default:
                menuPrincipal(u);
        }
    }
    public static void menuCursos(Universidad u){
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------------------------");
        System.out.println("a) Crear nuevo curso.");
        System.out.println("b) Obtener número de estudiantes.");
        System.out.println("c) Asignar profesor.");
        System.out.println("d) Desasignar profesor.");
        System.out.println("e) Obtener nombre completo del profesor.");
        System.out.println("f) ¿Está lleno?");
        System.out.println("g) Matricular.");
        System.out.println("h) Obtener resumen de datos");
        System.out.println("i) Listar estudiantes en curso.");
        System.out.println("Ingrese cualquier otra letra para volver al menú principal.");
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("Por favor, ingrese la letra correspondiente a la operación que desea realizar:");
        String letra = sc.nextLine().toLowerCase();
        //Creación de objetos para la llamada de sus métodos.
        Curso c = new Curso();
        Estudiante e = new Estudiante();
        Profesor p = new Profesor();
        //Comprobación de la letra ingresada.
        while (letra.length()!=1||letra.trim().length()==0){ 
            System.out.println("");
            System.out.println("Por favor, ingrese una letra.");
            letra = sc.nextLine().toLowerCase();
        }
        switch(letra){
            case "a":
                u.crearCurso();
                menuPrincipal(u);
                break;
            case "b":
                c.numEstudiantes(u);
                menuPrincipal(u);
                break;
            case "c":
                c.asignarProfesor(u, p);
                menuPrincipal(u);
                break;
            case "d":
                c.desasignarProf(u, p);
                menuPrincipal(u);
                break;
            case "e":
                c.getNombreProfesor(u, p);
                menuPrincipal(u);
                break;
            case "f":
                c.estaLleno(u);
                menuPrincipal(u);
                break;
            case "g":
                c.matricular(e, u);
                menuPrincipal(u);
                break;
            case "h":
                c.informacionCurso(u);
                menuPrincipal(u);
                break;
            case "i":
                c.imprimirEstudiantes(u);
                menuPrincipal(u);
                break;
            default:
                menuPrincipal(u);
        }
    }
    public static void menuProfesores(Universidad u){
        Profesor p = new Profesor();
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------------------------");
        System.out.println("a) Crear nuevo profesor.");
        System.out.println("b) Obtener salario de profesor.");
        System.out.println("c) Aumentar sueldo.");
        System.out.println("d) Obtener resumen de datos.");
        System.out.println("e) Listar cursos impartidos por profesor.");
        System.out.println("Ingrese cualquier otra letra para volver al menú principal.");
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("Por favor, ingrese la letra correspondiente a la operación que desea realizar:");
        String letra = sc.nextLine().toLowerCase();
        //Comprobación de la letra ingresada.
        while (letra.length()!=1||letra.trim().length()==0){ 
            System.out.println("");
            System.out.println("Por favor, ingrese una letra.");
            letra = sc.nextLine().toLowerCase();
        }
        switch(letra){
            case "a":
                u.crearProfesor();
                menuPrincipal(u);
                break;
            case "b":
                p.obtenerSalario(u);
                menuPrincipal(u);
                break;
            case "c": 
                p.aumentoSalario(u);
                menuPrincipal(u);
                break;
            case "d":
                p.datosProfesor(u);
                menuPrincipal(u);
                break;
            case "e":
                p.listarCursosImpartidos(u);
                menuPrincipal(u);
                break;
            default:
                menuPrincipal(u);
        }
    }
    public static void menuEstudiantes(Universidad u){
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------------------------");
        System.out.println("a) Crear nuevo estudiante.");
        System.out.println("b) Asignar departamento.");
        System.out.println("c) Obtener resumen de datos.");
        System.out.println("d) Listar cursos matriculados por alumno.");
        System.out.println("Ingrese cualquier otra letra para volver al menú principal.");
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("Por favor, ingrese la letra correspondiente a la operación que desea realizar:");
        String letra = sc.nextLine().toLowerCase();
        //Comprobación de la letra ingresada.
        while (letra.length()!=1||letra.trim().length()==0){ 
            System.out.println("");
            System.out.println("Por favor, ingrese una letra.");
            letra = sc.nextLine().toLowerCase();
        }
        switch(letra){
            case "a":
                u.crearEstudiante();
                menuPrincipal(u);
                break;
            case "b":
                u.asignarDepartamento(u);
                menuPrincipal(u);
                break;
            case "c":
                u.resumenDatosEstudiante();
                menuPrincipal(u);
                break;
            case "d":
                u.cursosMatriculado(u);
                menuPrincipal(u);
                break;
            default:
                menuPrincipal(u);
        }
    }   
    public static void main(String[] args) {
        //Se crea la universidad.
        Universidad u = new Universidad();
        //Llamada al menú principal.
        menuPrincipal(u);
    }   
}
