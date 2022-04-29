package omelcam934.dao;

import omelcam934.conexion.Conex;
import omelcam934.modelo.Monitor;
import omelcam934.modelo.Usuario;
import omelcam934.modelo.Visita;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class UsuarioBBDD {

    private Conex connection = null;

    public UsuarioBBDD(Conex connection) {
        this.connection = connection;
    }

    public UsuarioBBDD() {
        this.connection = new Conex();
    }

    public Usuario listarUsuario(String nombre, String pass){
        Usuario usuario = null;
        try{
            PreparedStatement preparedStatement = connection.getConnect().prepareStatement("select * from Usuario where Nombre = ? AND Pass = ?");
            preparedStatement.setString(1,nombre);
            preparedStatement.setString(2,pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                usuario = new Usuario(
                        resultSet.getString("Nombre"),
                        resultSet.getString("Pass"),
                        resultSet.getInt("Admin")!=0
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

}
