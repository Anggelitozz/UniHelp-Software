
/**
 * Write a description of class mensajería here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mensajeria {
    private Tutor tutor;
    private Estudiante estudiante;
    private boolean match = false;

    // Constructor para crear un objeto Mensajeria con tutor y estudiante
    public Mensajeria(Tutor tutor, Estudiante estudiante) {
        this.tutor = tutor;
        this.estudiante = estudiante;
    }

    // Método para notificar que se ha hecho un match
    public void notificar_match() {
        this.match = true;
        System.out.println("MATCH entre " + estudiante.getNombre() + " y " + tutor.getNombre());
    }

    // Método para verificar si hay un match
    public boolean isMatch() {
        return match;
    }
}
