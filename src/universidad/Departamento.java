package universidad;

import java.util.ArrayList;
import java.util.Scanner;

public class Departamento {
    //Declaración de atributos privados de los objetos departamentos.
    private String nombre;
    private String descripcion;
    private int maxEstudiantes;
    private ArrayList <Curso> cursosDeLosQueEsResponsable = new ArrayList<>(); 
    private ArrayList <Estudiante> estudiantesAdjuntos = new ArrayList<>(); 
    //Constructures, uno vacío y otro con las propiedades que nos interesa para crear un departamento de inicio.
    public Departamento(){
    }
    public Departamento(String nombre, String descripcion, int maxEstudiantes) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setMaxEstudiantes(maxEstudiantes);
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setMaxEstudiantes(int maxEstudiantes) {
        this.maxEstudiantes = maxEstudiantes;
    }
    public void setCursosDeLosQueEsResponsable(ArrayList<Curso> cursosDeLosQueEsResponsable) {
        this.cursosDeLosQueEsResponsable = cursosDeLosQueEsResponsable;
    }
    public void setEstudiantesAdjuntos(ArrayList<Estudiante> estudiantesAdjuntos) {
        this.estudiantesAdjuntos = estudiantesAdjuntos;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getMaxEstudiantes() {
        return maxEstudiantes;
    }
    public ArrayList<Curso> getCursosDeLosQueEsResponsable() {
        return cursosDeLosQueEsResponsable;
    }
    public ArrayList<Estudiante> getEstudiantesAdjuntos() {
        return estudiantesAdjuntos;
    }
    public int numCursos(){
        return cursosDeLosQueEsResponsable.size();
    }
    public int numEstudiante(){
        return estudiantesAdjuntos.size();
    }
    
    //Método que comprueba si hay creado algún departamento o no.
    public boolean noExisteDepartamento(Universidad u){
        if(u.getDepartamentos().isEmpty()){
            System.out.println("Todavía no se ha creado ningún departamento.");
            System.out.println("Operación cancelada.");
            return true;
        }
        return false;
    }
    //Método que pide al usuario los datos pertenecientes a un departamento que puede o no haber sido creado.
    public Departamento obtenerDep(Universidad u){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del departamento: ");
        String nombreBuscado = sc.nextLine();
        nombreBuscado = u.revisionCadenaVacia(nombreBuscado);
        //Llamada al método buscarDepartamento para saber si existe un departamento que tenga el nombre que acaba de ser ingresado.
        Departamento d = u.buscarDepartamento(nombreBuscado);
        //Devuelve null si no existe el departamento que se estaba intenando buscar.
        if(d==null){
            System.out.println("El departamento todavía no ha sido creado.");
            System.out.println("Operación cancelada.");
            return null ;
        }
        return d; 
    }
    //Método para buscar un curso en un departamento.
    public Curso buscarCurso(Universidad u){
        Curso c = new Curso();
        //Comprobación de si hay creado algún departamento o no.
        if(noExisteDepartamento(u)){
            return null;
        }
        //Comprobación de si hay creado algún curso o no.
        if(c.noExisteCurso(u)){
            return null;
        }
        Departamento d = obtenerDep(u);
        //Devuelve null si no existe el departamento que se estaba intenando buscar.
        if(d==null){
            return null;
        }
        Curso c1 = c.obtenerCurso(u);
        //Devuelve null si no existe el curso que se estaba intenando buscar.
        if(c1==null){
            return null;
        }
        //Se obtiene la lista de cursos de los que es responsable el departamento.
        for(int i = 0; i < d.cursosDeLosQueEsResponsable.size(); i++) {
            if(c1.getNombre().compareTo(d.cursosDeLosQueEsResponsable.get(i).getNombre())==0){
                return d.cursosDeLosQueEsResponsable.get(i);
            }
        }
        //Se avisa al usuario de que no existe el curso ingresado en el departamento ingresado.
        System.out.println("No existe ese curso en este departamento.");
        return null;
    }
    //Método que comprueba si un departamento esta lleno(de estudiantes).
    public boolean estaLleno(Universidad u){
        //Comprobación de si hay creado algún departamento o no.
        if(noExisteDepartamento(u)){
            return true;
        }
        Departamento d = obtenerDep(u);
        //Devuelve null si no existe el departamento que se estaba intenando buscar.
        if(d==null){
            return true;
        }
        //Se avisa al usuario de si el departamento está lleno o no.
        if(d.maxEstudiantes<=d.estudiantesAdjuntos.size()){
            System.out.println("El departamento "+d.getNombre()+" está lleno.");
            return true;
        }
        System.out.println("El departamento "+ d.getNombre()+" NO está lleno.");
        return false;
    }
    //Método que imprime los cursos de un departamento.
    public boolean imprimirCursos(Universidad u){
        //Comprobación de si hay creado algún departamento o no.
        if(noExisteDepartamento(u)){
            return false;
        }
        Departamento d = obtenerDep(u);
        //Devuelve null si no existe el departamento que se estaba intenando buscar.
        if(d==null){
            return false;
        }
        //Se muestra al usuario que el departamento no es responsable de ningún curso.
        if(d.cursosDeLosQueEsResponsable.size()<=0){
            System.out.println("Este departamento no es responsable de ningún curso.");
            return false;
        }
        //Se imprimen los cursos del departamento.
        System.out.println("Los cursos que pertenecen al departamento son: ");
        for(int i =0;i<d.cursosDeLosQueEsResponsable.size();i++){
            System.out.println("- "+d.cursosDeLosQueEsResponsable.get(i).getNombre());
        }
        return true;
    }
    //Método que imprime los estudiantes adjuntos de un departamento.
    public boolean imprimirEstudiantesAdjuntos(Universidad u){
        //Comprobación de si hay creado algún departamento o no.
        if(noExisteDepartamento(u)){
            return false;
        }
        Departamento d = obtenerDep(u);
        //Devuelve null si no existe el departamento que se estaba intenando buscar.
        if(d==null){
            return false;
        }
        //Se muestra al usuario que el departamento no tiene ningún estudiante asignado.
        if(d.estudiantesAdjuntos.size()<=0){
            System.out.println("No hay estudiantes asignados al departamento.");
            return false;
        }
        //Se muestra al usuario la lista de estudiantes adjuntos al departamento.
        for(int i =0;i<d.estudiantesAdjuntos.size();i++){
            System.out.println("- "+d.estudiantesAdjuntos.get(i));
        }
        return true;
    }
    //Método que añade un curso nuevo a cursosDeLosQueEsResponsable.
    public void anadirCurso(Departamento d, Curso c){
        d.cursosDeLosQueEsResponsable.add(c);
        c.anadirDep(d);
    }
    public void anadirEstudiante(Departamento d, Estudiante e){
        d.estudiantesAdjuntos.add(e);
        e.setDepertamentoPertenece(d);
    }
    //Método que añade a un estudiante a un departamento.
    public boolean asignarADept(Universidad u, Estudiante e){
        //Comprobación de si hay creado algún departamento o no.
        if(noExisteDepartamento(u)){
            return false;
        }
        //Comprobación de si hay creado algún estudiante o no.
        if(e.noExisteEstudiante(u)){
            return false;
        }
        Departamento d = obtenerDep(u);
        //Devuelve null si no existe el departamento que se estaba intentando buscar.
        if(d==null){
            return false;
        }
        e=e.obtenerEstudiante(u);
        //Devuelve null si no existe el estudiante que se estaba intentando buscar.
        if(e==null){
            //Se redirige al usuario a crear el estudiante que ha intentado añadir al departamento.
            System.out.println("Redirigiendo a crear el estudiante...");
            for(long l=0;l<5991911199L;l++){
            }
            //Si el departamento está lleno se cancela la creación del nuevo estudiante, de lo contrario se crea.
            if(d.maxEstudiantes<=d.estudiantesAdjuntos.size()){
                System.out.println("El departamento "+d.getNombre()+" está lleno.");
                System.out.println("Operación cancelada.");
                return false;
            }
            e=u.crearEstudiante();
        }
        //Si el departamento está lleno no se puede añadir al estudiante.
        if(d.maxEstudiantes<=d.estudiantesAdjuntos.size()){
            System.out.println("El departamento "+d.getNombre()+" está lleno.");
            System.out.println("Operación cancelada.");
            return false;
        }
        //Si el estudiante ya está en el departamento no podrá volverse a añadir.
        for(int i = 0; i<d.estudiantesAdjuntos.size(); i++){
            if(d.estudiantesAdjuntos.get(i).getNombreCompleto().compareTo(e.getNombreCompleto())==0){
                System.out.println("El estudiante "+e.getNombreCompleto()+" ya está en este departamento.");
                return false;
            }
        }
        //añadimos a la lista el estudiante y asignamos ese departamento al estudiante
        d.estudiantesAdjuntos.add(e);
        e.setDepertamentoPertenece(d);
        System.out.println("El estudiante ha sido asignado correctamente al departamento.");
        return true;
    }
    //Método para listar los estudiantes de un departamento.
    public boolean listarEstudiantes(Universidad u){
        if(noExisteDepartamento(u)){
            return false;
        }
        Departamento d = obtenerDep(u);
        //Si no lo encuentra o no existe, devuelve falso.
        if(d==null){
            return false;
        } 
        //Si la lista de estudiantes adjuntos al departamento esta vacía devuelve falso.
        if(d.estudiantesAdjuntos.isEmpty()){
            System.out.println("No hay ningún estudiante adjunto al departamento "+d.getNombre()+".");
            return false;
        }
        System.out.println("Los estudiantes pertenecientes al departamento son: ");
        for(int i=0;i<d.estudiantesAdjuntos.size();i++){
            System.out.println("- "+d.estudiantesAdjuntos.get(i).getNombreCompleto());
        }
        return true;
    }
    //Método para mostrar información respecto al departamento
    public boolean informacionDepartamento(Universidad u){
        //Comprobación de que departamento existe, si no existe devuelve falso.
        if(noExisteDepartamento(u)){
            return false;
        }
        Departamento d = obtenerDep(u);
        //Si no lo encuentra o no existe, devuelve falso.
        if(d==null){
            return false;
        }
        //Muestra resultados.
        System.out.println("Nombre del departamento: "+d.getNombre());
        System.out.println("Descripción: "+d.getDescripcion());
        System.out.println("Número máximo de estudiantes: "+d.getMaxEstudiantes()+" estudiantes.");
        //Se comprueba si el departamento tiene algún curso de los que es responsable.
        if(d.cursosDeLosQueEsResponsable.isEmpty()){
            System.out.println("El departamento "+d.getNombre()+" todavía no tiene ningún curso del que es responsable.");
        }else{
            System.out.println("Cursos de los que es responsable:");
            for(int i =0;i<d.cursosDeLosQueEsResponsable.size();i++){
                System.out.println("- "+d.cursosDeLosQueEsResponsable.get(i).getNombre());
            }
        }
        //Se comprueba si el departamento tiene algún estudiante adjunto.
        if(d.estudiantesAdjuntos.isEmpty()){
            System.out.println("El departamento "+d.getNombre()+" todavía no tiene ningún estudiante adjunto.");
        }else{
            System.out.println("Estudiantes adjuntos: ");
            for(int i =0;i<d.estudiantesAdjuntos.size();i++){
                System.out.println("- "+d.estudiantesAdjuntos.get(i).getNombre());
            }
        }
        return true;
    }
}
