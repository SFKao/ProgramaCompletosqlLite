package omelcam934.dao;

import omelcam934.conexion.Conex;
import omelcam934.modelo.Monitor;
import omelcam934.modelo.Visita;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VisitasBBDD {

    private Conex connection = null;

    public VisitasBBDD(Conex connextion) {
        this.connection = connextion;
    }

    public VisitasBBDD() {
        this.connection = new Conex();
    }

    public void guardarVisita(Visita visita){
        try{
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement
                    ("insert into visitas (fecha, duracion, numero_sala, id_monitor) values (?,?,?,?)");
            preparedStatement.setString(1,visita.getFecha());
            preparedStatement.setFloat(2,visita.getDuracion());
            preparedStatement.setInt(3,visita.getNumero_sala());
            preparedStatement.setInt(4,visita.getMonitor().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Visita> listarVisitas(){
        ArrayList<Visita> lista = new ArrayList<>();
        try{
            ResultSet visitas = connection.getConnect().createStatement().executeQuery
                    ("select * from visitas");
            while (visitas.next()){
                PreparedStatement preparedStatement = connection.getConnect().prepareStatement
                        ("select * from monitor where id_monitor = ?");
                preparedStatement.setInt(1,visitas.getInt("id_monitor"));
                ResultSet resultSet = preparedStatement.executeQuery();
                Monitor monitor = null;
                if(resultSet.next())
                    monitor = new Monitor(
                            resultSet.getInt("id_monitor"),
                            resultSet.getString("dni"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellidos")
                    );

                Visita visita = new Visita(
                        visitas.getInt("id_visita"),
                        visitas.getString("fecha"),
                        visitas.getFloat("duracion"),
                        visitas.getInt("numero_sala"),
                        monitor
                );
                monitor.getVisitas().add(visita);
                lista.add(visita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(lista.size()>0)
            return lista;
        return null;
    }

    public Visita listarVisita(int id){
        Visita visita = null;
        try{
            PreparedStatement p = connection.getConnect().prepareStatement("select * from visitas where id_visita = ?");
            p.setInt(1,id);
            ResultSet visitas = p.executeQuery();
            if (visitas.next()){
                PreparedStatement preparedStatement = connection.getConnect().prepareStatement
                        ("select * from monitor where id_monitor = ?");
                preparedStatement.setInt(1,visitas.getInt("id_monitor"));
                ResultSet resultSet = preparedStatement.executeQuery();
                Monitor monitor = null;
                if(resultSet.next())
                    monitor = new Monitor(
                            resultSet.getInt("id_monitor"),
                            resultSet.getString("dni"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellidos")
                    );

                visita = new Visita(
                        visitas.getInt("id_visita"),
                        visitas.getString("fecha"),
                        visitas.getFloat("duracion"),
                        visitas.getInt("numero_sala"),
                        monitor
                );
                monitor.getVisitas().add(visita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visita;
    }

    public void actualizarVisita(int id, String fecha, float duracion, int numeroSala, Monitor monitor){
        try{
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement
                    ("update visitas set fecha = ?, duracion = ?, numero_sala = ?, id_monitor = ? where id_visita = ?");
            preparedStatement.setString(1,fecha);
            preparedStatement.setFloat(2,duracion);
            preparedStatement.setInt(3,numeroSala);
            preparedStatement.setInt(4,monitor.getId());
            preparedStatement.setInt(5,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarVisita(int id){
        try{
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement("delete from visitas where id_visita = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
