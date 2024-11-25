
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

    // Constructor
    public Tutor(String nombre, String id, String edad, String carrera, boolean tutor, String foto, 
                 double[] calificaciones, String[] materias){
        super(nombre, id, edad, carrera, foto); // Llama al constructor de Estudiante
        this.calificaciones = calificaciones;
        this.materias = materias;
    }

    // Getters y Setters para calificaciones
    public double[] getCalificaciones(){
        return calificaciones;
    }

    public void setCalificaciones(double[] calificaciones){
        this.calificaciones = calificaciones;
    }

    // Getters y Setters para materias
    public String[] getMaterias(){
        return materias;
    }

    public void setMaterias(String[] materias){
        this.materias = materias;
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

