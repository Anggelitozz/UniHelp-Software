
/**
 * Write a description of class FiltroporAsignatura here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.ArrayList;

public class FiltroPorAsignatura implements Filtro {
    private Asignatura asignatura;

    // Constructor que acepta una asignatura
    public FiltroPorAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    // Constructor sin asignatura (se aplica filtro para todos los tutores)
    public FiltroPorAsignatura() {
        this.asignatura = null;  // No filtrar por ninguna asignatura
    }

    @Override
    public List<Tutor> filtrar(List<Tutor> tutores, Asignatura asignatura) {
        List<Tutor> tutoresAsignatura = new ArrayList<>();
        
        // Si no se ha establecido una asignatura, retornamos todos los tutores
        if (this.asignatura == null) {
            return tutores;
        }

        for (Tutor tutor : tutores) {
            for (String materia : tutor.getMaterias()) {
                if (materia.equals(this.asignatura.getNombre())) {
                    tutoresAsignatura.add(tutor);
                    break;
                }
            }
        }
        return tutoresAsignatura;
    }
}


