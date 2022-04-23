package omelcam934.pruebas;

import omelcam934.controlador.VisitaController;
import omelcam934.dao.VisitasBBDD;
import omelcam934.views.VisitaVista;

public class VisitasTest {

    public static void main(String[] args) {

        VisitasBBDD bbdd = new VisitasBBDD();
        VisitaController c = new VisitaController();
        c.setVisita(bbdd.listarVisita(1));
        VisitaVista vista = new VisitaVista();
        vista.iniciar(null,c);

    }

}
