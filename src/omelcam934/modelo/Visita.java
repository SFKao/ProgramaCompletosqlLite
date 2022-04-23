package omelcam934.modelo;

public class Visita {

    private int id;
    private String fecha;
    private float duracion;
    private int numero_sala;

    private Monitor monitor;


    public Visita(String fecha, float duracion, int numero_sala, Monitor monitor) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.numero_sala = numero_sala;
        this.monitor = monitor;
    }

    public Visita(int id, String fecha, float duracion, int numero_sala, Monitor monitor) {
        this(fecha, duracion, numero_sala, monitor);
        this.id = id;
    }

    public Visita() {
        id = -1;
        fecha = "";
        duracion = 0.0f;
        numero_sala = -1;
        monitor = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public int getNumero_sala() {
        return numero_sala;
    }

    public void setNumero_sala(int numero_sala) {
        this.numero_sala = numero_sala;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public String toString() {
        return "Visitas{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", duracion=" + duracion +
                ", numero_sala=" + numero_sala +
                '}';
    }

    public String imprimirConMonitor(){
        return "Visitas{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", duracion=" + duracion +
                ", numero_sala=" + numero_sala +
                ", monitor ="+monitor+
                '}';
    }
}
