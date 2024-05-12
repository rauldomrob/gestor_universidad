package universidad;

import java.util.ArrayList;
import java.util.Scanner;


public class Universidad {
    //Creacion de ArrayList dentro de universidad, para que cuando se creen nuevos objetos, entren dentro del objeto universidad.
    private ArrayList <Departamento> departamentos = new ArrayList<>();
    private ArrayList <Estudiante> estudiantes = new ArrayList<>();
    private ArrayList <Profesor> profesores = new ArrayList<>();
    private ArrayList <Curso> cursos = new ArrayList<>();
   
    public Universidad(){           
    }
    public ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }
    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }
    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }
    public ArrayList<Curso> getCursos() {
        return cursos;
    }
    
    //Método crear estudiante. Se le piden los datos y se comprueba que las cadenas no esten vacías, se juntan apellidos.
    public Estudiante crearEstudiante(){
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("Ingrese el nombre del nuevo estudiante:");
        String nombre = sc.nextLine();
        nombre=revisionCadenaVacia(nombre);
        System.out.println("");
        System.out.println("Ingrese el primer apellido del nuevo estudiante:");
        String apellido1 = sc.nextLine();
        apellido1=revisionCadenaVacia(apellido1);
        System.out.println("");
        System.out.println("Ingrese el segundo apellido del nuevo estudiante:");
        String apellido2 = sc.nextLine();
        apellido2=revisionCadenaVacia(apellido2);
        String apellidos=juntarApellidos(apellido1, apellido2);
        //Se comprueba que el nombre del estudiante que se introduce no coincida con otro ya existente.
        while (comprobarNombreEstudiante(nombre, apellidos)==true){
            System.out.println("");
            System.out.println("Ingrese el nombre del nuevo estudiante:");
            nombre = sc.nextLine();
            nombre=revisionCadenaVacia(nombre);
            System.out.println("");
            System.out.println("Ingrese el primer apellido del nuevo estudiante:");
            apellido1 = sc.nextLine();
            apellido1=revisionCadenaVacia(apellido1);
            System.out.println("");
            System.out.println("Ingrese el segundo apellido del nuevo estudiante:");
            apellido2 = sc.nextLine();
            apellido2=revisionCadenaVacia(apellido2);
            apellidos=juntarApellidos(apellido1, apellido2);
        }
        System.out.println("");
        System.out.println("Ingrese el número de teléfono del nuevo estudiante:");
        String telefono = sc.nextLine();
        telefono=revisionTelefonoValido(telefono);
        System.out.println("");
        System.out.println("Ingrese la edad del nuevo estudiante:");
        int edad = sc.nextInt();
        edad=comprobarEdad(edad);
        Estudiante e = new Estudiante(nombre,apellidos,telefono,edad);
        System.out.println("");
        System.out.println("El nuevo estudiante ha sido creado con éxito.");
        //Se añade a la ArrayList de estudiantes y se devuelve el estudiante.
        estudiantes.add(e);
        return e;
        }
    //Método crear departamento. Se piden datos y se comprueba que las cadenas no esten vacías.
    public Departamento crearDepartamento(){
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("Ingrese el nombre del nuevo departamento: ");
        String nombre = sc.nextLine();
        nombre=revisionCadenaVacia(nombre);
        //Comprobacion de que el nombre introducido no coincida con otro existente.
        while (comprobarNombreDepartamento(nombre)==true){
            listarDepartamentos();
            System.out.println("Ingrese el nombre del nuevo departamento: ");
            nombre = sc.nextLine();
            nombre=revisionCadenaVacia(nombre);
        }
        System.out.println("");
        System.out.println("Ingrese una descripción para el nuevo departamento: ");
        String descripcion = sc.nextLine();
        descripcion = revisionCadenaVacia(descripcion);
        System.out.println("");
        System.out.println("Ingrese el máximo de estudiantes que tendrá el departamento "+nombre+":");
        int maxEstudiantes = sc.nextInt();
        maxEstudiantes = revisarMaxEstudiantesDepartamento(maxEstudiantes);
        Departamento d = new Departamento(nombre,descripcion,maxEstudiantes);
        //Se añade a la ArrayList y se devuelve.
        departamentos.add(d);
        System.out.println("");
        System.out.println("El nuevo departamento ha sido creado con éxito.");
        return d;
    }
    //Método crear curso. Si no existe ningún departamento, redirigirá al usuario a la creación de uno.
    public Curso crearCurso() {
        Scanner sc = new Scanner(System.in);
        if(departamentos.isEmpty()){
            System.out.println("No existe ningún departamento al que puedas asignar el primer curso, se te redirigirá a crear el primer departamento.");
            System.out.println("Redirigiendo...");
            for(long l=0;l<5991911199L;l++){
            }
            crearDepartamento();
        }
        //Se muestran los departamentos disponibles, para que el usuario introduzca a que departamento pertenecerá el curso a crear.
        System.out.println("");
        System.out.println("Ingrese a que departamento va a pertenecer el curso: ");
        listarDepartamentos();
        String departamento = sc.nextLine();
        departamento=revisionCadenaVacia(departamento);
        Departamento d = buscarDepartamento(departamento);
        //Si el departamento introducido no existe, se le pregunta si quiere salir de este menú.
        while(d==null){
            System.out.println("Por favor ingrese uno que si exista.");
            listarDepartamentos();
            departamento = sc.nextLine();
            departamento=revisionCadenaVacia(departamento);
            d = buscarDepartamento(departamento);
            if(d == null) {
                System.out.println("No existe el departamento "+departamento+".");
                return null; 
            }
        }
        //Creación de curso, comprobación cadena vacía y método para que curso no se repita con uno existente.
        System.out.println("Ingrese el nombre del nuevo curso:");
        String nombre = sc.nextLine();
        nombre=revisionCadenaVacia(nombre);
        while (comprobarNombreCurso(nombre)==true){
            listarCursos();
            System.out.println("Ingrese el nombre del nuevo curso: ");
            nombre = sc.nextLine().toLowerCase();
            nombre=revisionCadenaVacia(nombre);
        }
        System.out.println("");
        System.out.println("Ingrese una descripción para el nuevo curso: ");
        String descripcion = sc.nextLine();
        descripcion=revisionCadenaVacia(descripcion);
        System.out.println("");
        System.out.println("Ingrese el número máximo de estudiantes para "+nombre+":");
        int maxEstudiantes = sc.nextInt();
        maxEstudiantes=revisarMaxEstudiantesCurso(maxEstudiantes);
        System.out.println("");
        System.out.println("Ingrese el número máximo de clases que tendrá el curso "+nombre+":");
        int maxClases = sc.nextInt();
        maxClases=revisarMaxClases(maxClases);
        System.out.println("");      
        Curso c1 = new Curso(nombre,descripcion,maxEstudiantes,maxClases,null,d);
        //Se crea curso, se añade a la ArrayList de cursos y a la de cursos del departamento. Se devuelve el nuevo curso.
        cursos.add(c1);
        d.anadirCurso(d, c1);
        System.out.println("El nuevo curso ha sido creado con éxito.");
        return c1;
    }
    //Método crear profesor.
    public Profesor crearProfesor(){
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("Ingrese el nombre del nuevo profesor:");
        String nombre = sc.nextLine();
        nombre=revisionCadenaVacia(nombre);
        System.out.println("");
        System.out.println("Ingrese el primer apellido del nuevo profesor:");
        String apellido1 = sc.nextLine();
        apellido1=revisionCadenaVacia(apellido1);
        System.out.println("");
        System.out.println("Ingrese el segundo apellido del nuevo profesor:");
        String apellido2 = sc.nextLine();
        apellido2=revisionCadenaVacia(apellido2);
        String apellidos=juntarApellidos(apellido1, apellido2);
        //Comprobación nombre del profesor no coincide con otro existente.
        while (comprobarNombreProfesor(nombre, apellidos)==true){
            System.out.println("");
            System.out.println("Ingrese el nombre del nuevo profesor:");
            nombre = sc.nextLine();
            nombre=revisionCadenaVacia(nombre);
            System.out.println("");
            System.out.println("Ingrese el primer apellido del nuevo profesor:");
            apellido1 = sc.nextLine();
            apellido1=revisionCadenaVacia(apellido1);
            System.out.println("");
            System.out.println("Ingrese el segundo apellido del nuevo profesor:");
            apellido2 = sc.nextLine();
            apellido2=revisionCadenaVacia(apellido2);
            apellidos=juntarApellidos(apellido1, apellido2);
        }
        System.out.println("");
        System.out.println("Ingrese el número de teléfono del nuevo profesor:");
        String telefono = sc.nextLine();
        telefono=revisionTelefonoValido(telefono);
        System.out.println("");
        System.out.println("Ingrese el salario del nuevo profesor:");
        int salario = sc.nextInt();
        //Método para comprobar que el salario introducido está en el rango adecuado.
        salario=comprobarSalario(salario);
        Profesor p = new Profesor(nombre,apellidos,telefono,salario);
        System.out.println("");
        System.out.println("El nuevo profesor ha sido creado con éxito.");
        //Se añade a la lista y se devuelve.
        profesores.add(p);
        return p;
    }
    //Método para revisar el máximo de estudiantes de un departamento.
     public int revisarMaxEstudiantesDepartamento(int maxEstudiantes){
        Scanner sc = new Scanner(System.in);
        while(maxEstudiantes<20 ||maxEstudiantes>20000){
            System.out.println("");
            System.out.println("Por favor, ingrese una cantidad de estudiantes máximos entre 20-20000.");
            maxEstudiantes = sc.nextInt();
        }
        return maxEstudiantes;
    }
    //Método para revisar el máximo de estudiantes de un curso.
    public int revisarMaxEstudiantesCurso(int maxEstudiantes){
        Scanner sc = new Scanner(System.in);
        while(maxEstudiantes<20 ||maxEstudiantes>500){
            System.out.println("");
            System.out.println("Por favor, ingrese una cantidad de estudiantes máximos entre 20-500.");
            maxEstudiantes = sc.nextInt();
        }
        return maxEstudiantes;
    }
       //método para revisar el máximo de clases que un curso puede llegar a tener.
      public int revisarMaxClases(int maxClases){
        Scanner sc = new Scanner(System.in);
        while(maxClases<1||maxClases>10){
            System.out.println("");
            System.out.println("El número de clases debe estar entre 1-10.");
            maxClases = sc.nextInt();
        }
        return maxClases;
    }
    //método revisión de cadena vacía
    public String revisionCadenaVacia(String cadena){
        Scanner sc = new Scanner(System.in);
        while(cadena.length()==0||cadena.trim().length()==0){
            System.out.println("");
            System.out.println("Este campo no puede estar vacío.");
            cadena = sc.nextLine().toLowerCase();
        }
        return cadena.trim().toLowerCase();
    }
    public String juntarApellidos(String apellido1, String apellido2){
        String apellidos=apellido1+" "+apellido2;
        return apellidos;
    }
    //Método revisión de teléfono introducido.
    public String revisionTelefonoValido(String telefono){
        Scanner sc = new Scanner(System.in);
        while(telefono.length()!=9||telefono.trim().length()!=9){
            System.out.println("");
            System.out.println("Por favor, ingrese un número de teléfono válido.");
            telefono = sc.nextLine().toLowerCase();
        }
        return "+34 "+telefono.trim();
    }
    //Método para comprobar la edad.
    public int comprobarEdad(int edad){
        Scanner sc = new Scanner(System.in);
        while(edad<15||edad>80){
            System.out.println("");
            System.out.println("Por favor, ingrese una edad entre 15-80");
            edad = sc.nextInt();
        }
        return edad;
    }
    //Método para comprobar que el nombre del departamento introducido no coincide con uno existente. 
    public boolean comprobarNombreDepartamento(String nombre){
        for(int i=0;i<departamentos.size();i++){
            if(departamentos.get(i).getNombre().compareTo(nombre)==0){
                System.out.println("");
                System.out.println("El departamento "+nombre+" ya existe.");
                return true;
            }
        }
        return false;
    }
    
    //Método para comprobar que el nombre del estudiante introducido no coincide con uno existente.
    public boolean comprobarNombreEstudiante(String nombre, String apellidos){
        for(int i=0;i<estudiantes.size();i++){
            if(estudiantes.get(i).getNombre().compareTo(nombre)==0&&estudiantes.get(i).getApellidos().compareTo(apellidos)==0){
                System.out.println("");
                System.out.println("El estudiante "+nombre+" "+apellidos+" ya existe.");
                return true;
            }
        }
        return false;
    }
    //Método para comprobar que el nombre del profesor introducido no coincide con uno existente.
    public boolean comprobarNombreProfesor(String nombre, String apellidos){
        for(int i=0;i<profesores.size();i++){
            if(profesores.get(i).getNombre().compareTo(nombre)==0&&profesores.get(i).getApellidos().compareTo(apellidos)==0){
                System.out.println("");
                System.out.println("El profesor "+nombre+" "+apellidos+" ya existe.");
                return true;
            }
        }
        return false;
    }
    
    //Método para comprobar que el nombre del curso introducido no coincide con uno existente.
    public boolean comprobarNombreCurso(String nombre){
        for(int i=0;i<cursos.size();i++){
            if(cursos.get(i).getNombre().compareTo(nombre)==0){
                System.out.println("");
                System.out.println("El curso "+nombre+" ya existe.");
                return true;
            }
        }
        return false;
    }
    //Método para comprobar que el salario está en el rango adecuado.
    public static int comprobarSalario(int salario){
        Scanner sc = new Scanner(System.in);
        while(salario<500||salario>10000){
            System.out.println("");
            System.out.println("Por favor, ingrese un salario válido, entre 500€ a 10000€.");
            salario = sc.nextInt();
        }
        return salario;
    }
    //Método que devuelve el tamaño de la lista de departamentos.
    public int numDepartamentos(){
    return departamentos.size();
    }
    //Método que devuelve el tamaño de la lista de cursos.
    public int numCursos(){
    return cursos.size();
    }
    //Método que devuelve el tamaño de la lista de profesores.
    public int numProfesores(){
    return profesores.size();
    }
    //Método que devuelve el tamaño de la lista de estudiantes.
    public int numEstudiantes(){
    return estudiantes.size();
    }
    //Método para buscar departamento entre los ya creados.
    public Departamento buscarDepartamento(String nombre){
        for(int i=0;i<departamentos.size();i++){
            if(departamentos.get(i).getNombre().compareTo(nombre)==0){
                return departamentos.get(i);
            }
        }
        System.out.println("No existe ningún departamento llamado "+nombre+ " en la universidad.");
        return null;
    }
   //Método para buscar un curso entre los ya creados.
    public Curso buscarCurso(String nombre){
        for(int i=0;i<cursos.size();i++){
            if(cursos.get(i).getNombre().compareTo(nombre)==0){
                return cursos.get(i);
            }
        }
        System.out.println("No existe ningún curso llamado "+nombre+ " en la universidad.");
        return null;
    }
    //Método para buscar un profesor entre los ya creados.
    public Profesor buscarProfesor(String nombre, String apellidos){
        for(int i=0;i<profesores.size();i++){
            if(profesores.get(i).getNombre().compareTo(nombre)==0 && profesores.get(i).getApellidos().compareTo(apellidos)==0){
                return profesores.get(i);
            }
        }
        System.out.println("No existe ningún profesor llamado "+nombre+" "+apellidos+" en la universidad.");
        return null;
    }
    //Método para buscar un estudiante entre los ya creados.   
    public Estudiante buscarEstudiante(String nombre, String apellidos){
        for(int i=0;i<estudiantes.size();i++){
            if(estudiantes.get(i).getNombre().compareTo(nombre)==0 && estudiantes.get(i).getApellidos().compareTo(apellidos)==0){
                return estudiantes.get(i);
            }
        }
        System.out.println("No existe ningún estudiante llamado "+nombre+" "+apellidos+" en la universidad.");
        return null;
    }
    //Métodos para listar departamentos.
    public void listarDepartamentos(){
        if(departamentos.isEmpty()){
            System.out.println("Todavía no se ha creado ningún departamento.");
            System.out.println("");
        }else{
        System.out.println("Estos son los departamentos que han sido creados: ");
        for(int i = 0; i < departamentos.size(); i++) {
            System.out.println("- "+departamentos.get(i).getNombre());
        }
        System.out.println("");
        }
    }
    //Métodos para listar cursos.
    public void listarCursos(){
        if(cursos.isEmpty()){
            System.out.println("Todavía no se ha creado ningún curso.");
            System.out.println("");
        }else{
        System.out.println("Estos son los cursos que han sido creados: ");
        for(int i = 0; i < cursos.size(); i++) {
            System.out.println("- "+cursos.get(i).getNombre());
        }
        System.out.println("");
        }
    }
    //Métodos para listar estudiantes.
    public void listarEstudiantes(){
        if(estudiantes.isEmpty()){
            System.out.println("Todavía no se ha creado ningún estudiante.");
            System.out.println("");
        }else{
        System.out.println("Estos son los estudiantes que han sido creados: ");
        for(int i = 0; i < estudiantes.size(); i++) {
            System.out.println("- "+estudiantes.get(i).getNombreCompleto());
        }
        System.out.println("");
        }
    }
    //Métodos para listar profesores.
    public void listarProfesores(){
        if(profesores.isEmpty()){
            System.out.println("Todavía no se ha creado ningún profesor.");
            System.out.println("");
        }else{
        System.out.println("Estos son los profesores que han sido creados: ");
        for(int i = 0; i < profesores.size(); i++) {
            System.out.println("- "+profesores.get(i).getNombreCompleto());
        }
        System.out.println("");
        }
    }
    //Método para asignar un departamento a un estudiante.
    public boolean asignarDepartamento(Universidad u){
        Estudiante e = new Estudiante();
        if(e.asignarDepartamento(u, e)==false){
            System.out.println("");
            return false;
        }
        System.out.println("El estudiante ha sido asignado correctamente al departamento.");
        return true;
    }
    //Método que imprime un resumen de datos del estudiante.
    public boolean resumenDatosEstudiante(){
        Scanner sc = new Scanner(System.in);
        if(numEstudiantes()==0){
            System.out.println("Todavía no se ha creado ningún estudiante.");
            System.out.println("Operación cancelada.");
            return false;
        }
        //Se piden los datos del estudiante.
        System.out.println("");
        System.out.println("Ingrese el nombre del estudiante del que quiere obtener los datos:");
        String nombre = sc.nextLine().toLowerCase();
        nombre=revisionCadenaVacia(nombre);
        System.out.println("");
        System.out.println("Ingrese el primer apellido del estudiante del que quiere obtener los datos:");
        String apellido1 = sc.nextLine().toLowerCase();
        apellido1=revisionCadenaVacia(apellido1);
        System.out.println("");
        System.out.println("Ingrese el segundo apellido del estudiante del que quiere obtener los datos:");
        String apellido2 = sc.nextLine().toLowerCase();
        apellido2=revisionCadenaVacia(apellido2);
        String apellidos=juntarApellidos(apellido1, apellido2);
        //Se muestran los datos del estudiante.
        for(int i=0;i<estudiantes.size();i++){
            if(estudiantes.get(i).getNombre().compareTo(nombre)==0&&estudiantes.get(i).getApellidos().compareTo(apellidos)==0){
                System.out.println("Nombre completo: "+estudiantes.get(i).getNombreCompleto());
                System.out.println("Teléfono: "+estudiantes.get(i).getTelefono());
                System.out.println("Edad: "+estudiantes.get(i).getEdad()+" años.");
                if(estudiantes.get(i).getDepertamentoPertenece()==null){
                    System.out.println("El alumno "+estudiantes.get(i).getNombreCompleto()+" todavía no ha sido asignado a ningún departamento.");
                    return false;
                }else{
                    System.out.println("Departamento al que pertenece: "+estudiantes.get(i).getDepertamentoPertenece().getNombre());
                }
                return true;
            }
        }
        System.out.println("El estudiante "+nombre+" "+apellidos+" no existe.");
        return false;
    }
    //Método para listar los cursos en los que está matriculado un estudiante cualquiera.
    public boolean cursosMatriculado(Universidad u){
        Scanner sc = new Scanner(System.in);
        if(numEstudiantes()==0){
            System.out.println("Todavía no se ha creado ningún estudiante.");
            System.out.println("Operación cancelada.");
            return false;
        }
        //Se piden los datos del estudiante.
        System.out.println("Ingrese el nombre del estudiante del que quiere obtener los datos: ");
        String nombre = sc.nextLine().toLowerCase();
        nombre=revisionCadenaVacia(nombre);
        System.out.println("");
        System.out.println("Ingrese el primer apellido del estudiante del que quiere obtener los datos:");
        String apellido1 = sc.nextLine().toLowerCase();
        apellido1=revisionCadenaVacia(apellido1);
        System.out.println("");
        System.out.println("Ingrese el segundo apellido del estudiante del que quiere obtener los datos:");
        String apellido2 = sc.nextLine().toLowerCase();
        apellido2=revisionCadenaVacia(apellido2);
        String apellidos=juntarApellidos(apellido1, apellido2);
        //Se comprueba si existe el estudiante y se llama al método que mostrara los cursos.
        for(int i=0;i<estudiantes.size();i++){
            if(estudiantes.get(i).getNombre().compareTo(nombre)==0&&estudiantes.get(i).getApellidos().compareTo(apellidos)==0){
                estudiantes.get(i).mostrarCursosPertenece(estudiantes, nombre, apellidos);
                return true;
            }
        }
        System.out.println("El estudiante "+nombre+" "+apellidos+" no existe.");
        return false;
    }
    //Muestra el número de cursos de los que es responsable un departamento.
    public boolean obtenerCursosDeLosQueEsResponsable(Universidad u){
        Scanner sc = new Scanner(System.in);
        int contador=0;
        //Si no hay ningún departamento se cancela la operación.
        if(departamentos.isEmpty()){
            System.out.println("Todavía no se ha creado ningún departamento.");
            System.out.println("Operación cancelada.");
            return false;
        }
        //Si no hay ningún curso se cancela la operación.
        if(cursos.isEmpty()){
            System.out.println("Todavía no se ha creado ningún curso.");
            System.out.println("Operación cancelada.");
            return false;
        }
        //Se pide el nombre del departamento y se comprueba que existe.
        System.out.println("Ingrese el nombre del departamento del que quiere obtener el número de cursos: ");
        String nombre = sc.nextLine().toLowerCase();
        nombre=revisionCadenaVacia(nombre);
        for(int i=0;i<departamentos.size();i++){
            if(departamentos.get(i).getNombre().compareTo(nombre)==0){
                for(int j=0; j<departamentos.get(i).getCursosDeLosQueEsResponsable().size(); j++){
                    contador++;
                }
                //Se muestra al usuario el número de cursos en un departamento.
                System.out.println("Hay "+contador+" cursos en el departamento "+nombre+".");
                return true;
            }
        }
        System.out.println("El departamento "+nombre+" no existe.");
        return false;
    }
    //Muestra el número de estudiantes que forman parte de un departamento.
    public boolean obtenerEstudiantesAdjuntos(Universidad u){
        Departamento d = new Departamento();
        int contador=0;
        //Si no hay ningún departamento se cancela la operación.
        if(departamentos.isEmpty()){
            System.out.println("Todavía no se ha creado ningún departamento.");
            System.out.println("Operación cancelada.");
            return false;
        }
        //Si no hay ningún departamento se cancela la operación.
        if(estudiantes.isEmpty()){
            System.out.println("Todavía no se ha creado ningún estudiante.");
            System.out.println("Operación cancelada.");
            return false;
        }
        d = d.obtenerDep(u);
        if(d==null){
            return false;
        }
        for(int i=0;i<departamentos.size();i++){
            if(departamentos.get(i).getNombre().compareTo(d.getNombre())==0){
                for(int j=0; j<departamentos.get(i).getEstudiantesAdjuntos().size(); j++){
                    contador++;
                }
                System.out.println("Hay "+contador+" estudiantes en el departamento "+d.getNombre()+".");
                return true;
            }
        }
        //Se muestra al usuario el número de cursos en un departamento.
        System.out.println("El departamento "+d.getNombre()+" no existe.");
        return false;
    }
}
