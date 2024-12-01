
/**
 * Write a description of interface Filtro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.ArrayList;

public interface Filtro {
    List<Tutor> filtrar(List<Tutor> tutores, Asignatura asignatura);
}
