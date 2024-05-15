package universidad;

import java.util.ArrayList;
import java.util.Scanner;

public final class Estudiante {
    //Declaración de atributos privados de los objetos estudiantes.
    private String nombre;
    private String apellidos;
    private String telefono;
    private int edad;
    private Departamento depertamentoPertenece;
    ArrayList <Curso> cursosMatriculados = new ArrayList<>();
    //Constructor de estudiante
    public Estudiante(String nombre, String apellidos, String telefono, int edad) {
        setNombre(nombre);
        setApellidos(apellidos);
        setTelefono(telefono);
        setEdad(edad);
    }
    //Constructor vacío
    public Estudiante(){
    }
    //Getters y setters de estudiante.
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getTelefono(){
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public Departamento getDepertamentoPertenece() {
        return depertamentoPertenece;
    }
    public void setDepertamentoPertenece(Departamento depertamentoPertenece) {
        this.depertamentoPertenece = depertamentoPertenece;
    }
    public ArrayList<Curso> getCursosMatriculado() {
        return cursosMatriculados;
    }
    public void setCursosMatriculado(ArrayList<Curso> cursosMatriculado) {
        this.cursosMatriculados = cursosMatriculado;
    }
    
    //Método que comprueba si hay creado algún estudiante o no.
    public boolean noExisteEstudiante(Universidad u){
        if(u.getEstudiantes().isEmpty()){
            System.out.println("Todavía no se ha creado ningún estudiante.");
            System.out.println("Operación cancelada.");
            return true;
        }
        return false;
    }
    //Método que pide al usuario los datos pertenecientes a un estudiante que puede o no haber sido creado.
    public Estudiante obtenerEstudiante(Universidad u){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del estudiante: ");
        String nombreBuscado = sc.nextLine();
        nombreBuscado = u.revisionCadenaVacia(nombreBuscado);
        System.out.println("Ingrese el primer apellido del estudiante: ");
        String apellido1 = sc.nextLine();
        apellido1 = u.revisionCadenaVacia(apellido1);
        System.out.println("Ingrese el segundo apellido del estudiante: ");
        String apellido2 = sc.nextLine();
        apellido2 = u.revisionCadenaVacia(apellido2);
        String apellidosBuscados = apellido1+" "+apellido2;
        //Llamada al método buscarEstudiante para saber si existe un estudiante que tenga los datos que acaban de ser ingresados.
        Estudiante e = u.buscarEstudiante(nombreBuscado, apellidosBuscados);
        //Devuelve null si no existe el estudiante que se estaba intenando buscar.
        if(e==null){
            System.out.println("El estudiante todavía no ha sido creado.");
            System.out.println("Operación cancelada.");
           return null;
        }
        //Si coincide devuelve el estudiante.
        return e;
    }
    //Método que devuelve el nombre completo del estudiante.
    public String getNombreCompleto(){
        String nombreCompleto = nombre+" "+apellidos; 
        return nombreCompleto;
    }
    //Método añadir curso, añade el curso a la lista del estudiante. Evitando que se repitan cursos.
    public void addCurso(Curso c, Estudiante e){
        int contador=0;
        //Si el curso que se quiere añadir, es igual a uno que está dentro de los cursos en los que está matriculado, el contador sumará uno, y no añadirá el curso.
        for(int i = 0; i<cursosMatriculados.size(); i++) {
            if(c.getNombre().compareTo(cursosMatriculados.get(i).getNombre())==0){
                contador++;
            }
        }
        //Si el contador es igual a 0 es que el curso que se quiere añadir no está en la lista del profesor, y lo añade.
        if(contador==0){
            e.cursosMatriculados.add(c);
        }
        else{
            System.out.println("El estudiante "+e.getNombreCompleto()+" ya se encuentra en este curso.");
        }
    }
    //Método que muestra los cursos a los que pertenece.
    public boolean mostrarCursosPertenece(ArrayList <Estudiante> estudiantes, String nombre, String apellidos){
        Estudiante e = new Estudiante();
        //Se avisa al usuario de que el estudiante no se ha matriculado en ningún curso.
        if(e.cursosMatriculados.isEmpty()){
            System.out.println("El estudiante todavía no se ha matriculado en ningún curso.");
            return false;
        }
        for(int i=0;i<estudiantes.size();i++){
            if(estudiantes.get(i).getNombre().compareTo(nombre)==0&&estudiantes.get(i).getApellidos().compareTo(apellidos)==0){
                e=estudiantes.get(i);
            }
        }
        //Se muestran los cursos en los que está matriculado.
        System.out.println("Cursos en los que está matriculado: ");
        for(int i =0;i<e.cursosMatriculados.size();i++){
            System.out.println("- "+e.cursosMatriculados.get(i).getNombre());
        }
        return true;
    }
    //Método para asignar un departamento a un estudiante.
    public boolean asignarDepartamento(Universidad u, Estudiante e){
        Scanner sc = new Scanner(System.in);
        Departamento guardarDept = new Departamento();
        //Comprobación si hay algún estudiante creado.
        if(noExisteEstudiante(u)){
            return false;
        }
        //Comprobación si hay algún departamento creado.
        if(guardarDept.noExisteDepartamento(u)){
            return false;
        }
        e = obtenerEstudiante(u);
        //Guardamos estudiate en variable e, si esta devuelve null es porque no encuentra al estudiante, o porque este no existe.
        if(e==null){
            return false;
        }
        System.out.println("");
        System.out.println("¿A qué departamento desea asignar al alumno "+e.getNombre()+" "+e.getApellidos()+"?");
        String departamento = sc.nextLine().toLowerCase();
        departamento = u.revisionCadenaVacia(departamento);
        guardarDept = u.buscarDepartamento(departamento);
        //Si el departamento es igual a null es porque no encuentra el departamento o porque no existe.
        if(guardarDept == null){
            System.out.println("El departamento todavía no ha sido creado.");
            System.out.println("Operación cancelada.");
            return false;
        }
        //Guarda el departamento al que pertenece el estudiante. 
        guardarDept.anadirEstudiante(guardarDept, e);
        e.setDepertamentoPertenece(guardarDept);
        return true;
    }
}
