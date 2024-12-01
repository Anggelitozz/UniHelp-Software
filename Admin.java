
/**
 * Write a description of class admin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Admin extends Usuario {
    // Constructor
    public Admin(String nombre, String id, String edad, String carrera) {
        super(nombre, id, edad, carrera);
    }

    // Métodos para gestionar estudiantes y tutores
    public void aceptarTutor(Estudiante estudiante) {
        if (estudiante != null) {
            estudiante.setTutor(true);
            System.out.println("El estudiante " + estudiante.getNombre() + " ahora es tutor.");
        } else {
            System.out.println("Estudiante inválido.");
        }
    }

    public void deshabilitarEstudiante(Estudiante estudiante) {
        if (estudiante != null) {
            estudiante.setCuenta(false);
            System.out.println("El estudiante " + estudiante.getNombre() + " ha sido deshabilitado.");
        } else {
            System.out.println("Estudiante inválido.");
        }
    }

    public void deshabilitarTutor(Tutor tutor) {
        if (tutor != null) {
            tutor.setTutor(false);
            System.out.println("El tutor " + tutor.getNombre() + " ha sido deshabilitado.");
        } else {
            System.out.println("Tutor inválido.");
        }
    }

    // Método para mostrar la información del administrador
    @Override
    public void mostrarInformacion() {
        System.out.println("Administrador: ");
        super.mostrarInformacion();
    }

    // Método principal para pruebas
    public static void main(String[] args) {
        // Crear un administrador
        Admin admin = new Admin("Jorge López", "A001", "40", "Sistemas");

        // Crear estudiantes y tutores
        Estudiante estudiante1 = new Estudiante("María García", "E001", "20", "Matemáticas", "maria_foto.jpg");
        Tutor tutor1 = new Tutor("Carlos Pérez", "T001", "22", "Física", true, "carlos_foto.jpg",
                                  new double[] {4.5, 4.7}, new String[] {"Física", "Matemáticas"},"3131578484");

        // Operaciones del administrador
        admin.mostrarInformacion();
        admin.aceptarTutor(estudiante1);
        admin.deshabilitarTutor(tutor1);
        admin.deshabilitarEstudiante(estudiante1);

        // Mostrar estado final
        System.out.println("\nEstado final de los usuarios:");
        estudiante1.mostrarInformacion();
        tutor1.mostrarInformacion();
    }
}



