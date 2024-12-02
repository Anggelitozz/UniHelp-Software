
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.awt.Frame;

import javax.swing.*;
import java.util.List;
import java.util.Arrays;
/**
 * The test class UniHelpTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class UniHelpTest
{
    /**
     * Default constructor for test class UniHelpTest
     */
    public UniHelpTest()
    {
    }


    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    private Catalogo catalogo;

    @BeforeEach
    public void setUp() {
        // Inicializar el catálogo antes de cada prueba
        catalogo = Catalogo.obtenerInstancia();
    }

    @Test
    public void testInicializarCatalogo() {
        // Ejecutar la inicialización del catálogo
        UniHelp.main(new String[]{});

        // Verificar que los tutores y asignaturas se cargaron correctamente
        List<Tutor> tutores = catalogo.obtenerTutores(null, null);
        assertFalse(tutores.isEmpty(), "El catálogo debería tener tutores después de la inicialización.");

        List<String> asignaturas = catalogo.obtenerNombresAsignaturas();
        assertFalse(asignaturas.isEmpty(), "El catálogo debería tener asignaturas después de la inicialización.");
    }

    @Test
    public void testGuardarTutoresJSON() {
        // Crear tutores de prueba
        Tutor tutor1 = new Tutor("Ana Pérez", "T001", "22", "Matemáticas", true, "ana_foto.jpg", 
                                 new double[]{4.5, 4.8},  new String[]{"Matemáticas", "Física"}, "3145658794");
        Tutor tutor2 = new Tutor("Luis Gómez", "T002", "25", "Física", true, "luis_foto.jpg", 
                                 new double[]{4.0, 3.9},  new String[]{"Física", "Química"}, "3184462929");
        catalogo.agregarTutor(tutor1);
        catalogo.agregarTutor(tutor2);

        // Guardar tutores en JSON
        UniHelp.guardarTutoresJSON(catalogo.obtenerTutores(null, null));

        // Verificar que el archivo JSON fue creado (opcional)
        java.io.File archivo = new java.io.File("tutores.json");
        assertTrue(archivo.exists(), "El archivo JSON debería haberse creado.");
    }

    @Test
    public void testAbrirVentanaTutores() {
        // Simular la apertura de la ventana de tutores
        SwingUtilities.invokeLater(() -> UniHelp.main(new String[]{}));
    
        // Comprobar que la ventana se ha inicializado
        Frame[] frames = Frame.getFrames(); // Devuelve Frame[], no JFrame[]
        boolean ventanaTutoresVisible = false;
        for (Frame frame : frames) {
            if (frame instanceof JFrame) { // Aseguramos que sea un JFrame
                JFrame jFrame = (JFrame) frame;
                if ("Lista de Tutores".equals(jFrame.getTitle())) {
                    ventanaTutoresVisible = true;
                    break;
                }
            }
        }
    
        assertTrue(ventanaTutoresVisible, "La ventana de tutores debería abrirse.");
    }


    @Test
    public void testMostrarInformacionTutor() {
        // Crear un tutor de prueba
        Tutor tutor = new Tutor("Carlos López", "T003", "30", "Química", true, "carlos_foto.jpg", 
                                new double[]{4.7, 4.8},  new String[]{"Química", "Biología"}, "3123456789");

        // Simular la llamada al método para mostrar información del tutor
        SwingUtilities.invokeLater(() -> tutor.mostrarInformacion());

    }
}

