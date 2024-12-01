
/**
 * Write a description of class FiltroPorCalificacion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.ArrayList;

public class FiltroPorCalificacion implements Filtro {
    private double minimo;

    public FiltroPorCalificacion(double minimo) {
        this.minimo = minimo;
    }

    @Override
    public List<Tutor> filtrar(List<Tutor> tutores, Asignatura asignatura) {
        List<Tutor> tutoresCalificados = new ArrayList<>();
        for (Tutor tutor : tutores) {
            for (double calificacion : tutor.getCalificaciones()) {
                if (calificacion >= minimo) {
                    tutoresCalificados.add(tutor);
                    break;
                }
            }
        }
        return tutoresCalificados;
    }
}
