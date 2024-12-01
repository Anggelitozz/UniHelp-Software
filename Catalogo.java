import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private static Catalogo instanciaUnica;
    private List<Tutor> tutores;
    private List<Asignatura> asignaturas;
    private List<Observer> observadores;

    // Constructor privado para Singleton
    private Catalogo() {
        this.tutores = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }

    // Método para obtener la instancia única de Catalogo
    public static Catalogo obtenerInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Catalogo();
        }
        return instanciaUnica;
    }

    // Métodos para manejar los tutores
    public List<Tutor> getTutores() {
        return tutores;
    }

    // Método que aplica el patrón Strategy usando el filtro
    public List<Tutor> obtenerTutores(Filtro filtro, Asignatura asignatura) {
        if (filtro == null) {
            filtro = new FiltroPorAsignatura();  // Asignar un filtro vacío por defecto
        }
        return filtro.filtrar(this.tutores, asignatura);
    }

    // Métodos para manejar los observadores
    public void agregarObservador(Observer observador) {
        observadores.add(observador);
    }

    private void notificarObservadores() {
        List<String> nombresAsignaturas = obtenerNombresAsignaturas();
        for (Observer observador : observadores) {
            observador.actualizar(nombresAsignaturas);
        }
    }

    // Métodos para manejar asignaturas
    public List<String> obtenerNombresAsignaturas() {
        List<String> nombres = new ArrayList<>();
        for (Asignatura asignatura : asignaturas) {
            nombres.add(asignatura.getNombre());
        }
        return nombres;
    }

    public void agregarTutor(Tutor tutor) {
        if (tutor != null && !tutores.contains(tutor)) {
        tutores.add(tutor);
        guardarTutores();
        System.out.println("Tutor " + tutor.getNombre() + " agregado al catálogo.");
        }
    }

    public void eliminarTutor(Tutor tutor) {
        if (tutores.remove(tutor)) {
            guardarTutores();
            System.out.println("Tutor " + tutor.getNombre() + " eliminado del catálogo.");
        } else {
            System.out.println("El tutor no se encuentra en el catálogo.");
        }
    }

    public void agregarAsignatura(Asignatura asignatura) {
        if (asignatura != null && !asignaturas.contains(asignatura)) {
            asignaturas.add(asignatura);
            guardarAsignaturas();
            notificarObservadores();
            System.out.println("Asignatura " + asignatura.getNombre() + " agregada al catálogo.");
        } else {
            System.out.println("La asignatura ya está en el catálogo o es nula.");
        }
    }

    public void eliminarAsignatura(Asignatura asignatura) {
        if (asignaturas.remove(asignatura)) {
            guardarAsignaturas();
            notificarObservadores();
            System.out.println("Asignatura " + asignatura.getNombre() + " eliminada del catálogo.");
        } else {
            System.out.println("La asignatura no se encuentra en el catálogo.");
        }
    }

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

    // Método para guardar tutores en un archivo JSON
    private void guardarTutores() {
        try (Writer writer = new FileWriter("tutores.json")) {
            Gson gson = new Gson();
            gson.toJson(tutores, writer);  // Convierte la lista de tutores a JSON y la escribe en el archivo
        } catch (IOException e) {
            System.out.println("Error al guardar los tutores: " + e.getMessage());
        }
    }

    // Método para guardar asignaturas en un archivo JSON
    private void guardarAsignaturas() {
        try (Writer writer = new FileWriter("asignaturas.json")) {
            Gson gson = new Gson();
            gson.toJson(asignaturas, writer);  // Convierte la lista de asignaturas a JSON y la escribe en el archivo
        } catch (IOException e) {
            System.out.println("Error al guardar las asignaturas: " + e.getMessage());
        }
    }

    // Método para cargar tutores desde el archivo JSON
    public void cargarTutores() {
        File file = new File("tutores.json");
        if (file.exists()) {
                try (Reader reader = new FileReader(file)) {
                    Gson gson = new Gson();
                    Tutor[] tutoresArray = gson.fromJson(reader, Tutor[].class);  // Lee el archivo JSON y lo convierte en un arreglo de tutores
                    if (tutoresArray != null) {
                        for (Tutor tutor : tutoresArray) {
                            tutores.add(tutor);  // Agrega los tutores existentes al catálogo
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error al cargar los tutores: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontró el archivo de tutores. Se creará uno nuevo.");
            }
    }

    // Método para cargar asignaturas desde el archivo JSON
    public void cargarAsignaturas() {
        File file = new File("asignaturas.json");
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Gson gson = new Gson();
                Asignatura[] asignaturasArray = gson.fromJson(reader, Asignatura[].class);
                if (asignaturasArray != null) {
                    for (Asignatura asignatura : asignaturasArray) {
                        asignaturas.add(asignatura);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al cargar las asignaturas: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró el archivo de asignaturas. Se creará uno nuevo.");
        }
    }

    public static void main(String[] args) {
        Catalogo catalogo = Catalogo.obtenerInstancia();
        catalogo.cargarTutores();
        catalogo.cargarAsignaturas();

        // Mostrar el catálogo
        catalogo.mostrarCatalogo();
    }
}



