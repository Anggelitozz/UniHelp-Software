
/**
 * Write a description of class Unihelp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class UniHelp {
    private static Catalogo catalogo; // Instancia de Catalogo
    private static Mensajeria mensajeria; // Instancia de Mensajeria
    private static Estudiante estudiante; // Instancia de Estudiante

    public static void main(String[] args) {
        // Crear y configurar el catálogo
        catalogo = Catalogo.obtenerInstancia();
        inicializarCatalogo(); // Llena el catálogo con datos de prueba

        // Crear la ventana principal
        JFrame frame = new JFrame("Pantalla de Inicio");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el panel
        JPanel panel = new JPanel();

        // Crear los botones
        JButton verTutoresButton = new JButton("Ver la lista de tutores");
        JButton acercaButton = new JButton("Leer acerca de la aplicación");
        JButton salirButton = new JButton("Salir");

        // Agregar los botones al panel
        panel.add(verTutoresButton);
        panel.add(acercaButton);
        panel.add(salirButton);

        // Agregar el panel al frame
        frame.add(panel);

        // Hacer visible la ventana
        frame.setVisible(true);

        // Acción de ver tutores (abre la nueva ventana)
        verTutoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaTutores();
            }
        });

        // Acción de leer acerca de la aplicación
        acercaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Bienvenido a UniHelp, una plataforma de tutorías.");
            }
        });

        // Acción de salir
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cerrar la aplicación
            }
        });
    }

    // Método para guardar la lista de tutores en un archivo JSON
    public static void guardarTutoresJSON(List<Tutor> tutores) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("tutores.json")) {
            gson.toJson(tutores, writer); // Convierte la lista de tutores a JSON y la guarda
            System.out.println("Lista de tutores guardada en tutores.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void inicializarCatalogo() {
        // Cargar los tutores desde el archivo JSON
        catalogo.cargarTutores();
        
        // Cargar asignaturas desde el archivo JSON
        catalogo.cargarAsignaturas();
        
        // Si no hay tutores en el catálogo, agrega datos de prueba
        if (catalogo.obtenerTutores(null, null).isEmpty()) {
            // Crear tutores de prueba solo si el catálogo está vacío
            Tutor tutor1 = new Tutor("Ana Pérez", "T001", "22", "Matemáticas", true, "ana_foto.jpg", 
                                     new double[]{4.5, 4.8}, new String[]{"Matemáticas", "Física"});
            Tutor tutor2 = new Tutor("Luis Gómez", "T002", "25", "Física", true, "luis_foto.jpg", 
                                     new double[]{4.0, 3.9}, new String[]{"Física", "Química"});
            
            // Agregar tutores al catalogo
            catalogo.agregarTutor(tutor1);
            catalogo.agregarTutor(tutor2);
            
            // Guardar los tutores en un archivo JSON
            guardarTutoresJSON(catalogo.obtenerTutores(null, null)); // Guardar datos en JSON
        }
        if (catalogo.obtenerNombresAsignaturas().isEmpty()) {
            // Crear asignaturas
            Asignatura matematicas = new Asignatura("Matemáticas", "MAT101");
            Asignatura fisica = new Asignatura("Física", "FIS201");
            Asignatura quimica = new Asignatura("Química", "QUI301");
            
            // Agregar asignaturas al catalogo
            catalogo.agregarAsignatura(matematicas);
            catalogo.agregarAsignatura(fisica);
            catalogo.agregarAsignatura(quimica);
        }
    }

    private static void abrirVentanaTutores() {
        // Nueva ventana de tutores
        JFrame tutoresFrame = new JFrame("Lista de Tutores");
        tutoresFrame.setSize(600, 400);
        tutoresFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());
    
        // Panel de filtros
        JPanel filtrosPanel = new JPanel();
        filtrosPanel.setLayout(new BoxLayout(filtrosPanel, BoxLayout.Y_AXIS));
    
        // Crear el combo box para filtrar por asignatura
        JComboBox<String> asignaturasComboBox = new JComboBox<>();
        asignaturasComboBox.setMaximumSize(new Dimension(200, 25));
        filtrosPanel.add(asignaturasComboBox);
    
        // Crear un observador para el combo box
        ComboBoxAsignaturas comboBoxObserver = new ComboBoxAsignaturas(asignaturasComboBox);
    
        // Registrar el observador en el catálogo
        catalogo.agregarObservador(comboBoxObserver);
    
        // Inicializar el combo box con las asignaturas actuales
        comboBoxObserver.actualizar(catalogo.obtenerNombresAsignaturas());
    
        // Panel para mostrar los tutores
        JPanel tutoresPanel = new JPanel();
        tutoresPanel.setLayout(new BoxLayout(tutoresPanel, BoxLayout.Y_AXIS));
    
        // Mostrar todos los tutores inicialmente
        mostrarTutores(tutoresPanel, catalogo.obtenerTutores(null, null));
    
        // Agregar el filtro de asignatura
        asignaturasComboBox.addActionListener(e -> {
            String asignaturaSeleccionada = (String) asignaturasComboBox.getSelectedItem();
            Asignatura asignatura = null;
            Filtro filtro = new FiltroPorAsignatura();
    
            if (!"Todas".equals(asignaturaSeleccionada)) {
                asignatura = new Asignatura(asignaturaSeleccionada, "");
                filtro = new FiltroPorAsignatura(asignatura);
            }
    
            List<Tutor> tutoresFiltrados = catalogo.obtenerTutores(filtro, asignatura);
            mostrarTutores(tutoresPanel, tutoresFiltrados);
        });
    
        // Agregar los paneles al panel principal
        panel.add(filtrosPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(tutoresPanel), BorderLayout.CENTER);
    
        // Configurar la ventana y hacerla visible
        tutoresFrame.add(panel);
        tutoresFrame.setVisible(true);
    }

    private static void mostrarTutores(JPanel panel, List<Tutor> tutores) {
        // Limpiar el panel antes de agregar los nuevos tutores filtrados
        panel.removeAll();
        for (Tutor tutor : tutores) {
            JButton tutorButton = new JButton(tutor.getNombre());
            tutorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mostrarInformacionTutor(tutor);
                }
            });
            panel.add(tutorButton);
        }
        panel.revalidate();
        panel.repaint();
    }

    private static void mostrarInformacionTutor(Tutor tutor) {
        // Crear una ventana para el perfil del tutor
        JFrame perfilFrame = new JFrame("Perfil del Tutor");
        perfilFrame.setSize(400, 300);

        // Crear un panel para organizar los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Mostrar información del tutor
        JLabel nombreLabel = new JLabel("Nombre: " + tutor.getNombre());
        JLabel edadLabel = new JLabel("Edad: " + tutor.getEdad());
        JLabel carreraLabel = new JLabel("Carrera: " + tutor.getCarrera());
        JLabel materiasLabel = new JLabel("Materias: " + String.join(", ", tutor.getMaterias()));
        JLabel calificacionLabel = new JLabel("Calificacion: " + tutor.getCalificaciones());
        
        panel.add(nombreLabel);
        panel.add(edadLabel);
        panel.add(carreraLabel);
        panel.add(materiasLabel);
        panel.add(calificacionLabel);

        // Crear botones
        JButton matchButton = new JButton("Match");
        JButton volverButton = new JButton("Volver");

        // Agregar acción al botón Match
        matchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de Mensajeria y notificar el match
                Estudiante estudiante = new Estudiante("Juan Pérez", "E001", "22", "Ingeniería", "juan_foto.jpg");
                Mensajeria mensajeria = new Mensajeria(tutor, estudiante);
                mensajeria.notificar_match();

                JOptionPane.showMessageDialog(perfilFrame, "¡Match realizado con éxito!");
            }
        });

        // Agregar acción al botón Volver
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                perfilFrame.dispose(); // Cierra la ventana del perfil
            }
        });

        // Agregar botones al panel
        JPanel botonesPanel = new JPanel();
        botonesPanel.add(matchButton);
        botonesPanel.add(volverButton);

        // Agregar todo al frame
        perfilFrame.add(panel, BorderLayout.CENTER);
        perfilFrame.add(botonesPanel, BorderLayout.SOUTH);

        perfilFrame.setVisible(true);
    }
}






