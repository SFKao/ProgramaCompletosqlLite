package omelcam934.pruebas;

import omelcam934.controlador.MonitorController;
import omelcam934.dao.MonitorBBDD;
import omelcam934.modelo.Monitor;
import omelcam934.views.MonitorVista;

public class MonitorTest {

    public static void main(String[] args) {
        MonitorBBDD bbdd = new MonitorBBDD();
        Monitor m = bbdd.listarMonitor(1);
        MonitorController controller = new MonitorController(m);
        MonitorVista vista = new MonitorVista();
        vista.iniciar(null,controller);

    }

}
