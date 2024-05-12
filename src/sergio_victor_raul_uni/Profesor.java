package sergio_victor_raul_uni;

import java.util.ArrayList;
import java.util.Scanner;

public class Profesor {
    //Declaración de atributos privados de los objetos profesores.
    private String nombre;
    private String apellidos;
    private String telefono;
    private double salario;
    private ArrayList <Curso> cursosImpartidos = new ArrayList <>();
    //Método constructor de objetos profesor (vacío).
    public Profesor(){
    }
    //Metodo constructor de objetos profesor con sus atributos.
    public Profesor(String nombre, String apellidos, String telefono, int salario){
        setNombre(nombre);
        setApellidos(apellidos);
        setTelefono(telefono);
        setSalario(salario);
    }  
    //Getters y setters de los atributos privados de profesor.
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
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public ArrayList<Curso> getCursosImpartidos() {
        return cursosImpartidos;
    }
    public void setCursosImpartidos(ArrayList<Curso> cursosImpartidos) {
        this.cursosImpartidos = cursosImpartidos;
    }
    public String getNombreCompleto(){
        String nombreCompleto = nombre+" "+apellidos; 
        return nombreCompleto;
    }
    //Método que comprueba si hay creado algún profesor o no.
    public boolean noExisteProfesor(Universidad u){ 
        if(u.getProfesores().isEmpty()){
            System.out.println("Todavía no se ha creado ningún profesor.");
            System.out.println("Operación cancelada.");
            return true;
        }
        return false;
    }
    //Método que pide al usuario los datos pertenecientes a un profesor que puede o no haber sido creado.
    public Profesor obtenerProfesor(Universidad u){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del profesor: ");
        String nombreBuscado = sc.nextLine();
        nombreBuscado = u.revisionCadenaVacia(nombreBuscado);
        System.out.println("Ingrese el primer apellido del profesor: ");
        String apellido1 = sc.nextLine();
        apellido1 = u.revisionCadenaVacia(apellido1);
        System.out.println("Ingrese el segundo apellido del profesor:");
        String apellido2 = sc.nextLine();
        apellido2 = u.revisionCadenaVacia(apellido2);
        String apellidosBuscados = apellido1+" "+apellido2;
        //Llamada al método buscarProfesor para saber si existe un profesor que tenga los datos que acaban de ser ingresados.
        Profesor p =u.buscarProfesor(nombreBuscado, apellidosBuscados);  
        //Devuelve null si no existe el profesor que se estaba intenando buscar.
        if(p==null){
            System.out.println("El profesor todavía no ha sido creado.");
            System.out.println("Operación cancelada.");
            return null ;
        }
        return p;
    }
    //Método aumento de salario del profesor.
    public double aumentoSalario(Universidad u){
        Scanner sc = new Scanner(System.in);
        //Comprobación de si hay creado algún profesor o no.
        if(noExisteProfesor(u)){
            return 0;
        }
        Profesor p = obtenerProfesor(u);
        //Devuelve null si no existe el profesor que se estaba intenando buscar.
        if(p==null){
            return 0;
        }
        System.out.println("Ingrese la cantidad en la que quiere aumentar el salario: ");
        double aumento = sc.nextDouble();
        //Comprobación de que el aumento de salario sea positivo y mayor que cero y que no execeda el salario máximo de un profesor.
        while(aumento<=0||aumento+salario>9999){
            //Aviso al usuario de que el aumento no podrá ser de 0€.
            if(aumento==0){
                System.out.println("No es posible aumentar el salrio en 0€, ya que el salario permanecerá idéntico al anterior.");
                System.out.println("Por favor, ingrese una cantidad de aumento de salario válida.");
                aumento = sc.nextDouble();
            }
            //Aviso al usuario de que el nuevo salario del profesor no podrá ser superior al salario máximo de un profesor.
            if(aumento+salario>9999){
                System.out.println("No es posible aumentar el salario en "+aumento+"€ ya que sino superaría el salario máximo de un profesor, es decir, 10000€.");
                aumento = sc.nextDouble();
            }else{
                //Aviso al usuario de que el aumento no podrá ser negativo
                System.out.println("El aumento de salario no podrá ser negativo, ya que sino estaremos disminuyéndolo.");
                System.out.println("Por favor, ingrese una cantidad de aumento de salario válida.");
                aumento = sc.nextDouble();
            }    
        }
        //Se informa al usuario del nuevo salario y se guarda como dato del profesor.
        p.setSalario(p.getSalario()+aumento);
        System.out.println("El nuevo salario es de: "+p.getSalario()+"€.");
        return p.getSalario();
    }
    //Método para añadir a un profesor a un curso. Evitando que se repitan cursos en la lista de cursos que imparte.
    public void addCurso(Curso c, Universidad u, Profesor p){
        int contador=0;
        //Si el curso que se quiere añadir, es igual a uno que está dentro de los cursos que imparte, el contador sumará uno, y no añadirá el curso.
        for(int i = 0; i<cursosImpartidos.size(); i++) {
            if(c.getNombre().compareTo(p.cursosImpartidos.get(i).getNombre())==0){
                contador++;
            }
        }
        //Si el contador es igual a 0 es que el curso que se quiere añadir no está en la lista del profesor, y lo añade.
        if(contador==0){
           p.cursosImpartidos.add(c);
        }
        else{
            System.out.println("El profesor "+p.getNombreCompleto()+" ya imparte clase en este curso.");
        }
    }
    //Método para obtener el salario de un profesor.
    public double obtenerSalario(Universidad u){
        //Comprobación de si hay creado algún profesor o no.
        if(noExisteProfesor(u)){
            return 0;
        }
        Profesor p = obtenerProfesor(u);
        //Si no existiese el profesor buscado por el usuario, se cancela la operación. 
        if(p==null){
            return 0;
        }
        //Se informa al usuario del salario del profesor.
        System.out.println("El salario de "+p.getNombreCompleto()+" es de "+p.getSalario()+"€.");
        return p.getSalario();
    }
    //Método que lista los cursos impartidos por el profesor.
    public boolean listarCursosImpartidos(Universidad u){
        //Comprobación de si hay creado algún profesor o no.
        if(noExisteProfesor(u)){
            return false;
        }
        Profesor p = obtenerProfesor(u);
        //Si no existiese el profesor buscado por el usuario, se cancela la operación. 
        if(p==null){
            return false;
        }
        //Si el profesor todavía no imparte clase, se le informa al usuario.
        if(p.cursosImpartidos.isEmpty()){
            System.out.println("Este profesor todavía no imparte clase a ningún curso.");
        }else{
            //Se muestra al usuario los cursos que imparte el profesor.
            for(int i =0;i<p.cursosImpartidos.size();i++){
                System.out.println("- "+p.cursosImpartidos.get(i).getNombre());
            }
        }
        return true;
    }
    //Método que muestra los datos de un profesor.
    public boolean datosProfesor(Universidad u){
        //Comprobación de si hay creado algún profesor o no.
        if(noExisteProfesor(u)){
            return false;
        }
        Profesor p = obtenerProfesor(u);
        //Si no existiese el profesor buscado por el usuario, se cancela la operación. 
        if(p==null){
            return false;
        }
        //Se muestran los datos al usuario.
        System.out.println("Nombre completo: "+p.getNombreCompleto()+".");
        System.out.println("Teléfono: "+p.getTelefono()+".");
        System.out.println("Salario: "+p.getSalario()+"€.");
        System.out.println("Cursos impartidos: ");
        //Si el profesor no imparte clase a ningún curso, se le avisa al usuario.
        if(p.cursosImpartidos.isEmpty()){
            System.out.println(p.getNombreCompleto()+" todavía no imparte clase a ningún curso.");
        }else{
            //Se muestran al usuario los cursos que imparte el profesor.
            for(int i =0;i<p.cursosImpartidos.size();i++){
                System.out.println("- "+p.cursosImpartidos.get(i).getNombre());
            }
        }
        return true;
    }
    //Método que elimina el curso de cursos impartidos del profesor.
    public boolean eliminarCurso(Profesor p, Curso c){
        for(int i = 0; i < cursosImpartidos.size(); i++){
            if(c.getNombre().compareTo(cursosImpartidos.get(i).getNombre()) == 0){
                p.cursosImpartidos.remove(c);
            } 
        }
        return true;
    }
}
