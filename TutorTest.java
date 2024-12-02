

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class TutorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TutorTest
{
    /**
     * Default constructor for test class TutorTest
     */
    public TutorTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
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

    @Test
    public void getNombre()
    {
        Tutor tutor1 = new Tutor("Pablo", "123456789", "21", "Filosofía", true, "foto_hombre.PNG", null, null, "3156428934");
        assertEquals("Pablo", tutor1.getNombre());
    }
    @Test
    public void getId()
    {
        Tutor tutor1 = new Tutor("Pablo", "123456789", "21", "Filosofía", true, "foto_hombre.PNG", null, null, "3156428934");
        assertEquals("123456789", tutor1.getId());
    }
    @Test
    public void getCarrera()
    {
        Tutor tutor1 = new Tutor("Pablo", "123456789", "21", "Filosofía", true, "foto_hombre.PNG", null, null, "3156428934");
        assertEquals("Filosofi", tutor1.getCarrera());
    }@Test
    public void getContacto()
    {
        Tutor tutor1 = new Tutor("Pablo", "123456789", "21", "Filosofía", true, "foto_hombre.PNG", null, null, "3156428934");
        assertEquals("32157515748", tutor1.getContacto());
    }
}
