import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    // Atributos
    private List<Tutor> tutores;           // Lista de tutores en el catálogo
    private List<Asignatura> asignaturas; // Lista de asignaturas en el catálogo

    // Constructor
    public Catalogo() {
        this.tutores = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
    }

    // Métodos para manejar los tutores
    public List<Tutor> getTutores() {
        return tutores;
    }

    public void agregarTutor(Tutor tutor) {
        if (tutor != null) {
            tutores.add(tutor);
            System.out.println("Tutor " + tutor.getNombre() + " agregado al catálogo.");
        }
    }

    public void eliminarTutor(Tutor tutor) {
        if (tutores.remove(tutor)) {
            System.out.println("Tutor " + tutor.getNombre() + " eliminado del catálogo.");
        } else {
            System.out.println("El tutor no se encuentra en el catálogo.");
        }
    }

    // Métodos para manejar las asignaturas
    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void agregarAsignatura(Asignatura asignatura) {
        if (asignatura != null) {
            asignaturas.add(asignatura);
            System.out.println("Asignatura " + asignatura.getNombre() + " agregada al catálogo.");
        }
    }

    public void eliminarAsignatura(Asignatura asignatura) {
        if (asignaturas.remove(asignatura)) {
            System.out.println("Asignatura " + asignatura.getNombre() + " eliminada del catálogo.");
        } else {
            System.out.println("La asignatura no se encuentra en el catálogo.");
        }
    }

    // Método para mostrar el catálogo completo
    public void mostrarCatalogo() {
        System.out.println("=== Catálogo de Tutores ===");
        if (tutores.isEmpty()) {
            System.out.println("No hay tutores en el catálogo.");
        } else {
            for (Tutor tutor : tutores) {
                System.out.println("Tutor: " + tutor.getNombre());
            }
        }

        System.out.println("\n=== Catálogo de Asignaturas ===");
        if (asignaturas.isEmpty()) {
            System.out.println("No hay asignaturas en el catálogo.");
        } else {
            for (Asignatura asignatura : asignaturas) {
                System.out.println("Asignatura: " + asignatura.getNombre());
            }
        }
    }

    // Método principal para pruebas
    public static void main(String[] args) {
        // Crear un catálogo
        Catalogo catalogo = new Catalogo();

        // Crear tutores
        double[] calificaciones = {4.5, 4.8};
        String[] materias = {"Matemáticas", "Física"};
        Tutor tutor1 = new Tutor("Ana Pérez", "T001", "22", "Matemáticas", true, "ana_foto.jpg", 
                                 calificaciones, materias);
        Tutor tutor2 = new Tutor("Luis Gómez", "T002", "25", "Física", true, "luis_foto.jpg", 
                                 calificaciones, materias);

        // Crear asignaturas
        Asignatura matematicas = new Asignatura("Matemáticas", "MAT101");
        Asignatura fisica = new Asignatura("Física", "FIS201");

        // Agregar tutores y asignaturas al catálogo
        catalogo.agregarTutor(tutor1);
        catalogo.agregarTutor(tutor2);
        catalogo.agregarAsignatura(matematicas);
        catalogo.agregarAsignatura(fisica);

        // Mostrar el catálogo
        catalogo.mostrarCatalogo();

        // Eliminar un tutor y una asignatura
        catalogo.eliminarTutor(tutor1);
        catalogo.eliminarAsignatura(fisica);

        // Mostrar el catálogo actualizado
        System.out.println("\nCatálogo actualizado:");
        catalogo.mostrarCatalogo();
    }
}

