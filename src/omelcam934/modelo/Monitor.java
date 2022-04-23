package omelcam934.modelo;

import java.util.ArrayList;

public class Monitor {

    private int id;
    private String dni;
    private String nombre;
    private String apellidos;

    private ArrayList<Visita> visitas;

    public Monitor(int id, String dni, String nombre, String apellidos) {
        this(dni, nombre, apellidos);
        this.id = id;
    }

    public Monitor(String dni, String nombre, String apellidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        visitas = new ArrayList<>();
    }

    public Monitor() {
        id = -1;
        dni = "";
        nombre = "";
        apellidos = "";
        visitas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public ArrayList<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(ArrayList<Visita> visitas) {
        this.visitas = visitas;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}
