
/**
 * Write a description of class ComboBoxAsignaturas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class ComboBoxAsignaturas implements Observer {
    private JComboBox<String> comboBox;

    public ComboBoxAsignaturas(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    @Override
    public void actualizar(List<String> nombresAsignaturas) {
        comboBox.removeAllItems(); // Limpiar items previos
        comboBox.addItem("Todas"); // Agregar opción por defecto
        for (String nombre : nombresAsignaturas) {
            comboBox.addItem(nombre); // Agregar nuevas asignaturas
        }
    }
}
