import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Ventana {
    private JPanel panel;
    private JComboBox comboPrioridad;
    private JTextField txtNombre;
    private JTextField txtSintoma;
    private JButton registrarPacienteButton;
    private JButton btnAtender;
    private JList jList;

    DefaultListModel dlm = new DefaultListModel();
    Clinica clinica = new Clinica();

    public void llenarJList(){
        dlm.removeAllElements();
        Stream<Paciente> ordenada = clinica.enlistar().stream().sorted();
        for(Paciente p : ordenada.toList()){
            dlm.addElement(p);
        }
        jList.setModel(dlm);
    }


public Ventana() {
    registrarPacienteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int prioridad = Integer.parseInt(comboPrioridad.getSelectedItem().toString());
            String nombre = txtNombre.getText();
            String sintoma = txtSintoma.getText();
            Paciente nuevoPaciente = new Paciente(prioridad, nombre, sintoma);
            clinica.encolar(nuevoPaciente);
            llenarJList();
        }
    });
    btnAtender.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Paciente p = clinica.atender();
                JOptionPane.showMessageDialog(null, "Paciente atendido: "+p.getNombre());
                llenarJList();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    });

}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
