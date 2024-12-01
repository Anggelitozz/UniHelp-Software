
/**
 * Write a description of class tutor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tutor extends Estudiante {
    // Atributos específicos de Tutor
    private double[] calificaciones; // Calificaciones del tutor
    private String[] materias;       // Materias que el tutor enseña
    private String contacto;       // Número de contacto

    // Constructor
    public Tutor(String nombre, String id, String edad, String carrera, boolean tutor, String foto, 
                 double[] calificaciones, String[] materias, String contacto){
        super(nombre, id, edad, carrera, foto); // Llama al constructor de Estudiante
        this.calificaciones = calificaciones;
        this.materias = materias;
        this.contacto = contacto;
    }

    // Getters y Setters para calificaciones
    public double[] getCalificaciones(){
        return calificaciones;
    }

    public void setCalificaciones(double[] calificaciones){
        this.calificaciones = calificaciones;
    }
    
    public double getPromedioCalificaciones() {
        if (calificaciones == null || calificaciones.length == 0) {
            return 0.0;
        }
        double suma = 0;
        for (double calificacion : calificaciones) {
            suma += calificacion;
        }
        return suma / calificaciones.length;
    }
    
    // Getters y Setters para materias
    public String[] getMaterias(){
        return materias;
    }

    public void setMaterias(String[] materias){
        this.materias = materias;
    }
    
    //Getters y Setters para contacto
    public String getContacto(){
        return contacto;
    }
    
    public void setContacto(String contacto){
        this.contacto = contacto;
    }
       
    // Método para aceptar tutoría
    public void aceptarTutoria(){
        System.out.println("Tutoría aceptada por " + getNombre());
    }

    // Método para mostrar información completa del tutor (sobreescribe el de Estudiante)
    @Override
    public void mostrarInformacion(){
        super.mostrarInformacion(); // Llama al método de Estudiante
        System.out.println("Materias que enseña: " + materias);
        System.out.println("Calificaciones: " + calificaciones);
    }

}

