
/**
 * Write a description of class asignatura here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.List;

public class Asignatura {
    // Atributos
    private String nombre;
    private String codigo;
    private List<Tutor> tutores; // Lista de tutores asociados a esta asignatura

    // Constructor
    public Asignatura(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.tutores = new ArrayList<>(); // Inicializa la lista de tutores
    }

    // Getter para el nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para el código
    public String getCodigo() {
        return codigo;
    }

    // Setter para el código
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Método para agregar un tutor a la lista
    public void agregarTutor(Tutor tutor) {
        if (tutor != null) {
            tutores.add(tutor);
            System.out.println("Tutor " + tutor.getNombre() + " agregado a la asignatura " + nombre);
        }
    }

    // Método para obtener la lista de tutores
    public List<Tutor> getTutores() {
        return tutores;
    }

    // Método para mostrar información de la asignatura
    public void mostrarInformacion() {
        System.out.println("Asignatura: " + nombre);
        System.out.println("Código: " + codigo);
        System.out.println("Tutores disponibles:");
        if (tutores.isEmpty()) {
            System.out.println("  No hay tutores asignados.");
        } else {
            for (Tutor tutor : tutores) {
                System.out.println("  - " + tutor.getNombre());
            }
        }
    }

    
}
