import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MensajeriaTest {

    private Tutor tutor;
    private Estudiante estudiante;
    private Mensajeria mensajeria;

    @BeforeEach
    public void setUp() {
        // Inicializamos los objetos antes de cada test
        tutor = new Tutor("Juan Pérez", "123456789", "21", "Filosofía", true, "foto_hombre.PNG", null, null, "3156428934");
        estudiante = new Estudiante("Carlos Rodríguez", "123456789", "21", "Filosofía", "foto_hombre.PNG");
        mensajeria = new Mensajeria(tutor, estudiante);
    }

    @Test
    public void testNotificarMatch() {
        // Verificar que inicialmente no hay match
        assertFalse(mensajeria.isMatch(), "Al principio no debería haber match.");
        
        // Llamar al método notificar_match
        mensajeria.notificar_match();
        
        // Verificar que después de notificar el match, el estado del match cambia
        assertTrue(mensajeria.isMatch(), "Debería haber match después de notificarlo.");
    }

    @Test
    public void testIsMatchInicialmenteFalso() {
        // Verificar que el match está inicialmente en falso
        assertFalse(mensajeria.isMatch(), "El estado inicial del match debería ser falso.");
    }

    @Test
    public void testNotificarMatchImprimeMensaje() {
        // Redirigir la salida para verificar el mensaje que se imprime
        final OutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        // Llamar al método notificar_match
        mensajeria.notificar_match();

        // Verificar que el mensaje correcto se imprime
        String expectedOutput = "MATCH entre Carlos Rodríguez y Juan Pérez";
        assertTrue(outContent.toString().contains(expectedOutput), "El mensaje de match no se imprimió correctamente.");
    }
}
