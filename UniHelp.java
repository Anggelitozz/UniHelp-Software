
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
        
        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(50, 50, 50)); // Fondo gris
    
        // Crear el panel para el encabezado con "UNIHELP"
        JPanel encabezadoPanel = new JPanel();
        encabezadoPanel.setBackground(new Color(50, 50, 50));
        JLabel encabezadoLabel = new JLabel("UNIHELP");
        encabezadoLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Fuente y tamaño
        encabezadoLabel.setForeground(Color.WHITE); // Color blanco para el texto
        encabezadoPanel.add(encabezadoLabel);
        panel.add(encabezadoPanel, BorderLayout.NORTH); // Agregar en la parte superior
        
        // Crear y agregar la imagen (logo) al panel
        ImageIcon logoIcon = new ImageIcon("Resources/logo.png"); // Reemplaza con la ruta de tu imagen
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(logoLabel, BorderLayout.CENTER); // Agrega la imagen en el centro
    
        // Crear un panel para los botones (ahora centrados en una sola columna)
        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(new Color(50, 50, 50)); // Fondo gris
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS)); // Alineación de botones en columna
    
        // Crear los botones con tamaño más pequeño
        JButton verTutoresButton = new JButton("Ver la lista de tutores");
        JButton acercaButton = new JButton("Leer acerca de la aplicación");
        JButton salirButton = new JButton("Salir");
    
        // Ajustar tamaños de los botones
        verTutoresButton.setPreferredSize(new Dimension(200, 40));
        acercaButton.setPreferredSize(new Dimension(200, 40));
        salirButton.setPreferredSize(new Dimension(200, 40));
    
        // Asegurar que los botones estén centrados
        verTutoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        acercaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        salirButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Agregar los botones al panel de botones
        botonesPanel.add(verTutoresButton);
        botonesPanel.add(acercaButton);
        botonesPanel.add(salirButton);
    
        // Agregar el panel de botones al panel principal
        panel.add(botonesPanel, BorderLayout.SOUTH);
    
        // Agregar el panel al frame
        frame.add(panel);
    
        // Hacer visible la ventana
        frame.setVisible(true);
    
        // Colores de los botones
        verTutoresButton.setBackground(new Color(255, 209, 0)); // Botón amarillo
        acercaButton.setBackground(new Color(255, 209, 0)); // Botón amarillo
        salirButton.setBackground(Color.RED); // Botón rojo
        
        // Acción de ver tutores (abre la nueva ventana)
        verTutoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaTutores();
            }
        });
    
        // Acción de leer acerca de la aplicación
        acercaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mensaje = """
                    Acerca de UniHelp:
                    
                    UniHelp es una plataforma diseñada para facilitar la conexión entre estudiantes universitarios 
                    y tutores calificados. Su objetivo es ayudar a los estudiantes a mejorar su rendimiento 
                    académico mediante tutorías personalizadas.
                    
                    Autores:
                    - Juan David Contreras López
                    - Ángel Fernando Órtega García
                    
                    Fecha de desarrollo:
                    Noviembre 2024
                    """;
                JOptionPane.showMessageDialog(frame, mensaje, "Acerca de UniHelp", JOptionPane.INFORMATION_MESSAGE);
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
            Tutor tutor1 = new Tutor("Ana Pérez", "T001", "22", "Matemáticas", true, "Resources/foto_mujer.png", 
                                     new double[]{4.5, 4.8}, new String[]{"Matemáticas", "Física"}, "3145658794");
            Tutor tutor2 = new Tutor("Luis Gómez", "T002", "25", "Física", true, "Resources/foto_hombre.png", 
                                     new double[]{4.0, 3.9}, new String[]{"Física", "Química"}, "3184462929");
            
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
    
        // Panel principal con un Layout para centrar los elementos
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(50, 50, 50)); // Fondo gris
    
        // Panel de filtros (donde estará el combo box)
        JPanel filtrosPanel = new JPanel();
        filtrosPanel.setLayout(new BoxLayout(filtrosPanel, BoxLayout.Y_AXIS));
        filtrosPanel.setBackground(new Color(50, 50, 50)); // Fondo gris
    
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
    
        // Panel para mostrar los tutores (donde estarán los botones de tutores)
        JPanel tutoresPanel = new JPanel();
        tutoresPanel.setLayout(new BoxLayout(tutoresPanel, BoxLayout.Y_AXIS));
        tutoresPanel.setBackground(new Color(50, 50, 50)); // Fondo gris
    
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
                String rutaImagen = tutor.getFoto(); // Se asume que getFoto() devuelve la ruta de la foto
            ImageIcon iconoTutor = null;
    
            try {
                // Verificar si la ruta de la imagen no es nula o vacía
                if (rutaImagen != null && !rutaImagen.isEmpty()) {
                    // Cargar la imagen desde el path
                    iconoTutor = new ImageIcon(rutaImagen);
    
                    // Escalar la imagen al tamaño adecuado (50x50 como ejemplo)
                    Image imagenEscalada = iconoTutor.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    iconoTutor = new ImageIcon(imagenEscalada);
                }
            } catch (Exception e) {
                System.err.println("No se pudo cargar la imagen para " + tutor.getNombre() + ": " + e.getMessage());
            }
            // Crear un botón con texto y la imagen
            JButton tutorButton = new JButton(tutor.getNombre(), iconoTutor);
            
            // Alinear la imagen y el texto
            tutorButton.setHorizontalTextPosition(SwingConstants.RIGHT); // Texto a la derecha
            tutorButton.setVerticalTextPosition(SwingConstants.CENTER); // Imagen y texto centrados verticalmente
            
            // Añadir evento de clic al botón
            tutorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mostrarInformacionTutor(tutor);
                }
            });
    
            // Añadir el botón al panel
            panel.add(tutorButton);
        }
    
        // Refrescar el panel
        panel.revalidate();
        panel.repaint();
    }


    private static void mostrarInformacionTutor(Tutor tutor) {
        // Crear una ventana para el perfil del tutor
        JFrame perfilFrame = new JFrame("Perfil del Tutor");
        perfilFrame.setSize(400, 300);
        perfilFrame.getContentPane().setBackground(new Color(50, 50, 50)); // Fondo gris oscuro
    
        // Crear un panel para organizar los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(50, 50, 50)); // Fondo gris oscuro
    
        // Establecer el color de las etiquetas a amarillo
        JLabel nombreLabel = new JLabel("Nombre: " + tutor.getNombre());
        JLabel edadLabel = new JLabel("Edad: " + tutor.getEdad());
        JLabel carreraLabel = new JLabel("Carrera: " + tutor.getCarrera());
        JLabel materiasLabel = new JLabel("Materias: " + String.join(", ", tutor.getMaterias()));
        JLabel calificacionLabel = new JLabel("Calificacion: " + tutor.getPromedioCalificaciones());
        JLabel contactoLabel = new JLabel("Contacto: " + tutor.getContacto());
        
        // Cambiar color de las etiquetas a amarillo
        nombreLabel.setForeground(Color.YELLOW);
        edadLabel.setForeground(Color.YELLOW);
        carreraLabel.setForeground(Color.YELLOW);
        materiasLabel.setForeground(Color.YELLOW);
        calificacionLabel.setForeground(Color.YELLOW);
        contactoLabel.setForeground(Color.YELLOW);
    
        // Centrar las etiquetas
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        edadLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        carreraLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        materiasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        calificacionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contactoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Agregar las etiquetas al panel
        panel.add(nombreLabel);
        panel.add(edadLabel);
        panel.add(carreraLabel);
        panel.add(materiasLabel);
        panel.add(calificacionLabel);
        panel.add(contactoLabel);
    
        // Crear botones
        JButton matchButton = new JButton("Match");
        JButton volverButton = new JButton("Volver");
    
        // Establecer color para los botones
        matchButton.setBackground(Color.GREEN); // Botón Match verde
        matchButton.setForeground(Color.WHITE); // Texto blanco
        volverButton.setBackground(Color.RED); // Botón Volver rojo
        volverButton.setForeground(Color.WHITE); // Texto blanco
    
        // Aumentar el tamaño de los botones
        matchButton.setPreferredSize(new Dimension(150, 40));
        volverButton.setPreferredSize(new Dimension(150, 40));
    
        // Centrar los botones
        matchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        volverButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    
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
    
        // Crear un panel para los botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(new Color(50, 50, 50)); // Fondo gris oscuro
        botonesPanel.add(matchButton);
        botonesPanel.add(volverButton);
    
        // Agregar todo al frame
        perfilFrame.add(panel, BorderLayout.CENTER);
        perfilFrame.add(botonesPanel, BorderLayout.SOUTH);
    
        perfilFrame.setVisible(true);
    }
}






