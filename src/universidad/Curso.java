package universidad;

import java.util.ArrayList;
import java.util.Scanner;

public class Curso {
    //Declaración de atributos privados de los objetos cursos.
    private String nombre;
    private String descripcion;
    private int maxEstudiantes; 
    private int clases;
    private ArrayList <Estudiante> estudiantesMatriculados = new ArrayList<>();
    private Profesor profesorAsignado;
    private Departamento departamentoAsignado;
    //Constructor vacío.
    public Curso(){  
    }
    //Constructor con los diferentes atributos privados de la clase.
    public Curso (String nombre, String descripcion, int maxEstudiantes, int clases,Profesor profesorAsignado,Departamento departamentoAsignado) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setMaxEstudiantes(maxEstudiantes);
        setClases(clases);
        setProfesorAsignado(profesorAsignado); 
    }
    //Métodos getters y setters de los atributos privados de curso.
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getMaxEstudiantes() {
        return maxEstudiantes;
    }
    public void setMaxEstudiantes(int maxEstudiantes) {
        this.maxEstudiantes = maxEstudiantes;
    }
    public int getClases() {
        return clases;
    }
    public void setClases(int clases) {
        this.clases = clases;
    }
    public ArrayList<Estudiante> getEstudiantesMatriculados() {
        return estudiantesMatriculados;
    }
    public void setEstudiantesMatriculados(ArrayList<Estudiante> estudiantesMatriculados) {
        this.estudiantesMatriculados = estudiantesMatriculados;
    }
    public Profesor getProfesorAsignado() {
        return profesorAsignado;
    }
    public void setProfesorAsignado(Profesor profesorAsignado) {
        this.profesorAsignado = profesorAsignado;
    }
    public Departamento getDepartamentoAsignado() {
        return departamentoAsignado;
    }
    public void setDepartamentoAsignado(Departamento departamentoAsignado) {
        this.departamentoAsignado = departamentoAsignado;
    }
    
    //Método que comprueba si hay creado algún curso o no.
    public boolean noExisteCurso(Universidad u){
        if(u.getCursos().isEmpty()){
            System.out.println("Todavía no se ha creado ningún curso.");
            System.out.println("Operación cancelada.");
            return true;
        }
        return false;
    }
    //Si existe el curso buscado por el usuario, es devuelto. De lo contrario se le avisará de que no existe dicho curso.
    public Curso obtenerCurso(Universidad u){
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("Ingrese el nombre del curso: ");
        String nombreBuscado = sc.nextLine();
        nombreBuscado = u.revisionCadenaVacia(nombreBuscado);
        //Llamada al método buscarCurso para saber si existe un curso que tenga el nombre que acaba de ser ingresado.
        Curso c =u.buscarCurso(nombreBuscado);
        //Devuelve null si no existe el curso que se estaba intenando buscar.
        if(c==null){
            System.out.println("El curso todavía no ha sido creado.");
            System.out.println("Operación cancelada.");
            return null ;
        }
        return c;
    }
    //Método que devuelve el número de estudiantes matriculados.
    public int numEstudiantes(Universidad u){
        //Comprobación de si hay creado algún curso o no.
        if(noExisteCurso(u)){
            return 0;
        }
        Curso c = obtenerCurso(u);
        //Si no existiese el curso buscado por el usuario, se cancela la operación. 
        if(c==null){
            return 0;
        }
        int numEstu=0;
        //Comprobación de que si el curso tiene algún estudiante matriculado.
        if(c.estudiantesMatriculados==null){
            System.out.println("El curso "+c.getNombre()+" todavía no tiene ningún estudiante matriculado.");
        }else{
            //Se obtiene el tamaño de la arraylist de estudiantes matriculados y se muestra al usuario.
            numEstu = c.estudiantesMatriculados.size();
            System.out.println("Hay "+numEstu+" estudiantes matriculados en el curso "+c.getNombre()+".");
        }
        return numEstu;  
    }
    //Método para asignar un profesor a un curso.
    public boolean asignarProfesor(Universidad u, Profesor p){
        //Comprobación de si hay creado algún curso o no.
        if(noExisteCurso(u)){
            return false;
        }
        //Comprobación de si hay creado algún profesor o no.
        if(p.noExisteProfesor(u)){
            return false;
        }
        Curso c = obtenerCurso(u);
        //Si no existiese el curso buscado por el usuario, se cancela la operación. 
        if(c==null){
            return false;
        }
        p = p.obtenerProfesor(u);
        //Si no existiese el profesor buscado por el usuario, se cancela la operación. 
        if(p==null){
            return false;
        }
        //Se comprueba si el curso tiene algún profesor asignado y se asigna el profesor buscado en caso de no tener uno asignado.
        if(c.profesorAsignado != null){
            System.out.println("Este curso ya tiene un profesor asignado.");
            return false;   
        }
        //Se avisa al usuario de que el curso ya tiene un profesor asignado.
        c.setProfesorAsignado(p);
        p.addCurso(c, u, p);
        System.out.println("El profesor "+p.getNombreCompleto()+" ha sido asignado con éxito.");
        return true;
    } 
   
    public boolean desasignarProf(Universidad u, Profesor p){
        //Comprobación de si hay creado algún curso o no.
        if(noExisteCurso(u)){
            return false;
        }
        //Comprobación de si hay creado algún profesor o no.
        if(p.noExisteProfesor(u)){
            return false;
        }
        Curso c = obtenerCurso(u);
        //Si no existiese el curso buscado por el usuario, se cancela la operación. 
        if(c==null){
            return false;
        }
        p = c.profesorAsignado;
        //Se comprueba si el curso tiene algún profesor asignado y se desasigna el profesor buscado en caso de tenerlo asignado.
        if(p==null){
            System.out.println("Este curso no tiene ningún profesor asignado.");
            return false;
        }
        //Se desasigna al profesor del curso.
        c.profesorAsignado=null;
        p.eliminarCurso(p,c);
        //Se avisa al usuario de que se ha desasignado el profesor que ha ingresado, del curso ingresado.
        System.out.println("El profesor "+p.getNombreCompleto()+" ha sido desasignado correctamente.");
        return true;
    }
    //Método que devuelve el nombre completo del profesor asignado.
    public String getNombreProfesor(){
        return profesorAsignado.getNombreCompleto();
    }
    public String getNombreProfesor(Universidad u, Profesor p){
        //Comprobación de si hay creado algún curso o no.
        if(noExisteCurso(u)){
            return null;
        }
        //Comprobación de si hay creado algún profesor o no.
        if(p.noExisteProfesor(u)){
            return null;
        }
        //Pedimos al usuario que nos ingrese un curso.
        Curso c = obtenerCurso(u);
        //Comprobación de si existe ese curso.
        if(c==null){
            return null;
        }
        if(c.getProfesorAsignado() == null) {
            System.out.println("Este curso no tiene ningún profesor asignado.");
            return null;
        }
        //Obtenemos el nombre completo del profesor y lo mostramos al usuario.
        String nombreCompleto = c.getProfesorAsignado().getNombreCompleto();
        System.out.println("El nombre completo del profesor asignado a este curso es: "+nombreCompleto+".");
        return nombreCompleto;           
    }
    //Método para saber si un curso tiene asiganado o no a un profesor.
    public boolean estaAsignado(Curso c){
        //Se le comunicará al usuario si el curso no tiene ningún profesor asigando.
        if(c.getProfesorAsignado()== null){
            System.out.println("El curso "+c.getNombre()+" no tiene ningún profesor asignado.");
            return false;
        }
        //Se muestra al usuario que profesor tiene asignado el curso.
        System.out.println("El profesor asignado al curso "+c.getNombre()+" es "+c.getProfesorAsignado().getNombreCompleto()+".");
        return true;
    }
    //Método para saber si un curso está asignado o no a un departamento.
    public boolean estaAsignadoDep(Curso c){
        //Se le comunicará al usuario si el curso no está asignado a ningún departamento.
        if(c.getDepartamentoAsignado()== null){
            System.out.println("El curso "+c.getNombre()+" no está asignado a ningún departamento.");
            return false;
        }
        //Se muestra al usuario a que departamento está asignado el curso.
        System.out.println("El departamento al que está asignado el curso "+c.getNombre()+" es "+c.getDepartamentoAsignado().getNombre()+".");
        return true;
    }
    //Método para comprobar si un método está lleno o no.
    public boolean estaLleno(Universidad u){
        //Comprobación de si hay creado algún curso o no.
        if(noExisteCurso(u)){
            return false;
        }
        Curso c = obtenerCurso(u);
        //Si no existiese el curso buscado por el usuario, se cancela la operación.
        if(c==null){
            return false;
        }
        //Si no hubiese estudiantes matriculados, se cancela la operación.
        if(c.estudiantesMatriculados == null){ 
            System.out.println("Todavía no hay estudiantes matriculados, por tanto no puede estar lleno.");
            System.out.println("Operación cancelada.");
            return false;
        }
        //Si el curso estuviese lleno, se cancela la operación.
        if(c.estudiantesMatriculados.size()>= c.maxEstudiantes){
            System.out.println("El curso está lleno, no se admiten más estudiantes.");
            System.out.println("Operación cancelada.");
            return true;
        }
        //Se muestra al usuario que el curso no está lleno todavia.
        System.out.println("El curso NO está lleno todavía.");
        return false;
    }
    public void anadirDep(Departamento d){
        departamentoAsignado=d;
    }
    //Método para matricular a un estudiante en un curso.
    public boolean matricular(Estudiante e, Universidad u){
        //Comprobación de si hay creado algún curso o no.
        if(noExisteCurso(u)){
            return false;
        }
        Curso c = obtenerCurso(u);
        //Si no existiese el curso buscado por el usuario, se cancela la operación.
        if(c==null){
            return false;
        }
        //Comprobación de si hay creado algún estudiante o no.
        if(e.noExisteEstudiante(u)){
            return false;
        }
        e = e.obtenerEstudiante(u);
        //Si no existiese el estudiante buscado por el usuario, se cancela la operación.
        if(e==null) {
            return false;
        }
        //Se comprueba si el alumno ya está matriculado en este curso o no.
        for(int i = 0; i<c.estudiantesMatriculados.size(); i++){
            if(c.estudiantesMatriculados.get(i).getNombreCompleto().compareTo(e.getNombreCompleto())==0){
                System.out.println("El estudiante "+e.getNombreCompleto()+" ya está matriculado en este curso.");
                return false;
            }
        }
        if(e.getDepertamentoPertenece()==null){
            System.out.println("Para poder matricular a"+ e.getNombreCompleto() +"debe estar asignado a un departamento");
            return false;
        }
        //Si el departamento asignado al curso, estuviese lleno se cancelaría la matriculación.
        if(c.departamentoAsignado.getEstudiantesAdjuntos().size() >= c.departamentoAsignado.getMaxEstudiantes()) {
            System.out.println("El departamento "+c.departamentoAsignado.getNombre()+" está lleno, por lo que es imposible matricular al alumno.");
            return false;
        }
        //Si el curso estuviese lleno, se cancela la operación.
        if(c.estudiantesMatriculados.size() >= c.maxEstudiantes){
            System.out.println("No se ha podido matricular al estudiante, el curso está lleno.");
            return false;
        } 
        //Se añade el estudiante al departamento del curso, se asigna el estudiante al curso y el curso al estudiante. 
        Departamento d = c.departamentoAsignado;
        d.anadirEstudiante(d, e);
        c.estudiantesMatriculados.add(e);     
        e.addCurso(c, e);
        //Se muestra al usuario que la operación ha sido exitosa.
        System.out.println("El estudiante "+e.getNombreCompleto()+" ha sido matriculado correctamente en el curso "+c.getNombre()+".");
        return true;
    }
    public boolean informacionCurso(Universidad u){
        //Comprobación de si hay creado algún curso o no.
        if(noExisteCurso(u)){
            return false;
        }
        Curso c = obtenerCurso(u);
        //Si no existiese el curso buscado por el usuario, se cancela la operación. 
        if(c==null){
            return false;
        }
        //Se muestran los datos del curso al usuario.
        System.out.println("Nombre del curso: "+c.getNombre());
        System.out.println("Descripción: "+c.getDescripcion());
        System.out.println("Número máximo de estudiantes: "+c.getMaxEstudiantes()+" estudiantes.");
        System.out.println("Número de clases: "+c.getClases()+" clases.");
        //Comprobación de que si el curso tiene algún estudiante matriculado.
        if(c.estudiantesMatriculados.isEmpty()){
            System.out.println("El curso "+c.getNombre()+" todavía no tiene ningún estudiante matriculado.");
        }else{
            System.out.println("Estudiantes matriculados: ");
            //Se recorre la arraylist y se muestran los estudiantes matriculados.
            for(int i =0;i<c.estudiantesMatriculados.size();i++){
                System.out.println("- "+c.estudiantesMatriculados.get(i).getNombre());
            }
        }
        //LLamada al método para saber si el curso tiene asiganado o no a un profesor.
        estaAsignado(c);
        estaAsignadoDep(c);
        return true;
    }
    //Método para imprimir los estudiantes que forman parte de X cursos.
    public boolean imprimirEstudiantes(Universidad u){
        //Comprobación de si hay creado algún curso o no.
        if(noExisteCurso(u)){
            return false;
        }
        Curso c = obtenerCurso(u);
        //Si no existiese el curso buscado por el usuario, se cancela la operación. 
        if(c==null){
            return false;
        }
        //Comprobación para saber si el curso tiene algún estudiante matriculado.
        if(c.estudiantesMatriculados.isEmpty()){
            System.out.println("El curso "+c.getNombre()+" todavía no tiene ningún estudiante matriculado.");
        }else{
            //Se recorre la arraylist y se muestran los estudiantes matriculados.
            for(int i =0;i<c.estudiantesMatriculados.size();i++){
                System.out.println("- "+c.estudiantesMatriculados.get(i).getNombre());
            }
        }
        return true;
    }
}
