package omelcam934.dao;

import omelcam934.conexion.Conex;
import omelcam934.modelo.Monitor;
import omelcam934.modelo.Visita;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MonitorBBDD {

    private Conex connection = null;

    public MonitorBBDD(Conex connection) {
        this.connection = connection;
    }

    public MonitorBBDD() {
        this.connection = new Conex();
    }

    public void guardarMonitor(Monitor monitor) throws Exception {
        if(!Pattern.matches("^\\d{8}\\w$",monitor.getDni()))
            throw new Exception("DNI invalido");
        try{
            PreparedStatement statement = connection.getConnect().prepareStatement
                    ("insert into monitor(dni, nombre, apellidos) values (?,?,?)");
            statement.setString(1,monitor.getDni());
            statement.setString(2,monitor.getNombre());
            statement.setString(3,monitor.getApellidos());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Monitor> listarMonitores(){
        ArrayList<Monitor> monitors = new ArrayList<>();
        try{
            Statement statement = connection.getConnect().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from monitor");
            while (resultSet.next()){

                Monitor monitor = new Monitor(
                        resultSet.getInt("id_monitor"),
                        resultSet.getString("dni"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos")
                );
                PreparedStatement preparedStatement = connection.getConnect().prepareStatement
                        ("select * from visitas where id_monitor = ?");
                preparedStatement.setInt(1,monitor.getId());
                ResultSet visitas = preparedStatement.executeQuery();
                while (visitas.next()){
                    monitor.getVisitas().add(
                            new Visita(visitas.getInt("id_visita"),
                                    visitas.getString("fecha"),
                                    visitas.getFloat("duracion"),
                                    visitas.getInt("numero_sala"),
                                    monitor)
                    );
                }
                monitors.add(monitor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if(monitors.size()==0)
            return null;
        return monitors;
    }

    public Monitor listarMonitor(int id){
        Monitor monitor = null;
        try{
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement("select * from monitor where id_monitor = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                monitor = new Monitor(
                        resultSet.getInt("id_monitor"),
                        resultSet.getString("dni"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos")
                );
                PreparedStatement preparedStatementVisita = connection.getConnect().prepareStatement
                        ("select * from visitas where id_monitor = ?");
                preparedStatementVisita.setInt(1,monitor.getId());
                ResultSet visitas = preparedStatementVisita.executeQuery();
                while (visitas.next()){
                    monitor.getVisitas().add(
                            new Visita(visitas.getInt("id_visita"),
                                    visitas.getString("fecha"),
                                    visitas.getFloat("duracion"),
                                    visitas.getInt("numero_sala"),
                                    monitor)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monitor;
    }

    public void actualizarMonitor(int id, String dni, String nombre, String apellidos) throws Exception {
        if(!Pattern.matches("^\\d{8}\\w$",dni))
            throw new Exception("DNI invalido");
        try{
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement
                    ("update monitor set dni = ?, nombre = ?, apellidos = ? where id_monitor = ?");
            preparedStatement.setString(1,dni);
            preparedStatement.setString(2,nombre);
            preparedStatement.setString(3,apellidos);
            preparedStatement.setInt(4,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarMonitor(int id){
        try{
            PreparedStatement statement = connection.getConnect().prepareStatement("delete from monitor where id_monitor = ?");
            statement.setInt(1,id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
