
/**
 * Write a description of class estudiante here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Estudiante extends Usuario {
    // Atributos específicos de Estudiante
    public boolean tutor = false;
    private String foto;

    // Constructor
    public Estudiante(String nombre, String id, String edad, String carrera, String foto) {
        super(nombre, id, edad, carrera); // Llama al constructor de Usuario
        this.foto = foto;
    }


    public boolean isTutor() {
        return tutor;
    }

    
    public void setTutor(boolean tutor) {
        this.tutor = tutor;
    }

    
    public String getFoto() {
        return foto;
    }

    
    public void setFoto(String foto) {
        this.foto = foto;
    }

    // Método para mostrar información completa del estudiante (sobreescribe el de Usuario)
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion(); // Llama al método de la clase Usuario
        System.out.println("Es tutor: " + tutor);
        System.out.println("Foto: " + (foto != null ? foto : "Sin foto disponible"));
    }
}
