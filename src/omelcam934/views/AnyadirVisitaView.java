package omelcam934.views;

import omelcam934.controlador.AnyadirMonitorController;
import omelcam934.controlador.AnyadirVisitaController;

import javax.swing.*;

public class AnyadirVisitaView {
    private JPanel visita;
    private JLabel fechaLabel;
    private JTextField fecha;
    private JLabel duracionLabel;
    private JTextField duracion;
    private JLabel numeroSalaLabel;
    private JTextField numeroSala;
    private JButton volver;
    private JButton anyadir;
    private JLabel monitorLabel;
    private JButton vincularButton;
    private JLabel vinculoLabel;
    private JPanel visitaanyadir;

    private JDialog frame;
    private AnyadirVisitaController controller;

    public void iniciar(JFrame parent, AnyadirVisitaController c){
        frame = new JDialog(parent,"Monitor", true);
        frame.setContentPane(visita);
        this.controller = c;
        controller.setVista(this);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //frame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

        colocarEscucha();

        frame.pack();
        frame.setVisible(true);
    }

    private void colocarEscucha(){
        volver.addActionListener(controller);
        anyadir.addActionListener(controller);
        vincularButton.addActionListener(controller);
    }

    public JTextField getFecha() {
        return fecha;
    }

    public JTextField getDuracion() {
        return duracion;
    }

    public JTextField getNumeroSala() {
        return numeroSala;
    }

    public JButton getVolver() {
        return volver;
    }

    public JButton getAnyadir() {
        return anyadir;
    }

    public JButton getVincularButton() {
        return vincularButton;
    }

    public JLabel getVinculoLabel() {
        return vinculoLabel;
    }

    public JDialog getFrame() {
        return frame;
    }
}
